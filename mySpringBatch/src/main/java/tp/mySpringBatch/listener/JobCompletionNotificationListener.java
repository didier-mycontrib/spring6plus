package tp.mySpringBatch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

//ok as it without @Component and used with this code:
//.listener(new JobCompletionNotificationListener()) in a job definition @Bean
public class JobCompletionNotificationListener implements JobExecutionListener {

  private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

  @Override
  public void afterJob(JobExecution jobExecution) {
    if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
      log.info(" --- JOB FINISHED : " + jobExecution.toString());
    }
    else {
      log.error(" --- JOB FINISHED : " + jobExecution.toString());
    }
  }
}