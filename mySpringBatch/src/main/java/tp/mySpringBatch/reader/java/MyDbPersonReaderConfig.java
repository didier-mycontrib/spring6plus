package tp.mySpringBatch.reader.java;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import tp.mySpringBatch.db.PersonRowMapper;
import tp.mySpringBatch.model.Person;

@Configuration
@Profile("!xmlJobConfig")
public class MyDbPersonReaderConfig {
	
	private static final String SELECT_QUERY = "select id, first_name, last_name, age, is_active from person"	;
	
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
	 
	 @Bean @Qualifier("db_withPartition")
	  ItemReader<Person> jdbcPartitionPersonReader(@Qualifier("inputdb") DataSource inputdbDataSource) throws Exception {
		 
		     SqlPagingQueryProviderFactoryBean pagingQueryProviderFactory = new SqlPagingQueryProviderFactoryBean();
		     pagingQueryProviderFactory.setDataSource(inputdbDataSource);
		     pagingQueryProviderFactory.setFromClause("select id, first_name, last_name, age, is_active");
		     pagingQueryProviderFactory.setFromClause("from person");
		     pagingQueryProviderFactory.setWhereClause("where id &gt;= :fromId and id &lt;= :toId");
		     pagingQueryProviderFactory.setSortKey("id");
		     PagingQueryProvider pagingQueryProvider = pagingQueryProviderFactory.getObject();
		    		 
		     Map<String,Object> parameterValues = new HashMap<>();
		     parameterValues.put("fromId", "#{stepExecutionContext[fromId]}");
		     parameterValues.put("toId", "#{stepExecutionContext[toId]}");
		 
			 return new JdbcPagingItemReaderBuilder<Person>()
			  .name("jdbcPartitionPersonReader")
			  .dataSource(inputdbDataSource)
			  .queryProvider(pagingQueryProvider)
			  .parameterValues(parameterValues)
			  .pageSize(5)
			  .rowMapper(new PersonRowMapper())
			  .build();
		
		}
	 
	 //TEMPORAIRE(premier lancement) pour préparer (créer) la table et un jeu de données dans la base inputdb
	 //old inputDatabaseInitializer() now replaced by db.MyInputDbInitializer
	
}
