package com.inspur.quartz;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClumpedExample {
	public void run () throws SchedulerException {  
		 @SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:applicationContext.xml","classpath:applicationContext-quartz.xml"});    
          
        System.out.println("集群列子1");
          
        Scheduler sche = (Scheduler) context.getBean("testCrawl");  
          
        //获取正在运行中的Job  
        @SuppressWarnings("unused")
		List<JobExecutionContext> executingJobs = sche.getCurrentlyExecutingJobs();  
          
        //获取数据库中的Job  
        GroupMatcher<JobKey> jobMatcher = GroupMatcher.anyJobGroup();  
        Set<JobKey> jobKeys = sche.getJobKeys(jobMatcher);  
        List<JobDetail> jobDetails = new ArrayList<JobDetail>();  
          
        for (JobKey key : jobKeys) {  
            jobDetails.add(sche.getJobDetail(key));  
        }  
          
        //获取数据库中的Trigger 
        while(true){
            GroupMatcher<TriggerKey> TgrMatcher = GroupMatcher.anyTriggerGroup();  
            Set<TriggerKey> Keys = sche.getTriggerKeys(TgrMatcher);  
            List<Trigger> triggers = new ArrayList<Trigger>();  
              
            for (TriggerKey key : Keys) {  
                triggers.add(sche.getTrigger(key));  
            }  
              
            //自动获取数据库中触发器和job的信息  然后执行  
            sche.start();
        }
    }  
      
	public static void main(String... ars){
		ClumpedExample ClumpedExample=new ClumpedExample();
		try {
			ClumpedExample.run();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
