package com.inspur.quartz;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.TriggerBuilder.*;

public class QuartzJobStart {
	public static void main(String[] args){
	// define the job and tie it to our HelloJob class
	 SchedulerFactory schedFact = new StdSchedulerFactory();
	 Scheduler sched;
	try {
		sched = schedFact.getScheduler();
		sched.start();
		 JobDetail job = newJob(KafkaProducerJob.class)
			      .withIdentity("myJob", "group1") // name "myJob", group "group1"
			      .usingJobData("Topic", "Html_urls")
			      .build();
			  // Trigger the job to run now, and then every 40 seconds
			  Trigger trigger = newTrigger()
			      .withIdentity("myTrigger", "group1")
			      .startNow()
			      .withSchedule(simpleSchedule()
			       .withIntervalInHours(2)
			       .repeatForever())
			      .build();
			  // Tell quartz to schedule the job using our trigger
			  sched.scheduleJob(job, trigger);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
