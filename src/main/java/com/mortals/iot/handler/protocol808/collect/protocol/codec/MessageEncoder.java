package com.mortals.iot.handler.protocol808.collect.protocol.codec;

import java.io.ByteArrayOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mortals.iot.protocol.jt808.entity.JT808ProtocalPack;
import com.mortals.iot.protocol.jt808.util.HexUtils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

@SuppressWarnings("rawtypes")
public class MessageEncoder extends MessageToByteEncoder {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	protected void encode(ChannelHandlerContext contex, Object message, ByteBuf out) throws Exception {
		try {
			if (message instanceof JT808ProtocalPack) {
				JT808ProtocalPack jt808Data = (JT808ProtocalPack) message;
				byte[] resultData = jt808Data.pack();

				if (resultData != null && resultData.length > 0) {
					byte[] transferData = transfer(resultData, 1, resultData.length - 2);
					ChannelFuture channelFuture = contex.writeAndFlush(Unpooled.copiedBuffer(transferData));
					if (!channelFuture.isSuccess()) {
						logger.error("发送jt808消息到设备出错", channelFuture.cause());
					} else {
						logger.info("发送jt808消息：" + HexUtils.bytesToHexString(transferData));
					}
				} else {
					logger.info("jt808消息为空");
				}
			}
		} catch (Exception e) {
			logger.info("消息发送出错", e);
		}
	}

	public byte[] transfer(byte[] data, int start, int end) throws Exception {
		ByteArrayOutputStream baos = null;

		try {
			if (start < 0 || end > data.length) {
				return data;
			}

			baos = new ByteArrayOutputStream();
			for (int i = 0; i < start; i++) {
				baos.write(data[i]);
			}
			for (int i = start; i < end; i++) {
				if (data[i] == 0x7d) {
					baos.write(0x7d);
					baos.write(0x01);
				} else if (data[i] == 0x7e) {
					baos.write(0x7d);
					baos.write(0x02);
				} else {
					baos.write(data[i]);
				}
			}
			for (int i = end; i < data.length; i++) {
				baos.write(data[i]);
			}
			return baos.toByteArray();
		} catch (Exception e) {
			throw e;
		} finally {
			if (baos != null) {
				baos.close();
			}
		}
	}

}
