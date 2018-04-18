package com.mortals.iot.handler.protocol808.collect.protocol.handler.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mortals.iot.protocol.jt808.entity.JT808ProtocalPack;
import com.mortals.iot.protocol.jt808.util.HexUtils;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

/**
 * @ClassName: AbstractDeviceCommandHandler
 * @Description: 指令处理抽象类
 * @Company iot
 * @author
 * @date 2017-1 下午3:37:41
 *
 */
public abstract class AbstractDeviceCommandHandler {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 接受设备消息
	 * 
	 * @param message
	 * @param session
	 * @return Map<String, Object>
	 * @throws Exception
	 */
	public abstract Map<String, Object> receive(JT808ProtocalPack message, Channel channel) throws Exception;

	/**
	 * 发送响应消息到设备
	 * 
	 * @param message
	 * @param modelMap
	 * @param session
	 * @throws Exception
	 */
	public abstract void send(JT808ProtocalPack message, Channel channel, Map<String, Object> reslutMap) throws Exception;

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
