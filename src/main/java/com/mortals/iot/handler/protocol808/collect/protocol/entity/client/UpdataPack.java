package com.mortals.iot.handler.protocol808.collect.protocol.entity.client;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import com.mortals.iot.handler.protocol808.collect.commons.Log;
import com.mortals.iot.protocol.jt808.util.DataHelper;

/**
 * 下发终端升级包 消息ID：0x8108。
 * 
 * @author 
 * @package com.mortals.iot.handler.protocol808.collect.comm.protocol.message.client
 * @copyright iot
 * @date:2017-4-17 下午4:23:57
 */
public class UpdataPack {
	// 升级类型 0：终端，12：道路运输证IC 卡读卡器，52：iot
	// 卫星定位模块
	private int updataValue;
	// 制造商编号
	private String ManufacturersID;
	// 版本号长度
	private int verLength;
	// 版本号
	private String ver;
	// 升级数据包长度
	private int updataLength;
	// 升级数据包
	private byte[] data;

	/**
	 * 
	 * @param out
	 * @return
	 * @throws IOException
	 */
	public OutputStream encoder(OutputStream out) throws IOException {
		int size = 0;
		size += DataHelper.getbyte(out, updataValue);
		out.write(DataHelper.string26Bcd(ManufacturersID));
		size += 5;
		size += DataHelper.getbyte(out, verLength);
		out.write(DataHelper.string26Bcd(ver));
		size += verLength;
		size += DataHelper.getInt(out, updataLength);
		out.write(data);
		if (size < 7 + verLength + updataLength) {
			Log.getLogger(this.getClass()).equals(
					"encoder is error:" + this.toString());
			return null;
		}

		return out;
	}

	public int getUpdataValue() {
		return updataValue;
	}

	public void setUpdataValue(int updataValue) {
		this.updataValue = updataValue;
	}

	public String getManufacturersID() {
		return ManufacturersID;
	}

	public void setManufacturersID(String manufacturersID) {
		ManufacturersID = manufacturersID;
	}

	public int getVerLength() {
		return verLength;
	}

	public void setVerLength(int verLength) {
		this.verLength = verLength;
	}

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

	public int getUpdataLength() {
		return updataLength;
	}

	public void setUpdataLength(int updataLength) {
		this.updataLength = updataLength;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ManufacturersID == null) ? 0 : ManufacturersID.hashCode());
		result = prime * result + Arrays.hashCode(data);
		result = prime * result + updataLength;
		result = prime * result + updataValue;
		result = prime * result + ((ver == null) ? 0 : ver.hashCode());
		result = prime * result + verLength;
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
		UpdataPack other = (UpdataPack) obj;
		if (ManufacturersID == null) {
			if (other.ManufacturersID != null)
				return false;
		} else if (!ManufacturersID.equals(other.ManufacturersID))
			return false;
		if (!Arrays.equals(data, other.data))
			return false;
		if (updataLength != other.updataLength)
			return false;
		if (updataValue != other.updataValue)
			return false;
		if (ver == null) {
			if (other.ver != null)
				return false;
		} else if (!ver.equals(other.ver))
			return false;
		if (verLength != other.verLength)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UpdataPack [updataValue=" + updataValue + ", ManufacturersID="
				+ ManufacturersID + ", verLength=" + verLength + ", ver=" + ver
				+ ", updataLength=" + updataLength + ", data="
				+ Arrays.toString(data) + "]";
	}

}
