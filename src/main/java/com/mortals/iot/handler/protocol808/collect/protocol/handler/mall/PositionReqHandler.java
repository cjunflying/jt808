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
import com.mortals.iot.protocol.jt808.entity.PositionEntity;
import com.mortals.iot.protocol.jt808.entity.SessionManager;
import com.mortals.iot.protocol.jt808.util.HexUtils;

/**
 * @ClassName: PositionReqHandler
 * @Description: 位置信息查询(点名定位)0x8201：服务端处理来自客户端发送的请求
 * @Company iot
 * @author
 * @version 1.0, 2017-3-26 下午4:18:30
 */
@Component(value = "p_position_req_client")
public class PositionReqHandler extends AbstractMallCommandHandler {
	private SessionManager sessionManager = SessionManager.getInstance();
	@Resource
	private KafkaTemplate<?, String> kafkaTemplate;
	protected String kafakaTopicName = SystemConstants.KAFAKA_DEFAULT_TOPIC_NAME;

	@Override
	public JT808ProtocalPack receive(String message) throws Exception {
		JT808ProtocalPack jt808ProtocalPack = new JT808ProtocalPack();
		// 设置消息id
		jt808ProtocalPack.setId(MessageID.INQUIRY_LOCATION_INFORMATION);
		return jt808ProtocalPack;
	}

	@Override
	public void send(JT808ProtocalPack message) throws Exception {
		byte[] messageBody = message.getBody();
		int replyFlowId = HexUtils.bytesToInt2(HexUtils.copyOfRange(messageBody, 0, 2));
		byte[] positionInfo = HexUtils.copyOfRange(messageBody, 2, messageBody.length);
		PositionEntity position = new PositionEntity();
		position.setValues(positionInfo);

		JSONObject resultData = new JSONObject();
		resultData.put(SystemConstants.CODE_KEY, 0);
		String serialNumber = sessionManager.removeFlowIdSerialNumber(message.getChannelId() + "-" + replyFlowId);
		resultData.put(SystemConstants.SERIAL_NUMBER_KEY, serialNumber == null ? "" : serialNumber);

		resultData.put(SystemConstants.DATA_KEY, JSON.toJSONString(position));
		kafkaTemplate.send(kafakaTopicName, resultData.toJSONString());
	}

}
