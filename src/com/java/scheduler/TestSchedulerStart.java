package com.java.scheduler;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import com.java.constants.ConfigConstants;

/**
 * ��ʱ�������
 * @author xjl
 * 2019-06-03 09:49:59
 */
public class TestSchedulerStart {

	private Logger log = Logger.getLogger(TestSchedulerStart.class);
	
	public void start() {
		
		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			
			//start
			scheduler.start();
			
			//����job
			JobDetail job = JobBuilder.newJob(TestSchedulerJob.class).withIdentity("TestSchedulerJob", "XJSYS").build();
			
			String con = ConfigConstants.TEST_SCHEDULER_JOB;
			
			//����trigger
			CronTrigger trigger = newTrigger().withIdentity("TestSchedulerJobTrigger", "XJSYS").withSchedule(cronSchedule(con)).build();
			
			//��job��trigger
			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			log.error("��ʱ�������");
		}
		
		
	}
}
