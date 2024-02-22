package tp.mySpringBatch.job;

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

import tp.mySpringBatch.listener.JobCompletionNotificationListener;
import tp.mySpringBatch.model.Person;
import tp.mySpringBatch.processor.SimpleUppercasePersonProcessor;

@Configuration
public class MyBasicJsonToXmlJobConfig extends MyAbstractJobConfig{

  public static final Logger logger = LoggerFactory.getLogger(MyBasicJsonToXmlJobConfig.class);


  @Bean
  public Job fromJsonToXmlJob(@Qualifier("jsonToXml") Step step1) {
    var name = "Persons jsonToXml Job";
    var jobBuilder = new JobBuilder(name, jobRepository);
    return jobBuilder.start(step1)
    		.listener(new JobCompletionNotificationListener())
    		.build();
  }

  @Bean @Qualifier("jsonToXml")
  public Step stepJsonToXml(@Qualifier("json") ItemReader<Person> personItemReader,
		                   @Qualifier("xml") ItemWriter<Person> personItemWriter,
				            SimpleUppercasePersonProcessor simpleUppercasePersonProcessor) {
    var name = "COPY json RECORDS To  xml Step";
    var stepBuilder = new StepBuilder(name, jobRepository);
    return stepBuilder
        .<Person, Person>chunk(5, batchTxManager)
        .reader(personItemReader)
        .processor(simpleUppercasePersonProcessor)
        .writer(personItemWriter)
        .build();
  }
  
  

}