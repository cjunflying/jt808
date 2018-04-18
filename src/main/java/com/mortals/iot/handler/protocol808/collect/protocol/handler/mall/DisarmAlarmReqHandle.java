package com.mortals.iot.handler.protocol808.collect.protocol.handler.mall;

import org.springframework.stereotype.Component;

import com.mortals.iot.handler.protocol808.collect.protocol.handler.service.AbstractMallCommandHandler;
import com.mortals.iot.protocol.jt808.entity.JT808ProtocalPack;

/**
 * 人工确认报警消息
 * 
 * @MessageID 8203
 * @author
 * @package com.mortals.iot.handler.protocol808.collect.comm.protocol.handler.client.request
 * @copyright iot
 * @date:2017-4-21 上午10:45:11
 */
@Component(value = "p_Disarm_Alarm_client")
public class DisarmAlarmReqHandle extends AbstractMallCommandHandler {

	@Override
	public JT808ProtocalPack receive(String message) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void send(JT808ProtocalPack message) throws Exception {
		// TODO Auto-generated method stub

	}

}
