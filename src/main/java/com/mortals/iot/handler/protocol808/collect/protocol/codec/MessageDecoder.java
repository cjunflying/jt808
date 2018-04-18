package com.mortals.iot.handler.protocol808.collect.protocol.codec;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mortals.iot.protocol.jt808.constant.MessageConstants;
import com.mortals.iot.protocol.jt808.constant.NumberConstants;
import com.mortals.iot.protocol.jt808.constant.SystemConstants;
import com.mortals.iot.protocol.jt808.entity.JT808ProtocalPack;
import com.mortals.iot.protocol.jt808.util.BCD8421Operater;
import com.mortals.iot.protocol.jt808.util.HexUtils;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class MessageDecoder extends ByteToMessageDecoder {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	public static volatile Integer MESSAGE_COUNT = 0;

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
		byte[] data = null;
		byte[] jt808Data = null;

		try {
			if (in.readableBytes() <= 0) {
				in.release();
				return;
			}

			data = new byte[in.readableBytes()];
			in.readBytes(data);
			if (data.length <= 0) {
				in.release();
				return;
			}

			// jt808数据
			if (data[0] == SystemConstants.JT808_DELIMITER) {
				int i = 0;
				while (i < data.length) {
					int startIndex = indexOf(data, new byte[] { SystemConstants.JT808_DELIMITER }, i);
					if (startIndex < 0) {
						logger.info(MessageConstants.ERROR_NOT_JT808_DATA);
						break;
					}
					int endIndex = indexOf(data, new byte[] { SystemConstants.JT808_DELIMITER }, startIndex + 1);
					if (endIndex < 0) {
						logger.info(MessageConstants.ERROR_NOT_JT808_DATA);
						break;
					}

					jt808Data = new byte[endIndex - startIndex - 1];
					System.arraycopy(data, startIndex + 1, jt808Data, 0, jt808Data.length);
					// 转意
					jt808Data = transferJT808Data(jt808Data, 0, jt808Data.length);

					// 消息头数据
					byte[] messageId = new byte[2];
					System.arraycopy(jt808Data, 0, messageId, 0, messageId.length);
					// 检查ID是否有效
					if (!JT808ProtocalPack.isValidId(messageId)) {
						logger.info(MessageConstants.ERROR_NOT_JT808_DATA);
						break;
					}
					byte[] messageProperty = new byte[2];
					System.arraycopy(jt808Data, 2, messageProperty, 0, messageProperty.length);
					int msgProperty = HexUtils.bytesToShort(messageProperty);
					// 消息体长度
					int bodyLength = msgProperty & 0x03FF;
					// 是否分包
					boolean isSubpackaged = ((msgProperty & 0x2000) >> 13) == 1;
					byte[] mobile = new byte[6];
					System.arraycopy(jt808Data, 4, mobile, 0, mobile.length);
					String teminalMobile = BCD8421Operater.bcd2String(mobile);
					byte[] serialNumber = new byte[2];
					System.arraycopy(jt808Data, 10, serialNumber, 0, serialNumber.length);
					// 分包信息
					// if (isSubpackaged) {
					// byte[] totalPackage = new byte[2];
					// System.arraycopy(jt808Data, 12, totalPackage, 0, totalPackage.length);
					// byte[] packageSequencce = new byte[2];
					// System.arraycopy(jt808Data, 12, packageSequencce , 0, packageSequencce
					// .length);
					// }
					// 消息体数据
					int messageBodyStartIndex = isSubpackaged ? 16 : 12;
					byte[] body = new byte[bodyLength];
					System.arraycopy(jt808Data, messageBodyStartIndex, body, 0, body.length);
					// 效验码
					byte validateCode = jt808Data[jt808Data.length - 1];
					if (!validateCode(validateCode, jt808Data, 0, jt808Data.length - 1)) {
						logger.info(MessageConstants.ERROR_VALIDATE_CODE);
						// break;
					}

					JT808ProtocalPack pack = new JT808ProtocalPack(messageId, messageProperty, teminalMobile, serialNumber, body, validateCode);
					out.add(pack);

					i = endIndex + 1;

					MESSAGE_COUNT++;
					logger.info("-----------消息数量:[" + MESSAGE_COUNT + "]-----------");
				}

			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			if (data != null) {
				logger.error("error messages:" + HexUtils.bytesToHexString(data));
			}
			if (jt808Data != null) {
				logger.error("error message:" + HexUtils.bytesToHexString(jt808Data));
			}
		}

	}

	/**
	 * 校验验证码
	 * 
	 * @param validCode
	 * @param id
	 * @param msgprp
	 * @param mobile
	 * @param serialNumber
	 * @param body
	 * @return
	 */
	private boolean validateCode(int validCode, byte[] data, int start, int end) {
		int generateValidateCode = getValidateCode(data, start, end);

		return generateValidateCode == validCode;
	}

	public static int getValidateCode(byte[] data, int start, int end) {
		if (start < 0 || end > data.length) {
			throw new ArrayIndexOutOfBoundsException(
					"getValidateCode error : index out of bounds(start=" + start + ",end=" + end + ",bytes length=" + data.length + ")");
		}
		int validateCode = 0;
		for (int i = start; i < end; i++) {
			validateCode ^= data[i];
		}
		return validateCode;
	}

	/**
	 * 从source数据中发现delimiter分隔符的起始地址
	 * 
	 * @param source
	 * @param delimiter
	 * @return
	 */
	private static int indexOf(byte[] source, byte[] delimiter, int searchStartIndex) {
		for (int i = searchStartIndex; i < source.length; i++) {
			int sourceIndex = i;
			int delimiterIndex;
			for (delimiterIndex = 0; delimiterIndex < delimiter.length; delimiterIndex++) {
				if (source[sourceIndex] != delimiter[delimiterIndex]) {
					break;
				} else {
					sourceIndex++;
					if (sourceIndex == source.length && delimiterIndex != delimiter.length - 1) {
						return NumberConstants._N1;
					}
				}
			}

			if (delimiterIndex == delimiter.length) {
				// 发现分隔符
				return i;
			}
		}
		return NumberConstants._N1;
	}

	/**
	 * jt808数据转意
	 * 
	 * @param jd808Data
	 */
	@SuppressWarnings("unused")
	private static byte[] transferJT808DataBak(byte[] jd808Data) {
		String hexString = HexUtils.bytesToHexString(jd808Data).replace(" ", "").toUpperCase();
		if (hexString.indexOf("7D01") < 0 && hexString.indexOf("7D02") < 0) {
			return jd808Data;
		}

		hexString = hexString.replace("7D01", "7D").replace("7D02", "7E");
		return HexUtils.hexStringToByte(hexString);

	}

	public byte[] transferJT808Data(byte[] bs, int start, int end) throws Exception {
		if (start < 0 || end > bs.length) {
			throw new ArrayIndexOutOfBoundsException(
					"transferJT808Data error : index out of bounds(start=" + start + ",end=" + end + ",bytes length=" + bs.length + ")");
		}

		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			for (int i = 0; i < start; i++) {
				baos.write(bs[i]);
			}
			for (int i = start; i < end - 1; i++) {
				if (bs[i] == 0x7d && bs[i + 1] == 0x01) {
					baos.write(0x7d);
					i++;
				} else if (bs[i] == 0x7d && bs[i + 1] == 0x02) {
					baos.write(0x7e);
					i++;
				} else {
					baos.write(bs[i]);
				}
			}
			for (int i = end - 1; i < bs.length; i++) {
				baos.write(bs[i]);
			}
			return baos.toByteArray();
		} catch (Exception e) {
			// logger.error("error message:" + HexUtils.bytesToHexString(bs));
			throw e;
		} finally {
			if (baos != null) {
				baos.close();
				baos = null;
			}
		}
	}

	public static void main(String[] args) {
		byte[] source = { 124, 0, 125, 125 };
		byte[] delimiter = { 125, 125 };
		System.out.println(indexOf(source, delimiter, 0));
	}
}
