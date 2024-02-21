package tp.mySpringBatch.writer;

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
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.WritableResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import tp.mySpringBatch.model.Person;

@Configuration
public class MyDbPersonWriterConfig {
	
	/*
	 NB: in application.properties
	 NOT spring.datasource.url=jdbc:h2:mem:outputDb
	 BUT spring.outputdb.datasource.url=jdbc:h2:~/outputDb
	 and spring.outputdb.datasource.username=sa
         spring.outputdb.datasource.password=
	 */
	@Bean @Qualifier("outputdb")
   @ConfigurationProperties("spring.outputdb.datasource")
   public DataSourceProperties outputdbDataSourceProperties() {
       return new DataSourceProperties();
   }
	
	@Bean(name="outputdbDataSource") @Qualifier("outputdb")
	@BatchDataSource
	public DataSource outputdbDataSource(@Qualifier("outputdb") DataSourceProperties outputdbDataSourceProperties) {
	    return outputdbDataSourceProperties
	      .initializeDataSourceBuilder()
	      .build();
	}

	  
	private static final String INSERT_QUERY = """
	      insert into person (first_name, last_name, age, is_active)
	      values (:firstName,:lastName,:age,:active)""";
	 

	//pour préparer (créer) la table dans la base outputdb
	 @Bean
	  public DataSourceInitializer databaseInitializer(@Qualifier("outputdb") DataSource outputdbDataSource) {
	    ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
	    populator.addScript(new ClassPathResource("sql/batch-schema.sql"));
	    populator.setContinueOnError(false);
	    populator.setIgnoreFailedDrops(false);
	    DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
	    dataSourceInitializer.setDataSource(outputdbDataSource);
	    dataSourceInitializer.setDatabasePopulator(populator);
	    return dataSourceInitializer;
	  }

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
	
}
