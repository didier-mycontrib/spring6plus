package tp.mySpringBatch.job.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import tp.mySpringBatch.listener.JobCompletionNotificationListener;
import tp.mySpringBatch.model.Person;
import tp.mySpringBatch.processor.SimpleUppercasePersonProcessor;

@Configuration
@Profile("!xmlJobConfig")
public class MyBasicCsvToJsonJobConfig extends MyAbstractJobConfig{

  public static final Logger logger = LoggerFactory.getLogger(MyBasicCsvToJsonJobConfig.class);


  @Bean
  public Job fromCsvToJsonJob(@Qualifier("csvToJson") Step step1) {
    var name = "fromCsvToJsonJob";//same as method name for simple mind developper
    var jobBuilder = new JobBuilder(name, jobRepository);
    return jobBuilder.start(step1)
    		.listener(new JobCompletionNotificationListener())
    		.build();
  }

  @Bean @Qualifier("csvToJson")
  public Step stepCsvToJson(@Qualifier("csv") ItemReader<Person> personItemReader,
		            @Qualifier("json") ItemWriter<Person> personItemWriter ,
		            SimpleUppercasePersonProcessor simpleUppercasePersonProcessor) {
    var name = "stepCsvToJson";//same as method name for simple mind developper
    var stepBuilder = new StepBuilder(name, jobRepository);
    return stepBuilder
        .<Person, Person>chunk(5, batchTxManager)
        .reader(personItemReader)
        .processor(simpleUppercasePersonProcessor)
        .writer(personItemWriter)
        .build();
  }
 
  

}