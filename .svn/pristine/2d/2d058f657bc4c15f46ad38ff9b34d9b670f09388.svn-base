package com.inspur.quartz;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.inspur.kafka.ProducerUrls;

public class KafkaProducerJob implements Job {

	public KafkaProducerJob(){
		
	}
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		String Topic = dataMap.getString("Topic");
		@SuppressWarnings("unused")
		ProducerUrls producer=new ProducerUrls(Topic);
	}

}
