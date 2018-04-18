package com.mortals.iot.handler.protocol808.collect.protocol.handler.device.response;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.mortals.iot.handler.protocol808.collect.commons.Log;
import com.mortals.iot.handler.protocol808.collect.protocol.handler.service.AbstractDeviceCommandHandler;
import com.mortals.iot.protocol.jt808.entity.JT808ProtocalPack;
import com.mortals.iot.protocol.jt808.util.HexUtils;

import io.netty.channel.Channel;

/**
 * @ClassName: CommonReplayHandler
 * @Description: 终端通用应答0x0001：终端响应服务端的消息， 服务端处理并转发给请求端
 * @Company iot
 * @author
 * @version 1.0, 2017-3-20 上午10:59:00
 */
@Component(value = "p_device_common_replay")
public class CommonReplayHandler extends AbstractDeviceCommandHandler {

	public Map<String, Object> receive(JT808ProtocalPack message, Channel tsession) throws Exception {
		String replyId = HexUtils.bytesToHexString(HexUtils.copyOfRange(message.getBody(), 2, 4));
		// Log.getLogger(this.getClass()).info("测试成功~收到终端通用应答0x0001 应答ID为"+replyId);
		Log.getLogger(this.getClass()).info("应答ID:" + replyId);
		return null;
	}

	public void send(JT808ProtocalPack message, Channel tsession, Map<String, Object> reslutMap) throws Exception {

	}

}
