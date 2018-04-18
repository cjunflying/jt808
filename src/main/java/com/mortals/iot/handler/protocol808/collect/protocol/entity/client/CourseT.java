package com.mortals.iot.handler.protocol808.collect.protocol.entity.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.mortals.iot.protocol.jt808.util.DataHelper;

/**
 * 设置路线子类
 * 消息ID：0x8606
 * @author 
 * @package com.mortals.iot.handler.protocol808.collect.comm.protocol.message.client
 * @copyright iot
 * @date:2017-4-18 上午11:13:39
 */
public class CourseT {
	//拐点ID
	private int inflectionID;
	//路段ID
	private int courseID;
	//拐点纬度
	private int inflectionLatitude;
	//拐点经度
	private int inflectionLongitude;
	/**
	 * 路段宽度 byte 不大于127
	 */
	private int courseWidth;
	/**
	 * 路段属性 byte 8位2进制表示从低位到高位7~0
	 * 0 1：行驶时间
       1 1：限速
       2 0：北纬；1：南纬
       3 0：东经；1：西经
       4-7 保留
	 */
	private String courseValue;
	/**
	 * 路段行驶过长阈值   不大于32767 两个字节
	 */
	private int drivingLong;
	/**
	 * 路段行驶不足阈值  不大于32767 两个字节
	 */
	private int drivingInadequate;
	/**
	 * 路段最高速度  不大于32767 两个字节
	 */
	private int speed;
	/**
	 * 路段超速持续时间    byte 不大于127
	 */
	private int time;
	/**
	 * 初始化
	 */
	public CourseT(){
		
	}
	
	/**
	 * 编码方式
	 * @param out
	 * @param size
	 * @return
	 * @throws IOException
	 */
	public int encoder(OutputStream out ,int size) throws IOException{
		size+=DataHelper.getInt(out, inflectionID);
		size+=DataHelper.getInt(out, courseID);
		size+=DataHelper.getInt(out, inflectionLatitude);
		size+=DataHelper.getInt(out, inflectionLongitude);
		size+=DataHelper.getbyte(out, courseWidth);
		int value=DataHelper.StringBinaryToInt(courseValue);
		size+=DataHelper.getbyte(out, value);
		size+=DataHelper.getShort(out, (short)drivingLong);
		size+=DataHelper.getShort(out, (short)drivingInadequate);
		size+=DataHelper.getShort(out, (short)speed);
		size+=DataHelper.getbyte(out, time);
		return size;
	}
	
	public int getInflectionID() {
		return inflectionID;
	}
	public void setInflectionID(int inflectionID) {
		this.inflectionID = inflectionID;
	}
	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public int getInflectionLatitude() {
		return inflectionLatitude;
	}
	public void setInflectionLatitude(int inflectionLatitude) {
		this.inflectionLatitude = inflectionLatitude;
	}
	public int getInflectionLongitude() {
		return inflectionLongitude;
	}
	public void setInflectionLongitude(int inflectionLongitude) {
		this.inflectionLongitude = inflectionLongitude;
	}
	public int getCourseWidth() {
		return courseWidth;
	}
	public void setCourseWidth(int courseWidth) {
		this.courseWidth = courseWidth;
	}
	

	public String getCourseValue() {
		return courseValue;
	}

	public void setCourseValue(String courseValue) {
		this.courseValue = courseValue;
	}

	public int getDrivingLong() {
		return drivingLong;
	}

	public void setDrivingLong(int drivingLong) {
		this.drivingLong = drivingLong;
	}

	public int getDrivingInadequate() {
		return drivingInadequate;
	}

	public void setDrivingInadequate(int drivingInadequate) {
		this.drivingInadequate = drivingInadequate;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + courseID;
		result = prime * result
				+ ((courseValue == null) ? 0 : courseValue.hashCode());
		result = prime * result + courseWidth;
		result = prime * result + drivingInadequate;
		result = prime * result + drivingLong;
		result = prime * result + inflectionID;
		result = prime * result + inflectionLatitude;
		result = prime * result + inflectionLongitude;
		result = prime * result + speed;
		result = prime * result + time;
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
		CourseT other = (CourseT) obj;
		if (courseID != other.courseID)
			return false;
		if (courseValue == null) {
			if (other.courseValue != null)
				return false;
		} else if (!courseValue.equals(other.courseValue))
			return false;
		if (courseWidth != other.courseWidth)
			return false;
		if (drivingInadequate != other.drivingInadequate)
			return false;
		if (drivingLong != other.drivingLong)
			return false;
		if (inflectionID != other.inflectionID)
			return false;
		if (inflectionLatitude != other.inflectionLatitude)
			return false;
		if (inflectionLongitude != other.inflectionLongitude)
			return false;
		if (speed != other.speed)
			return false;
		if (time != other.time)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SetCourse_T [inflectionID=" + inflectionID + ", courseID="
				+ courseID + ", inflectionLatitude=" + inflectionLatitude
				+ ", inflectionLongitude=" + inflectionLongitude
				+ ", courseWidth=" + courseWidth + ", courseValue="
				+ courseValue + ", drivingLong=" + drivingLong
				+ ", drivingInadequate=" + drivingInadequate + ", speed="
				+ speed + ", time=" + time + "]";
	}
	public static void main(String[] args) throws IOException {
		CourseT aa=new CourseT();
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		aa.encoder(out, 0);
		
		System.err.println(DataHelper.DataToHexString(out.toByteArray()));
	}

}
