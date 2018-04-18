package com.mortals.iot.handler.protocol808.collect.protocol.handler.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import com.alibaba.fastjson.JSONObject;
import com.mortals.iot.protocol.jt808.constant.SystemConstants;
import com.mortals.iot.protocol.jt808.entity.JT808ProtocalPack;
import com.mortals.iot.protocol.jt808.entity.MessageID;
import com.mortals.iot.protocol.jt808.entity.SessionManager;
import com.mortals.iot.protocol.jt808.util.HexUtils;

/**
 * @ClassName: AbstractMallCommandHandler
 * @Description: 客户端请求处理器
 * @Company iot
 * @author
 * @date 2018-2-1 下午4:31:03
 *
 */
public abstract class AbstractMallCommandHandler {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private KafkaTemplate<?, String> kafkaTemplate;
	protected String kafakaTopicName = SystemConstants.KAFAKA_DEFAULT_TOPIC_NAME;
	private SessionManager sessionManager = SessionManager.getInstance();

	/**
	 * 解析json对象为协议对象
	 * 
	 * @param message
	 * @throws Exception
	 */
	public abstract JT808ProtocalPack receive(String message) throws Exception;

	/**
	 * 发送数据到mall
	 * 
	 * @param message
	 * @throws Exception
	 */
	public void send(JT808ProtocalPack message) throws Exception {
		int messageId = HexUtils.bytesToInt2(message.getId());
		// 设备通用应答
		if (MessageID.P_DEVICE_COMMON_RESP_SERVER == messageId) {
			// 应答流水号
			int replyFlowId = HexUtils.bytesToInt2(HexUtils.copyOfRange(message.getBody(), 0, 2));
			// 应答消息id
			int replyMessageId = HexUtils.bytesToInt2(HexUtils.copyOfRange(message.getBody(), 2, 4));
			// 结果,0:成功/确认;1:失败;2:消息有误;3:不支持
			int result = HexUtils.copyOfRange(message.getBody(), 4, 5)[0];

			JSONObject resultData = new JSONObject();
			resultData.put(SystemConstants.CODE_KEY, 0);
			String serialNumber = sessionManager.removeFlowIdSerialNumber(message.getChannelId() + "-" + replyFlowId);
			resultData.put(SystemConstants.SERIAL_NUMBER_KEY, serialNumber == null ? "" : serialNumber);

			JSONObject messageBody = new JSONObject();
			// 应答流水号
			messageBody.put("5bqU562U5rWB5rC05Y+3", replyFlowId);
			// 应答id
			messageBody.put("5bqU562USUQ=", replyMessageId);
			// 结果
			messageBody.put("57uT5p6c", result);
			resultData.put(SystemConstants.DATA_KEY, messageBody.toJSONString());

			kafkaTemplate.send(kafakaTopicName, resultData.toJSONString());
		}
	}
}
