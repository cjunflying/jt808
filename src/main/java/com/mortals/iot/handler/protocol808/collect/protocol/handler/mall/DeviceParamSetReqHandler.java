package com.mortals.iot.handler.protocol808.collect.protocol.handler.mall;

import org.springframework.stereotype.Component;

import com.mortals.iot.handler.protocol808.collect.protocol.handler.service.AbstractMallCommandHandler;
import com.mortals.iot.protocol.jt808.entity.JT808ProtocalPack;
import com.mortals.iot.protocol.jt808.entity.TerminalParameterSettingEntity;

/**
 * @ClassName: DeviceparamSetReqHandler
 * @Description: 设置终端参数0x8103：服务端处理来自客户端发送的请求
 * @Company iot
 * @author
 * @version 1.0, 2017-3-29 上午11:32:12
 */
@Component(value = "p_deviceparam_set_req_client")
public class DeviceParamSetReqHandler extends AbstractMallCommandHandler {

	@Override
	public JT808ProtocalPack receive(String message) throws Exception {
		TerminalParameterSettingEntity terminalParameterEntity = new TerminalParameterSettingEntity();
		JT808ProtocalPack jt808ProtocalPack = terminalParameterEntity.toJT808ProtocalPack(message);
		return jt808ProtocalPack;
	}

}
