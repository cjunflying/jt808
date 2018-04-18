package com.mortals.iot.handler.protocol808.collect.protocol.handler.mall;

import org.springframework.stereotype.Component;

import com.mortals.iot.handler.protocol808.collect.protocol.handler.service.AbstractMallCommandHandler;
import com.mortals.iot.protocol.jt808.entity.JT808ProtocalPack;

/**
 * @ClassName: ShootImmediatelyReqHandler
 * @Description: 摄像头立即拍摄命令0x8801：服务端处理来自客户端发送的请求
 * @Company iot
 * @author
 * @version 1.0, 2017-3-27 下午4:05:43
 */
@Component(value = "p_shoot_immediately_client")
public class ShootImmediatelyReqHandler extends AbstractMallCommandHandler {

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
