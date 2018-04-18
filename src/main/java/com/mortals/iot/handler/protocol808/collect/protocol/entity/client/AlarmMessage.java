package com.mortals.iot.handler.protocol808.collect.protocol.entity.client;

import java.io.IOException;
import java.io.OutputStream;

import com.mortals.iot.protocol.jt808.util.DataHelper;

/**
 * 人工确认报警消息 消息ID：0x8203
 * 
 * @author 
 * @package com.mortals.iot.handler.protocol808.collect.comm.protocol.message.client
 * @copyright iot
 * @date:2017-4-18 上午9:08:14
 */
public class AlarmMessage {
	// 报警消息流水号
	private short alarmID;
	// 人工确认报警类型
	// 32位各代表各意思
	private int alarmType;

	/**
	 * 编码方法
	 * 
	 * @param out
	 * @return
	 * @throws IOException
	 */
	public OutputStream encoder(OutputStream out) throws IOException {
		int size = 0;
		size += DataHelper.getShort(out, alarmID);
		size += DataHelper.getInt(out, alarmType);
		if (size != 6) {
			return null;
		}

		return out;
	}

	public short getAlarmID() {
		return alarmID;
	}

	public void setAlarmID(short alarmID) {
		this.alarmID = alarmID;
	}

	public int getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(int alarmType) {
		this.alarmType = alarmType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + alarmID;
		result = prime * result + alarmType;
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
		AlarmMessage other = (AlarmMessage) obj;
		if (alarmID != other.alarmID)
			return false;
		if (alarmType != other.alarmType)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AlarmMessage [alarmID=" + alarmID + ", alarmType=" + alarmType
				+ "]";
	}

}
