package tp.mySpringBatch.job.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemReaderException;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import tp.mySpringBatch.exception.MyProcessException;
import tp.mySpringBatch.listener.JobCompletionNotificationListener;
import tp.mySpringBatch.listener.MySkippedErrorsListener;
import tp.mySpringBatch.model.Person;
import tp.mySpringBatch.model.PersonWithNumAndAddress;
import tp.mySpringBatch.processor.SimpleUppercasePersonProcessor;
import tp.mySpringBatch.processor.UppercasePersonProcessorWithFailuresForRetry;

@Configuration
@Profile("!xmlJobConfig")
public class MyBasicCsvWithSkipsErrorsToJsonJobConfig extends MyAbstractJobConfig{

  public static final Logger logger = LoggerFactory.getLogger(MyBasicCsvWithSkipsErrorsToJsonJobConfig.class);


  @Bean
  public Job fromCsvWithSkipsErrorsToJsonJob(@Qualifier("csvWithSkipsErrorsToJson") Step step1) {
    var name = "fromCsvWithSkipsErrorsToJsonJob"; //same as method name for simple mind developper
    var jobBuilder = new JobBuilder(name, jobRepository);
    return jobBuilder.start(step1)
    		.listener(new JobCompletionNotificationListener())
    		.build();
  }

  //stepCsvWithSkipsErrorsToJson is now in
  //tp.mySpringBatch.step.java.StepCsvWithSkipsErrorsToJsonConfig
  
  
  

}