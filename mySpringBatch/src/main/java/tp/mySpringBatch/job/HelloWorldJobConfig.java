package tp.mySpringBatch.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import tp.mySpringBatch.listener.JobCompletionNotificationListener;
import tp.mySpringBatch.tasklet.PrintMessageTasklet;

@Configuration
public class HelloWorldJobConfig extends MyAbstractJobConfig{

  public static final Logger logger = LoggerFactory.getLogger(HelloWorldJobConfig.class);


  @Bean(name="myHelloWorldJob")
  public Job myHelloWorldJob() {
    var name = "myHelloWorldJob";
    var jobBuilder = new JobBuilder(name, jobRepository);
    return jobBuilder
    		 .start(this.simplePrintMessageStep("hello world"))
    		 .next(this.simplePrintMessageStep("by SpringBatch"))
    		.listener(new JobCompletionNotificationListener())
    		.build();
  }


  private Step simplePrintMessageStep(String message){
    var name = "simplePrintMessageStep_"+message;
    var stepBuilder = new StepBuilder(name, jobRepository);
    return stepBuilder
        .tasklet(new PrintMessageTasklet(message), this.batchTxManager)
        .build();
  }


}