package com.mortals.iot.handler.protocol808.collect.protocol.handler.service;

import java.util.Map;

import com.mortals.iot.protocol.jt808.entity.JT808ProtocalPack;

import io.netty.channel.Channel;

/**
 * 
 * @ClassName: IDeviceCommandContext
 * @Description: 上下文，先接收，再发送
 * @author
 * @date 2017-9-28 11:13:57
 *
 */
@Deprecated
public class IDeviceCommandContext {

	private AbstractDeviceCommandHandler handler;

	public IDeviceCommandContext(AbstractDeviceCommandHandler handler) {
		this.handler = handler;
	}

	/**
	 * 先处理设备发送的消息，并返回设备信息
	 * 
	 * @param content
	 * @param session
	 * @throws Exception
	 */
	public void handle(JT808ProtocalPack content, Channel session) throws Exception {
		Map<String, Object> resultMap = handler.receive(content, session);
		handler.send(content, session, resultMap);
	}
}
