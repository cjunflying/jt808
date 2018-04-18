package com.mortals.iot.handler.protocol808.collect.protocol.entity.client;

import java.io.Serializable;

import com.mortals.iot.handler.protocol808.collect.commons.Log;
import com.mortals.iot.protocol.jt808.util.DataHelper;
import com.mortals.iot.protocol.jt808.util.HexUtils;

/**
 * @MessageID 消息ID：0x0107
 * @author 
 * @package com.mortals.iot.handler.protocol808.collect.comm.protocol.message.client
 * @copyright iot
 * @date:2017-4-16 上午9:22:12
 */
public class DeviceValue implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 终端手机号 01 34 80 77 07 42
	private String deviceMobile;
	// 终端类型
	private short deviceType;
	// 制造商ID
	private String manufacturerID;
	// 终端型号
	private String terminalType;
	// 终端ID
	private String deviceID;
	// 终端SIM 卡ICCID
	private String SIM;
	// 终端硬件版本号长度
	private int hardVerlength;
	// 终端硬件版本号
	private String hardVerion;
	// 终端固件版本号长度
	private int firmwareVerLength;
	// 终端固件版本号
	private String firmwareVer;
	// GNSS
	private int GNSS;
	// 通信模块属性
	private int commModProperties;

	public DeviceValue() {

	}

	/**
	 * decoder zhe DeviceValue
	 * 
	 * @param moble
	 * @param body
	 */
	public DeviceValue(String moble, byte[] body) {

		Log.getLogger(this.getClass()).info(HexUtils.bytesToHexString(body));
		if (body == null ||body.length<46) {
			return;
		}
		try{
			this.deviceMobile=moble;
			this.deviceType=DataHelper.parseUint16(body, 0);
			this.manufacturerID=DataHelper.bytesTo6HexString(body, 2, 5);
			this.terminalType=DataHelper.bytesTo6HexString(body, 7, 20);
			this.deviceID=DataHelper.bytesTo6HexString(body, 27, 7);
			this.SIM=DataHelper.bytesTo6HexString(body, 34, 10);
			this.hardVerlength=DataHelper.parseUint8(body, 44);
			this.hardVerion=DataHelper.bytesTo6HexString(body, 45, hardVerlength);
			this.firmwareVerLength=DataHelper.parseUint8(body, 45+hardVerlength);
			this.firmwareVer=DataHelper.bytesTo6HexString(body, 45+hardVerlength, firmwareVerLength);
			this.GNSS=DataHelper.parseUint8(body, 45+hardVerlength+firmwareVerLength);
			this.commModProperties=DataHelper.parseUint8(body, 46+hardVerlength+firmwareVerLength);
		}catch (Exception e) {
			Log.getLogger(this.getClass()).error("DeviceValue Moble:"+moble+" body:"+HexUtils.bytesToHexString(body));
		}
		

	}

	public String getDeviceMobile() {
		return deviceMobile;
	}

	public void setDeviceMobile(String deviceMobile) {
		this.deviceMobile = deviceMobile;
	}

	public short getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(short deviceType) {
		this.deviceType = deviceType;
	}

	public String getManufacturerID() {
		return manufacturerID;
	}

	public void setManufacturerID(String manufacturerID) {
		this.manufacturerID = manufacturerID;
	}

	public String getTerminalType() {
		return terminalType;
	}

	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public String getSIM() {
		return SIM;
	}

	public void setSIM(String sIM) {
		SIM = sIM;
	}

	public int getHardVerlength() {
		return hardVerlength;
	}

	public void setHardVerlength(int hardVerlength) {
		this.hardVerlength = hardVerlength;
	}

	public String getHardVerion() {
		return hardVerion;
	}

	public void setHardVerion(String hardVerion) {
		this.hardVerion = hardVerion;
	}

	public int getFirmwareVerLength() {
		return firmwareVerLength;
	}

	public void setFirmwareVerLength(int firmwareVerLength) {
		this.firmwareVerLength = firmwareVerLength;
	}

	public String getFirmwareVer() {
		return firmwareVer;
	}

	public void setFirmwareVer(String firmwareVer) {
		this.firmwareVer = firmwareVer;
	}

	public int getGNSS() {
		return GNSS;
	}

	public void setGNSS(int gNSS) {
		GNSS = gNSS;
	}

	public int getCommModProperties() {
		return commModProperties;
	}

	public void setCommModProperties(int commModProperties) {
		this.commModProperties = commModProperties;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + GNSS;
		result = prime * result + ((SIM == null) ? 0 : SIM.hashCode());
		result = prime * result + commModProperties;
		result = prime * result
				+ ((deviceID == null) ? 0 : deviceID.hashCode());
		result = prime * result
				+ ((deviceMobile == null) ? 0 : deviceMobile.hashCode());
		result = prime * result + deviceType;
		result = prime * result
				+ ((firmwareVer == null) ? 0 : firmwareVer.hashCode());
		result = prime * result + firmwareVerLength;
		result = prime * result
				+ ((hardVerion == null) ? 0 : hardVerion.hashCode());
		result = prime * result + hardVerlength;
		result = prime * result
				+ ((manufacturerID == null) ? 0 : manufacturerID.hashCode());
		result = prime * result
				+ ((terminalType == null) ? 0 : terminalType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeviceValue other = (DeviceValue) obj;
		if (GNSS != other.GNSS)
			return false;
		if (SIM == null) {
			if (other.SIM != null)
				return false;
		} else if (!SIM.equals(other.SIM))
			return false;
		if (commModProperties != other.commModProperties)
			return false;
		if (deviceID == null) {
			if (other.deviceID != null)
				return false;
		} else if (!deviceID.equals(other.deviceID))
			return false;
		if (deviceMobile == null) {
			if (other.deviceMobile != null)
				return false;
		} else if (!deviceMobile.equals(other.deviceMobile))
			return false;
		if (deviceType != other.deviceType)
			return false;
		if (firmwareVer == null) {
			if (other.firmwareVer != null)
				return false;
		} else if (!firmwareVer.equals(other.firmwareVer))
			return false;
		if (firmwareVerLength != other.firmwareVerLength)
			return false;
		if (hardVerion == null) {
			if (other.hardVerion != null)
				return false;
		} else if (!hardVerion.equals(other.hardVerion))
			return false;
		if (hardVerlength != other.hardVerlength)
			return false;
		if (manufacturerID == null) {
			if (other.manufacturerID != null)
				return false;
		} else if (!manufacturerID.equals(other.manufacturerID))
			return false;
		if (terminalType == null) {
			if (other.terminalType != null)
				return false;
		} else if (!terminalType.equals(other.terminalType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DeviceValue [deviceMobile=" + deviceMobile + ", deviceType="
				+ deviceType + ", manufacturerID=" + manufacturerID
				+ ", terminalType=" + terminalType + ", deviceID=" + deviceID
				+ ", SIM=" + SIM + ", hardVerlength=" + hardVerlength
				+ ", hardVerion=" + hardVerion + ", firmwareVerLength="
				+ firmwareVerLength + ", firmwareVer=" + firmwareVer
				+ ", GNSS=" + GNSS + ", commModProperties=" + commModProperties
				+ "]";
	}

}
