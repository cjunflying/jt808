package com.mortals.iot.handler.protocol808.collect.protocol.entity.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import com.mortals.iot.protocol.jt808.util.DataHelper;

/**
 * @MessageID 消息ID：0x8601
 * @author 
 * @package com.mortals.iot.handler.protocol808.collect.comm.protocol.message.client
 * @copyright iot
 * @date:2017-4-16 上午11:08:53
 */
public class DeleteCircularArea implements Serializable {

	private static final long serialVersionUID = 1L;
	// 删除圆形区域
	private int number;
	// 圆形区域ID
	private ArrayList<Integer> id = new ArrayList<Integer>();

	/**
	 * encoder the DeleteCircularArea
	 * 
	 * @param out
	 * @return
	 * @throws IOException
	 */
	public OutputStream encoder(OutputStream out) throws IOException {
		int size = 0;
		if (this.number == 0) {
			return out;
		}
		size += DataHelper.getbyte(out, number);
		for (int i = 0; i < number; i++) {
			size += DataHelper.getInt(out, id.get(i));
		}
		if (size < 4) {
			return null;
		}
		return out;
	}

	@Override
	public String toString() {
		return "DeleteCircularArea [number=" + number + ", id=" + id + "]";
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public ArrayList<Integer> getId() {
		return id;
	}

	public void setId(ArrayList<Integer> id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + number;
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
		DeleteCircularArea other = (DeleteCircularArea) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (number != other.number)
			return false;
		return true;
	}

	public static void main(String[] args) throws IOException {
		ArrayList<Integer> aa = new ArrayList<>();

		aa.add(12);
		aa.add(333);
		aa.add(2564);
		aa.add(25462);
		// int[] aa=new int[]{12,33,44,22,55,66,994,1112,23412,2523};
		DeleteCircularArea a = new DeleteCircularArea();
		a.setNumber(aa.size());
		a.setId(aa);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		a.encoder(out);
		System.err.println(a.toString());
		System.err.println(DataHelper.DataToHexString(out.toByteArray()));
		System.err.println(out.size());

	}

}
