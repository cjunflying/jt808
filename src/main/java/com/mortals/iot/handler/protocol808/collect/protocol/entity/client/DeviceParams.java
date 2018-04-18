package com.mortals.iot.handler.protocol808.collect.protocol.entity.client;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.builder.HashCodeBuilder;

import com.mortals.iot.handler.protocol808.collect.commons.Log;
import com.mortals.iot.protocol.jt808.util.HexUtils;

/*
 * 标识位					—— 7E
 * 消息体ID				—— 01 04
 * 消息体属性				—— 01 4F
 * 终端手机号				—— 01 34 80 77 07 42
 * 流水号					—— 00 04
 * 应答流水号				—— 00 04
 * 参数总数				—— 25
 * 参数ID：心跳			—— 00 00 00 01
 * 参数长度				—— 04
 * 参数值：心跳间隔		—— 00 00 00 37
 * 参数ID：主APN			—— 00 00 00 10
 * 参数长度				—— 05
 * 参数值：CMNET			—— 43 4D 4E 45 54
 * 参数ID：主服务器无线通信拨号用户名	—— 00 00 00 11
 * 参数长度				—— 00
 * 参数ID：主服务器无线通信拨号密码	—— 00 00 00 12
 * 参数长度				—— 00
 * 参数ID：主服务器地址,IP或域名		—— 00 00 00 13
 * 参数长度				—— 10
 * 参数值：				—— 31 31 30 2E 31 38 34 2E 31 30 39 * 2E 31 31 31 34
 * 参数ID：备份服务器APN	—— 00 00 00 14
 * 参数长度				—— 05
 * 参数值：				—— 43 4D 4E 45 54
 * 参数ID：备份服务器无线通信拨号用户名	—— 00 00 00 15
 * 参数长度				—— 00
 * 参数ID：备份服务器无线通信拨号密码	—— 00 00 00 16
 * 参数长度				—— 00
 * 参数ID：备份服务器地址,IP或域名		—— 00 00 00 17
 * 参数长度				—— 0F
 * 参数值：				—— 31 31 30 2E 31 38 34 2E 31 30 39 * 2E 31 31 34
 * 参数ID：服务器TCP端口	—— 00 00 00 18
 * 参数长度				—— 04
 * 参数值					—— 00 00 22 E8
 * 参数ID：缺省时间汇报间隔，单位为秒>0	—— 00 00 00 29
 * 参数长度				—— 04
 * 参数值					—— 00 00 00 B4
 * 参数ID：缺省距离汇报间隔，单位为米>0	—— 00 00 00 2C
 * 参数长度				—— 04
 * 参数值：				—— 00 00 00 64
 * 参数ID：拐点补传角度，<180		—— 00 00 00 30
 * 参数长度：				—— 04
 * 参数值：				—— 00 00 00 00
 * 参数ID:	监控平台电话号码		—— 00 00 00 40
 * 参数长度：				—— 00
 * 参数ID：复位电话号码	—— 00 00 00 41
 * 参数长度：				—— 00
 * 参数ID：恢复出厂设置电话号码		—— 00 00 00 42
 * 参数长度				—— 00
 * 参数ID：监控平台SMS电话号码		—— 00 00 00 43
 * 参数长度				—— 00
 * 参数ID：接收终端SMS文本报警号码		—— 00 00 00 44 
 * 参数长度：				—— 0A 
 * 参数值					—— 34 30 30 36 39 30 36 30 36 31
 * 参数ID：终端电话接听策略—— 00 00 00 45
 * 参数长度：				—— 04
 * 参数值					—— 00 00 00 00
 * 参数ID：每次最长通话时间(秒)		—— 00 00 00 46 
 * 参数长度：				—— 04 
 * 参数值					—— 00 00 00 00
 * 参数ID:	监听电话号码	—— 00 00 00 48 
 * 参数长度				—— 0B
 * 参数值					—— 31 35 30 31 34 31 39 30 34 30 36
 * 参数ID					—— 00 00 00 49 
 * 参数长度				—— 00
 * 参数ID					—— 00 00 00 50
 * 参数长度				—— 04
 * 参数值					—— 00 00 00 00
 * 参数ID					—— 00 00 00 51
 * 参数长度				—— 04
 * 参数值					—— 00 00 00 00
 * 参数ID：报警拍摄开关	—— 00 00 00 52
 * 参数长度				—— 04
 * 参数值					—— 00 00 00 00
 * 参数ID：报警拍摄存储标志—— 00 00 00 53
 * 参数长度				—— 04
 * 参数值					—— 00 00 00 00
 * 参数ID：最高速度KM/H	—— 00 00 00 55
 * 参数长度				—— 04
 * 参数值					—— 00 00 00 00
 * 参数ID:	超速持续时间，单位为秒		—— 00 00 00 56
 * 参数长度				—— 04
 * 参数值					—— 00 00 00 00
 * 参数ID：连续驾驶时间门限，单位为秒	—— 00 00 00 57
 * 参数长度				—— 04
 * 参数值					—— 00 00 00 00
 * 参数ID					—— 00 00 00 58 
 * 参数长度				—— 04
 * 参数值					—— 00 00 00 00
 * 参数ID：最小休息时间，单位为秒	—— 00 00 00 59
 * 参数长度				—— 04
 * 参数值					—— 00 00 00 00
 * 参数ID：最长停车时间，单位为秒	—— 00 00 00 5A 
 * 参数长度				—— 04
 * 参数值					—— 00 00 00 00
 * 参数ID：车辆里程表读数，1/10km		—— 00 00 00 80
 * 参数长度				—— 04
 * 参数值					—— 00 00 03 EA
 * 参数ID：车辆所在的省域ID—— 00 00 00 81
 * 参数长度				—— 02
 * 参数值					—— D4 C1
 * 参数ID：车辆所在的市域ID—— 00 00 00 82 
 * 参数长度				—— 02
 * 参数值					—— 02 F3
 * 参数ID：公安交通管理部门颁发的机动车号牌—— 00 00 00 83
 * 参数长度				—— 08
 * 参数值					—— D4 C1 42 37 38 33 31 41
 * 参数ID：车牌颜色		—— 00 00 00 84
 * 参数长度				—— 01
 * 参数值					—— 01
 * 校验码					—— 24
 * 标识位					—— 7E
 */
