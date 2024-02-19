package tp.mySpringBatch.job;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;

import tp.mySpringBatch.listener.JobCompletionNotificationListener;
import tp.mySpringBatch.model.Person;

@Configuration
public class CsvToDatabaseJob {

  public static final Logger logger = LoggerFactory.getLogger(CsvToDatabaseJob.class);
 

  private final JobRepository jobRepository;

  public CsvToDatabaseJob(JobRepository jobRepository) {
    this.jobRepository = jobRepository;
  }


  @Bean(name="insertIntoDbFromCsvJob")
  public Job insertIntoDbFromCsvJob(@Qualifier("csvToDb") Step step1) {
    var name = "Persons Import Job";
    var builder = new JobBuilder(name, jobRepository);
    return builder.start(step1).listener(new JobCompletionNotificationListener()).build();
  }

  @Bean @Qualifier("csvToDb")
  public Step stepCsvToDb(ItemReader<Person> reader,
		            @Qualifier("db") ItemWriter<Person> writer,
                    @Qualifier("batch") PlatformTransactionManager txManager) {
    var name = "INSERT CSV RECORDS To DB Step";
    var builder = new StepBuilder(name, jobRepository);
    return builder
        .<Person, Person>chunk(5, txManager)
        .reader(reader)
        .writer(writer)
        .build();
  }


}