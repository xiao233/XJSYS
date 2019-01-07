package com.java.utils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * ��ʼ��bean��ʱ��ִ��afterPropertiesSet����,��ʼ��Redis����
 * @author xjl
 * 2019-01-02 15:01:29
 */
@Component
public class InitRedisCache implements InitializingBean{
	
	private Logger log = Logger.getLogger(InitRedisCache.class);
	
	public  InitRedisCache() {
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		try {
			RedisUtils.init();
		}catch(Exception e) {
			log.error(e.getMessage());
		}
		
	}
}
