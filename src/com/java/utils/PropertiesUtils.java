package com.java.utils;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.java.constants.CommonConstants;

public class PropertiesUtils {
	private static Logger log = Logger.getLogger(PropertiesUtils.class);
	private static LoadProperties loadProperties = new LoadProperties();
	public static String xssBlack[];
	public static void initConfigProperties() {
		try {
			Properties properties = loadProperties.getConfigProperties(CommonConstants.APP_CONFIG_NAME);
			String a = properties.getProperty("user");
			log.info("a==="+a);
			xssBlack = loadProperties.getXSSBlack(CommonConstants.APP_XSS_BLACK_NAME);
		} catch (IOException e) {
			log.error("[PropertiesUtils.initConfigProperties] 加载配置文件失败： "+e.getMessage());
		}
		
	}
}
