package com.mortals.iot.handler.protocol808.collect.protocol.entity.client;

import java.io.IOException;
import java.io.OutputStream;

import com.mortals.iot.protocol.jt808.util.DataHelper;

/**
 * 0x8203人工解除告警
 * 
 * @author 
 * @package com.mortals.iot.handler.protocol808.collect.comm.protocol.message.client
 * @copyright iot
 * @date:2017-4-21 上午9:05:44
 */
public class DisarmAlarm {
	/**
	 * 报警消息流水号
	 */
	private short serialNumber;
	/**
	 * 确认紧急报警 0
	 */
	private boolean emergencyAlarm;
	/**
	 * 确认危险预警3
	 */
	private boolean hazardWarning;
	/**
	 * 确认进出区域报警 20
	 */
	private boolean regionalPolice;
	/**
	 * 确认进出路线报警 21
	 */
	private boolean lineAlarm;
	/**
	 * 确认路段行驶时间不足/过程报警22
	 */
	private boolean timeAlarm;
	/**
	 * 确认车辆非法点火报警 27
	 */
	private boolean IllegalIgnitionAlarm;
	/**
	 * 确认车辆非法位移报警 28
	 */
	private boolean IllegalDisplacementAlarm;

	/**
	 * 初始化
	 */
	public DisarmAlarm() {
		this.serialNumber = 0;
		this.emergencyAlarm = false;
		this.hazardWarning = false;
		this.IllegalDisplacementAlarm = false;
		this.IllegalIgnitionAlarm = false;
		this.lineAlarm = false;
		this.regionalPolice = false;
		this.timeAlarm = false;

	}

	/**
	 * 确认告警编码
	 * 
	 * @param out
	 * @return
	 * @throws IOException
	 */
	public OutputStream encoder(OutputStream out) throws IOException {
		int size = 0;
		size += DataHelper.getShort(out, serialNumber);
		int alarm;
		alarm=DataHelper.StringBinaryToInt(getString());
		size+=DataHelper.getInt(out, alarm);
		if(size!=4){
			return null;
		}

		return out;
	}

	public short getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(short serialNumber) {
		this.serialNumber = serialNumber;
	}

	public boolean isEmergencyAlarm() {
		return emergencyAlarm;
	}

	public void setEmergencyAlarm(boolean emergencyAlarm) {
		this.emergencyAlarm = emergencyAlarm;
	}

	public boolean isHazardWarning() {
		return hazardWarning;
	}

	public void setHazardWarning(boolean hazardWarning) {
		this.hazardWarning = hazardWarning;
	}

	public boolean isRegionalPolice() {
		return regionalPolice;
	}

	public void setRegionalPolice(boolean regionalPolice) {
		this.regionalPolice = regionalPolice;
	}

	public boolean isLineAlarm() {
		return lineAlarm;
	}

	public void setLineAlarm(boolean lineAlarm) {
		this.lineAlarm = lineAlarm;
	}

	public boolean isTimeAlarm() {
		return timeAlarm;
	}

	public void setTimeAlarm(boolean timeAlarm) {
		this.timeAlarm = timeAlarm;
	}

	public boolean isIllegalIgnitionAlarm() {
		return IllegalIgnitionAlarm;
	}

	public void setIllegalIgnitionAlarm(boolean illegalIgnitionAlarm) {
		IllegalIgnitionAlarm = illegalIgnitionAlarm;
	}

	public boolean isIllegalDisplacementAlarm() {
		return IllegalDisplacementAlarm;
	}

	public void setIllegalDisplacementAlarm(boolean illegalDisplacementAlarm) {
		IllegalDisplacementAlarm = illegalDisplacementAlarm;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (IllegalDisplacementAlarm ? 1231 : 1237);
		result = prime * result + (IllegalIgnitionAlarm ? 1231 : 1237);
		result = prime * result + (emergencyAlarm ? 1231 : 1237);
		result = prime * result + (hazardWarning ? 1231 : 1237);
		result = prime * result + (lineAlarm ? 1231 : 1237);
		result = prime * result + (regionalPolice ? 1231 : 1237);
		result = prime * result + serialNumber;
		result = prime * result + (timeAlarm ? 1231 : 1237);
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
		DisarmAlarm other = (DisarmAlarm) obj;
		if (IllegalDisplacementAlarm != other.IllegalDisplacementAlarm)
			return false;
		if (IllegalIgnitionAlarm != other.IllegalIgnitionAlarm)
			return false;
		if (emergencyAlarm != other.emergencyAlarm)
			return false;
		if (hazardWarning != other.hazardWarning)
			return false;
		if (lineAlarm != other.lineAlarm)
			return false;
		if (regionalPolice != other.regionalPolice)
			return false;
		if (serialNumber != other.serialNumber)
			return false;
		if (timeAlarm != other.timeAlarm)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DisarmAlarm [serialNumber=" + serialNumber
				+ ", emergencyAlarm=" + emergencyAlarm + ", hazardWarning="
				+ hazardWarning + ", regionalPolice=" + regionalPolice
				+ ", lineAlarm=" + lineAlarm + ", timeAlarm=" + timeAlarm
				+ ", IllegalIgnitionAlarm=" + IllegalIgnitionAlarm
				+ ", IllegalDisplacementAlarm=" + IllegalDisplacementAlarm
				+ "]";
	}

	/**
	 * 计算出数据位
	 * 
	 * @return
	 */
	private String getString() {
		String str = "111" + (IllegalDisplacementAlarm == false ? "0" : "1")
				+ (IllegalIgnitionAlarm == false ? "0" : "1") + "1111"
				+ (timeAlarm == false ? "0" : "1")
				+ (lineAlarm == false ? "0" : "1")
				+ (regionalPolice == false ? "0" : "1") + "1111111111111111"
				+ (hazardWarning == false ? "0" : "1") + "11"
				+ (emergencyAlarm == false ? "0" : "1");

		return str;
	}
	
	public static void main(String[] args) {
		DisarmAlarm a=new DisarmAlarm();
		System.err.println(a.getString());
		System.err.println(a.getString().length());
		
	}

}
