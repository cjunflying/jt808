package com.mortals.iot.handler.protocol808.collect.protocol.entity.client;

/**
 * @ClassName: DeviceParamSetting
 * @Description: 设置设备参数实体
 * @Company iot
 * @author 
 * @version  1.0, 2017-3-29 上午10:03:40  
 */
public class DeviceParamSetting {
	
	// ID: 终端心跳发送间隔
	public static final int ID_HEART_INTERVAL  = 0x00000001;
	// ID: 主服务器地址,IP或域名
	public static final int ID_MAINSERVER_ADDR = 0x00000013;
	// ID：备份服务器地址,IP或域名	
	public static final int ID_BACKSERVER_ADDR = 0x00000017;
	// ID: 服务器TCP端口
	public static final int ID_SERVER_TCP_PORT = 0x00000018;
	//位置汇报策略 
	public static final int locationType = 0x00000020;
	// ID: 休眠时汇报时间间隔
	public static final int ID_SLEEP_INTERVAL   = 0x00000027;
	// ID: 紧急报警时汇报时间间隔
	public static final int ID_ALARM_INTERVAL  = 0x00000028;
	//ID: 缺省时间汇报间隔，单位为秒>0
	public static final int ID_DEFAULT_INTERVAL  =0x00000029;
	// ID: 监控平台电话号码
	public static final int ID_PLATFORM_MOBILE_INTERVAL = 0x00000040;
	// ID: 复位电话号码，可采用此电话号码拨打终端电话让终端复位
	public static final int ID_RESET_MOBILE   = 0x00000041;
	// ID: 恢复出厂设置电话号码，可采用此电话号码拨打终端电话让终端恢复出厂设置
	public static final int ID_FACTORY_SET_MOBILE = 0x00000042;
	// ID: 监听电话号码
	public static final int ID_MONITOR_MOBILE = 0x00000048;
	// ID: 最高速度
	public static final int ID_MAX_SPEED      = 0x00000055;
	// ID: 超速持续时间
	public static final int ID_OVERSPEED_TIME = 0x00000056;
	// ID: 连续驾驶时间门限，超时时间
	public static final int ID_DRIVE_TIME_VAL = 0x00000057;
	// ID: 车辆里程表读数
	public static final int ID_MILEAGE_VAL    = 0x00000080;
	// ID: 车辆所在的省域ID
	public static final int ID_PROVINCE       = 0x00000081;
	// ID: 车辆所在的市域ID
	public static final int ID_CITY           = 0x00000082;
	// ID: 公安交通管理部门颁发的机动车号牌
	public static final int ID_PLATE_NUMBER   = 0x00000083;
	// ID: 车牌颜色
	public static final int ID_PLATE_COLOR    = 0x00000084;
	
	public DeviceParamSetting(int id, Object value){
		this.id = id;
		this.value = value;
	}

	private int id;
	
	private Object value;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}

}
