package com.mortals.iot.handler.protocol808.collect.protocol.entity.device;

import com.mortals.iot.protocol.jt808.util.DataHelper;

/**
 * 存储多媒体数据检索应答 消息ID：0x0802
 * 
 * @author 
 * @package com.mortals.iot.handler.protocol808.collect.comm.protocol.message.device
 * @copyright iot
 * @date:2017-4-23 下午3:11:09
 */
public class SaveMediaRepT {
	/**
	 * 长度
	 */
	private final static int length=35;
	/**
	 * 多媒体ID
	 * 
	 */
	private int ID;
	/**
	 * 多媒体类型 byte
	 */
	private int mediaValue;
	/**
	 * 通道ID byte
	 */
	private int channelID;
	/**
	 * 事件项编码 byte
	 * 
	 */
	private int event;

	/**
	 * 报警标志
	 */
	private int mark;
	/**
	 * 状态
	 */
	private int start;
	/**
	 * 纬度
	 */
	private int latitude;
	/**
	 * 经度
	 */
	private int longitude;

	/**
	 * 高程
	 */
	private short elevation;
	/**
	 * 速度
	 */
	private short speed;
	/**
	 * 方向
	 */
	private short direction;
	/**
	 * 时间 BCD[6]
	 */
	private String time;
	/**
	 * 初始化
	 */
	public SaveMediaRepT(){
		
	}
	/**
	 * 解码
	 * @param data
	 */
	public int  decode(byte[] data,int size){
		
		this.ID=DataHelper.parseUint32(data,size+ 0);
		this.mediaValue=DataHelper.parseUint8(data, size+4);
		this.channelID=DataHelper.parseUint8(data, size+5);
		this.event=DataHelper.parseUint8(data, size+6);
		this.mark=DataHelper.parseUint32(data, size+7);
		this.start=DataHelper.parseUint32(data, size+11);
		this.latitude=DataHelper.parseUint32(data, size+15);
		this.longitude=DataHelper.parseUint32(data, size+19);
		this.elevation=DataHelper.parseUint16(data, size+23);
		this.speed=DataHelper.parseUint16(data,size+25);
		this.direction=DataHelper.parseUint16(data,size+27);
		this.time=DataHelper.bytesTo6HexString(data,size+28, size+28+6);
		
		return length;
		
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getMediaValue() {
		return mediaValue;
	}
	public void setMediaValue(int mediaValue) {
		this.mediaValue = mediaValue;
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
	public int getMark() {
		return mark;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLatitude() {
		return latitude;
	}
	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}
	public int getLongitude() {
		return longitude;
	}
	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}
	public short getElevation() {
		return elevation;
	}
	public void setElevation(short elevation) {
		this.elevation = elevation;
	}
	public short getSpeed() {
		return speed;
	}
	public void setSpeed(short speed) {
		this.speed = speed;
	}
	public short getDirection() {
		return direction;
	}
	public void setDirection(short direction) {
		this.direction = direction;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		result = prime * result + channelID;
		result = prime * result + direction;
		result = prime * result + elevation;
		result = prime * result + event;
		result = prime * result + latitude;
		result = prime * result + longitude;
		result = prime * result + mark;
		result = prime * result + mediaValue;
		result = prime * result + speed;
		result = prime * result + start;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
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
		SaveMediaRepT other = (SaveMediaRepT) obj;
		if (ID != other.ID)
			return false;
		if (channelID != other.channelID)
			return false;
		if (direction != other.direction)
			return false;
		if (elevation != other.elevation)
			return false;
		if (event != other.event)
			return false;
		if (latitude != other.latitude)
			return false;
		if (longitude != other.longitude)
			return false;
		if (mark != other.mark)
			return false;
		if (mediaValue != other.mediaValue)
			return false;
		if (speed != other.speed)
			return false;
		if (start != other.start)
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SaveMediaRepT [ID=" + ID + ", mediaValue=" + mediaValue
				+ ", channelID=" + channelID + ", event=" + event + ", mark="
				+ mark + ", start=" + start + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", elevation=" + elevation
				+ ", speed=" + speed + ", direction=" + direction + ", time="
				+ time + "]";
	}
	
	

}
