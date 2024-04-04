package tp.mySpringBatch.reader.java;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import tp.mySpringBatch.db.PersonRowMapper;
import tp.mySpringBatch.model.Person;

@Configuration
@Profile("!xmlJobConfig")
public class MyDbPersonReaderConfig {
	
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
