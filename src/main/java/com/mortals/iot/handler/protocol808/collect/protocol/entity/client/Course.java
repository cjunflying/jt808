package com.mortals.iot.handler.protocol808.collect.protocol.entity.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import com.mortals.iot.protocol.jt808.util.DataHelper;

/**
 * 设置路线 消息ID：0x8606
 * 
 * @author 
 * @package com.mortals.iot.handler.protocol808.collect.comm.protocol.message.client
 * @copyright iot
 * @date:2017-4-18 上午11:03:15
 */
public class Course {
	// 路线ID
	private int courseID;
	/**
	 *  路线属性 2 16位 2进制
	 */
	private String courseValue;
	/**
	 *  起始时间 BCD[6] YY-MM-DD-hh-mm-ss
	 */
	private String startTime;
	/**
	 *  结束时间 BCD[6] YY-MM-DD-hh-mm-ss
	 */
	private String endTime;
	/**
	 * 路线总拐点数  2字节 不大于 32767
	 */
	private int InflectionNum;
	/**
	 *  拐点项
	 */
	private ArrayList<CourseT> course = new ArrayList<CourseT>();

	/**
	 * 初始化
	 */
	public Course() {
		this.setStartTime("000000000000");
		this.setEndTime("000000000000");

	}

	/**
	 * 
	 * @param out
	 * @return
	 * @throws IOException
	 */
	public OutputStream encoder(OutputStream out) throws IOException {
		int size = 0;
		size += DataHelper.getInt(out, courseID);
		int value = DataHelper.StringBinaryToInt(courseValue);
		size += DataHelper.getShort(out, (short) value);
		out.write(DataHelper.string26Bcd(startTime));
		size += 6;
		out.write(DataHelper.string26Bcd(endTime));
		size += 6;
		size += DataHelper.getShort(out, (short)InflectionNum);
		for (CourseT iterable_element : course) {
			size += iterable_element.encoder(out, size);
		}

		return out;
	}

	public void setCourseList(CourseT course) {
		this.course.add(course);
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
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

	

	public int getInflectionNum() {
		return InflectionNum;
	}

	public void setInflectionNum(int inflectionNum) {
		InflectionNum = inflectionNum;
	}

	public ArrayList<CourseT> getCourse() {
		return course;
	}

	public void setCourse(ArrayList<CourseT> course) {
		this.course = course;
	}

	public String getCourseValue() {
		return courseValue;
	}

	public void setCourseValue(String courseValue) {
		this.courseValue = courseValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + InflectionNum;
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result + courseID;
		result = prime * result
				+ ((courseValue == null) ? 0 : courseValue.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
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
		Course other = (Course) obj;
		if (InflectionNum != other.InflectionNum)
			return false;
		if (course == null) {
			if (other.course != null)
				return false;
		} else if (!course.equals(other.course))
			return false;
		if (courseID != other.courseID)
			return false;
		if (courseValue == null) {
			if (other.courseValue != null)
				return false;
		} else if (!courseValue.equals(other.courseValue))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
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
		return "SetCourse [courseID=" + courseID + ", courseValue="
				+ courseValue + ", startTime=" + startTime + ", endTime="
				+ endTime + ", InflectionNum=" + InflectionNum + ", course="
				+ course + "]";
	}

	public static void main(String[] args) throws IOException {
		Course cc = new Course();
		cc.setCourseID(1);
		cc.setCourseValue("1111111111111111");

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		cc.encoder(out);
		System.err.println(DataHelper.DataToHexString(out.toByteArray()));

	}

}
