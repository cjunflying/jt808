package com.mortals.iot.handler.protocol808.collect.protocol.entity.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import com.mortals.iot.protocol.jt808.util.DataHelper;
import com.mortals.iot.protocol.jt808.util.HexUtils;

/**
 * @MessageID 0x8604
 * @author 
 * @package com.mortals.iot.handler.protocol808.collect.comm.protocol.message.client
 * @copyright iot
 * @date:2017-4-17 上午9:26:43
 */
public class SetPolygonArea {
	// 区域ID
	private int areaId;
	// 区域属性
	private byte[] areaValue;
	// 起始时间
	private String beginTime;
	// 结束时间
	private String endTime;
	// 最高时速
	private short speed;
	// 超速持续时间
	private int duration;
	// 区域总顶点数
	private short totalAreanum;
	// 区域顶点
	private ArrayList<SetPolygonArea_T> polygon = new ArrayList<SetPolygonArea_T>();

	public OutputStream encoder(OutputStream out) throws IOException {
		int size = 0;
		setValue(areaValue);
		size += DataHelper.getInt(out, areaId);
		out.write(areaValue);
		size += 2;
		String BinaryStr = getBoolean(areaValue);
		out.write(DataHelper.string26Bcd(beginTime));
		size += 6;
		out.write(DataHelper.string26Bcd(endTime));
		size += 6;
		size += DataHelper.getShort(out, speed);
		if (BinaryStr.substring(BinaryStr.length() - 2, BinaryStr.length() - 1)
				.equals("1")) {
			size += DataHelper.getbyte(out, duration);
		}
		size += DataHelper.getShort(out, totalAreanum);
		if (totalAreanum == 0) {
			return out;
		}
		for (SetPolygonArea_T iterable_element : polygon) {
			iterable_element.encoder(out, size);
		}

		return out;
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

	public short getSpeed() {
		return speed;
	}

	public void setSpeed(short speed) {
		this.speed = speed;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public short getTotalAreanum() {
		return totalAreanum;
	}

	public void setTotalAreanum(short totalAreanum) {
		this.totalAreanum = totalAreanum;
	}

	public ArrayList<SetPolygonArea_T> getPolygon() {
		return polygon;
	}

	public void setPolygon(ArrayList<SetPolygonArea_T> polygon) {
		this.polygon = polygon;
	}

	public void addPolygonList(SetPolygonArea_T polygon) {
		this.polygon.add(polygon);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + areaId;
		result = prime * result + Arrays.hashCode(areaValue);
		result = prime * result
				+ ((beginTime == null) ? 0 : beginTime.hashCode());
		result = prime * result + duration;
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((polygon == null) ? 0 : polygon.hashCode());
		result = prime * result + speed;
		result = prime * result + totalAreanum;
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
		SetPolygonArea other = (SetPolygonArea) obj;
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
		if (polygon == null) {
			if (other.polygon != null)
				return false;
		} else if (!polygon.equals(other.polygon))
			return false;
		if (speed != other.speed)
			return false;
		if (totalAreanum != other.totalAreanum)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SetPolygonArea [areaId=" + areaId + ", areaValue="
				+ Arrays.toString(areaValue) + ", beginTime=" + beginTime
				+ ", endTime=" + endTime + ", speed=" + speed + ", duration="
				+ duration + ", totalAreanum=" + totalAreanum + ", polygon="
				+ polygon + "]";
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

	/**
	 * 
	 * @param data
	 */
	private void setValue(byte[] data) {
		byte[] num;
		if (data.length == 16) {
			String sr = "";
			for (int i = 0; i < data.length; i++) {
				sr = sr + (byte) ((data[i] >> 0) & 0x1);
			}
			System.err.println(sr);
			num = HexUtils.intToByte2Array(DataHelper.StringBinaryToInt(sr));
			this.areaValue = num;
		} else {
			this.areaValue = new byte[] { (byte) 0xff, (byte) 0xff };
		}

	}

	public static void main(String[] args) throws IOException {
		SetPolygonArea cc = new SetPolygonArea();
		cc.setAreaId(11);
		cc.setAreaValue(new byte[] { (byte) 0xff, (byte) 0xff });
		cc.setBeginTime("000000001800");
		cc.setEndTime("000000073000");
		cc.setDuration(3);
		cc.setSpeed((short) 123);
		cc.setTotalAreanum((short) 22);
		for (int i = 0; i <= 100; i++) {
			System.err.println(i);
			SetPolygonArea_T a = new SetPolygonArea_T();
			a.setVertexLatitude(3334512);
			a.setVertexLongitude(i);
			cc.addPolygonList(a);
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		cc.encoder(out);
		System.err.println(cc.toString());
		System.err.println(DataHelper.DataToHexString(out.toByteArray()));
		System.err.println(out.size());

	}
}
