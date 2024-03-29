package tp.mySpringBatch.writer;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.FieldExtractor;
import org.springframework.batch.item.file.transform.FormatterLineAggregator;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.WritableResource;

import tp.mySpringBatch.model.Person;

@Configuration
public class MyFixedPosTxtFilePersonWriterConfig {

	  
	 
	  
	  @Value("file:data/output/txt/fixedPositionOutputData.txt") //to read in project root directory
	  //NB: by default @Value(path) is @Value("classpath:path) //to read in src/main/resource or other classpath part
	  private WritableResource outputTxtResource;

	  // V2 wia builder:
	  @Bean @Qualifier("fixedPosTxt")
	  FlatFileItemWriter<Person> fixedPosTxtFilePersonWriter() {
		  return new FlatFileItemWriterBuilder<Person>()
				  .name("fixedPosTxtFilePersonWriter")
				  .resource(outputTxtResource)
				  .formatted()
				  .maximumLength(58)
				  .minimumLength(58)
				  .format("%-24s%-24s%-4d%-6b")
				  .names("firstName", "lastName", "age", "active")
				  .build();
	  }
	  
	  /*
      // V1 without builder:
	  @Bean @Qualifier("fixedPosTxt")
	  FlatFileItemWriter<Person> fixedPosTxtFilePersonWriter() {
		 
		  //Create writer instance
		  FlatFileItemWriter<Person> writer = new FlatFileItemWriter<>();

		  //Set output file location
		  //WritableResource outputCsvResource = new FileSystemResource("data/output/csv/outputData.csv");
		  writer.setResource(outputTxtResource);

		  //All job repetitions should "append" to same output file
		  //writer.setAppendAllowed(true);
		  writer.setAppendAllowed(false);

		  writer.setLineAggregator(this.personLineAggregator(this.personFieldExtractor()));
		  return writer;
		}
	  
	  FieldExtractor<Person> personFieldExtractor(){
		  BeanWrapperFieldExtractor<Person> beanWrapperFieldExtractor = new BeanWrapperFieldExtractor<>();
		  beanWrapperFieldExtractor.setNames(new String[]{"firstName", "lastName", "age", "active"});
	      return beanWrapperFieldExtractor;
	  }
	  
	  FormatterLineAggregator<Person> personLineAggregator(FieldExtractor<Person> personFieldExtractor){
	
		  FormatterLineAggregator<Person> formatterLineAggregator = new FormatterLineAggregator<>();
		  formatterLineAggregator.setMinimumLength(58);
		  formatterLineAggregator.setMaximumLength(58);
		  formatterLineAggregator.setFormat("%-24s%-24s%-4d%-6b");
		  formatterLineAggregator.setFieldExtractor(personFieldExtractor);
	      return formatterLineAggregator;
	  }
	*/

}
