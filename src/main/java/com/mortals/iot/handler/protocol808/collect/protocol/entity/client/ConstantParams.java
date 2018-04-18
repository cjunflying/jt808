package com.mortals.iot.handler.protocol808.collect.protocol.entity.client;



/**
 * @ClassName: ConstantParams
 * @Description: 常量参数：文本信息、电话回拨、设置电话本、摄像头立即拍摄等参数
 * @Company iot
 * @author 
 * @version  1.0, 2017-3-27 下午2:00:23  
 */
public class ConstantParams {
	
	// --------------------------------------------------------
	// 		文本信息下发标志
	// --------------------------------------------------------
	/**
	 * 文本信息下发标志：紧急
	 */
	public static byte SENT_TXT_TYPE_EMERGENCY = 0x00;
	/**
	 * 文本信息下发标志：终端显示器显示
	 */
	public static byte SENT_TXT_TYPE_SHOW = 2;
	/**
	 * 文本信息下发标志：终端TTS播读
	 */
	public static byte SENT_TXT_TYPE_TTS = 3;
	/**
	 * 文本信息下发标志：广告屏显示
	 */
	public static byte SENT_TXT_TYPE_AD = 4;
	/**
	 * 文本信息下发标志：保留
	 */
	public static byte SENT_TXT_TYPE_1 = 1;
	/**
	 * 文本信息下发标志：保留
	 */
	public static byte SENT_TXT_TYPE_5 = 5;
	/**
	 * 文本信息下发标志：保留
	 */
	public static byte SENT_TXT_TYPE_6 = 6;
	/**
	 * 文本信息下发标志：保留
	 */
	public static byte SENT_TXT_TYPE_7 = 7;
	
	// --------------------------------------------------------
	// 		电话回拨标志
	// --------------------------------------------------------
	/**
	 * 	电话回拨标志：0:普通通话
	 */
	public static byte CALL_TYPE_COMMON = 0;
	/**
	 *  电话回拨标志：0:普通通话
	 */
	public static byte CALL_TYPE_MONITOR = 1;
	
	// --------------------------------------------------------
	// 		设置电话本
	// --------------------------------------------------------
	/**
	 * 	设置电话本
	 * 	删除终端上所有存储的联系人
	 */
	public static byte PHONEBOOK_TYPE_DEL = 0;
	/**
	 * 	更新电话本（删除终端中已有全部联系人并追加消息中的联系人）；
	 */
	public static byte PHONEBOOK_TYPE_UPDATE = 1;
	/**
	 * 	追加电话本
	 */
	public static byte PHONEBOOK_TYPE_ADD = 2;
	/**
	 * 	修改电话本
	 */
	public static byte PHONEBOOK_TYPE_MODIFY = 3;
	
	// --------------------------------------------------------
	// 		摄像头立即拍摄参数
	// --------------------------------------------------------
	/**
	 * 	拍摄命令:停止拍摄: 00 00
	 */
	public static int SHOOT_STOP = 0x0000;
	
	/**
	 * 	拍摄命令：录像 : FF FF
	 */
	public static int SHOOT_RECORD = (short) 0xFFFF;
	
	/**
	 *	 拍照间隔/录像时间
	 */
	public static int SHOOT_MIN_INTERVAL = 0x0000;
	
	/**
	 * 	保存标志：1 保存
	 */
	public static int SHOOT_FLAG_SAVE = 0x01;
	
	/**
	 * 	保存标志：0 实时上传
	 */
	public static int SHOOT_FLAG_REALTIME = 0x00;
	
	/**
	 * 	分辨率:320*240
	 */
	public static int SHOOT_RESOLUTION_320x240 = 0x01;
	
	/**
	 * 	分辨率:640*480
	 */
	public static int SHOOT_RESOLUTION_640x480 = 0x02;
	
	/**
	 * 	分辨率:800*600
	 */
	public static int SHOOT_RESOLUTION_800x600 = 0x03;
	
	/**
	 * 	分辨率:1024*768
	 */
	public static int SHOOT_RESOLUTION_1024x768 = 0x04;
	
	/**
	 * 	分辨率:176*144(qcif)
	 */
	public static int SHOOT_RESOLUTION_176x144_QCIF = 0x05;
	
	/**	
	 * 	分辨率:352*288(cif)
	 */
	public static int SHOOT_RESOLUTION_352x288_CIF = 0x06;
	
	/**
	 * 	分辨率:704*288(HALF D1)
	 */
	public static int SHOOT_RESOLUTION_704x288_HALF_D1 = 0x07;
	
	/**
	 * 	分辨率:704*576(D1)
	 */
	public static int SHOOT_RESOLUTION_704x576_D1 = 0x08;
	
	/**
	 * 	图像/视频质量:01
	 */
	public static int SHOOT_VIDE_QUALITY_1 = 0x01;
	
	/**
	 * 	图像/视频质量:02
	 */
	public static int SHOOT_VIDE_QUALITY_2 = 0x02;
	
	/**
	 * 	图像/视频质量:03
	 */
	public static int SHOOT_VIDE_QUALITY_3 = 0x03;
	
	/**
	 * 	图像/视频质量:04
	 */
	public static int SHOOT_VIDE_QUALITY_4 = 0x04;
	
	/**
	 * 	图像/视频质量:05
	 */
	public static int SHOOT_VIDE_QUALITY_5 = 0x05;
	
	/**
	 * 	图像/视频质量:06
	 */
	public static int SHOOT_VIDE_QUALITY_6 = 0x06;
	
	/**
	 * 	图像/视频质量:07
	 */
	public static int SHOOT_VIDE_QUALITY_7 = 0x07;
	
	/**
	 * 	图像/视频质量:08
	 */
	public static int SHOOT_VIDE_QUALITY_8 = 0x08;
	
	/**
	 * 	图像/视频质量:09
	 */
	public static int SHOOT_VIDE_QUALITY_9 = 0x09;
	
	/**
	 * 	图像/视频质量:10
	 */
	public static int SHOOT_VIDE_QUALITY_10 = 0x0A;
	
	
}
