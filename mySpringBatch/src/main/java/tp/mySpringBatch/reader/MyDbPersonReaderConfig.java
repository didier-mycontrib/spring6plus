package tp.mySpringBatch.reader;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import tp.mySpringBatch.db.PersonRowMapper;
import tp.mySpringBatch.model.Person;

@Configuration
public class MyDbPersonReaderConfig {
	
	/*
	 NB: in application.properties
	 NOT spring.datasource.url=jdbc:h2:mem:inputDb
	 BUT spring.inputdb.datasource.url=jdbc:h2:~/inputDb
	 and spring.inputdb.datasource.username=sa
         spring.inputdb.datasource.password=
	 */
	@Bean @Qualifier("inputdb")
  @ConfigurationProperties("spring.inputdb.datasource")
  public DataSourceProperties inputdbDataSourceProperties() {
      return new DataSourceProperties();
  }
	
	@Bean(name="inputdbDataSource") @Qualifier("inputdb")
	@BatchDataSource
	public DataSource inputdbDataSource(@Qualifier("inputdb") DataSourceProperties inputdbDataSourceProperties) {
	    return inputdbDataSourceProperties
	      .initializeDataSourceBuilder()
	      .build();
	}
	
	
	private static final String SELECT_QUERY = "select first_name, last_name, age, is_active from person"	;
	
	 //with builder:
	 @Bean @Qualifier("db")
	  ItemReader<Person> jdbcPersonReader(@Qualifier("inputdb") DataSource inputdbDataSource) {
			 return new JdbcCursorItemReaderBuilder<Person>()
			  .name("jdbcPersonReader")
			  .dataSource(inputdbDataSource)
			  .sql(SELECT_QUERY)
			  .rowMapper(new PersonRowMapper())
			  .build();
		
		}
	 
	 //TEMPORAIRE(premier lancement) pour préparer (créer) la table et un jeu de données dans la base inputdb
	 //old inputDatabaseInitializer() now replaced by db.MyInputDbInitializer
	
}
