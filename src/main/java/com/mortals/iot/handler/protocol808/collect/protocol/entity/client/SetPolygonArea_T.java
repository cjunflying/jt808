package com.mortals.iot.handler.protocol808.collect.protocol.entity.client;

import java.io.IOException;
import java.io.OutputStream;

import com.mortals.iot.protocol.jt808.util.DataHelper;

/**
 * 设置多边形区域
 * 
 * @author 
 * @package com.mortals.iot.handler.protocol808.collect.comm.protocol.message.client
 * @copyright iot
 * @date:2017-4-17 上午9:34:45
 */
public class SetPolygonArea_T {
	// 顶点纬度
	private int VertexLatitude;
	// 顶点经度
	private int VertexLongitude;

	/**
	 * 编码方法
	 * 
	 * @param out
	 * @param size
	 * @return
	 * @throws IOException
	 */
	public int encoder(OutputStream out, int size) throws IOException {
		size += DataHelper.getInt(out, VertexLatitude);
		size += DataHelper.getInt(out, VertexLongitude);

		return size;
	}

	public int getVertexLatitude() {
		return VertexLatitude;
	}

	public void setVertexLatitude(int vertexLatitude) {
		VertexLatitude = vertexLatitude;
	}

	public int getVertexLongitude() {
		return VertexLongitude;
	}

	public void setVertexLongitude(int vertexLongitude) {
		VertexLongitude = vertexLongitude;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + VertexLatitude;
		result = prime * result + VertexLongitude;
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
		SetPolygonArea_T other = (SetPolygonArea_T) obj;
		if (VertexLatitude != other.VertexLatitude)
			return false;
		if (VertexLongitude != other.VertexLongitude)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SetPolygonArea_T [VertexLatitude=" + VertexLatitude
				+ ", VertexLongitude=" + VertexLongitude + "]";
	}

}
