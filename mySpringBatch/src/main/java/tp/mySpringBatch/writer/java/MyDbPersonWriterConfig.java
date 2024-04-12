package tp.mySpringBatch.writer.java;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.WritableResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import tp.mySpringBatch.model.Person;

@Configuration
@Profile("!xmlJobConfig")
public class MyDbPersonWriterConfig {

	  
	private static final String INSERT_QUERY = """
	      insert into person (first_name, last_name, age, is_active)
	      values (:firstName,:lastName,:age,:active)""";
	 

	/*
      //V1 (sans builder):
	  @Bean @Qualifier("db")
	  public JdbcBatchItemWriter<Person> jdbcItemWriter(@Qualifier("outputdb") DataSource outputdbDataSource) {
	    var provider = new BeanPropertyItemSqlParameterSourceProvider<Person>();
	    var itemWriter = new JdbcBatchItemWriter<Person>();
	    itemWriter.setDataSource(outputdbDataSource);
	    itemWriter.setSql(INSERT_QUERY);
	    itemWriter.setItemSqlParameterSourceProvider(provider);
	    return itemWriter;
	  }
	  */
	 
	 //V2: avec builder
	 @Bean @Qualifier("db")
	  public JdbcBatchItemWriter<Person> jdbcItemWriter(@Qualifier("outputdb") DataSource outputdbDataSource) {
		 return new JdbcBatchItemWriterBuilder<Person>()
		  .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>())
		  .dataSource(outputdbDataSource)
		  .sql(INSERT_QUERY)
		  .build();
	  }
	 
	 @Bean @Qualifier("generate_db")
	  public JdbcBatchItemWriter<Person> genJdbcItemWriter(@Qualifier("inputdb") DataSource outputdbDataSource) {
		 return new JdbcBatchItemWriterBuilder<Person>()
		  .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>())
		  .dataSource(outputdbDataSource)
		  .sql(INSERT_QUERY)
		  .build();
	  }
	
}
