package com.mortals.iot.handler.protocol808.collect.protocol.entity.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import com.mortals.iot.protocol.jt808.util.DataHelper;

/**
 * 信息点播菜单设置
 * 消息ID：0x8303。
 * @author 
 * @package com.mortals.iot.handler.protocol808.collect.comm.protocol.message.client
 * @copyright iot
 * @date:2017-4-18 上午9:17:48
 */
public class InfoMenuSettings {
	//设置类型
	private int Value;
	//信息项总数
	private int num;
	//信息项列表
	private ArrayList<InfoMenuSettings_T> info=new ArrayList<InfoMenuSettings_T>();
	/**
	 * 
	 * @param out
	 * @return
	 * @throws IOException
	 */
	public OutputStream encoder(OutputStream out) throws IOException{
		int size =0;
		size+=DataHelper.getbyte(out, Value);
		if(num <=0){
			return out;
		}
		size+=DataHelper.getbyte(out, num);
		for (InfoMenuSettings_T iterable_element : info) {
			size+=iterable_element.encoder(out, size);	
		}
		return out;
	}
	
	public int getValue() {
		return Value;
	}
	public void setValue(int value) {
		Value = value;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public ArrayList<InfoMenuSettings_T> getInfo() {
		return info;
	}
	
	public void setInfoList(InfoMenuSettings_T info){
		this.info.add(info);
	}
	
	public void setInfo(ArrayList<InfoMenuSettings_T> info) {
		this.info = info;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Value;
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		result = prime * result + num;
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
		InfoMenuSettings other = (InfoMenuSettings) obj;
		if (Value != other.Value)
			return false;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		if (num != other.num)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "InfoMenuSettings [Value=" + Value + ", num=" + num + ", info="
				+ info + "]";
	}
	
	public static void main(String[] args) throws IOException {
		InfoMenuSettings aa=new InfoMenuSettings();
		aa.Value=4;
		aa.num=2;
		for (int i = 0; i < 3; i++) {
			InfoMenuSettings_T c=new InfoMenuSettings_T();
			c.setMessageType(4);
			c.setMessageLength((short)6);
			c.setMessageName("ajaj");
			aa.setInfoList(c);
			
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.err.println(aa.toString());
		aa.encoder(out);
		System.err.println(DataHelper.DataToHexString(out.toByteArray()));
		System.err.println(out.size());
		
	}

}
