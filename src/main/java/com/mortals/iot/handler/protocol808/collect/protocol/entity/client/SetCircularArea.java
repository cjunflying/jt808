package com.mortals.iot.handler.protocol808.collect.protocol.entity.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import com.mortals.iot.protocol.jt808.util.DataHelper;

/**
 * 设置圆形区域
 * 
 * @MessageID=0x8600
 * @author 
 * @package com.mortals.iot.handler.protocol808.collect.comm.protocol.message.device
 * @copyright iot
 * @date:2017-4-9 下午3:12:33
 */
public class SetCircularArea implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 0：更新区域；
	// 1：追加区域；
	// 2：修改区域
	private int value;
	// 设置区域总算
	private int number;
	// 数据包
	private ArrayList<CircularArea_T> area=new ArrayList<CircularArea_T>();

	/**
	 * 
	 * @param out
	 * @return
	 * @throws IOException
	 */
	public OutputStream encoder(OutputStream out) throws IOException {
		int size = 0;
		size += DataHelper.getbyte(out, value);
		size += DataHelper.getbyte(out, number);
		if (number != 0) {
			for (CircularArea_T iterable_element : area) {
				size += iterable_element.encoder(out, size);
			}
		}
		return out;
	}

	public void setAreaList(CircularArea_T area) {
		this.area.add(area);

	}

	public ArrayList<CircularArea_T> getArea() {
		return area;
	}

	public void setArea(ArrayList<CircularArea_T> area) {
		this.area = area;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		result = prime * result + number;
		result = prime * result + value;
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
		SetCircularArea other = (SetCircularArea) obj;
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		if (number != other.number)
			return false;
		if (value != other.value)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SetCircularArea [value=" + value + ", number=" + number
				+ ", area=" + area + "]";
	}

	public static void main(String[] args) throws IOException {
		SetCircularArea aa = new SetCircularArea();
		// CircularArea_T[] bb = new CircularArea_T[1];
		aa.setNumber(2);
		aa.setValue(2);
		for (int i = 0; i < 2; i++) {
			CircularArea_T cc = new CircularArea_T();
			cc.setAreaId(i);
			cc.setAreaValue(new byte[] { (byte) 0xff, (byte) 0xff });
			cc.setBeginTime("0930");
			cc.setEndTime("1800");
			int a1=(int) (104.073487*Math.pow(10, 6));
			cc.setLatitude(a1);
			int a2=(int) (30.600753*Math.pow(10, 6));
			cc.setRadius(a2);
			int a3=(int) (50.600753*Math.pow(10, 6));
			cc.setLongitude(a3);
			cc.setSpeed((short) 100);
			cc.setDuration((byte) 0xff);
			aa.setAreaList(cc);
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		aa.encoder(out);
		System.err.println(aa.toString());
		System.err.println(DataHelper.DataToHexString(out.toByteArray()));
		System.err.println(out.size());

	}
}
