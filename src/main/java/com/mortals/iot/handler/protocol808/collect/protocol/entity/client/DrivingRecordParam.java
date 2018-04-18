package com.mortals.iot.handler.protocol808.collect.protocol.entity.client;

import com.mortals.iot.protocol.jt808.util.HexUtils;

/**
 * <li>文件名称: DrivingRecordParam</li> 
 * <li>文件描述: 行驶记录参数下发实体类$</li> 
 * <li>内容摘要: 包括模块、函数及其功能的说明</li>
 * <li>完成日期：2017-4-24</li> 
 * <li>修改记录1:iot</li>
 */
public class DrivingRecordParam {
	
	//终端手机号码
	private String deviceMobile;
	
	//命令字
	private int  command;
	
	//数据块
	private String dataBlock;
	
	public  DrivingRecordParam(String deviceMobile,byte[] body){
		byte b_cmd=body[0];
		byte [] b_data_block=HexUtils.copyOfRange(body, 1, body.length);
		
		this.deviceMobile = deviceMobile;
		this.command = HexUtils.byteBcdToInt(b_cmd);
		this.dataBlock = HexUtils.bytesToHexString(b_data_block);
	}

	public DrivingRecordParam(String deviceMobile, int command, String dataBlock) {
		this.deviceMobile = deviceMobile;
		this.command = command;
		this.dataBlock = dataBlock;
	}

	public String getDeviceMobile() {
		return deviceMobile;
	}

	public void setDeviceMobile(String deviceMobile) {
		this.deviceMobile = deviceMobile;
	}

	public int getCommand() {
		return command;
	}

	public void setCommand(int command) {
		this.command = command;
	}

	public String getDataBlock() {
		return dataBlock;
	}

	public void setDataBlock(String dataBlock) {
		this.dataBlock = dataBlock;
	}

}
