package com.mortals.iot.handler.protocol808.collect.protocol.entity.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.mortals.iot.protocol.jt808.util.DataHelper;

/**
 * 信息点播菜单设置 子结构
消息ID：0x8303。
 * @author 
 * @package com.mortals.iot.handler.protocol808.collect.comm.protocol.message.client
 * @copyright iot
 * @date:2017-4-18 上午9:19:58
 */
public class InfoMenuSettings_T {
	//信息类型
	private int MessageType;
	//信息名称长度
	private short MessageLength;
	//信息名称
	private String MessageName;
	/**
	 * 
	 * @param out
	 * @param size
	 * @return
	 * @throws IOException
	 */
	public int encoder(OutputStream out ,int size) throws IOException{
		size+=DataHelper.getbyte(out, MessageType);
		size+=DataHelper.getShort(out, MessageLength);
		out.write(DataHelper.encodeGBK(MessageName));
		size+=MessageLength;
		return size;
	}
	/**
	 * get 结构长度
	 * @return
	 */
	public int getLength(){
		
		return 3+MessageLength;
	}
	
	
	public int getMessageType() {
		return MessageType;
	}
	public void setMessageType(int messageType) {
		MessageType = messageType;
	}
	public short getMessageLength() {
		return MessageLength;
	}
	public void setMessageLength(short messageLength) {
		MessageLength = messageLength;
	}
	public String getMessageName() {
		return MessageName;
	}
	public void setMessageName(String messageName) {
		MessageName = messageName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + MessageLength;
		result = prime * result
				+ ((MessageName == null) ? 0 : MessageName.hashCode());
		result = prime * result + MessageType;
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
		InfoMenuSettings_T other = (InfoMenuSettings_T) obj;
		if (MessageLength != other.MessageLength)
			return false;
		if (MessageName == null) {
			if (other.MessageName != null)
				return false;
		} else if (!MessageName.equals(other.MessageName))
			return false;
		if (MessageType != other.MessageType)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "InfoMenuSettings_T [MessageType=" + MessageType
				+ ", MessageLength=" + MessageLength + ", MessageName="
				+ MessageName + "]";
	}
	
	public static void main(String[] args) throws IOException {
		InfoMenuSettings_T t=new InfoMenuSettings_T();
		t.setMessageLength((short)33);
		t.setMessageType(1);
		t.setMessageName("asdsadsd");
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		t.encoder(out, 0);
		System.err.println(DataHelper.DataToHexString(out.toByteArray()));
	}

}
