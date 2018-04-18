package com.mortals.iot.handler.protocol808.collect.protocol.entity.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import com.mortals.iot.protocol.jt808.util.DataHelper;

/**
 * 设置矩形区域
 * 
 * @MessageID 0x8602
 * @author 
 * @package com.mortals.iot.handler.protocol808.collect.comm.protocol.message.client
 * @copyright iot
 * @date:2017-4-10 下午3:42:49
 */
public class SetRectangularArea implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 设置属性 0;1;2
	 */
	private int value;
	/**
	 * 区域总数
	 */
	private int number;

	private ArrayList<RectanggularArea_T> rectanggula = new ArrayList<RectanggularArea_T>();

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
			for (RectanggularArea_T iterable_element : rectanggula) {
				size += iterable_element.encoder(out, size);
			}
		}
		return out;
	}

	public void setAreaList(RectanggularArea_T rectanggula) {
		this.rectanggula.add(rectanggula);

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

	public ArrayList<RectanggularArea_T> getRectanggula() {
		return rectanggula;
	}

	public void setRectanggula(ArrayList<RectanggularArea_T> rectanggula) {
		this.rectanggula = rectanggula;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		result = prime * result
				+ ((rectanggula == null) ? 0 : rectanggula.hashCode());
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
		SetRectangularArea other = (SetRectangularArea) obj;
		if (number != other.number)
			return false;
		if (rectanggula == null) {
			if (other.rectanggula != null)
				return false;
		} else if (!rectanggula.equals(other.rectanggula))
			return false;
		if (value != other.value)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SetRectangularArea [value=" + value + ", number=" + number
				+ ", rectanggula=" + rectanggula + "]";
	}

	public static void main(String[] args) throws IOException {
		SetRectangularArea aa = new SetRectangularArea();
		// CircularArea_T[] bb = new CircularArea_T[1];
		aa.setNumber(2);
		aa.setValue(2);
		for (int i = 0; i < 2; i++) {
			RectanggularArea_T cc = new RectanggularArea_T();
			cc.setAreaId(i);
			cc.setAreaValue(new byte[] { (byte) 0, (byte) 1, (byte) 1,
					(byte) 1, (byte) 1, (byte) 0, (byte) 1, (byte) 0, (byte) 1,
					(byte) 1, (byte) 0, (byte) 1, (byte) 1, (byte) 1, (byte) 1,
					(byte) 1 });
			cc.setBeginTime("000000093000");
			cc.setEndTime("000000180000");
			cc.setLULatitude(10);
			cc.setLULongitude(20);
			cc.setRDLatitude(30);
			cc.setRDLongitude(40);
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
