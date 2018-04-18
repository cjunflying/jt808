package com.mortals.iot.handler.protocol808.collect.protocol.handler.mall;

import javax.annotation.Resource;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mortals.iot.handler.protocol808.collect.protocol.handler.service.AbstractMallCommandHandler;
import com.mortals.iot.protocol.jt808.constant.SystemConstants;
import com.mortals.iot.protocol.jt808.entity.DrivingRecordEntity;
import com.mortals.iot.protocol.jt808.entity.JT808ProtocalPack;
import com.mortals.iot.protocol.jt808.entity.SessionManager;

/**
 * <li>文件名称: DrivingRecordReqHandler</li>
 * <li>文件描述: 行驶记录参数下发处理类$</li>
 * <li>内容摘要: 包括模块、函数及其功能的说明</li>
 * <li>完成日期：2017-4-24</li>
 * <li>修改记录1:iot</li>
 *
 */
@Component(value = "p_driving_record_req_client")
public class DrivingRecordReqHandler extends AbstractMallCommandHandler {
	private SessionManager sessionManager = SessionManager.getInstance();
	@Resource
	private KafkaTemplate<?, String> kafkaTemplate;

	@Override
	public JT808ProtocalPack receive(String message) throws Exception {
		DrivingRecordEntity drivingRecordEntity = new DrivingRecordEntity();
		JT808ProtocalPack jt808ProtocalPack = drivingRecordEntity.toJT808ProtocalPack(message);
		return jt808ProtocalPack;
	}

	@Override
	public void send(JT808ProtocalPack message) throws Exception {
		DrivingRecordEntity drivingRecordEntity = new DrivingRecordEntity();
		drivingRecordEntity.setValues(message.getBody());

		JSONObject resultData = new JSONObject();
		resultData.put(SystemConstants.CODE_KEY, 0);
		String serialNumber = sessionManager.removeFlowIdSerialNumber(message.getChannelId() + "-" + drivingRecordEntity.getReplyFlowId());
		resultData.put(SystemConstants.SERIAL_NUMBER_KEY, serialNumber == null ? "" : serialNumber);

		resultData.put(SystemConstants.DATA_KEY, JSON.toJSONString(drivingRecordEntity));
		kafkaTemplate.send(kafakaTopicName, resultData.toJSONString());
	}
}
