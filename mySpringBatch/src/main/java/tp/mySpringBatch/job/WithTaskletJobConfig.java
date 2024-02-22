package tp.mySpringBatch.job;

import java.util.ArrayList;
import java.util.Arrays;

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
import tp.mySpringBatch.tasklet.StoreDataListInJobExecutionContextTasklet;

@Configuration
public class WithTaskletJobConfig extends MyAbstractJobConfig{

  public static final Logger logger = LoggerFactory.getLogger(WithTaskletJobConfig.class);
 

  @Bean
  public Job withMyTaskletJob(@Qualifier("jobContextToCsv") Step step2) {
    var name = "withMyTaskletJob";
    var jobBuilder = new JobBuilder(name, jobRepository);
    return jobBuilder.start(this.generatePersonsStep())
    		.next(step2)
    		.listener(new JobCompletionNotificationListener())
    		.build();
  }
  
  private Step generatePersonsStep(){
	    var name = "generatePersonsStep";
	    var stepBuilder = new StepBuilder(name, jobRepository);
	    var readOnlyPersonList = Arrays.asList(
	    		new Person("Paul","Ochon", 34, true),
	    		new Person("Laurent","Houtan", 29, false),
	    		new Person("Alain","Verse", 42, false),
	    		new Person("Tristan","Douille", 42, false)
	    		);
	    var personList = new ArrayList<Person>(readOnlyPersonList);
	    return stepBuilder
	        .tasklet(new StoreDataListInJobExecutionContextTasklet<Person>(personList), this.batchTxManager)
	        .build();
	  }

  @Bean @Qualifier("jobContextToCsv")
  public Step stepJobExecutionContextToCsv(@Qualifier("fromJobExecutionContext") ItemReader<Person>  personItemReader,
		                   @Qualifier("csv") ItemWriter<Person> personItemWriter ,
				            SimpleUppercasePersonProcessor simpleUppercasePersonProcessor ) {
    var name = "COPY Generated RECORDS into JobExecutionContext To  CSV Step";
    var stepBuilder = new StepBuilder(name, jobRepository);
    return stepBuilder
        .<Person, Person>chunk(5, batchTxManager)
        .reader(personItemReader)
        .processor(simpleUppercasePersonProcessor)
        .writer(personItemWriter)
        .build();
  }
  
  

}