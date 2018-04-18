package com.mortals.iot.handler.protocol808.collect.protocol.handler.device.up;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mortals.framework.exception.AppException;
import com.mortals.iot.handler.protocol808.collect.protocol.handler.service.AbstractDeviceCommandHandler;
import com.mortals.iot.protocol.jt808.constant.NumberConstants;
import com.mortals.iot.protocol.jt808.entity.JT808ProtocalPack;
import com.mortals.iot.protocol.jt808.entity.MessageID;
import com.mortals.iot.protocol.jt808.entity.MultiMediaEventEntity;
import com.mortals.iot.protocol.jt808.util.HexUtils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;

/**
 * @ClassName: MultimediaEventInfoHandler
 * @Description: 多媒体事件信息上传（意义：拍照完成通知）0800：终端在拍照完成后发的信息。
 * @Company iot
 * @author
 * @version 1.0, 2017-3-28 上午11:23:54
 */
//@Component(value = "p_multimedia_event_info")
public class MultimediaEventInfoHandler extends AbstractDeviceCommandHandler implements MessageID {

	public Map<String, Object> receive(JT808ProtocalPack message, Channel tsession) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Integer result = NumberConstants._0;

		try {
			MultiMediaEventEntity multiMediaEventInfo = new MultiMediaEventEntity(message.getDeviceMobile(), message.getBody());
			logger.info(multiMediaEventInfo.toString());
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

	public void send(JT808ProtocalPack message, Channel tsession, Map<String, Object> reslutMap) throws Exception {
		Integer result = (Integer) reslutMap.get("result");
		// 响应的流水号
		byte[] serialnumber = JT808ProtocalPack.generateSerialNumber(tsession);

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

		sendMessage(tsession, fpp);
	}

}
