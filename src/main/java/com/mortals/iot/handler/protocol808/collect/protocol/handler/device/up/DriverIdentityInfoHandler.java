package com.mortals.iot.handler.protocol808.collect.protocol.handler.device.up;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mortals.framework.exception.AppException;
import com.mortals.iot.handler.protocol808.collect.commons.Log;
import com.mortals.iot.handler.protocol808.collect.protocol.handler.service.AbstractDeviceCommandHandler;
import com.mortals.iot.protocol.jt808.constant.NumberConstants;
import com.mortals.iot.protocol.jt808.entity.DriverIdentityEntity;
import com.mortals.iot.protocol.jt808.entity.JT808ProtocalPack;
import com.mortals.iot.protocol.jt808.entity.MessageID;
import com.mortals.iot.protocol.jt808.util.HexUtils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;

/**
 * <li>文件名称: DriverIdentityInfoHandler</li>
 * <li>文件描述: 驾驶员身份信息采集上传业务处理类$</li>
 * <li>内容摘要: 包括模块、函数及其功能的说明</li>
 * <li>完成日期：2017-4-24</li>
 * <li>修改记录1:iot</li>
 */
//@Component(value = "p_driverinfo_to_server")
public class DriverIdentityInfoHandler extends AbstractDeviceCommandHandler implements MessageID {

	@Override
	public Map<String, Object> receive(JT808ProtocalPack message, Channel channel) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Integer result = NumberConstants._0;

		try {
			DriverIdentityEntity driverInfo = new DriverIdentityEntity(message.getDeviceMobile(), message.getBody());
			logger.info("收到驾驶员身份信息上报消息，" + driverInfo.toString());
		} catch (AppException e) {
			logger.error(e.getMessage());
			result = NumberConstants._1;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = NumberConstants._1;
		}

		resultMap.put("result", result);
		return resultMap;
	}

	@Override
	public void send(JT808ProtocalPack message, Channel session, Map<String, Object> resultMap) throws Exception {
		Integer result = (Integer) resultMap.get("result");
		// 响应的流水号
		byte[] serialnumber = JT808ProtocalPack.generateSerialNumber(session);

		JT808ProtocalPack fpp = new JT808ProtocalPack();
		fpp.setId(P_PLATFORM_COMMON_REPLAY);
		fpp.setDeviceMobile(message.getDeviceMobile());
		fpp.setSerialNumber(serialnumber);

		ByteBuf buf = Unpooled.buffer(5);
		buf.writeBytes(message.getSerialNumber());
		buf.writeBytes(message.getId());
		buf.writeByte(result == null ? NumberConstants._1 : result);

		fpp.setBody(buf.array());
		fpp.setProperty(HexUtils.intToByteArray(buf.array().length));

		Log.getLogger(this.getClass()).info("驾驶员身份信息回复：" + fpp.toHexString());
		sendMessage(session, fpp);
	}

}
