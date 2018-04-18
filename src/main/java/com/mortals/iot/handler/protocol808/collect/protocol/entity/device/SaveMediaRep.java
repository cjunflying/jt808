package com.mortals.iot.handler.protocol808.collect.protocol.entity.device;

import java.util.ArrayList;

import com.mortals.iot.protocol.jt808.util.DataHelper;

/**
 * 存储多媒体数据检索应答 消息ID：0x0802
 * 
 * @author 
 * @package com.mortals.iot.handler.protocol808.collect.comm.protocol.message.device
 * @copyright iot
 * @date:2017-4-23 下午2:59:08
 */
public class SaveMediaRep {
	/**
	 * 应答流水号
	 */
	private short serialNumber;
	/**
	 * 多媒体数据总项数
	 */
	private short num;

	/**
	 * 检索项
	 */
	private ArrayList<SaveMediaRepT> events = new ArrayList<SaveMediaRepT>();

	/**
	 * 初始化
	 */
	public SaveMediaRep() {

	}

	/**
	 * 解码
	 * 
	 * @param data
	 */
	public void decode(byte[] data) {
		this.serialNumber = DataHelper.parseUint16(data, 0);
		this.num = DataHelper.parseUint16(data, 2);
		int size = 0;
		for (int i = 0; i < num; i++) {
			SaveMediaRepT event = new SaveMediaRepT();
			size += event.decode(data, 4 + size);
			events.add(event);

		}

	}

	public short getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(short serialNumber) {
		this.serialNumber = serialNumber;
	}

	public short getNum() {
		return num;
	}

	public void setNum(short num) {
		this.num = num;
	}

	public ArrayList<SaveMediaRepT> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<SaveMediaRepT> events) {
		this.events = events;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((events == null) ? 0 : events.hashCode());
		result = prime * result + num;
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
		SaveMediaRep other = (SaveMediaRep) obj;
		if (events == null) {
			if (other.events != null)
				return false;
		} else if (!events.equals(other.events))
			return false;
		if (num != other.num)
			return false;
		if (serialNumber != other.serialNumber)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SaveMediaRep [serialNumber=" + serialNumber + ", num=" + num
				+ ", events=" + events + "]";
	}
	

}
