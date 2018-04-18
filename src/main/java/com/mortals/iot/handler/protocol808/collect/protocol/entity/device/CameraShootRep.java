package com.mortals.iot.handler.protocol808.collect.protocol.entity.device;

import java.util.ArrayList;

import com.mortals.iot.protocol.jt808.util.DataHelper;

/**
 * 摄像头立即拍摄命令应答 消息ID：0x0805。
 * 
 * @author 
 * @package com.mortals.iot.handler.protocol808.collect.comm.protocol.message.device
 * @copyright iot
 * @date:2017-4-23 上午10:09:30
 */
public class CameraShootRep {
	/**
	 * 手机号码
	 */
	private String mobile;

	/**
	 * 应答流水号
	 */
	private short serialNumber;

	/**
	 * 结果 byte
	 */
	private int result;
	/**
	 * ]多媒体ID 个数
	 */
	private short Ids;
	/**
	 * 多媒体ID 列表
	 */
	private ArrayList<Integer> id;
	/**
	 * 初始化
	 */
	public CameraShootRep(){
		
	}

	/**
	 * 解码过程
	 */
	public void decode(byte[] body, String mobile) {
		this.mobile = mobile;
		this.serialNumber = DataHelper.parseUint16(body, 0);
		this.result = DataHelper.parseUint8(body, 2);
		this.Ids = DataHelper.parseUint16(body, 3);
		for (int i = 0; i < Ids; i++) {
			id.add(DataHelper.parseUint32(body, i * 4));
		}
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public short getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(short serialNumber) {
		this.serialNumber = serialNumber;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public short getIds() {
		return Ids;
	}

	public void setIds(short ids) {
		Ids = ids;
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
		result = prime * result + Ids;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + this.result;
		result = prime * result + serialNumber;
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
		CameraShootRep other = (CameraShootRep) obj;
		if (Ids != other.Ids)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (result != other.result)
			return false;
		if (serialNumber != other.serialNumber)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CameraShootRep [mobile=" + mobile + ", serialNumber="
				+ serialNumber + ", result=" + result + ", Ids=" + Ids
				+ ", id=" + id + "]";
	}

}
