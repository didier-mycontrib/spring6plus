package tp.mySpringBatch.job.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import tp.mySpringBatch.decider.MySkipCheckingDecider;
import tp.mySpringBatch.listener.JobCompletionNotificationListener;
import tp.mySpringBatch.tasklet.PrintMessageWithDelayTasklet;

@Configuration
@Profile("!xmlJobConfig")
public class MyDecisionFlowJobConfig extends MyAbstractJobConfig{

  public static final Logger logger = LoggerFactory.getLogger(MyDecisionFlowJobConfig.class);

  
  @Bean
  public MySkipCheckingDecider mySkipCheckingDecider() {
	  return new MySkipCheckingDecider();
  }
  
  @Bean(name="withDecisionFlowJob")
  public Job withDecisionFlowJob(
		  MySkipCheckingDecider mySkipCheckingDecider,
		  @Qualifier("csvWithSkipsErrorsToJson") Step step1
		  // @Qualifier("csvToJson") Step step1
		  ) {
    var name = "withDecisionFlowJob";
    var stepBuilder = new StepBuilder(name, jobRepository);
    
    Step stepWithSkips = stepBuilder.tasklet(new PrintMessageWithDelayTasklet("COMPLETED_WITH_SKIPS",500L), this.batchTxManager).build();
    Step stepWithoutSkip = stepBuilder.tasklet(new PrintMessageWithDelayTasklet("COMPLETED",500L), this.batchTxManager).build();
    
    var jobBuilder = new JobBuilder(name, jobRepository);
    
 
    final Flow withOrWithoutSkipFlow = new FlowBuilder<SimpleFlow>("withOrWithoutSkipFlow")
    		.start(step1)
    	    .next(mySkipCheckingDecider).on("COMPLETED_WITH_SKIPS").to(stepWithSkips)
            .from(mySkipCheckingDecider).on("COMPLETED").to(stepWithoutSkip)
            .end();//end of flow
    
    return jobBuilder
    		.start(withOrWithoutSkipFlow)
    		.end()//end FlowBuilder and return to JobBuilder 
    		.listener(new JobCompletionNotificationListener())
    		.build();
  }
  
  /*
  Autres possibilités (variantes):
  from_or_next(stepXy).on("FAILED").end()  to ending full job with COMPLETED status when intermediate stepXy "FAILED"
  from_or_next(stepXy).on("FAILED").fail() to ending full job with FAILED status when intermediate stepXy "FAILED"
  */
}