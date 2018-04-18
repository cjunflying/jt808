package com.mortals.iot.handler.protocol808.collect.protocol.handler.mall;

import org.springframework.stereotype.Component;

import com.mortals.iot.handler.protocol808.collect.protocol.handler.service.AbstractMallCommandHandler;
import com.mortals.iot.protocol.jt808.entity.JT808ProtocalPack;

/**
 * @MessageID 0x8601
 * @author
 * @package com.mortals.iot.handler.protocol808.collect.comm.protocol.handler.client.request
 * @copyright iot
 * @date:2017-4-16 上午11:25:38
 */
@Component(value = "p_del_Circular_Area_client")
public class DelCircularAreaHandler extends AbstractMallCommandHandler {

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
