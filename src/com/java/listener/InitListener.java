package com.java.listener;



import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoaderListener;

import com.java.utils.PropertiesUtils;

public class InitListener extends ContextLoaderListener{
	
	Logger log = Logger.getLogger(InitListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		super.contextDestroyed(event);
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		try {
			log.info("开始初始化-------------");
			PropertiesUtils.initConfigProperties();
		}catch(Exception e) {
			log.error("初始化配置文件失败"+e.getMessage());
		}
		
		
	}
	
}
