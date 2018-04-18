package com.mortals.iot.handler.protocol808.collect.protocol.handler.mall;

import org.springframework.stereotype.Component;

import com.mortals.iot.handler.protocol808.collect.protocol.handler.service.AbstractMallCommandHandler;
import com.mortals.iot.protocol.jt808.entity.JT808ProtocalPack;

/**
 * @ClassName: CallReqClientHandler
 * @Description: 电话回拨0x8400：服务端处理来自客户端发送的请求
 * @Company iot
 * @author
 * @version 1.0, 2017-3-27 上午11:27:43
 */
@Component(value = "p_call_req_client")
public class CallReqHandler extends AbstractMallCommandHandler {

	@Override
	public JT808ProtocalPack receive(String message) throws Exception {
		return null;
	}
	
}
