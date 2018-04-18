package com.mortals.iot.handler.protocol808.collect.commons;



import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: ConfigUtils
 * @Description: 配置端口等信息工具
 * @author 
 * @date 2017-12-31 下午2:16:45
 *
*/
public class ConfigUtils {
	
	private static final Properties props;
	
	private final static Logger logger = LoggerFactory.getLogger(ConfigUtils.class);

	static{
		props = new Properties();
		try {
			props.load(ConfigUtils.class.getClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	private ConfigUtils(){}
	
	public static Object getValue(String name){
		if(props.containsKey(name)){
			try {
				return new String(props.get(name).toString().getBytes("ISO-8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(e.getMessage(), e);
				return "";
			}
		}else{
			return "";
		}
	}
}
