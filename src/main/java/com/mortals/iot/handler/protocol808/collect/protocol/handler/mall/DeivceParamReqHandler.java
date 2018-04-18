package com.mortals.iot.handler.protocol808.collect.protocol.handler.mall;

import javax.annotation.Resource;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mortals.iot.handler.protocol808.collect.protocol.handler.service.AbstractMallCommandHandler;
import com.mortals.iot.protocol.jt808.constant.SystemConstants;
import com.mortals.iot.protocol.jt808.entity.JT808ProtocalPack;
import com.mortals.iot.protocol.jt808.entity.MessageID;
import com.mortals.iot.protocol.jt808.entity.SessionManager;
import com.mortals.iot.protocol.jt808.entity.TerminalParameterQueryResponseEntity;

/**
 * @ClassName: DeivceParamReqHandler
 * @Description: 查询终端参数0x8104：服务端处理来自客户端发送的请求
 * @Company iot
 * @author
 * @date 2017-9-28 下午3:55:57
 *
 */
@Component(value = "p_device_param_req_client")
public class DeivceParamReqHandler extends AbstractMallCommandHandler {
	private SessionManager sessionManager = SessionManager.getInstance();
	@Resource
	private KafkaTemplate<?, String> kafkaTemplate;

	@Override
	public JT808ProtocalPack receive(String message) throws Exception {
		JT808ProtocalPack jt808ProtocalPack = new JT808ProtocalPack();
		// 设置消息id
		jt808ProtocalPack.setId(MessageID.INQUIRY_DEVICE_PARAMETER);
		return jt808ProtocalPack;
	}

	@Override
	public void send(JT808ProtocalPack message) throws Exception {
		TerminalParameterQueryResponseEntity paramQueryResp = new TerminalParameterQueryResponseEntity();
		paramQueryResp.setValues(message.getBody());

		JSONObject resultData = new JSONObject();
		resultData.put(SystemConstants.CODE_KEY, 0);
		String serialNumber = sessionManager.removeFlowIdSerialNumber(message.getChannelId() + "-" + paramQueryResp.getReplyFlowId());
		resultData.put(SystemConstants.SERIAL_NUMBER_KEY, serialNumber == null ? "" : serialNumber);

		resultData.put(SystemConstants.DATA_KEY, JSON.toJSONString(paramQueryResp));
		kafkaTemplate.send(kafakaTopicName, resultData.toJSONString());
	}
}