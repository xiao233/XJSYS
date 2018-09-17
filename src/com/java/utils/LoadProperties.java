package com.java.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
			log.error(e.getMessage());
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
	/**
	 * 读取xss配置信息
	 * 2018-09-17 10:54:33
	 * @param appXssBlackName
	 * @return
	 * @throws IOException 
	 */
	public String[] getXSSBlack(String appXssBlackName) throws IOException{
		File file = new File(CommonConstants.APP_CONFIG_PATH+File.separator+appXssBlackName);
		InputStream inputStream  = null;
		
		try {
			//tomcat下配置文件
			inputStream  = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			log.error(CommonConstants.APP_CONFIG_PATH+File.separator+appXssBlackName+"文件不存在！");
		}
		//classpath下配置文件
		if(inputStream==null) {
			URL url =  getClass().getClassLoader().getResource("resource/"+appXssBlackName);
			inputStream= url.openStream();
		}
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
		String lines = "";
		log.info("开始读取"+CommonConstants.APP_XSS_BLACK_NAME+"文件信息！");
		String content = "";
		while((lines=bufferedReader.readLine())!=null) {
			if(lines.indexOf("#")<0) {
				content+=lines;
			}
		}
		inputStream.close();
		bufferedReader.close();
		return content.split(",");
	}
}
