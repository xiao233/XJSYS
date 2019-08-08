package com.java.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.java.scheduler.TestSchedulerStart;

/**
 * ʵ��ServletContextListener�ӿڼ���Ӧ�õ�������ֹͣ
 * ���ڼ�����ʱ����������ʱ����
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
