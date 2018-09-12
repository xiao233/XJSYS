package com.java.utils;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesUtils {
	private static Logger log = Logger.getLogger(PropertiesUtils.class);
	private static LoadProperties loadProperties = new LoadProperties();
	public static void initConfigProperties() {
		try {
			Properties properties = loadProperties.getConfigProperties("config.properties");
			String a = properties.getProperty("user");
			log.info("a==="+a);
		} catch (IOException e) {
			log.error("[PropertiesUtils.initConfigProperties] 加载配置文件失败： "+e.getMessage());
		}
		
	}
}
