package com.mortals.iot.handler.protocol808.collect.protocol.handler.device.up;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mortals.iot.handler.protocol808.collect.protocol.handler.service.AbstractDeviceCommandHandler;
import com.mortals.iot.module.device.service.DeviceJt808Service;
import com.mortals.iot.module.device.service.DeviceService;
import com.mortals.iot.protocol.jt808.entity.JT808ProtocalPack;
import com.mortals.iot.protocol.jt808.entity.MessageID;
import com.mortals.iot.protocol.jt808.entity.RegisterEntity;
import com.mortals.iot.protocol.jt808.util.HexUtils;

import io.netty.channel.Channel;

/**
 * @ClassName: RegisterHander
 * @Description: 终端注册0x0100：终端到服务端的注册信息
 * @Company iot
 * @author
 * @version 1.0, 2017-3-19 下午4:57:52
 */
@Component(value = "p_device_regist")
public class RegisterHander extends AbstractDeviceCommandHandler implements MessageID {
	private DeviceService deviceService;
	private DeviceJt808Service deviceJt808Service;

	// iot收到：7E 01 00 00 25 01 32 58 16 82 96 00 09 00 00 00 00 53 46 43 4F 4D 53 46
	// 31 31 30 47 34 30 4E 45 64 33 33 36 30 00 00 00 00 00 00 00 00 00 00 00 00 00
	// 1F 7E
	/*
	 * 7E 01 00 00 21 01 34 80 77 07 42 00 1F D4 C1 02 F3 31 32 33 34 35 43 42 2D 46
	 * 4A 54 54 31 36 38 30 30 30 30 31 01 D4 C1 42 37 38 33 31 41 59 7E
	 * 收到终端[013480770742]的注册信息:省域(54465),市域(755),制造商(12345),型号(CB-FJTT1),厂家设备ID(
	 * 6800001),颜色(1),车牌(粤B7831A).
	 */
	public Map<String, Object> receive(JT808ProtocalPack message, Channel tsession) throws Exception {
		// Map<String, Object> resultMap = new HashMap<String, Object>();
		// // 0：成功； 1：车辆已被注册； 2：数据库中无该车辆； 3：终端已被注册； 4：数据库中无该终端
		// Integer registResult = NumberConstants._0;
		// // 鉴权码
		// String authorizationCode = null;

		// try {
		// String channelId = tsession.id().asLongText();
		// // 当会话信息不存在时,保存会话信息
		// if (sessionManager.getSession(channelId) == null) {
		// Session session = Session.buildSession(tsession, message.getDeviceMobile());
		// sessionManager.putSession(channelId, session);
		// }

		RegisterEntity registeInfo = new RegisterEntity(message.getDeviceMobile(), message.getBody());
		logger.info("收到终端注册消息" + registeInfo.toString());

		// String deviceId = registeInfo.getDeviceId().trim();
		// if (StringUtils.isEmpty(deviceId)) {
		// registResult = NumberConstants._4;
		// throw new AppException("终端Id信息为空");
		// }
		//
		// // 终端注册信息判断
		// DeviceQuery deviceQuery = new DeviceQuery();
		// deviceQuery.setDeviceId(deviceId);
		// List<DeviceEntity> deviceList = deviceService.find(deviceQuery, null);
		// // 终端信息为空
		// if (deviceList == null || deviceList.size() <= 0 ||
		// deviceList.get(NumberConstants._0) == null
		// || deviceList.get(NumberConstants._0).getId() == null) {
		// registResult = NumberConstants._4;
		// throw new AppException("终端信息为空");
		// }
		// if (deviceList.size() > 1) {
		// registResult = NumberConstants._4;
		// throw new AppException("终端信息不唯一，deviceId:" + deviceId);
		// }
		//
		// DeviceEntity deviceEntity = deviceList.get(NumberConstants._0);
		// DeviceJt808Query deviceJt808Query = new DeviceJt808Query();
		// deviceJt808Query.setId(deviceEntity.getId());
		// int deviceJt808EntityCount = deviceJt808Service.count(deviceJt808Query,
		// null);
		// // 终端已被注册
		// if (deviceJt808EntityCount > NumberConstants._0) {
		// registResult = NumberConstants._3;
		// throw new AppException("终端已被注册");
		// }
		//
		// // 更新设备表信息
		// deviceEntity.setRegisterTime(new Date());
		// deviceService.update(deviceEntity, null);
		// // 保存jt808设备数据
		// DeviceJt808Entity deviceJt808Entity = new DeviceJt808Entity();
		// deviceJt808Entity.setId(deviceEntity.getId());
		// deviceJt808Entity.setProvinceID((long) registeInfo.getProvinceId());
		// deviceJt808Entity.setCityID((long) registeInfo.getCityId());
		// deviceJt808Entity.setPlateColor(registeInfo.getColor());
		// deviceJt808Entity.setPlateNumber(registeInfo.getPlateNumber());
		// authorizationCode = UUID.randomUUID().toString().replaceAll("-", "");
		// deviceJt808Entity.setAuthorizationCode(authorizationCode);
		// deviceJt808Service.save(deviceJt808Entity, null);
		// } catch (AppException e) {
		// logger.error(e.getMessage());
		// if (registResult == NumberConstants._0) {
		// registResult = NumberConstants._4;
		// }
		// if (StringUtils.isEmpty(authorizationCode)) {
		// authorizationCode = null;
		// }
		// } catch (Exception e) {
		// logger.error(e.getMessage(), e);
		// if (registResult == NumberConstants._0) {
		// registResult = NumberConstants._4;
		// }
		// if (StringUtils.isEmpty(authorizationCode)) {
		// authorizationCode = null;
		// }
		// }
		//
		// resultMap.put("registResult", registResult);
		// resultMap.put("authorizationCode", authorizationCode);

		return null;
	}

	/*
	 * 7E 81 00 00 0C 01 34 80 77 07 42 03 D8 00 00 00 50 6F 77 65 72 43 6F 64 65 A3
	 * 7E 标识位 —— 7E 消息体ID —— 81 00 消息体属性 —— 00 0C 终端手机号 —— 01 34 80 77 07 42 流水号 ——
	 * 00 00 应答流水号 —— 00 00 结果 —— 00 鉴权码（成功后） —— 50 6F 77 65 72 43 6F 64 65 校验码 ——
	 * 78 标识位 —— 7E
	 */
	public void send(JT808ProtocalPack message, Channel tsession, Map<String, Object> resultMap) throws Exception {
		Integer registResult = 0;
		// 鉴权码
		String authorizationCode = "authorizationCode";
//		if (registResult == null) {
//			registResult = NumberConstants._4;
//		}

		byte[] serialnumber = JT808ProtocalPack.generateSerialNumber(tsession);
		byte[] authCode = authorizationCode != null ? authorizationCode.getBytes() : null;

		JT808ProtocalPack fpp = new JT808ProtocalPack();
		// ID
		fpp.setId(P_DEVICE_REGIST_REQUEST);
		// 终端手机号
		fpp.setDeviceMobile(message.getDeviceMobile());
		// 流水号
		fpp.setSerialNumber(serialnumber);

		byte[] body = {};
		// 应答流水号
		body = HexUtils.mergeByte(body, message.getSerialNumber());
		// 0：成功； 1：车辆已被注册； 2：数据库中无该车辆； 3：终端已被注册； 4：数据库中无该终端
		body = HexUtils.mergeByte(body, HexUtils.intToByte1Array(registResult));
		// 鉴权码
		body = authCode != null ? HexUtils.mergeByte(body, authCode) : body;

		fpp.setBody(body);

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
