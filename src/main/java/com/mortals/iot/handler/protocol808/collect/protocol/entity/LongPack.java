package com.mortals.iot.handler.protocol808.collect.protocol.entity;

import java.util.Arrays;

import com.mortals.iot.protocol.jt808.util.DataHelper;

/**
 * 长报文
 * @author 
 * @package com.mortals.iot.handler.protocol808.collect.comm.protocol.message
 * @copyright iot
 * @date:2017-5-4 上午11:18:54
 */
public class LongPack {
	/**
	 * 消息总包数
	 */
	private int sun;
	/**
	 * 包序号
	 */
	private int seqNumber;
	/**
	 * 数据
	 */
	private byte[] body;
	/**
	 * 
	 * @param data
	 */
	public void decoder(byte[] data){
		this.sun=DataHelper.parseUint16(data, 0);
		this.seqNumber=DataHelper.parseUint16(data, 2);
		body=new byte[data.length-4];
		System.arraycopy(data, 4, body, 0, data.length-4);	
	}
	public int getSun() {
		return sun;
	}
	public void setSun(int sun) {
		this.sun = sun;
	}
	public int getSeqNumber() {
		return seqNumber;
	}
	public void setSeqNumber(int seqNumber) {
		this.seqNumber = seqNumber;
	}
	public byte[] getBody() {
		return body;
	}
	public void setBody(byte[] body) {
		this.body = body;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(body);
		result = prime * result + seqNumber;
		result = prime * result + sun;
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
		LongPack other = (LongPack) obj;
		if (!Arrays.equals(body, other.body))
			return false;
		if (seqNumber != other.seqNumber)
			return false;
		if (sun != other.sun)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "LongPack [sun=" + sun + ", seqNumber=" + seqNumber + ", body="
				+ Arrays.toString(body) + "]";
	}
	public static void main(String[] args) {
		byte[] cc=new byte[]{(byte)0x00,(byte)0x05,(byte)0x00,(byte)0x01,(byte)0x01,(byte)0x02,(byte)0x03,(byte)0x04,(byte)0x05,(byte)0x06};
		LongPack aa=new LongPack();
		aa.decoder(cc);
		System.err.println(aa.toString());
	}

}
