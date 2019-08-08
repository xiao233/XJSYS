package com.java.utils;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.java.constants.CommonConstants;
import com.java.constants.ConfigConstants;

public class PropertiesUtils {
	private static Logger log = Logger.getLogger(PropertiesUtils.class);
	private static LoadProperties loadProperties = new LoadProperties();
	public static String xssBlack[];
	public static void initConfigProperties() {
		try {
			Properties properties = loadProperties.getConfigProperties(CommonConstants.APP_CONFIG_NAME);
			String a = properties.getProperty("user");
			
			String codeImageLiveTime= properties.getProperty("CODE_IMAGE_LIVE_TIME");
			if(!StringUtils.isEmpty(codeImageLiveTime)) {
				log.info("CODE_IMAGE_LIVE_TIME: "+codeImageLiveTime);
				ConfigConstants.CODE_IMAGE_LIVE_TIME = Integer.parseInt(codeImageLiveTime);
			}
			
			String testSchedulerJob = properties.getProperty("TEST_SCHEDULER_JOB");
			if(!StringUtils.isEmpty(testSchedulerJob)) {
				log.info("TEST_SCHEDULER_JOB: "+testSchedulerJob);
				ConfigConstants.TEST_SCHEDULER_JOB = testSchedulerJob;
			}
			log.info("a==="+a);
			xssBlack = loadProperties.getXSSBlack(CommonConstants.APP_XSS_BLACK_NAME);
		} catch (IOException e) {
			log.error("[PropertiesUtils.initConfigProperties] º”‘ÿ≈‰÷√Œƒº˛ ß∞‹£°"+e.getMessage());
		}
		
	}
}
