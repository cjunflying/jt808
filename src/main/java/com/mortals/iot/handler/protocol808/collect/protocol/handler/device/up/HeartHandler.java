package com.mortals.iot.handler.protocol808.collect.protocol.handler.device.up;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mortals.iot.handler.protocol808.collect.protocol.handler.service.AbstractDeviceCommandHandler;
import com.mortals.iot.module.device.service.DeviceService;
import com.mortals.iot.protocol.jt808.constant.NumberConstants;
import com.mortals.iot.protocol.jt808.entity.JT808ProtocalPack;
import com.mortals.iot.protocol.jt808.entity.MessageID;
import com.mortals.iot.protocol.jt808.util.HexUtils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;

/**
 * @ClassName: HeartHandler
 * @Description: 心跳0x0002 <-> 0x8001
 * @Company iot
 * @author
 * @date 2017-12-15 下午2:30:03
 *
 */
@Component(value = "p_heart")
public class HeartHandler extends AbstractDeviceCommandHandler implements MessageID {
	private DeviceService deviceService;

	public Map<String, Object> receive(JT808ProtocalPack message, Channel tsession) throws Exception {
		// Map<String, Object> resultMap = new HashMap<String, Object>();
		// Integer result = NumberConstants._0;
		//
		// try {
		// 根据channelid获取会话信息
		// Session session =
		// sessionManager.getChannelIdSession(tsession.id().asLongText());
		// if (session == null || StringUtils.isEmpty(session.getDeviceId())) {
		// throw new AppException("netty会话信息异常" + (session != null ? ",sessionId is:" +
		// session.getId() : ""));
		// }
		//
		// // 更新设备的心跳信息
		// Map<String, Object> data = new HashMap<String, Object>();
		// data.put("heartTime", new Date());
		// Map<String, Object> condition = new HashMap<String, Object>();
		// condition.put("deviceId", session.getDeviceId());
		// deviceService.update(data, condition);
		// } catch (AppException e) {
		// logger.error(e.getMessage());
		// result = NumberConstants._1;
		// } catch (Exception e) {
		// logger.error(e.getMessage(), e);
		// result = NumberConstants._1;
		// }

		// resultMap.put("result", result);
		// return resultMap;
		logger.info("收到心跳消息");
		return null;
	}

	//
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

	public DeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(DeviceService deviceService) {
		this.deviceService = deviceService;
	}

}
