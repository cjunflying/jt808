package com.mortals.iot.handler.protocol808.collect.protocol.entity.client;

/**
 * @ClassName: Linkman
 * @Description: 联系人信息：联系人标志+号码长度+电话号码+联系人长度+联系人姓名
 * @Company iot
 * @author 
 * @version  1.0, 2017-3-27 下午1:53:05  
 */
public class LinkMan {

//	联系人标志+号码长度+电话号码+联系人长度+联系人姓名
	
	
	// 1：呼入 
	public static final int IN = 1;
	// 2：呼出	
	public static final int OUT = 2;
	// 3：呼入/呼出
	public static final int INOUT = 3;
	
	private int flag;
	
	private String phoneNumber;
	
	private String linkName;

	public LinkMan(int flag, String phoneNumber, String linkName){
		this.flag = flag;
		this.phoneNumber = phoneNumber;
		this.linkName = linkName;
	}
	
	/**
	 * @return the flag
	 */
	public int getFlag() {
		return flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(int flag) {
		this.flag = flag;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the linkName
	 */
	public String getLinkName() {
		return linkName;
	}

	/**
	 * @param linkName the linkName to set
	 */
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	
	
}
