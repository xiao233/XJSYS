package com.java.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.java.constants.CommonConstants;

/**
 * 加载配置文件工具类
 * @author xjl
 * 2018-08-25 11:31:14
 */
public class LoadProperties {
	
	Logger log = Logger.getLogger(LoadProperties.class);
	public  Properties getConfigProperties(String filename) throws IOException {
		Properties properties = new Properties();
		
		FileInputStream inputStream = null;
		
		/**
		 * 读取外部配置文件
		 */
		try {
			File file = new File(CommonConstants.APP_CONFIG_PATH+File.separator+filename);
			inputStream = new FileInputStream(file);
			properties.load(new InputStreamReader(inputStream,"UTF-8"));
			log.info("LoadProperties: 从"+CommonConstants.APP_CONFIG_PATH+"读取外部配置文件");
		}catch(Exception e) {
		}
		
		/**
		 * 从classpath里面加载配置信息
		 */
		if(inputStream == null) {
			URL url =  getClass().getClassLoader().getResource("resource/"+filename);
			properties.load(new InputStreamReader(url.openStream(),"UTF-8"));
			log.info("LoadProperties: 从classpath加载配置文件");
		}
		
		
		return properties;
	}
}
