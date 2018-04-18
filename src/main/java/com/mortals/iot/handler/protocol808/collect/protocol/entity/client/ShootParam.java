package com.mortals.iot.handler.protocol808.collect.protocol.entity.client;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @ClassName: ShootParam
 * @Description: 拍摄参数
 * @Company iot
 * @author 
 * @version 1.0, 2017-3-27 下午3:15:41
 */
public class ShootParam {

	// 通道ID
	private int channelId;

	// 拍摄命令
	private int shootCmd;

	// 拍照间隔/录像时间
	private int shootInterval;

	// 保存标志
	private int saveFlag;

	// 分辨率
	private int resolution;

	// 图像/视频质量
	private int videoQuality;

	// 亮度
	private int brightness;

	// 对比度
	private int contrast;

	// 饱和度
	private int saturation;

	// 色度
	private int chroma;

	public ShootParam() {
		this.channelId = 1;
		this.shootCmd = 1;
		this.shootInterval = 60;
		this.saveFlag = 0;
		this.resolution = 1;
		this.videoQuality = 2;
		this.brightness = 0;
		this.contrast = 0;
		this.saturation = 0;
		this.chroma = 0;
	}

	public ShootParam(int channelId, int shootCmd, int shootInterval,
			int saveFlag, int resolution, int videoQuality, int brightness,
			int contrast, int saturation, int chroma) {
		this.channelId = channelId;
		this.shootCmd = shootCmd;
		this.shootInterval = shootInterval;
		this.saveFlag = saveFlag;
		this.resolution = resolution;
		this.videoQuality = videoQuality;
		this.brightness = brightness;
		this.contrast = contrast;
		this.saturation = saturation;
		this.chroma = chroma;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	/**
	 * @return 通道ID
	 */
	public int getChannelId() {
		return channelId;
	}

	/**
	 * @param 通道ID
	 */
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	/**
	 * @return 拍摄命令
	 */
	public int getShootCmd() {
		return shootCmd;
	}

	/**
	 * @param 拍摄命令
	 */
	public void setShootCmd(int shootCmd) {
		this.shootCmd = shootCmd;
	}

	/**
	 * @return 拍照间隔/录像时间
	 */
	public int getShootInterval() {
		return shootInterval;
	}

	/**
	 * @param 拍照间隔/录像时间
	 */
	public void setShootInterval(int shootInterval) {
		this.shootInterval = shootInterval;
	}

	/**
	 * @return 保存标志
	 */
	public int getSaveFlag() {
		return saveFlag;
	}

	/**
	 * @param 保存标志
	 */
	public void setSaveFlag(int saveFlag) {
		this.saveFlag = saveFlag;
	}

	/**
	 * @return 分辨率
	 */
	public int getResolution() {
		return resolution;
	}

	/**
	 * @param 亮度
	 */
	public void setResolution(int resolution) {
		this.resolution = resolution;
	}

	/**
	 * @return 图像/视频质量
	 */
	public int getVideoQuality() {
		return videoQuality;
	}

	/**
	 * @param 图像/视频质量
	 */
	public void setVideoQuality(int videoQuality) {
		this.videoQuality = videoQuality;
	}

	/**
	 * @return 亮度
	 */
	public int getBrightness() {
		return brightness;
	}

	/**
	 * @param 亮度
	 */
	public void setBrightness(int brightness) {
		this.brightness = brightness;
	}

	/**
	 * @return 对比度
	 */
	public int getContrast() {
		return contrast;
	}

	/**
	 * @param 对比度
	 */
	public void setContrast(int contrast) {
		this.contrast = contrast;
	}

	/**
	 * @return 饱和度
	 */
	public int getSaturation() {
		return saturation;
	}

	/**
	 * @param 饱和度
	 */
	public void setSaturation(int saturation) {
		this.saturation = saturation;
	}

	/**
	 * @return 色度
	 */
	public int getChroma() {
		return chroma;
	}

	/**
	 * @param 色度
	 */
	public void setChroma(int chroma) {
		this.chroma = chroma;
	}


}
