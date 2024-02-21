package tp.mySpringBatch.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldExtractor;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.transaction.PlatformTransactionManager;

import tp.mySpringBatch.listener.JobCompletionNotificationListener;
import tp.mySpringBatch.model.Person;
import tp.mySpringBatch.processor.SimpleUppercasePersonProcessor;

@Configuration
public class MyFixedPosTxtToCsvJobConfig extends MyAbstractJobConfig{

  public static final Logger logger = LoggerFactory.getLogger(MyFixedPosTxtToCsvJobConfig.class);
  
  @Bean
  public Job fromFixedPosTxtToCsvJob(@Qualifier("fixedPosTxtToCsv") Step step1) {
    return this.buildMySingleStepJob("fromFixedPosTxtToCsvJob", step1);
  }

  @Bean @Qualifier("fixedPosTxtToCsv")
  public Step stepFixedPosTxtToCsv(@Qualifier("fixedPosTxt") ItemReader<Person> personItemReader,
		                           @Qualifier("csv") ItemWriter<Person> personItemWriter  ) {
    var name = "COPY CSV RECORDS To another CSV Step";
    var stepBuilder = new StepBuilder(name, jobRepository);
    return stepBuilder
        .<Person, Person>chunk(5, batchTxManager)
        .reader(personItemReader)
        .processor(SimpleUppercasePersonProcessor.INSTANCE)
        .writer(personItemWriter)
        .build();
  }
  
  

}