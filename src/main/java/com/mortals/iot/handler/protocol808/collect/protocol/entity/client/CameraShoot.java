package com.mortals.iot.handler.protocol808.collect.protocol.entity.client;

import java.io.IOException;
import java.io.OutputStream;

import com.mortals.iot.protocol.jt808.util.DataHelper;

/**
 * 摄像头立即拍摄命令
 * 
 * @MessageID 0x8801
 * @author 
 * @package com.mortals.iot.handler.protocol808.collect.comm.protocol.message.client
 * @copyright iot
 * @date:2017-4-22 上午10:29:00
 */
public class CameraShoot {
	/**
	 * 消息体长度
	 */
	private static final int length = 12;

	/**
	 * 通道ID BYTE >0
	 */
	private int passageID;
	/**
	 * 拍摄命令
	 */
	private short cmd;
	/**
	 * 拍照间隔/录像时间
	 */
	private short time;
	/**
	 * 保存标志 byte
	 */
	private int mark;
	/**
	 * 分辨率 byte 0x01:320*240； 0x02:640*480； 0x03:800*600； 0x04:1024*768;
	 * 0x05:176*144;[Qcif]; 0x06:352*288;[Cif]; 0x07:704*288;[HALF D1];
	 * 0x08:704*576;[D1];
	 */
	private int resolution;

	/**
	 * 图像/视频质量 byte 1-10，1 代表质量损失最小，10 表示压缩比最大
	 */
	private int quality;
	/**
	 * 亮度 byte
	 * 
	 */
	private int brightness;
	/**
	 * 对比度 byte
	 */
	private int contrast;
	/**
	 * 饱和度 byte
	 */
	private int saturation;
	/**
	 * 色度 byte
	 */
	private int chroma;

	/**
	 * 摄像头立即拍摄命令 编码
	 * 
	 * @param out
	 * @return
	 * @throws IOException
	 */
	public OutputStream encoder(OutputStream out) throws IOException {
		int size = 0;
		size += DataHelper.getbyte(out, passageID);
		size += DataHelper.getShort(out, cmd);
		size += DataHelper.getShort(out, time);
		size += DataHelper.getbyte(out, mark);
		size += DataHelper.getbyte(out, resolution);
		size += DataHelper.getbyte(out, quality);
		size += DataHelper.getbyte(out, brightness);
		size += DataHelper.getbyte(out, contrast);
		size += DataHelper.getbyte(out, saturation);
		size += DataHelper.getbyte(out, chroma);
		if (size != length) {
			return null;
		}
		return out;
	}

	public int getPassageID() {
		return passageID;
	}

	public void setPassageID(int passageID) {
		this.passageID = passageID;
	}

	public short getCmd() {
		return cmd;
	}

	public void setCmd(short cmd) {
		this.cmd = cmd;
	}

	public short getTime() {
		return time;
	}

	public void setTime(short time) {
		this.time = time;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public int getResolution() {
		return resolution;
	}

	public void setResolution(int resolution) {
		this.resolution = resolution;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public int getBrightness() {
		return brightness;
	}

	public void setBrightness(int brightness) {
		this.brightness = brightness;
	}

	public int getContrast() {
		return contrast;
	}

	public void setContrast(int contrast) {
		this.contrast = contrast;
	}

	public int getSaturation() {
		return saturation;
	}

	public void setSaturation(int saturation) {
		this.saturation = saturation;
	}

	public int getChroma() {
		return chroma;
	}

	public void setChroma(int chroma) {
		this.chroma = chroma;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + brightness;
		result = prime * result + chroma;
		result = prime * result + cmd;
		result = prime * result + contrast;
		result = prime * result + mark;
		result = prime * result + passageID;
		result = prime * result + quality;
		result = prime * result + resolution;
		result = prime * result + saturation;
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
		CameraShoot other = (CameraShoot) obj;
		if (brightness != other.brightness)
			return false;
		if (chroma != other.chroma)
			return false;
		if (cmd != other.cmd)
			return false;
		if (contrast != other.contrast)
			return false;
		if (mark != other.mark)
			return false;
		if (passageID != other.passageID)
			return false;
		if (quality != other.quality)
			return false;
		if (resolution != other.resolution)
			return false;
		if (saturation != other.saturation)
			return false;
		if (time != other.time)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CameraShoot [passageID=" + passageID + ", cmd=" + cmd
				+ ", time=" + time + ", mark=" + mark + ", resolution="
				+ resolution + ", quality=" + quality + ", brightness="
				+ brightness + ", contrast=" + contrast + ", saturation="
				+ saturation + ", chroma=" + chroma + "]";
	}

}
