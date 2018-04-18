package com.mortals.iot.handler.protocol808.collect.protocol.entity.client;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import com.mortals.iot.protocol.jt808.util.DataHelper;
import com.mortals.iot.protocol.jt808.util.HexUtils;

/**
 * 圆形区域格式
 * 
 * @MessageID 0x8600
 * @author 
 * @package com.mortals.iot.handler.protocol808.collect.comm.protocol.message.device
 * @copyright iot
 * @date:2017-4-9 下午3:19:32
 */
public class CircularArea_T {

	public static final int length = 33;
	// 区域ID
	private int areaId;
	// 区域属性
	private byte[] areaValue;
	// 纬度
	private int Latitude;
	// 经度
	private int Longitude;
	// 半径
	private int Radius;
	// 起始时间
	private String beginTime;
	// 结束时间
	private String endTime;
	// 最高时速
	private short speed;
	// 持续时间
	private byte duration;

	/**
	 * 
	 * @param out
	 * @param size
	 * @return
	 * @throws IOException
	 */
	public int encoder(OutputStream out, int size) throws IOException {

		setValue(areaValue);

		size += DataHelper.getInt(out, areaId);
		String BinaryStr = getBoolean(areaValue);
		out.write(areaValue);
		size += 2;
		size += DataHelper.getInt(out, Latitude);
		size += DataHelper.getInt(out, Longitude);
		size += DataHelper.getInt(out, Radius);
		if (BinaryStr.substring(BinaryStr.length() - 1, BinaryStr.length())
				.equals("1")) {
			out.write(DataHelper.string26Bcd(beginTime));
			size += 6;
			out.write(DataHelper.string26Bcd(endTime));
			size += 6;
		}
		if (BinaryStr.substring(BinaryStr.length() - 2, BinaryStr.length() - 1)
				.equals("1")) {
			size += DataHelper.getShort(out, speed);
			out.write(duration);
			size += 1;
		}

		return size;
	}

	public short getSpeed() {
		return speed;
	}

	public void setSpeed(short speed) {
		this.speed = speed;
	}

	public byte getDuration() {
		return duration;
	}

	public void setDuration(byte duration) {
		this.duration = duration;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public byte[] getAreaValue() {
		return areaValue;
	}

	public void setAreaValue(byte[] areaValue) {
		this.areaValue = areaValue;
	}

	public int getLatitude() {
		return Latitude;
	}

	public void setLatitude(int latitude) {
		Latitude = latitude;
	}

	public int getLongitude() {
		return Longitude;
	}

	public void setLongitude(int longitude) {
		Longitude = longitude;
	}

	public int getRadius() {
		return Radius;
	}

	public void setRadius(int radius) {
		Radius = radius;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Latitude;
		result = prime * result + Longitude;
		result = prime * result + Radius;
		result = prime * result + areaId;
		result = prime * result + Arrays.hashCode(areaValue);
		result = prime * result
				+ ((beginTime == null) ? 0 : beginTime.hashCode());
		result = prime * result + duration;
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + speed;
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
		CircularArea_T other = (CircularArea_T) obj;
		if (Latitude != other.Latitude)
			return false;
		if (Longitude != other.Longitude)
			return false;
		if (Radius != other.Radius)
			return false;
		if (areaId != other.areaId)
			return false;
		if (!Arrays.equals(areaValue, other.areaValue))
			return false;
		if (beginTime == null) {
			if (other.beginTime != null)
				return false;
		} else if (!beginTime.equals(other.beginTime))
			return false;
		if (duration != other.duration)
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (speed != other.speed)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CircularArea_T [areaId=" + areaId + ", areaValue="
				+ Arrays.toString(areaValue) + ", Latitude=" + Latitude
				+ ", Longitude=" + Longitude + ", Radius=" + Radius
				+ ", beginTime=" + beginTime + ", endTime=" + endTime
				+ ", speed=" + speed + ", duration=" + duration + "]";
	}

	/**
	 * 
	 * @param data
	 */
	private void setValue(byte[] data) {
		byte[] num;
		if (data.length == 16) {
			String sr="";
			for (int i = 0; i < data.length; i++) {
				sr=sr+ (byte) ((data[i] >> 0) & 0x1);
			}
			System.err.println(sr);
			num = HexUtils.intToByte2Array(DataHelper.StringBinaryToInt(sr));
			this.areaValue = num;
		} else {
			this.areaValue = new byte[] { (byte) 0xff, (byte) 0xff };
		}

	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	private String getBoolean(byte[] data) {
		String value = null;

		if (data.length > 2) {
			return value;
		}
		value = DataHelper.bytes2BinaryStr(data);
		return value;
	}

}
