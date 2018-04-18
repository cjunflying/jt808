package com.mortals.iot.handler.protocol808.collect.protocol.entity.client;

import java.io.IOException;
import java.io.OutputStream;

import com.mortals.iot.protocol.jt808.util.DataHelper;

/**
 * 录音开始命令 消息ID：0x8804
 * 
 * @author 
 * @package com.mortals.iot.handler.protocol808.collect.comm.protocol.message.client
 * @copyright iot
 * @date:2017-4-24 上午10:07:29
 */
public class Recording {
	/**
	 * 录音命令 byte
	 */
	private int cmd;
	/**
	 * 录音时间 short
	 */
	private short time;
	/**
	 * 保存标志 byte
	 */
	private int save;
	/**
	 * 音频采样率 byte 0：8K；1：11K；2：23K；3：32K；其他保留
	 */
	private int audioSamplingRate;

	/**
	 * 初始化
	 */
	public Recording() {

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
		size += DataHelper.getbyte(out, cmd);
		size += DataHelper.getShort(out, time);
		size += DataHelper.getbyte(out, save);
		size += DataHelper.getbyte(out, audioSamplingRate);
		if (size != 5) {
			return null;
		}

		return out;
	}

	public int getCmd() {
		return cmd;
	}

	public void setCmd(int cmd) {
		this.cmd = cmd;
	}

	public short getTime() {
		return time;
	}

	public void setTime(short time) {
		this.time = time;
	}

	public int getSave() {
		return save;
	}

	public void setSave(int save) {
		this.save = save;
	}

	public int getAudioSamplingRate() {
		return audioSamplingRate;
	}

	public void setAudioSamplingRate(int audioSamplingRate) {
		this.audioSamplingRate = audioSamplingRate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + audioSamplingRate;
		result = prime * result + cmd;
		result = prime * result + save;
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
		Recording other = (Recording) obj;
		if (audioSamplingRate != other.audioSamplingRate)
			return false;
		if (cmd != other.cmd)
			return false;
		if (save != other.save)
			return false;
		if (time != other.time)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Recording [cmd=" + cmd + ", time=" + time + ", save=" + save
				+ ", audioSamplingRate=" + audioSamplingRate + "]";
	}

}
