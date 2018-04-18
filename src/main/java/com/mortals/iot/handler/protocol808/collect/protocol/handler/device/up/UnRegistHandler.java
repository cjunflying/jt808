package com.mortals.iot.handler.protocol808.collect.protocol.handler.device.up;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mortals.framework.exception.AppException;
import com.mortals.framework.util.StringUtils;
import com.mortals.iot.handler.protocol808.collect.protocol.handler.service.AbstractDeviceCommandHandler;
import com.mortals.iot.module.device.model.DeviceEntity;
import com.mortals.iot.module.device.model.DeviceQuery;
import com.mortals.iot.module.device.service.DeviceJt808Service;
import com.mortals.iot.module.device.service.DeviceService;
import com.mortals.iot.protocol.jt808.constant.NumberConstants;
import com.mortals.iot.protocol.jt808.entity.JT808ProtocalPack;
import com.mortals.iot.protocol.jt808.entity.MessageID;
import com.mortals.iot.protocol.jt808.entity.Session;
import com.mortals.iot.protocol.jt808.entity.SessionManager;
import com.mortals.iot.protocol.jt808.util.HexUtils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;

/**
 * 
 * @ClassName: UnregistHandler
 * @Description: 终端注销0x0101：服务端收到终端的注销信息
 * @Company iot
 * @author
 * @version 1.0, 2017-3-20 上午10:25:28
 */
//@Component(value = "p_device_unregist")
public class UnRegistHandler extends AbstractDeviceCommandHandler implements MessageID {
	private SessionManager sessionManager = SessionManager.getInstance();
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private DeviceJt808Service deviceJt808Service;

	public Map<String, Object> receive(JT808ProtocalPack message, Channel channel) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Integer unRegistResult = NumberConstants._0;

		try {
			String channelId = channel.id().asLongText();

			// 删除会话信息
			Session session = sessionManager.getChannelIdSession(channelId);
			sessionManager.removeAuthSession(channelId);

			// 更改设备状态为离线
			if (session == null || StringUtils.isEmpty(session.getDeviceId())) {
				throw new AppException("设备会话信息异常,channelId:" + channelId + (session == null ? "" : ",deviceId:" + session.getDeviceId()));
			}
			DeviceQuery deviceQuery = new DeviceQuery();
			deviceQuery.setDeviceId(session.getDeviceId());
			List<DeviceEntity> deviceList = deviceService.find(deviceQuery, null);
			// device信息为空
			if (deviceList == null || deviceList.size() <= 0 || deviceList.get(NumberConstants._0) == null
					|| deviceList.get(NumberConstants._0).getId() == null) {
				throw new AppException("device信息为空，deviceId:" + session.getDeviceId());
			}
			if (deviceList.size() > 1) {
				throw new AppException("device信息不唯一，deviceId:" + session.getDeviceId());
			}
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("status", NumberConstants._1);
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("id", deviceList.get(NumberConstants._0).getId());
			deviceService.update(data, condition);

			// 删除设备与车辆注册关系
			deviceJt808Service.remove(new Long[] { deviceList.get(NumberConstants._0).getId() }, null);
		} catch (AppException e) {
			logger.error(e.getMessage());
			unRegistResult = NumberConstants._1;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			unRegistResult = NumberConstants._1;
		}

		resultMap.put("unRegistResult", unRegistResult);
		return resultMap;
	}

	public void send(JT808ProtocalPack message, Channel tsession, Map<String, Object> resultMap) throws Exception {
		Integer unRegistResult = (Integer) resultMap.get("unRegistResult");
		// 响应的流水号
		byte[] serialnumber = JT808ProtocalPack.generateSerialNumber(tsession);

		JT808ProtocalPack fpp = new JT808ProtocalPack();
		fpp.setId(P_PLATFORM_COMMON_REPLAY);
		fpp.setDeviceMobile(message.getDeviceMobile());
		fpp.setSerialNumber(serialnumber);

		ByteBuf buf = Unpooled.buffer(5);
		buf.writeBytes(message.getSerialNumber());
		buf.writeBytes(message.getId());
		buf.writeByte(unRegistResult);

		fpp.setBody(buf.array());
		fpp.setProperty(HexUtils.intToByteArray(buf.array().length));
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
