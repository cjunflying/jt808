package com.mortals.iot.handler.protocol808.collect.protocol.handler;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mortals.framework.exception.AppException;
import com.mortals.framework.util.StringUtils;
import com.mortals.iot.handler.protocol808.collect.commons.SpringContextUtils;
import com.mortals.iot.handler.protocol808.collect.protocol.handler.service.AbstractDeviceCommandHandler;
import com.mortals.iot.handler.protocol808.collect.protocol.handler.service.AbstractMallCommandHandler;
import com.mortals.iot.module.device.service.DeviceService;
import com.mortals.iot.protocol.jt808.entity.JT808ProtocalPack;
import com.mortals.iot.protocol.jt808.entity.MessageID;
import com.mortals.iot.protocol.jt808.entity.SessionManager;
import com.mortals.iot.protocol.jt808.util.DevicePacketUtils;
import com.mortals.iot.protocol.jt808.util.HexUtils;
import com.mortals.iot.protocol.jt808.util.MallPacketUtils;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;

@Component
@Sharable
public class DeviceMessageHandler extends ChannelInboundHandlerAdapter {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private DeviceService deviceService;
	private SessionManager sessionManager = SessionManager.getInstance();
	public static volatile Integer CONNECTION_COUNT = 0;

	public DeviceMessageHandler() {
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object message) throws InterruptedException, UnsupportedEncodingException { // (2)
		try {
			if (message instanceof JT808ProtocalPack) {
				JT808ProtocalPack jt808ProtocalPack = (JT808ProtocalPack) message;
				if (jt808ProtocalPack == null || jt808ProtocalPack.getId() == null) {
					throw new AppException("设备消息为空，或设备消息消息id为空");
				}

				// 设备注册，鉴权前不能发送注册，鉴权外的其它消息
				// int messageId = HexUtils.bytesToInt2(jt808ProtocalPack.getId());
				// if (MessageID.P_DEVICE_REGIST != messageId && MessageID.P_DEVICE_AUTH !=
				// messageId) {
				// String channelId = ctx.channel().id().asLongText();
				// if (!sessionManager.getChannelIdSessionMap().containsKey(channelId)) {
				// logger.info("channelId:" + channelId + ",设备还未鉴权");
				// return;
				// }
				// }

				// 处理设备链路信息
				deviceHander(jt808ProtocalPack, ctx);

				// 处理mall链路
				// mallHander(jt808ProtocalPack, ctx);

			}
		} catch (AppException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void deviceHander(JT808ProtocalPack jt808ProtocalPack, ChannelHandlerContext ctx) throws Exception {
		// 获取消息处理器
		String messageId = HexUtils.bytesToHexString(jt808ProtocalPack.getId());
		String handlerBeanName = StringUtils.isNotEmpty(messageId) ? (String) DevicePacketUtils.getValue(messageId) : null;
		AbstractDeviceCommandHandler handler = StringUtils.isNotEmpty(handlerBeanName) ? SpringContextUtils.getBean(handlerBeanName) : null;
		if (handler == null) {
			throw new AppException("根据消息没有找到对应的消息处理器");
		}

		// 接收处理设备消息，并返回服务端消息给设备
		Map<String, Object> resultMap = handler.receive(jt808ProtocalPack, ctx.channel());
		handler.send(jt808ProtocalPack, ctx.channel(), resultMap);
	}

	private void mallHander(JT808ProtocalPack jt808ProtocalPack, ChannelHandlerContext ctx) throws Exception {
		// 判断是否需要转发到mall ，调用client的rsponse方法发给mall
		int messageId = HexUtils.bytesToInt2(jt808ProtocalPack.getId());
		// 当不是设备通用应答，位置信息查询应答，查询终端参数应答消息时，不转发
		if (MessageID.P_DEVICE_COMMON_RESP_SERVER != messageId && MessageID.INQUIRY_DEVICE_PARAMETER_REQUEST != messageId
				&& MessageID.INQUIRY_LOCATION_INFORMATION_REQUEST != messageId) {
			return;
		}

		String handlerBeanName = null;
		AbstractMallCommandHandler mallHandler = null;
		if (MessageID.P_DEVICE_COMMON_RESP_SERVER == messageId) {
			byte[] replyMessageId = HexUtils.copyOfRange(jt808ProtocalPack.getBody(), 2, 4);
			if (replyMessageId == null) {
				throw new AppException("转发消息id错误");
			}

			if (JT808ProtocalPack.canTransfer(replyMessageId)) {
				String replyMessageIdStringValue = HexUtils.bytesToHexString(replyMessageId);
				handlerBeanName = StringUtils.isNotEmpty(replyMessageIdStringValue) ? (String) MallPacketUtils.getValue(replyMessageIdStringValue)
						: null;
				mallHandler = StringUtils.isNotEmpty(handlerBeanName) ? SpringContextUtils.getBean(handlerBeanName) : null;
			}
		} else {
			String messageIdStringValue = HexUtils.bytesToHexString(jt808ProtocalPack.getId());
			handlerBeanName = StringUtils.isNotEmpty(messageIdStringValue) ? (String) MallPacketUtils.getValue(messageIdStringValue) : null;
			mallHandler = StringUtils.isNotEmpty(handlerBeanName) ? SpringContextUtils.getBean(handlerBeanName) : null;
		}

		if (null == mallHandler) {
			throw new AppException("无mall转发处理器");
		}

		jt808ProtocalPack.setChannelId(ctx.channel() != null && ctx.channel().id() != null ? ctx.channel().id().asLongText() : null);
		mallHandler.send(jt808ProtocalPack);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		logger.error("发生异常:{}", cause.getMessage());
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		if (ctx.channel() != null) {
			// if (ctx.channel().id() != null) {
			// deviceService.deviceOffline(ctx.channel().id().asLongText());
			// }
			ctx.channel().close();
			if (ctx.channel().id().asLongText() != null) {
				JT808ProtocalPack.flowIdMap.remove(ctx.channel().id().asLongText());
			}
		}

	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		super.handlerAdded(ctx);
		CONNECTION_COUNT++;
		logger.info("-----------连接数量:[" + CONNECTION_COUNT + "]-----------");
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		super.handlerRemoved(ctx);
		logger.info(ctx.channel().id() + "离开了");
		if (ctx.channel() != null) {
			// if (ctx.channel().id() != null) {
			// deviceService.deviceOffline(ctx.channel().id().asLongText());
			// }
			ctx.channel().close();
			if (ctx.channel().id().asLongText() != null) {
				JT808ProtocalPack.flowIdMap.remove(ctx.channel().id().asLongText());
			}
		}
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (IdleStateEvent.class.isAssignableFrom(evt.getClass())) {
			IdleStateEvent event = (IdleStateEvent) evt;
			if (event.state() == IdleState.READER_IDLE) {
				ctx.close();
				if (ctx.channel().id().asLongText() != null) {
					JT808ProtocalPack.flowIdMap.remove(ctx.channel().id().asLongText());
				}
			}
		}
	}

	@SuppressWarnings("unused")
	private void release(Object msg) {
		try {
			ReferenceCountUtil.release(msg);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public DeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(DeviceService deviceService) {
		this.deviceService = deviceService;
	}

}
