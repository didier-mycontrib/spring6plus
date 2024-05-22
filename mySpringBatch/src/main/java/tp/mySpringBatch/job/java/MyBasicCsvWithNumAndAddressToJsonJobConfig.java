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
import tp.mySpringBatch.model.PersonWithNumAndAddress;

@Configuration
@Profile("!xmlJobConfig")
public class MyBasicCsvWithNumAndAddressToJsonJobConfig extends MyAbstractJobConfig{

  public static final Logger logger = LoggerFactory.getLogger(MyBasicCsvWithNumAndAddressToJsonJobConfig.class);

  
  @Bean
  public Job fromCsvWithNumAndAddressToJsonJob(@Qualifier("csvWithNumAndAddressToJson") Step step1) {
    var name = "fromCsvWithNumAndAddressToJsonJob";//same as method name for simple mind developper
    var jobBuilder = new JobBuilder(name, jobRepository);
    return jobBuilder.start(step1)
    		.listener(new JobCompletionNotificationListener())
    		.build();
  }

  
  @Bean @Qualifier("csvWithNumAndAddressToJson")
  public Step stepCsvWithNumAndAddressToJson(@Qualifier("csv") ItemReader<PersonWithNumAndAddress> personItemReader,
		            @Qualifier("json") ItemWriter<PersonWithNumAndAddress> personItemWriter ) {
    var name = "stepCsvWithNumAndAddressToJson";//same as method name for simple mind developper
    var stepBuilder = new StepBuilder(name, jobRepository);
    return stepBuilder
        .<PersonWithNumAndAddress, PersonWithNumAndAddress>chunk(5, batchTxManager)
        .reader(personItemReader)
        .writer(personItemWriter)
        .build();
  }
  

}