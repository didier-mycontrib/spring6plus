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
public class MyBasicCsvToJsonWithRetryJobConfig extends MyAbstractJobConfig{

  public static final Logger logger = LoggerFactory.getLogger(MyBasicCsvToJsonWithRetryJobConfig.class);

  @Bean
  public Job fromCsvToJsonWithRetryJob(@Qualifier("csvToJsonWithRetry") Step step1) {
    var name = "fromCsvToJsonWithRetryJob";
    var jobBuilder = new JobBuilder(name, jobRepository);
    return jobBuilder.start(step1)
    		.listener(new JobCompletionNotificationListener())
    		.build();
  }

  @Bean @Qualifier("csvToJsonWithRetry")
  public Step stepCsvToJsonWithRetry(@Qualifier("csv") ItemReader<Person> personItemReader,
		            @Qualifier("json") ItemWriter<Person> personItemWriter ,
		            UppercasePersonProcessorWithFailuresForRetry uppercasePersonProcessorWithFailuresForRetry) {
    var name = "stepCsvToJsonWithRetry";
    var stepBuilder = new StepBuilder(name, jobRepository);
    return stepBuilder
        .<Person, Person>chunk(5, batchTxManager)
        .listener(new MySkippedErrorsListener())
        .reader(personItemReader)
        .processor(uppercasePersonProcessorWithFailuresForRetry)
        .writer(personItemWriter)
        .faultTolerant()
        //.backOffPolicy(new ExponentialBackOffPolicy())
        .retryLimit(3).retry(MyProcessException.class) //IMPORTANT BUT BUG IN JAVA (not XML)  !!!!
        //MyProcessException a non Skippable exception !!!!
        .build();
    
  }
  
  

}