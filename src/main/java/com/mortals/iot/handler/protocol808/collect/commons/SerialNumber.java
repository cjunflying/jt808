package com.mortals.iot.handler.protocol808.collect.commons;

/**
 * 主要用于各个发送类的SerialNumber统一管理
 * 
 * @author 
 * @package com.mortals.iot.handler.protocol808.collect.comm.commons
 * @copyright iot
 * @date:2017-4-9 上午9:18:54
 */
public interface SerialNumber {
	/**
	 *  查询终端参数流水号0x00C3
	 */
	public static final byte[] queryPosition2SerialNumber = new byte[] { 0x00,
			(byte) 0xC3 };
	/**
	 *  查询终端参数流水号0x02BA
	 */
	public static final byte[] queryDeviceParamsSerialNumber = new byte[] {
			0x02, (byte) 0xBA };
	/**
	 *  文本信息下发流水号 0x0000
	 */
	public static final byte[] sendTxtSerialNumber = new byte[] { 0x00,
			(byte) 0x00 };
	/**
	 *  位置信息查询流水号0x00C3
	 */
	public static final byte[] queryPositionSerialNumber = new byte[] { 0x00,
			(byte) 0xC3 };
	/**
	 *  电话回拨流水号 0x0001
	 */
	public static final byte[] callSerialNumber = new byte[] { 0x00,
			(byte) 0x01 };
	/**
	 *  设置电话本流水号0x0001
	 */
	public static final byte[] settingMobileListSerialNumber = new byte[] {
			0x00, (byte) 0x01 };
	/**
	 *  摄像头立即拍摄命令流水号0x0001
	 */
	public static final byte[] shootImmediatelySerialNumber = new byte[] {
			0x00, (byte) 0x01 };
	/**
	 *  数据下行透传流水号0x0001
	 */
	public static final byte[] unvarnishedTransmiteSerialNumber = new byte[] {
			0x00, (byte) 0x01 };
	/**
	 *  录音开始命令流水号0x0001
	 */
	public static final byte[] recordSerialNumber = new byte[] { 0x00,
			(byte) 0x01 };
	/**
	 *  提问下发流水号0x0001
	 */
	public static final byte[] questionSerialNumber = new byte[] { 0x00,
			(byte) 0x01 };
	/**
	 *  设置终端参数流水号0x0001
	 */
	public static final byte[] setDeviceParamSerialNumber = new byte[] { 0x00,
			(byte) 0x01 };
	
	/**
	 * 设置圆形区域流水号0x8600
	 */
	public static final byte[] setCircularAreaSerialNumber=new byte[]{ 0x00,
		(byte) 0x01 };
	
	/**
	 * 设置矩形区域流水号0x8600
	 */
	public static final byte[] setRectangularAreaSerialNumber=new byte[]{ 0x00,
		(byte) 0x01 };
	
	/**
	 * 设置多边形区域流水号0x8600
	 */
	public static final byte[] setPolygonAreaSerialNumber=new byte[]{ 0x00,
		(byte) 0x01 };
	
	/**
	 * 人工消息确认流水号0x8203
	 */
	public static final byte[] setDisarmAlarmSerialNumber=new byte[]{ 0x00,
		(byte) 0x01 };
	
	/**
	 * 设置圆形区域流水号0x8600
	 */
	public static final byte[] delCircularAreaSerialNumber=new byte[]{ 0x00,
		(byte) 0x01 };
	
	/**
	 * 查询驾驶员身份信息请求流水号 0x8702
	 */
	public static final byte[] DriverIcSerialNumber=new byte[]{0x00,(byte)0x01};
	
	/**
	 * 设置流水号0x8600
	 */
	public static final byte[] setSerialNumber=new byte[]{ 0x00,
		(byte) 0x01 };
	
	/**
	 *  body为空, 空包体
	 */
	
	
	public static final byte[] nullBody = new byte[] {};

}
