package com.mortals.iot.handler.protocol808.collect.protocol.handler.device.response;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.mortals.iot.handler.protocol808.collect.commons.Log;
import com.mortals.iot.handler.protocol808.collect.protocol.handler.service.AbstractDeviceCommandHandler;
import com.mortals.iot.protocol.jt808.entity.JT808ProtocalPack;

import io.netty.channel.Channel;

/**
 * @ClassName: DrivingRecordToServerHandler
 * @Description: 行驶记录上传0x0700
 * @Company iot
 * @author
 * @version 1.0, 2017-3-21 下午3:25:50
 */
@Component(value = "p_driving_record_to_server")
public class DrivingRecordToServerHandler extends AbstractDeviceCommandHandler {

	@Override
	public Map<String, Object> receive(JT808ProtocalPack message, Channel channel) throws Exception {
		Log.getLogger(this.getClass()).info("收到终端响应的信息：" + message.toHexString());
		return null;
	}

	@Override
	public void send(JT808ProtocalPack message, Channel channel, Map<String, Object> resultMap) throws Exception {
	}

}
