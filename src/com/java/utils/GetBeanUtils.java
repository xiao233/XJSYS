package com.java.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * 用非注解的方式获取实例
 * @author xjl
 * 2018-12-24 09:36:27
 */
@Component
public class GetBeanUtils implements ApplicationContextAware{

	private  static ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		GetBeanUtils.applicationContext = arg0;
		
	}
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}
	
}
