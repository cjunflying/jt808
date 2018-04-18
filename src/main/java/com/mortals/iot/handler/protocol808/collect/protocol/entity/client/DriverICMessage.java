package com.mortals.iot.handler.protocol808.collect.protocol.entity.client;

import java.io.Serializable;

import com.mortals.iot.handler.protocol808.collect.commons.Log;
import com.mortals.iot.protocol.jt808.util.DataHelper;
import com.mortals.iot.protocol.jt808.util.HexUtils;

/**
 * @MessageID 0x0702
 * @author 
 * @package com.mortals.iot.handler.protocol808.collect.comm.protocol.message.client
 * @copyright iot
 * @date:2017-4-15 上午9:54:44
 */
public class DriverICMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String deviceMobile;
	// 状态
	private int condition;
	// 时间 ，YY-MM-DD-hh-mm-ss
	private String time;
	// IC 卡读取结果
	// 0x00：IC 卡读卡成功；
	// 0x01：读卡失败，原因为卡片密钥认证未通过；
	// 0x02：读卡失败，原因为卡片已被锁定；
	// 0x03：读卡失败，原因为卡片被拔出；
	// 0x04：读卡失败，原因为数据校验错误。
	private int readIC;
	// 驾驶员姓名长度
	private int nameleng;
	// 驾驶员姓名
	private String driverName;
	// 从业资格证编码
	private String quaCertCoding;
	// 发证机构名称长度
	private int IssAgencyLen;
	// 发证机构名称
	private String IssAgencyName;
	// 证件有效期
	private String validDocuments;

	public DriverICMessage() {

	}

	/**
	 * Decoder zhe DriverICMessage
	 * 
	 * @param deviceMobile
	 * @param body
	 */
	public DriverICMessage(String deviceMobile, byte[] body) {
		Log.getLogger(this.getClass()).info(HexUtils.bytesToHexString(body));
		if (body == null || body.length <34) {
			return;
		}
		try {
			this.deviceMobile = deviceMobile;
			this.condition = DataHelper.parseUint8(body, 0);
			this.time = DataHelper.bytesTo6HexString(body, 1, 6);
			this.readIC = DataHelper.parseUint8(body, 7);
			// byte[]
			this.nameleng = DataHelper.parseUint8(body, 8);
			this.driverName = DataHelper.parseString(body, 9, nameleng);
			this.quaCertCoding = DataHelper.parseString(body, 9 + nameleng, 20);
			this.IssAgencyLen = DataHelper.parseUint8(body, 29 + nameleng);
			this.IssAgencyName = DataHelper.parseString(body, 30 + nameleng,
					IssAgencyLen);
			this.validDocuments = DataHelper.bytesTo6HexString(body, 30
					+ nameleng + IssAgencyLen, 4);

		} catch (Exception e) {
			Log.getLogger(this.getClass()).error(
					"Error decoder:" + HexUtils.bytesToHexString(body));
		}

	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getQuaCertCoding() {
		return quaCertCoding;
	}

	public void setQuaCertCoding(String quaCertCoding) {
		this.quaCertCoding = quaCertCoding;
	}

	public int getCondition() {
		return condition;
	}

	public void setCondition(int condition) {
		this.condition = condition;
	}

	public int getReadIC() {
		return readIC;
	}

	public void setReadIC(int readIC) {
		this.readIC = readIC;
	}

	public int getNameleng() {
		return nameleng;
	}

	public void setNameleng(int nameleng) {
		this.nameleng = nameleng;
	}

	public int getIssAgencyLen() {
		return IssAgencyLen;
	}

	public void setIssAgencyLen(int issAgencyLen) {
		IssAgencyLen = issAgencyLen;
	}

	public String getIssAgencyName() {
		return IssAgencyName;
	}

	public void setIssAgencyName(String issAgencyName) {
		IssAgencyName = issAgencyName;
	}

	public String getValidDocuments() {
		return validDocuments;
	}

	public void setValidDocuments(String validDocuments) {
		this.validDocuments = validDocuments;
	}

	public String getDeviceMobile() {
		return deviceMobile;
	}

	public void setDeviceMobile(String deviceMobile) {
		this.deviceMobile = deviceMobile;
	}

	@Override
	public String toString() {
		return "DriverICMessage [deviceMobile=" + deviceMobile + ", condition="
				+ condition + ", time=" + time + ", readIC=" + readIC
				+ ", nameleng=" + nameleng + ", driverName=" + driverName
				+ ", quaCertCoding=" + quaCertCoding + ", IssAgencyLen="
				+ IssAgencyLen + ", IssAgencyName=" + IssAgencyName
				+ ", validDocuments=" + validDocuments + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + IssAgencyLen;
		result = prime * result
				+ ((IssAgencyName == null) ? 0 : IssAgencyName.hashCode());
		result = prime * result + condition;
		result = prime * result
				+ ((deviceMobile == null) ? 0 : deviceMobile.hashCode());
		result = prime * result
				+ ((driverName == null) ? 0 : driverName.hashCode());
		result = prime * result + nameleng;
		result = prime * result
				+ ((quaCertCoding == null) ? 0 : quaCertCoding.hashCode());
		result = prime * result + readIC;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result
				+ ((validDocuments == null) ? 0 : validDocuments.hashCode());
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
		DriverICMessage other = (DriverICMessage) obj;
		if (IssAgencyLen != other.IssAgencyLen)
			return false;
		if (IssAgencyName == null) {
			if (other.IssAgencyName != null)
				return false;
		} else if (!IssAgencyName.equals(other.IssAgencyName))
			return false;
		if (condition != other.condition)
			return false;
		if (deviceMobile == null) {
			if (other.deviceMobile != null)
				return false;
		} else if (!deviceMobile.equals(other.deviceMobile))
			return false;
		if (driverName == null) {
			if (other.driverName != null)
				return false;
		} else if (!driverName.equals(other.driverName))
			return false;
		if (nameleng != other.nameleng)
			return false;
		if (quaCertCoding == null) {
			if (other.quaCertCoding != null)
				return false;
		} else if (!quaCertCoding.equals(other.quaCertCoding))
			return false;
		if (readIC != other.readIC)
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (validDocuments == null) {
			if (other.validDocuments != null)
				return false;
		} else if (!validDocuments.equals(other.validDocuments))
			return false;
		return true;
	}

}
