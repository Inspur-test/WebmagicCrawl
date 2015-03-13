package com.inspur.quartz;

import java.lang.reflect.Method;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
@Component("SimpleService")
public class ScheduledJob extends QuartzJobBean{
 
	/*@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		JobDataMap dataMap = arg0.getJobDetail().getJobDataMap();
		
		System.out.println("3232323232s"+dataMap.getString("SimpleService"));
		
		
	}*/
	

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        try {        	
        	this.setTargetObject((String) context.getJobDetail().getJobDataMap().get("targetObject"));
            this.setTargetMethod((String) context.getJobDetail().getJobDataMap().get("targetMethod"));
            
        	//System.out.println("execute [" + this.targetObject + "] at once>>>>>>");
            ApplicationContext ctx = (ApplicationContext) context.getScheduler().getContext().get("applicationContextKey");
            Object otargetObject = ctx.getBean(targetObject);
            Method m = null;
            try {
                m = otargetObject.getClass().getMethod(targetMethod, new Class[]{});

                m.invoke(otargetObject, new Object[]{});
            }
            catch (SecurityException e) {
                e.printStackTrace();
            }
            catch (NoSuchMethodException e) {
            	e.printStackTrace();
            }

        }
        catch (Exception e) {
          e.printStackTrace();
        }
    }

    private String targetObject;
    private String targetMethod;

    public void setTargetObject(String targetObject) {
        this.targetObject = targetObject;
    }

    public void setTargetMethod(String targetMethod) {
        this.targetMethod = targetMethod;
    }
}