/**
 * @ClassName: DeviceParams
 * @Description: 查询的设备参数实体
 * @Company iot
 * @author 
 * @version 1.0, 2017-3-20 上午11:36:07
 */
public class DeviceParams implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 终端手机号 01 34 80 77 07 42
	private String deviceMobile;

	// 心跳间隔 00 00 00 01
	private int heartInterval;

	// 主服务器APN，无线通信拨号访问点。若网络制式为CDMA，
	// 则该处为PPP拨号号码 00 00 00 10
	private String mainServerAPN;

	// 主服务器无线通信拨号用户名 00 00 00 11
	private String mainServerUser;

	// 主服务器无线通信拨号密码 00 00 00 12
	private String mainServedrPwd;

	// 主服务器地址,IP或域名 00 00 00 13
	private String mainServerIpOrDomain;

	// 备份服务器APN 00 00 00 14
	private String backServerAPN;

	// 备份服务器无线通信拨号用户名 00 00 00 15
	private String backServerUser;

	// 备份服务器无线通信拨号密码 00 00 00 16
	private String backSerqverPwd;

	// 备份服务器地址,IP或域名 00 00 00 17
	private String backServerIpOrDomain;

	// 服务器TCP端口 00 00 00 18
	private int serverTcpPort;

	// 休眠时汇报时间间隔，单位为秒（s），>0
	private int sleepInterval;

	// 紧急报警时汇报时间间隔，单位为秒（s），>0
	private int alarmInterval;

	// 缺省时间汇报间隔，单位为秒>0 00 00 00 29
	private int defaultTimeInterval;

	// 缺省距离汇报间隔，单位为米>0 00 00 00 2C
	private int defaultMileInterval;

	// 拐点补传角度，<180 00 00 00 30
	private int corner;

	// 监控平台电话号码 00 00 00 40
	private String monitorPlatformMobile;

	// 复位电话号码 00 00 00 41
	private String resetMobile;

	// 恢复出厂设置电话号码 00 00 00 42
	private String rescoverySettingMobile;

	// 监控平台SMS电话号码 00 00 00 43
	private String mobilePlatformSmsMobile;

	// 接收终端SMS文本报警号码 00 00 00 44
	private String deviceSmsMobile;

	// 终端电话接听策略 00 00 00 45
	private int deviceMobileStrategy;

	// 每次最长通话时间(秒) 00 00 00 46
	private int everyCallMaxTimeLen;

	// 监听电话号码 00 00 00 48
	private String monitorPhoneNumber;

	// 监管平台特权短信号码 00 00 00 49
	private String monitorPrivilegeSmsNumber;

	// 报警屏蔽字 00 00 00 50
	private String alarmMaskWord;

	// 报警发送文本SMS开关00 00 00 51
	private int alarmSendSmsSwitch;

	// 报警拍摄开关 00 00 00 52
	private int alarmShootSwitch;

	// 报警拍摄存储标志 00 00 00 53
	private int alarmShootSaveFlag;

	// 最高速度KM/H 00 00 00 55
	private int maxSpeed;

	// 超速持续时间，单位为秒 00 00 00 56
	private int overSpeedContinueTime;

	// 连续驾驶时间门限，单位为秒 00 00 00 57
	private int driveContinueTime;

	// 当天累计驾驶时间门限 00 00 00 58
	private int driveTime;

	// 最小休息时间，单位为秒 00 00 00 59
	private int minRestTime;

	// 最长停车时间，单位为秒 00 00 00 5A
	private int maxStopTime;

	// 车辆里程表读数，1/10km 00 00 00 80
	private double mileage;

	// 车辆所在的省域ID 00 00 00 81
	private int provinceId;

	// 车辆所在的市域ID 00 00 00 82
	private int cityId;

	// 公安交通管理部门颁发的机动车号牌 00 00 00 83
	private String plateNumber;

	// 车牌颜色 00 00 00 84
	private int plateColor;

	// 位置汇报测试
	private int locationType;
	// 0x0002 TCP 消息应答超时时间，单位为秒（s）
	private int tcpResp;
	// 0x0003 TCP 消息重传次数
	private int tcpRettimes;
	// 0x0004 UDP 消息应答超时时间，单位为秒（s）
	private int udpResp;
	// 0x0005 UDP 消息重传次数
	private int udpRettimes;
	// 0x0006 SMS 消息应答超时时间，单位为秒（s）
	private int smsResp;
	// 0x0007 SMS 消息重传次数
	private int smsRettimes;
	// 0x00000019:// 服务器UDP 端口
	private int serverUdpPort;
	// 0x00000021:// 位置汇报方案，
	private int positionRep;
	// 0x00000022:// 驾驶员未登录汇报时间间隔，单位为秒（s），>0
	private int driverUnRepTime;
	// 0x0000002D:// 驾驶员未登录汇报距离间隔，单位为米（m），>0
	private int driverUnRepDistance;
	// 0x0000002E:// 休眠时汇报距离间隔，单位为米（m），>0
	private int dormancyRepDis;
	// 0x0000002F:// 紧急报警时汇报距离间隔，单位为米（m），>0
	private int alarmRepDisInt;
	// 0x00000047:// 当月最长通话时间，单位为秒（s），0 为不允许通话，0xFFFFFFFF 为不限制
	private int monthCallMaxTimeLen;
	// 0x00000054:// 关键标志，与位置信息汇报消息中的报警标志相对应，相应位为1 则对相应报警为关键报警
	private int keyMark;
	// 0x00000070:// 图像/视频质量，1-10，1 最好
	private int imageOrAV;
	// 0x00000071:// 亮度，0-255
	private int brightness;
	// 0x00000072:// 对比度，0-127
	private int contrast;
	// 0x00000073:// 饱和度，0-127
	private int saturation;
	// 0x00000074:// 色度，0-255
	private int chroma;

	public DeviceParams() {
	}

	public DeviceParams(String deviceMobile, byte[] body) {
		// 终端手机号
		Log.getLogger(this.getClass()).info(HexUtils.bytesToHexString(body));

		this.deviceMobile = deviceMobile;

		// 以下内容用不着
		// 应答流水号
		// byte[] replySerialnumberByte = HexUtils.copyOfRange(body, 0, 2);
		// 应答参数个数
		// byte[] replyParamCountByte = HexUtils.copyOfRange(body, 2, 3);

		byte[] paramsByte = HexUtils.copyOfRange(body, 3, body.length);

		// 迭代分析参数
		analyst(paramsByte);

	}

	/**
	 * @param paramsByte
	 */
	private void analyst(byte[] paramsByte) {
		if (paramsByte.length == 0) {
			return;
		}

		byte[] idByte = HexUtils.copyOfRange(paramsByte, 0, 4);
		// ID
		int id = HexUtils.bytesToInt4(idByte);
		// 长度
		int length = paramsByte[4];
		byte[] value = HexUtils.copyOfRange(paramsByte, 5, 5 + length);

		switch (id) {
		case 0x00000001:// 心跳间隔
			this.heartInterval = HexUtils.bytesToInt4(value);
			break;

		case 0x00000002:// TCP 消息应答超时时间，单位为秒（s）
			this.tcpResp = HexUtils.bytesToInt4(value);
			break;
		case 0x00000003:// TCP 消息重传次数
			this.tcpRettimes = HexUtils.bytesToInt4(value);
			break;
		case 0x00000004:// UDP 消息应答超时时间，单位为秒（s）
			this.udpResp = HexUtils.bytesToInt4(value);
			break;
		case 0x00000005:// UDP 消息重传次数
			this.udpRettimes = HexUtils.bytesToInt4(value);
			break;
		case 0x00000006:// SMS 消息应答超时时间，单位为秒（s）
			this.smsResp = HexUtils.bytesToInt4(value);
			break;
		case 0x00000007:// SMS 消息重传次数
			this.smsRettimes = HexUtils.bytesToInt4(value);
			break;
		case 0x00000010:// 主服务器APN
			this.mainServerAPN = new String(value);
			break;
		case 0x00000011:// 主服务器无线通信拨号用户名
			this.mainServerUser = new String(value);
			break;
		case 0x00000012:// 主服务器无线通信拨号密码
			this.mainServedrPwd = new String(value);
			break;
		case 0x00000013:// 主服务器地址,IP或域名
			this.mainServerIpOrDomain = new String(value);
			break;
		case 0x00000014:// 备份服务器APN
			this.backServerAPN = new String(value);
			break;
		case 0x00000015:// 备份服务器无线通信拨号用户名
			this.backServerUser = new String(value);
			break;
		case 0x00000016:// 备份服务器无线通信拨号密码
			this.backSerqverPwd = new String(value);
			break;
		case 0x00000017:// 备份服务器地址,IP或域名
			this.backServerIpOrDomain = new String(value);
			break;
		case 0x00000018:// 服务器TCP端口
			this.serverTcpPort = HexUtils.bytesToInt4(value);
			break;
		case 0x00000019:// 服务器UDP 端口
			this.serverUdpPort = HexUtils.bytesToInt4(value);
			break;
		case 0x00000020:// 位置汇报策略
			this.locationType = HexUtils.bytesToInt4(value);
			break;
		case 0x00000021:// 位置汇报方案，
			this.positionRep = HexUtils.bytesToInt4(value);
			break;
		case 0x00000022:// 驾驶员未登录汇报时间间隔，单位为秒（s），>0
			this.driverUnRepTime = HexUtils.bytesToInt4(value);
			break;

		case 0x00000027:// 休眠时汇报时间间隔，单位为秒（s），>0
			this.sleepInterval = HexUtils.bytesToInt4(value);
			break;
		case 0x00000028:// 紧急报警时汇报时间间隔，单位为秒（s），>0
			this.alarmInterval = HexUtils.bytesToInt4(value);
			break;
		case 0x00000029:// 缺省时间汇报间隔，单位为秒>0
			this.defaultTimeInterval = HexUtils.bytesToInt4(value);
			break;
		case 0x0000002C:// 缺省距离汇报间隔，单位为米>0
			this.defaultMileInterval = HexUtils.bytesToInt4(value);
			break;
		case 0x0000002D:// 驾驶员未登录汇报距离间隔，单位为米（m），>0
			this.driverUnRepDistance = HexUtils.bytesToInt4(value);
			break;
		case 0x0000002E:// 休眠时汇报距离间隔，单位为米（m），>0
			this.dormancyRepDis = HexUtils.bytesToInt4(value);
			break;
		case 0x0000002F:// 紧急报警时汇报距离间隔，单位为米（m），>0
			this.alarmRepDisInt = HexUtils.bytesToInt4(value);
			break;
		case 0x00000030:// 拐点补传角度，<180
			this.corner = HexUtils.bytesToInt4(value);
			break;
		case 0x00000040:// 监控平台电话号码
			this.monitorPlatformMobile = new String(value);
			break;
		case 0x00000041:// 复位电话号码
			this.resetMobile = new String(value);
			break;
		case 0x00000042:// 恢复出厂设置电话号码
			this.rescoverySettingMobile = new String(value);
			break;
		case 0x00000043:// 监控平台SMS电话号码
			this.mobilePlatformSmsMobile = new String(value);
			break;
		case 0x00000044:// 接收终端SMS文本报警号码
			this.deviceSmsMobile = new String(value);
			break;
		case 0x00000045:// 终端电话接听策略
			this.deviceMobileStrategy = HexUtils.bytesToInt4(value);
			break;
		case 0x00000046:// 每次最长通话时间(秒)
			this.everyCallMaxTimeLen = HexUtils.bytesToInt4(value);
			break;
		case 0x00000047:// 当月最长通话时间，单位为秒（s），0 为不允许通话，0xFFFFFFFF 为不限制
			this.monthCallMaxTimeLen = HexUtils.bytesToInt4(value);
			break;
		case 0x00000048:// 监听电话号码
			this.monitorPhoneNumber = new String(value);
			break;
		case 0x00000049:// 监管平台特权短信号码
			this.monitorPrivilegeSmsNumber = new String(value);
			break;
		case 0x00000050:// 报警屏蔽字
			this.alarmMaskWord = new String(value);
			break;
		case 0x00000051:// 报警发送文本SMS开关
			this.alarmSendSmsSwitch = HexUtils.bytesToInt4(value);
			break;
		case 0x00000052:// 报警拍摄开关
			this.alarmShootSwitch = HexUtils.bytesToInt4(value);
			break;
		case 0x00000053:// 报警拍摄存储标志
			this.alarmShootSaveFlag = HexUtils.bytesToInt4(value);
			break;
		case 0x00000054:// 关键标志，与位置信息汇报消息中的报警标志相对应，相应位为1 则对相应报警为关键报警
			this.keyMark = HexUtils.bytesToInt4(value);
			break;
		case 0x00000055:// 最高速度KM/H
			this.maxSpeed = HexUtils.bytesToInt4(value);
			break;
		case 0x00000056:// 超速持续时间，单位为秒
			this.overSpeedContinueTime = HexUtils.bytesToInt4(value);
			break;
		case 0x00000057:// 连续驾驶时间门限，单位为秒
			this.driveContinueTime = HexUtils.bytesToInt4(value);
			break;
		case 0x00000058:// 当天累计驾驶时间门限
			this.driveTime = HexUtils.bytesToInt4(value);
			break;
		case 0x00000059:// 最小休息时间，单位为秒
			this.minRestTime = HexUtils.bytesToInt4(value);
			break;
		case 0x0000005A:// 最长停车时间，单位为秒
			this.maxStopTime = HexUtils.bytesToInt4(value);
			break;
		case 0x00000070:// 图像/视频质量，1-10，1 最好
			this.imageOrAV = HexUtils.bytesToInt4(value);
			break;
		case 0x00000071:// 亮度，0-255
			this.brightness = HexUtils.bytesToInt4(value);
			break;
		case 0x00000072:// 对比度，0-127
			this.contrast = HexUtils.bytesToInt4(value);
			break;
		case 0x00000073:// 饱和度，0-127
			this.saturation = HexUtils.bytesToInt4(value);
			break;
		case 0x00000074:// 色度，0-255
			this.chroma = HexUtils.bytesToInt4(value);
			break;
		case 0x00000080:// 车辆里程表读数，1/10km
			this.mileage = HexUtils.bytesToInt4(value) * 0.1;
			break;
		case 0x00000081:// 车辆所在的省域ID
			this.provinceId = HexUtils.bytesToShort(value) & 0xFFFF;
			break;
		case 0x00000082:// 车辆所在的市域ID
			this.cityId = HexUtils.bytesToShort(value) & 0xFFFF;
			break;
		case 0x00000083:// 公安交通管理部门颁发的机动车号牌
			try {
				this.plateNumber = new String(value, "GBK");
			} catch (UnsupportedEncodingException e) {
				Log.getLogger(this.getClass()).error(e.getMessage(), e);
			}
			break;
		case 0x00000084:// 车牌颜色
			this.plateColor = value[0] & 0xFF;
			break;
		default:
			break;
		}

		byte[] otherParamByte = HexUtils.copyOfRange(paramsByte, 5 + length,
				paramsByte.length);
		analyst(otherParamByte);
	}

	@Override
	public String toString() {
		return "DeviceParams [deviceMobile=" + deviceMobile
				+ ", heartInterval=" + heartInterval + ", mainServerAPN="
				+ mainServerAPN + ", mainServerUser=" + mainServerUser
				+ ", mainServedrPwd=" + mainServedrPwd
				+ ", mainServerIpOrDomain=" + mainServerIpOrDomain
				+ ", backServerAPN=" + backServerAPN + ", backServerUser="
				+ backServerUser + ", backSerqverPwd=" + backSerqverPwd
				+ ", backServerIpOrDomain=" + backServerIpOrDomain
				+ ", serverTcpPort=" + serverTcpPort + ", sleepInterval="
				+ sleepInterval + ", alarmInterval=" + alarmInterval
				+ ", defaultTimeInterval=" + defaultTimeInterval
				+ ", defaultMileInterval=" + defaultMileInterval + ", corner="
				+ corner + ", monitorPlatformMobile=" + monitorPlatformMobile
				+ ", resetMobile=" + resetMobile + ", rescoverySettingMobile="
				+ rescoverySettingMobile + ", mobilePlatformSmsMobile="
				+ mobilePlatformSmsMobile + ", deviceSmsMobile="
				+ deviceSmsMobile + ", deviceMobileStrategy="
				+ deviceMobileStrategy + ", everyCallMaxTimeLen="
				+ everyCallMaxTimeLen + ", monitorPhoneNumber="
				+ monitorPhoneNumber + ", monitorPrivilegeSmsNumber="
				+ monitorPrivilegeSmsNumber + ", alarmMaskWord="
				+ alarmMaskWord + ", alarmSendSmsSwitch=" + alarmSendSmsSwitch
				+ ", alarmShootSwitch=" + alarmShootSwitch
				+ ", alarmShootSaveFlag=" + alarmShootSaveFlag + ", maxSpeed="
				+ maxSpeed + ", overSpeedContinueTime=" + overSpeedContinueTime
				+ ", driveContinueTime=" + driveContinueTime + ", driveTime="
				+ driveTime + ", minRestTime=" + minRestTime + ", maxStopTime="
				+ maxStopTime + ", mileage=" + mileage + ", provinceId="
				+ provinceId + ", cityId=" + cityId + ", plateNumber="
				+ plateNumber + ", plateColor=" + plateColor
				+ ", locationType=" + locationType + ", tcpResp=" + tcpResp
				+ ", tcpRettimes=" + tcpRettimes + ", udpResp=" + udpResp
				+ ", udpRettimes=" + udpRettimes + ", smsResp=" + smsResp
				+ ", smsRettimes=" + smsRettimes + ", serverUdpPort="
				+ serverUdpPort + ", positionRep=" + positionRep
				+ ", driverUnRepTime=" + driverUnRepTime
				+ ", driverUnRepDistance=" + driverUnRepDistance
				+ ", dormancyRepDis=" + dormancyRepDis + ", alarmRepDisInt="
				+ alarmRepDisInt + ", monthCallMaxTimeLen="
				+ monthCallMaxTimeLen + ", keyMark=" + keyMark + ", imageOrAV="
				+ imageOrAV + ", brightness=" + brightness + ", contrast="
				+ contrast + ", saturation=" + saturation + ", chroma="
				+ chroma + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	/**
	 * @return the deviceMobile
	 */
	public String getDeviceMobile() {
		return deviceMobile;
	}

	/**
	 * @param deviceMobile
	 *            the deviceMobile to set
	 */
	public void setDeviceMobile(String deviceMobile) {
		this.deviceMobile = deviceMobile;
	}

	public int getLocationType() {
		return locationType;
	}

	public void setLocationType(int locationType) {
		this.locationType = locationType;
	}

	/**
	 * @return the heartInterval
	 */
	public int getHeartInterval() {
		return heartInterval;
	}

	/**
	 * @param heartInterval
	 *            the heartInterval to set
	 */
	public void setHeartInterval(int heartInterval) {
		this.heartInterval = heartInterval;
	}

	/**
	 * @return the mainServerAPN
	 */
	public String getMainServerAPN() {
		return mainServerAPN;
	}

	/**
	 * @param mainServerAPN
	 *            the mainServerAPN to set
	 */
	public void setMainServerAPN(String mainServerAPN) {
		this.mainServerAPN = mainServerAPN;
	}

	/**
	 * @return the mainServerUser
	 */
	public String getMainServerUser() {
		return mainServerUser;
	}

	/**
	 * @param mainServerUser
	 *            the mainServerUser to set
	 */
	public void setMainServerUser(String mainServerUser) {
		this.mainServerUser = mainServerUser;
	}

	/**
	 * @return the mainServedrPwd
	 */
	public String getMainServedrPwd() {
		return mainServedrPwd;
	}

	/**
	 * @param mainServedrPwd
	 *            the mainServedrPwd to set
	 */
	public void setMainServedrPwd(String mainServedrPwd) {
		this.mainServedrPwd = mainServedrPwd;
	}

	/**
	 * @return the mainServerIpOrDomain
	 */
	public String getMainServerIpOrDomain() {
		return mainServerIpOrDomain;
	}

	/**
	 * @param mainServerIpOrDomain
	 *            the mainServerIpOrDomain to set
	 */
	public void setMainServerIpOrDomain(String mainServerIpOrDomain) {
		this.mainServerIpOrDomain = mainServerIpOrDomain;
	}

	/**
	 * @return the backServerAPN
	 */
	public String getBackServerAPN() {
		return backServerAPN;
	}

	/**
	 * @param backServerAPN
	 *            the backServerAPN to set
	 */
	public void setBackServerAPN(String backServerAPN) {
		this.backServerAPN = backServerAPN;
	}

	/**
	 * @return the backServerUser
	 */
	public String getBackServerUser() {
		return backServerUser;
	}

	/**
	 * @param backServerUser
	 *            the backServerUser to set
	 */
	public void setBackServerUser(String backServerUser) {
		this.backServerUser = backServerUser;
	}

	/**
	 * @return the backSerqverPwd
	 */
	public String getBackSerqverPwd() {
		return backSerqverPwd;
	}

	/**
	 * @param backSerqverPwd
	 *            the backSerqverPwd to set
	 */
	public void setBackSerqverPwd(String backSerqverPwd) {
		this.backSerqverPwd = backSerqverPwd;
	}

	/**
	 * @return the backServerIpOrDomain
	 */
	public String getBackServerIpOrDomain() {
		return backServerIpOrDomain;
	}

	/**
	 * @param backServerIpOrDomain
	 *            the backServerIpOrDomain to set
	 */
	public void setBackServerIpOrDomain(String backServerIpOrDomain) {
		this.backServerIpOrDomain = backServerIpOrDomain;
	}

	/**
	 * @return the serverTcpPort
	 */
	public int getServerTcpPort() {
		return serverTcpPort;
	}

	/**
	 * @param serverTcpPort
	 *            the serverTcpPort to set
	 */
	public void setServerTcpPort(int serverTcpPort) {
		this.serverTcpPort = serverTcpPort;
	}

	/**
	 * @return the sleepInterval
	 */
	public int getSleepInterval() {
		return sleepInterval;
	}

	/**
	 * @param sleepInterval
	 *            the sleepInterval to set
	 */
	public void setSleepInterval(int sleepInterval) {
		this.sleepInterval = sleepInterval;
	}

	/**
	 * @return the alarmInterval
	 */
	public int getAlarmInterval() {
		return alarmInterval;
	}

	/**
	 * @param alarmInterval
	 *            the alarmInterval to set
	 */
	public void setAlarmInterval(int alarmInterval) {
		this.alarmInterval = alarmInterval;
	}

	/**
	 * @return the defaultTimeInterval
	 */
	public int getDefaultTimeInterval() {
		return defaultTimeInterval;
	}

	/**
	 * @param defaultTimeInterval
	 *            the defaultTimeInterval to set
	 */
	public void setDefaultTimeInterval(int defaultTimeInterval) {
		this.defaultTimeInterval = defaultTimeInterval;
	}

	/**
	 * @return the defaultMileInterval
	 */
	public int getDefaultMileInterval() {
		return defaultMileInterval;
	}

	/**
	 * @param defaultMileInterval
	 *            the defaultMileInterval to set
	 */
	public void setDefaultMileInterval(int defaultMileInterval) {
		this.defaultMileInterval = defaultMileInterval;
	}

	/**
	 * @return the corner
	 */
	public int getCorner() {
		return corner;
	}

	/**
	 * @param corner
	 *            the corner to set
	 */
	public void setCorner(int corner) {
		this.corner = corner;
	}

	/**
	 * @return the monitorPlatformMobile
	 */
	public String getMonitorPlatformMobile() {
		return monitorPlatformMobile;
	}

	/**
	 * @param monitorPlatformMobile
	 *            the monitorPlatformMobile to set
	 */
	public void setMonitorPlatformMobile(String monitorPlatformMobile) {
		this.monitorPlatformMobile = monitorPlatformMobile;
	}

	/**
	 * @return the resetMobile
	 */
	public String getResetMobile() {
		return resetMobile;
	}

	/**
	 * @param resetMobile
	 *            the resetMobile to set
	 */
	public void setResetMobile(String resetMobile) {
		this.resetMobile = resetMobile;
	}

	/**
	 * @return the rescoverySettingMobile
	 */
	public String getRescoverySettingMobile() {
		return rescoverySettingMobile;
	}

	/**
	 * @param rescoverySettingMobile
	 *            the rescoverySettingMobile to set
	 */
	public void setRescoverySettingMobile(String rescoverySettingMobile) {
		this.rescoverySettingMobile = rescoverySettingMobile;
	}

	/**
	 * @return the mobilePlatformSmsMobile
	 */
	public String getMobilePlatformSmsMobile() {
		return mobilePlatformSmsMobile;
	}

	/**
	 * @param mobilePlatformSmsMobile
	 *            the mobilePlatformSmsMobile to set
	 */
	public void setMobilePlatformSmsMobile(String mobilePlatformSmsMobile) {
		this.mobilePlatformSmsMobile = mobilePlatformSmsMobile;
	}

	/**
	 * @return the deviceSmsMobile
	 */
	public String getDeviceSmsMobile() {
		return deviceSmsMobile;
	}

	/**
	 * @param deviceSmsMobile
	 *            the deviceSmsMobile to set
	 */
	public void setDeviceSmsMobile(String deviceSmsMobile) {
		this.deviceSmsMobile = deviceSmsMobile;
	}

	/**
	 * @return the deviceMobileStrategy
	 */
	public int getDeviceMobileStrategy() {
		return deviceMobileStrategy;
	}

	/**
	 * @param deviceMobileStrategy
	 *            the deviceMobileStrategy to set
	 */
	public void setDeviceMobileStrategy(int deviceMobileStrategy) {
		this.deviceMobileStrategy = deviceMobileStrategy;
	}

	/**
	 * @return the everyCallMaxTimeLen
	 */
	public int getEveryCallMaxTimeLen() {
		return everyCallMaxTimeLen;
	}

	/**
	 * @param everyCallMaxTimeLen
	 *            the everyCallMaxTimeLen to set
	 */
	public void setEveryCallMaxTimeLen(int everyCallMaxTimeLen) {
		this.everyCallMaxTimeLen = everyCallMaxTimeLen;
	}

	/**
	 * @return the monitorPhoneNumber
	 */
	public String getMonitorPhoneNumber() {
		return monitorPhoneNumber;
	}

	/**
	 * @param monitorPhoneNumber
	 *            the monitorPhoneNumber to set
	 */
	public void setMonitorPhoneNumber(String monitorPhoneNumber) {
		this.monitorPhoneNumber = monitorPhoneNumber;
	}

	/**
	 * @return the monitorPrivilegeSmsNumber
	 */
	public String getMonitorPrivilegeSmsNumber() {
		return monitorPrivilegeSmsNumber;
	}

	/**
	 * @param monitorPrivilegeSmsNumber
	 *            the monitorPrivilegeSmsNumber to set
	 */
	public void setMonitorPrivilegeSmsNumber(String monitorPrivilegeSmsNumber) {
		this.monitorPrivilegeSmsNumber = monitorPrivilegeSmsNumber;
	}

	/**
	 * @return the alarmMaskWord
	 */
	public String getAlarmMaskWord() {
		return alarmMaskWord;
	}

	/**
	 * @param alarmMaskWord
	 *            the alarmMaskWord to set
	 */
	public void setAlarmMaskWord(String alarmMaskWord) {
		this.alarmMaskWord = alarmMaskWord;
	}

	/**
	 * @return the alarmSendSmsSwitch
	 */
	public int getAlarmSendSmsSwitch() {
		return alarmSendSmsSwitch;
	}

	/**
	 * @param alarmSendSmsSwitch
	 *            the alarmSendSmsSwitch to set
	 */
	public void setAlarmSendSmsSwitch(int alarmSendSmsSwitch) {
		this.alarmSendSmsSwitch = alarmSendSmsSwitch;
	}

	/**
	 * @return the alarmShootSwitch
	 */
	public int getAlarmShootSwitch() {
		return alarmShootSwitch;
	}

	/**
	 * @param alarmShootSwitch
	 *            the alarmShootSwitch to set
	 */
	public void setAlarmShootSwitch(int alarmShootSwitch) {
		this.alarmShootSwitch = alarmShootSwitch;
	}

	/**
	 * @return the alarmShootSaveFlag
	 */
	public int getAlarmShootSaveFlag() {
		return alarmShootSaveFlag;
	}

	/**
	 * @param alarmShootSaveFlag
	 *            the alarmShootSaveFlag to set
	 */
	public void setAlarmShootSaveFlag(int alarmShootSaveFlag) {
		this.alarmShootSaveFlag = alarmShootSaveFlag;
	}

	/**
	 * @return the maxSpeed
	 */
	public int getMaxSpeed() {
		return maxSpeed;
	}

	/**
	 * @param maxSpeed
	 *            the maxSpeed to set
	 */
	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	/**
	 * @return the overSpeedContinueTime
	 */
	public int getOverSpeedContinueTime() {
		return overSpeedContinueTime;
	}

	/**
	 * @param overSpeedContinueTime
	 *            the overSpeedContinueTime to set
	 */
	public void setOverSpeedContinueTime(int overSpeedContinueTime) {
		this.overSpeedContinueTime = overSpeedContinueTime;
	}

	/**
	 * @return the driveContinueTime
	 */
	public int getDriveContinueTime() {
		return driveContinueTime;
	}

	/**
	 * @param driveContinueTime
	 *            the driveContinueTime to set
	 */
	public void setDriveContinueTime(int driveContinueTime) {
		this.driveContinueTime = driveContinueTime;
	}

	/**
	 * @return the driveTime
	 */
	public int getDriveTime() {
		return driveTime;
	}

	/**
	 * @param driveTime
	 *            the driveTime to set
	 */
	public void setDriveTime(int driveTime) {
		this.driveTime = driveTime;
	}

	/**
	 * @return the minRestTime
	 */
	public int getMinRestTime() {
		return minRestTime;
	}

	/**
	 * @param minRestTime
	 *            the minRestTime to set
	 */
	public void setMinRestTime(int minRestTime) {
		this.minRestTime = minRestTime;
	}

	/**
	 * @return the maxStopTime
	 */
	public int getMaxStopTime() {
		return maxStopTime;
	}

	/**
	 * @param maxStopTime
	 *            the maxStopTime to set
	 */
	public void setMaxStopTime(int maxStopTime) {
		this.maxStopTime = maxStopTime;
	}

	/**
	 * @return the mileage
	 */
	public double getMileage() {
		return mileage;
	}

	/**
	 * @param mileage
	 *            the mileage to set
	 */
	public void setMileage(double mileage) {
		this.mileage = mileage;
	}

	/**
	 * @return the provinceId
	 */
	public int getProvinceId() {
		return provinceId;
	}

	/**
	 * @param provinceId
	 *            the provinceId to set
	 */
	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	/**
	 * @return the cityId
	 */
	public int getCityId() {
		return cityId;
	}

	/**
	 * @param cityId
	 *            the cityId to set
	 */
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	/**
	 * @return the plateNumber
	 */
	public String getPlateNumber() {
		return plateNumber;
	}

	/**
	 * @param plateNumber
	 *            the plateNumber to set
	 */
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	/**
	 * @return the plateColor
	 */
	public int getPlateColor() {
		return plateColor;
	}

	/**
	 * @param plateColor
	 *            the plateColor to set
	 */
	public void setPlateColor(int plateColor) {
		this.plateColor = plateColor;
	}

	/**
	 * 
	 * @return the getTcpResp
	 */

	public int getTcpResp() {
		return tcpResp;
	}

	/**
	 * 
	 * @param tcpResp
	 *            the tcpResp to set
	 */
	public void setTcpResp(int tcpResp) {
		this.tcpResp = tcpResp;
	}

	/**
	 * 
	 * @return tcpRettimes
	 */

	public int getTcpRettimes() {
		return tcpRettimes;
	}

	/**
	 * 
	 * @param tcpRettimes
	 *            the tcpRettimes to set
	 */

	public void setTcpRettimes(int tcpRettimes) {
		this.tcpRettimes = tcpRettimes;
	}

	/**
	 * 
	 * @return UdpResp
	 */

	public int getUdpResp() {
		return udpResp;
	}

	/**
	 * 
	 * @param udpResp
	 *            the udpResp set
	 */

	public void setUdpResp(int udpResp) {
		this.udpResp = udpResp;
	}

	public int getUdpRettimes() {
		return udpRettimes;
	}

	public void setUdpRettimes(int udpRettimes) {
		this.udpRettimes = udpRettimes;
	}

	public int getSmsResp() {
		return smsResp;
	}

	public void setSmsResp(int smsResp) {
		this.smsResp = smsResp;
	}

	public int getSmsRettimes() {
		return smsRettimes;
	}

	public void setSmsRettimes(int smsRettimes) {
		this.smsRettimes = smsRettimes;
	}

	public int getServerUdpPort() {
		return serverUdpPort;
	}

	public void setServerUdpPort(int serverUdpPort) {
		this.serverUdpPort = serverUdpPort;
	}

	public int getPositionRep() {
		return positionRep;
	}

	public void setPositionRep(int positionRep) {
		this.positionRep = positionRep;
	}

	public int getDriverUnRepTime() {
		return driverUnRepTime;
	}

	public void setDriverUnRepTime(int driverUnRepTime) {
		this.driverUnRepTime = driverUnRepTime;
	}

	public int getDriverUnRepDistance() {
		return driverUnRepDistance;
	}

	public void setDriverUnRepDistance(int driverUnRepDistance) {
		this.driverUnRepDistance = driverUnRepDistance;
	}

	public int getDormancyRepDis() {
		return dormancyRepDis;
	}

	public void setDormancyRepDis(int dormancyRepDis) {
		this.dormancyRepDis = dormancyRepDis;
	}

	public int getAlarmRepDisInt() {
		return alarmRepDisInt;
	}

	public void setAlarmRepDisInt(int alarmRepDisInt) {
		this.alarmRepDisInt = alarmRepDisInt;
	}

	public int getMonthCallMaxTimeLen() {
		return monthCallMaxTimeLen;
	}

	public void setMonthCallMaxTimeLen(int monthCallMaxTimeLen) {
		this.monthCallMaxTimeLen = monthCallMaxTimeLen;
	}

	public int getKeyMark() {
		return keyMark;
	}

	public void setKeyMark(int keyMark) {
		this.keyMark = keyMark;
	}

	public int getImageOrAV() {
		return imageOrAV;
	}

	public void setImageOrAV(int imageOrAV) {
		this.imageOrAV = imageOrAV;
	}

	public int getBrightness() {
		return brightness;
	}

	public void setBrightness(int brightness) {
		this.brightness = brightness;
	}

	public int getContrast() {
		return contrast;
	}

	public void setContrast(int contrast) {
		this.contrast = contrast;
	}

	public int getSaturation() {
		return saturation;
	}

	public void setSaturation(int saturation) {
		this.saturation = saturation;
	}

	public int getChroma() {
		return chroma;
	}

	public void setChroma(int chroma) {
		this.chroma = chroma;
	}

}
