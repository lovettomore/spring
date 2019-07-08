package kr.or.ddit.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class RangerBatch {
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:kr/or/ddit/config/spring/application-batch-dev.xml");
		
		JobLauncher jobLauncher = context.getBean("jobLauncher", JobLauncher.class);
		Job rangerJob = context.getBean("rangerJob", Job.class);
		
		
		//job을 실행할때 파라미터를 같이 보낼수 있거든요? 근데 지금은 없으니까 null
		try {
			jobLauncher.run(rangerJob, new JobParameters());
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
