package com.mortals.iot.handler.protocol808.collect.protocol.handler.device.up;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mortals.framework.exception.AppException;
import com.mortals.iot.handler.protocol808.collect.protocol.handler.service.AbstractDeviceCommandHandler;
import com.mortals.iot.protocol.jt808.constant.NumberConstants;
import com.mortals.iot.protocol.jt808.entity.JT808ProtocalPack;

import io.netty.channel.Channel;

/**
 * @ClassName: MultimediaUploadHandler
 * @Description: 多媒体数据上传0x0801：终端上发给服务端的多媒体数据
 * @Company iot
 * @author
 * @version 1.0, 2017-3-28 下午4:25:37
 */
//@Component(value = "p_multimedia_upload")
public class MultimediaUploadHandler extends AbstractDeviceCommandHandler {

	public Map<String, Object> receive(JT808ProtocalPack message, Channel channel) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Integer result = NumberConstants._0;

		try {
			logger.info("多媒体数据上传:" + message.toHexString());
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

	// TODO bono: 分包处理
	public void send(JT808ProtocalPack message, Channel channel, Map<String, Object> reslutMap) throws Exception {

	}

}
