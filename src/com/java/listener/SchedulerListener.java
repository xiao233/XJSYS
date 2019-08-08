package com.java.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.java.scheduler.TestSchedulerStart;

/**
 * 实现ServletContextListener接口监听应用的启动与停止
 * 用于监听定时任务，启动定时任务
 * @author xjl
 * 2019-06-03 09:49:17
 */
public class SchedulerListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		new TestSchedulerStart().start();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}
	
}
