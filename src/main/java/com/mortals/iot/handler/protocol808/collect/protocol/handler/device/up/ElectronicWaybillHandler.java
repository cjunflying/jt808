package com.mortals.iot.handler.protocol808.collect.protocol.handler.device.up;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mortals.framework.exception.AppException;
import com.mortals.iot.handler.protocol808.collect.commons.Log;
import com.mortals.iot.handler.protocol808.collect.protocol.handler.service.AbstractDeviceCommandHandler;
import com.mortals.iot.protocol.jt808.constant.NumberConstants;
import com.mortals.iot.protocol.jt808.entity.JT808ProtocalPack;
import com.mortals.iot.protocol.jt808.entity.MessageID;
import com.mortals.iot.protocol.jt808.util.HexUtils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;

/**
 * @ClassName: ElectronicWaybillHandler
 * @Description: 电子运单上报0x0701,具体运单内容看具体设备情况
 * @Company iot
 * @author
 * @version 1.0, 2017-3-28 上午10:35:25
 */
//@Component(value = "p_electronic_waybill")
public class ElectronicWaybillHandler extends AbstractDeviceCommandHandler implements MessageID {

	public Map<String, Object> receive(JT808ProtocalPack message, Channel tsession) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Integer result = NumberConstants._0;

		try {
			// 电子运单长度
			int length = HexUtils.bytesToInt4(HexUtils.copyOfRange(message.getBody(), 0, 4));
			// 电子运单内容
			byte[] content = HexUtils.copyOfRange(message.getBody(), 4, 4 + length);
			logger.info("收到电子运单内容，" + HexUtils.bytesToHexString(content));

			// String cmdstr = HexUtils.copyToHexStr(b_content, 2, 1);
			// 由于计价器并非业内统一标准，所以下面并不适合转发指令情形，故改为if语句判断：
			// String beanName = (String) ServerPacketUtils.getValue(cmdstr);
			// IotDeviceCommandHandler handler = SpringContextUtils.getBean(beanName);
			// IoCommandContext context = new IoCommandContext(handler);
			// context.handle(message, tsession);

			// if ("dc".equalsIgnoreCase(cmdstr)) {
			// EletronicWaybill ewb = new EletronicWaybill(message.getDeviceMobile(),
			// b_content);
			// // CollectCallServiceImpl.getInstance().extdeviceMeterInfo(ewb);
			// Log.getLogger(this.getClass()).info("电子运单具体内容：" + ewb.toString());
			// }
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

		Log.getLogger(this.getClass()).info("电子运单应答：" + fpp.toHexString());
		sendMessage(tsession, fpp);
	}

}
