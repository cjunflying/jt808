package com.mortals.iot.handler.protocol808.collect.protocol.handler.device.up;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mortals.iot.handler.protocol808.collect.protocol.handler.service.AbstractDeviceCommandHandler;
import com.mortals.iot.module.device.service.DeviceJt808Service;
import com.mortals.iot.module.device.service.DeviceService;
import com.mortals.iot.protocol.jt808.constant.NumberConstants;
import com.mortals.iot.protocol.jt808.entity.JT808ProtocalPack;
import com.mortals.iot.protocol.jt808.entity.MessageID;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;

/**
 * @ClassName: AuthenticHandler
 * @Description: 鉴权0x0102：终端主动上发给服务端的信息
 * @Company iot
 * @author
 * @version 1.0, 2017-3-20 上午10:39:31
 */
@Component(value = "p_device_auth")
public class AuthenticHandler extends AbstractDeviceCommandHandler implements MessageID {
	
	private DeviceService deviceService;
	
	private DeviceJt808Service deviceJt808Service;

	public Map<String, Object> receive(JT808ProtocalPack message, Channel tsession) throws Exception {
		// Map<String, Object> resultMap = new HashMap<String, Object>();
		// Integer authResult = NumberConstants._0;
		//
		// try {
		String authCode = new String(message.getBody(), "GBK");
		logger.info("收到鉴权码：" + authCode);

		// String channelId = tsession.id().asLongText();
		// Session session = sessionManager.getSession(channelId);
		// if (session == null) {
		// session = Session.buildSession(tsession, message.getDeviceMobile());
		// sessionManager.putSession(channelId, session);
		// }
		//
		// DeviceJt808Query deviceJt808Query = new DeviceJt808Query();
		// deviceJt808Query.setAuthorizationCode(authCode);
		// List<DeviceJt808Entity> deviceJt808EntityList =
		// deviceJt808Service.find(deviceJt808Query, null);
		// // 鉴权码不存在
		// if (deviceJt808EntityList == null || deviceJt808EntityList.size() <=
		// NumberConstants._0
		// || deviceJt808EntityList.get(NumberConstants._0) == null) {
		// throw new AppException("鉴权码：" + authCode + " ,不存在");
		// }
		//
		// // 还要判断该鉴权码是否已经鉴过权 TODO bono:需要在应用重启的时候更新devcie表中的status状态为离线
		// DeviceEntity deviceEntity =
		// deviceService.get(deviceJt808EntityList.get(NumberConstants._0).getId(),
		// null);
		// if (deviceEntity == null) {
		// throw new AppException("鉴权码" + authCode + " ,对应设备信息不存在");
		// }
		// if (deviceEntity.getStatus() == NumberConstants._0) {
		// throw new AppException("鉴权码：" + authCode + " ,已经鉴过权");
		// }
		//
		// // 鉴权通过,保存鉴权通过会话信息
		// session.setDeviceId(deviceEntity.getDeviceId());
		// session.setAuthenticated(true);
		// sessionManager.putAuthSession(deviceEntity.getDeviceId(), session);
		//
		// // 鉴权通过,更新设备状态为在线状态
		// Map<String, Object> data = new HashMap<String, Object>();
		// data.put("status", NumberConstants._0);
		// Map<String, Object> condition = new HashMap<String, Object>();
		// condition.put("id", deviceEntity.getId());
		// deviceService.update(data, condition);
		// } catch (AppException e) {
		// logger.error(e.getMessage());
		// authResult = NumberConstants._1;
		// } catch (Exception e) {
		// logger.error(e.getMessage(), e);
		// authResult = NumberConstants._1;
		// }
		//
		// resultMap.put("authResult", authResult);
		// return resultMap;
		return null;
	}

	public void send(JT808ProtocalPack message, Channel tsession, Map<String, Object> resultMap) throws Exception {
		Integer authResult = 0;
		// 响应的流水号
		byte[] serialnumber = JT808ProtocalPack.generateSerialNumber(tsession);

		JT808ProtocalPack fpp = new JT808ProtocalPack();
		fpp.setId(P_PLATFORM_COMMON_REPLAY);
		fpp.setDeviceMobile(message.getDeviceMobile());
		fpp.setSerialNumber(serialnumber);

		ByteBuf buf = Unpooled.buffer(5);
		buf.writeBytes(message.getSerialNumber());
		buf.writeBytes(message.getId());
		buf.writeByte(authResult == null ? NumberConstants._1 : authResult);

		fpp.setBody(buf.array());
		sendMessage(tsession, fpp);
	}

	public DeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(DeviceService deviceService) {
		this.deviceService = deviceService;
	}

	public DeviceJt808Service getDeviceJt808Service() {
		return deviceJt808Service;
	}

	public void setDeviceJt808Service(DeviceJt808Service deviceJt808Service) {
		this.deviceJt808Service = deviceJt808Service;
	}

}
