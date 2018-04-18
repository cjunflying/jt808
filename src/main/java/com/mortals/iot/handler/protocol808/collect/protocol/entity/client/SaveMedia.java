package com.mortals.iot.handler.protocol808.collect.protocol.entity.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.mortals.iot.protocol.jt808.util.DataHelper;

/**
 * 存储多媒体数据检索
        消息ID：0x8802
 * @author 
 * @package com.mortals.iot.handler.protocol808.collect.comm.protocol.message.client
 * @copyright iot
 * @date:2017-4-23 上午10:53:54
 */
public class SaveMedia {

	/**
	 * 多媒体类型 BYTE
	 */
	private int mediaValue;
	/**
	 * 通道ID BYTE
	 */
	private int ID;
	/**
	 * 事件项编码 BYTE
	 */
	private int event;
	/**
	 * 起始时间BCD[6] YY-MM-DD-hh-mm-ss
	 */
	private String startTime;
	/**
	 * 结束时间 BCD[6] YY-MM-DD-hh-mm-ss
	 */
	private String endTime;
	/**
	 * 初始化
	 */
	public SaveMedia(){
		this.startTime="100000000000";
		this.endTime="100000000000";
	}
	/**
	 * 编码
	 * @param out
	 * @return
	 * @throws IOException
	 */
	public OutputStream encoder(OutputStream out) throws IOException {
		int size=0;
		size+=DataHelper.getbyte(out, mediaValue);
		size+=DataHelper.getbyte(out, ID);
		size+=DataHelper.getbyte(out, event);
		out.write(DataHelper.string26Bcd(startTime));
		size+=6;
		out.write(DataHelper.string26Bcd(endTime));
		size+=6;
		if(size!=15){
			return null;
		}
		
		return out;
	}
	
	public int getMediaValue() {
		return mediaValue;
	}
	public void setMediaValue(int mediaValue) {
		this.mediaValue = mediaValue;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + event;
		result = prime * result + mediaValue;
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
		SaveMedia other = (SaveMedia) obj;
		if (ID != other.ID)
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (event != other.event)
			return false;
		if (mediaValue != other.mediaValue)
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
		return "SaveMedia [mediaValue=" + mediaValue + ", ID=" + ID
				+ ", event=" + event + ", startTime=" + startTime
				+ ", endTime=" + endTime + "]";
	}
	
	public static void main(String[] args) throws IOException {
		SaveMedia a=new SaveMedia();
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		a.encoder(out);
		System.err.println(DataHelper.DataToHexString(out.toByteArray()));
		System.err.println(out.size());
		System.err.println(a.toString());
	}
	
}
