package com.mortals.iot.handler.protocol808.collect.protocol.handler;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mortals.framework.exception.AppException;
import com.mortals.framework.util.StringUtils;
import com.mortals.iot.handler.protocol808.collect.commons.SpringContextUtils;
import com.mortals.iot.handler.protocol808.collect.protocol.handler.service.AbstractMallCommandHandler;
import com.mortals.iot.module.device.service.impl.DeviceServiceImpl;
import com.mortals.iot.protocol.jt808.constant.SystemConstants;
import com.mortals.iot.protocol.jt808.entity.JT808ProtocalPack;
import com.mortals.iot.protocol.jt808.entity.Session;
import com.mortals.iot.protocol.jt808.entity.SessionManager;
import com.mortals.iot.protocol.jt808.util.HexUtils;
import com.mortals.iot.protocol.jt808.util.MallPacketUtils;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

@Component
public class MallMessageHandler {
	private final Logger logger = LoggerFactory.getLogger(MallMessageHandler.class);
	private SessionManager sessionManager = SessionManager.getInstance();
	// @Autowired
	DeviceServiceImpl deviceServiceImpl;

	/**
	 * 接收处理App端发到kafka的消息
	 * 
	 * @param record
	 */
	@KafkaListener(topics = { "commandreq" })
	public void appMessageRecievied(ConsumerRecord<?, ?> record) {
		try {
			String message = (String) record.value();
			logger.info("收到Mall消息:" + message);
			JSONObject jsonObjectMessage = JSON.parseObject(message);
			Integer messageId = jsonObjectMessage.getInteger(SystemConstants.JSON_KEY_ACTION);

			// mall处理数据为协议数据
			JT808ProtocalPack pack = mallHander(message, messageId);
			// 对设备通道进行数据发送
			deviceHander(jsonObjectMessage, pack);
		} catch (AppException e) {
			logger.error("处理mall消息异常" + e.getMessage());
		} catch (Exception e) {
			logger.error("处理mall消息异常" + e.getMessage(), e);
		}
	}

	private JT808ProtocalPack mallHander(String message, Integer messageId) throws Exception {
		String messageIdStringValue = messageId != null ? HexUtils.bytesToHexString(HexUtils.intToByte2Array(messageId)) : null;
		String handlerBeanName = StringUtils.isNotEmpty(messageIdStringValue) ? (String) MallPacketUtils.getValue(messageIdStringValue) : null;
		AbstractMallCommandHandler handler = StringUtils.isNotEmpty(handlerBeanName) ? SpringContextUtils.getBean(handlerBeanName) : null;
		if (handler == null) {
			throw new AppException("根据消息没有找到对应的消息处理器");
		}

		// 返回协议对象
		JT808ProtocalPack jt808ProtocalPack = handler.receive(message);
		return jt808ProtocalPack;
	}

	private void deviceHander(JSONObject jsonObjectMessage, JT808ProtocalPack jt808ProtocalPack) {
		if (jt808ProtocalPack == null) {
			throw new AppException("转换后的jt808协议对象为空");
		}
		String deviceId = jsonObjectMessage.getString(SystemConstants.JSON_KEY_DEVICE_ID);
		String serialNumber = jsonObjectMessage.getString(SystemConstants.JSON_KEY_SERIAL_NUMBER);

		// 根据设备id获取netty tcp连接
		if (StringUtils.isEmpty(deviceId)) {
			throw new AppException("终端Id信息为空");
		}
		Session session = sessionManager.getDeviceIdSession(deviceId);
		if (session == null || session.getChannel() == null || StringUtils.isEmpty(session.getId())) {
			throw new AppException("终端会话信息不存在");
		}
		if (!session.getChannel().isActive()) {
			throw new AppException("终端会话信息已断开");
		}

		int currentFlowId = session.currentFlowId();
		jt808ProtocalPack.setSerialNumber(HexUtils.intToByte2Array(currentFlowId));
		jt808ProtocalPack.setDeviceMobile(sessionManager.getDeviceIdPhone(deviceId));

		// 使用对应的device channel进行发送
		sendMessage(session.getChannel(), jt808ProtocalPack);

		// 存储jt808协议流水号与app端serialNumber映射关系
		if (StringUtils.isNotEmpty(serialNumber)) {
			sessionManager.putFlowIdSerialNumber(session.getId() + "-" + currentFlowId, serialNumber);
		}
	}

	/**
	 * 发送数据到netty通道
	 * 
	 * @param channel
	 * @param message
	 * @throws Exception
	 */
	public void sendMessage(Channel channel, JT808ProtocalPack message) {
		try {
			byte[] resultData = message.pack();

			if (resultData != null && resultData.length > 0) {
				ChannelFuture channelFuture = channel.writeAndFlush(Unpooled.copiedBuffer(resultData));
				if (!channelFuture.isSuccess()) {
					logger.error("发送jt808消息到设备出错:", channelFuture.cause());
				} else {
					logger.info("发送jt808消息：" + HexUtils.bytesToHexString(resultData));
				}
			} else {
				logger.info("jt808消息为空");
			}
		} catch (Exception e) {
			logger.info("消息发送出错", e);
		}
	}
}
