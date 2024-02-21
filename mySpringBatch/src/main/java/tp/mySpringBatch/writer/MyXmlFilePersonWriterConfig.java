package tp.mySpringBatch.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.xml.builder.StaxEventItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.WritableResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import tp.mySpringBatch.model.Person;

@Configuration
public class MyXmlFilePersonWriterConfig {


	  @Value("file:data/output/xml/outputData.xml") //to read in project root directory
	  //NB: by default @Value(path) is @Value("classpath:path) //to read in src/main/resource or other classpath part
	  private WritableResource outputXmlResource;
	 
	  /*
	  //V1 sans builder
	  @Bean @Qualifier("xml")
	  ItemWriter<Person> personXmlFileItemWriter() {

		  //Create writer instance
		  StaxEventItemWriter<Person> itemWriter = 
		          new StaxEventItemWriter<Person>();
		  
		  var personXmlMarshaller = new Jaxb2Marshaller();
		  personXmlMarshaller.setClassesToBeBound(new Class[] { Person.class });

		  itemWriter.setMarshaller(personXmlMarshaller);
	      itemWriter.setRootTagName("persons");
	      itemWriter.setResource(outputXmlResource);
	      return itemWriter;
		}
		*/
	  
	  //V2 avec builder:
	  @Bean @Qualifier("xml")
	  ItemWriter<Person> personXmlFileItemWriter() {
		  var personXmlMarshaller = new Jaxb2Marshaller();
		  personXmlMarshaller.setClassesToBeBound(new Class[] { Person.class });
		  
		  return new StaxEventItemWriterBuilder<Person>()
		  .name("personXmlFileItemWriter")
		  .marshaller(personXmlMarshaller)
	      .rootTagName("persons")
	      .resource(outputXmlResource)
	      .build();
		}
}
