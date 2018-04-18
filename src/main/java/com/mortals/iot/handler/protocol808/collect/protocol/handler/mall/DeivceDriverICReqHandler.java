package com.mortals.iot.handler.protocol808.collect.protocol.handler.mall;

import org.springframework.stereotype.Component;

import com.mortals.iot.handler.protocol808.collect.protocol.handler.service.AbstractMallCommandHandler;
import com.mortals.iot.protocol.jt808.entity.JT808ProtocalPack;

/**
 * @MessageID 8702
 * @author
 * @package com.mortals.iot.handler.protocol808.collect.comm.protocol.handler.client.request
 * @copyright iot
 * @date:2017-4-15 下午2:48:24
 */
@Component(value = "p_device_driver_req_client")
public class DeivceDriverICReqHandler extends AbstractMallCommandHandler {

	@Override
	public JT808ProtocalPack receive(String message) throws Exception {
		return null;
		// TODO Auto-generated method stub

	}

	@Override
	public void send(JT808ProtocalPack message) throws Exception {
		// TODO Auto-generated method stub

	}

}
