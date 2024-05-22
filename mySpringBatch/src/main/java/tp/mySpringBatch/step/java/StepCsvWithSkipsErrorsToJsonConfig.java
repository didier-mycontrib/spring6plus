package tp.mySpringBatch.step.java;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemReaderException;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import tp.mySpringBatch.listener.MySkippedErrorsListener;
import tp.mySpringBatch.model.Person;
import tp.mySpringBatch.processor.SimpleUppercasePersonProcessor;

@Configuration
@Profile("!xmlJobConfig")
public class StepCsvWithSkipsErrorsToJsonConfig extends MyAbstractStepConfig {

	
	@Bean @Qualifier("csvWithSkipsErrorsToJson")
	  public Step stepCsvWithSkipsErrorsToJson(@Qualifier("csvWithErrors") ItemReader<Person> personItemReader,
			            @Qualifier("json") ItemWriter<Person> personItemWriter ,
			            SimpleUppercasePersonProcessor simpleUppercasePersonProcessor) {
	    var name = "stepCsvWithSkipsErrorsToJson";
	    var stepBuilder = new StepBuilder(name, jobRepository);
	    return stepBuilder
	        .<Person, Person>chunk(5, batchTxManager)
	        .listener(new MySkippedErrorsListener())
	        .reader(personItemReader)
	        .processor(simpleUppercasePersonProcessor)
	        .writer(personItemWriter)
	        .faultTolerant().skipLimit(5).skip(ItemReaderException.class) //IMPORTANT
	        .listener(new MySkippedErrorsListener())//IMPORTANT
	        .build();
	    
	    //NB: .skipLimit(globalInclusiveMaxLimit) , not consecutive error count but global count
	  }
	  
}
