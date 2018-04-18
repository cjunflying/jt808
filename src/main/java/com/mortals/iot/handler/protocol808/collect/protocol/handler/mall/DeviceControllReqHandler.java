package com.mortals.iot.handler.protocol808.collect.protocol.handler.mall;

import org.springframework.stereotype.Component;

import com.mortals.iot.handler.protocol808.collect.protocol.handler.service.AbstractMallCommandHandler;
import com.mortals.iot.protocol.jt808.entity.DeviceControllEntity;
import com.mortals.iot.protocol.jt808.entity.JT808ProtocalPack;

/**
 * @ClassName: DeviceControllReqHandler
 * @Description: 终端控制0x8105
 * @Company iot
 * @author
 * @version 1.0, 2017-3-29 上午11:32:12
 */
@Component(value = "p_device_controll_req_client")
public class DeviceControllReqHandler extends AbstractMallCommandHandler {

	@Override
	public JT808ProtocalPack receive(String message) throws Exception {
		DeviceControllEntity deviceControllEntity = new DeviceControllEntity();
		JT808ProtocalPack jt808ProtocalPack = deviceControllEntity.toJT808ProtocalPack(message);
		return jt808ProtocalPack;
	}

}
