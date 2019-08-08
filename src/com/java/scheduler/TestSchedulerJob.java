package com.java.scheduler;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestSchedulerJob implements Job{

	private Logger log = Logger.getLogger(TestSchedulerJob.class);
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		
		log.info("定时任务测试！");
	}
	
}
