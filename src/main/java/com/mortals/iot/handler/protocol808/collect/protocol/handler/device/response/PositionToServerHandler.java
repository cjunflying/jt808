package com.mortals.iot.handler.protocol808.collect.protocol.handler.device.response;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.mortals.iot.handler.protocol808.collect.commons.Log;
import com.mortals.iot.handler.protocol808.collect.protocol.handler.service.AbstractDeviceCommandHandler;
import com.mortals.iot.protocol.jt808.entity.JT808ProtocalPack;
import com.mortals.iot.protocol.jt808.entity.MessageID;

import io.netty.channel.Channel;

/**
 * @ClassName: PositionToServerHandler
 * @Description: 位置信息查询应答0x0201：终端直接响应服务端的请求
 * @Company iot
 * @author
 * @version 1.0, 2017-3-26 下午4:21:38
 */
@Component(value = "p_location_reply")
public class PositionToServerHandler extends AbstractDeviceCommandHandler implements MessageID {

	@Override
	public Map<String, Object> receive(JT808ProtocalPack message, Channel channel) throws Exception {
		Log.getLogger(this.getClass()).info("收到终端响应的信息：" + message.toHexString());
		return null;
	}

	@Override
	public void send(JT808ProtocalPack message, Channel channel, Map<String, Object> resultMap) throws Exception {
	}

}
