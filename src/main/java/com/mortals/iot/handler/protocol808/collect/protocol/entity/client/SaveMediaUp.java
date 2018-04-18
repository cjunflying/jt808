package com.mortals.iot.handler.protocol808.collect.protocol.entity.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.mortals.iot.protocol.jt808.util.DataHelper;

/**
 * 存储多媒体数据上传命令 消息ID：0x8803
 * 
 * @author 
 * @package com.mortals.iot.handler.protocol808.collect.comm.protocol.message.client
 * @copyright iot
 * @date:2017-4-24 上午9:02:10
 */
public class SaveMediaUp {

	/**
	 * 多媒体类型 byte
	 */
	private int MediaValue;
	/**
	 * 通道ID byte
	 */
	private int channelID;
	/**
	 * 事件项编码 byte
	 */
	private int event;
	/**
	 * 起始时间 bcd[6]
	 */
	private String startTime;
	/**
	 * 结束时间 bcd[6]
	 */
	private String endTime;
	/**
	 * 删除标志 byte
	 */
	private int deleteMark;

	/**
	 * 初始化
	 */
	public SaveMediaUp() {

		this.startTime="000000000000";
		this.endTime="000000000000";
	}

	/**
	 * 编码
	 * 
	 * @param out
	 * @return
	 * @throws IOException
	 */
	public OutputStream encoder(OutputStream out) throws IOException {
		int size = 0;
		size += DataHelper.getbyte(out, MediaValue);
		size += DataHelper.getbyte(out, channelID);
		size += DataHelper.getbyte(out, event);
		out.write(DataHelper.string26Bcd(startTime));
		size += 6;
		out.write(DataHelper.string26Bcd(endTime));
		size += 6;
		size += DataHelper.getbyte(out, deleteMark);
		if (size != 16) {
			return null;
		}

		return out;
	}

	public int getMediaValue() {
		return MediaValue;
	}

	public void setMediaValue(int mediaValue) {
		MediaValue = mediaValue;
	}

	public int getChannelID() {
		return channelID;
	}

	public void setChannelID(int channelID) {
		this.channelID = channelID;
	}

	public int getEvent() {
		return event;
	}

	public void setEvent(int event) {
		this.event = event;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getDeleteMark() {
		return deleteMark;
	}

	public void setDeleteMark(int deleteMark) {
		this.deleteMark = deleteMark;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + MediaValue;
		result = prime * result + channelID;
		result = prime * result + deleteMark;
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + event;
		result = prime * result
				+ ((startTime == null) ? 0 : startTime.hashCode());
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
		SaveMediaUp other = (SaveMediaUp) obj;
		if (MediaValue != other.MediaValue)
			return false;
		if (channelID != other.channelID)
			return false;
		if (deleteMark != other.deleteMark)
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (event != other.event)
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SaveMediaUp [MediaValue=" + MediaValue + ", channelID="
				+ channelID + ", event=" + event + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", deleteMark=" + deleteMark + "]";
	}

	public static void main(String[] args) throws IOException {
		SaveMediaUp u=new SaveMediaUp();
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		u.encoder(out);
		
		System.err.println(out.size());
		System.err.println(DataHelper.DataToHexString(out.toByteArray()));
		
	}
}
