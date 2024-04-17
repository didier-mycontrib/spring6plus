package tp.mySpringBatch.job.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import tp.mySpringBatch.listener.JobCompletionNotificationListener;
import tp.mySpringBatch.listener.MyRestartableSerieStepExecutionListener;
import tp.mySpringBatch.model.Person;
import tp.mySpringBatch.processor.SimpleUppercasePersonProcessor;
import tp.mySpringBatch.tasklet.CheckSerieTasklet;
import tp.mySpringBatch.writer.custom.SimpleObjectWriter;

@Configuration
@Profile("!xmlJobConfig")
public class WithRestartCsvSeriesToXmlJobConfig extends MyAbstractJobConfig{

  public static final Logger logger = LoggerFactory.getLogger(WithRestartCsvSeriesToXmlJobConfig.class);


  @Bean
  public Job fromPersonSerieCsvToXmlJob(
		  @Qualifier("check-serie") Step step1,
		  @Qualifier("csvPersonSerieToXml") Step step2
		  ) {
    var name = "fromPersonSerieCsvToXmlJob";
    var jobBuilder = new JobBuilder(name, jobRepository);
    return jobBuilder.start(step1)
    		.next(step2)
    		.listener(new JobCompletionNotificationListener())
    		.build();
  }
  
  
  
  @Bean @Qualifier("check-serie")
  public Step stepCheckSerie() {
    var name = "stepCheckSerie";
    var stepBuilder = new StepBuilder(name, jobRepository);
    return stepBuilder
	        .tasklet(new CheckSerieTasklet(), this.batchTxManager)
	        .allowStartIfComplete(true)
	        .build();
  }
  
  
  @Bean @Qualifier("print-serie")
  @StepScope
  public ItemWriter<Object> simpleObjectWriter(
		  @Value("#{stepExecutionContext[sIndex]}") Integer sIndex
		  ) {
	  System.out.println("WWWW simpleObjectWriter: sIndex="+sIndex);
	  return new SimpleObjectWriter();
	}

  @Bean @Qualifier("csvPersonSerieToXml")
  public Step stepPersonSerieCsvToXml(@Qualifier("csv-serie") ItemReader<Person> personItemReader,
		            @Qualifier("csv-serie") ItemWriter<Person> personItemWriter,
		            //@Qualifier("xml-serie") ItemWriter<Person>  personItemWriter,
		            //@Qualifier("print-serie") ItemWriter<Object> personItemWriter,
		            SimpleUppercasePersonProcessor simpleUppercasePersonProcessor) {
    var name = "csvPersonSerieToXml";
    var stepBuilder = new StepBuilder(name, jobRepository);
    return stepBuilder
        .<Person, Person>chunk(5, batchTxManager)
        .startLimit(10)
        .reader(personItemReader)
        .processor(simpleUppercasePersonProcessor)
        .writer(personItemWriter)
        .listener(new MyRestartableSerieStepExecutionListener())
        .build();
  }

  

}