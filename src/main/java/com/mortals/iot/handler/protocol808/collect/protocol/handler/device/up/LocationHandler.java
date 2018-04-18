package com.mortals.iot.handler.protocol808.collect.protocol.handler.device.up;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.mortals.iot.handler.protocol808.collect.protocol.handler.service.AbstractDeviceCommandHandler;
import com.mortals.iot.protocol.jt808.constant.NumberConstants;
import com.mortals.iot.protocol.jt808.entity.JT808ProtocalPack;
import com.mortals.iot.protocol.jt808.entity.MessageID;
import com.mortals.iot.protocol.jt808.entity.PositionEntity;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;

/**
 * @ClassName: LocationHandler
 * @Description: 位置信息汇报0x0200：终端发给服务端的消息
 * @Company iot
 * @author
 * @version 1.0, 2017-4-6 下午2:40:52
 */
@Component(value = "p_location")
public class LocationHandler extends AbstractDeviceCommandHandler implements MessageID {

	public Map<String, Object> receive(JT808ProtocalPack message, Channel tsession) throws Exception {
		// Map<String, Object> resultMap = new HashMap<String, Object>();
		// Integer result = NumberConstants._0;
		//
		// try {
		PositionEntity position = new PositionEntity(message.getDeviceMobile(), message.getBody());
		logger.info("收到位置上报消息，" + position.toString());
		// } catch (AppException e) {
		// logger.error(e.getMessage());
		// result = NumberConstants._1;
		// } catch (Exception e) {
		// logger.error(e.getMessage(), e);
		// result = NumberConstants._1;
		// }
		//
		// resultMap.put("result", result);
		// return resultMap;
		return null;
	}

	public void send(JT808ProtocalPack message, Channel tsession, Map<String, Object> reslutMap) throws Exception {
		Integer result = 0;
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

		sendMessage(tsession, fpp);
	}

}
