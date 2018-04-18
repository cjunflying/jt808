package com.mortals.iot.handler.protocol808.collect.protocol.handler.mall;

import org.springframework.stereotype.Component;

import com.mortals.iot.handler.protocol808.collect.protocol.handler.service.AbstractMallCommandHandler;
import com.mortals.iot.protocol.jt808.entity.JT808ProtocalPack;

/**
 * 设置矩形区域 消息ID：0x8602
 * 
 * @author
 * @package com.mortals.iot.handler.protocol808.collect.comm.protocol.handler.client.request
 * @copyright iot
 * @date:2017-4-17 上午11:48:00
 */
@Component(value = "p_set_Rectangular_Area_client")
public class RectangularAreaReqHandle extends AbstractMallCommandHandler {

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
