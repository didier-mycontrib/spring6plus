package tp.mySpringBatch.job.java;

import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import tp.mySpringBatch.config.AutomaticSpringBootBatchJobRepositoryConfig;
import tp.mySpringBatch.datasource.MyInputDbDataSourceConfig;
import tp.mySpringBatch.datasource.MyOutputDbDataSourceConfig;
import tp.mySpringBatch.job.AbstractBasicActiveTestJob;
import tp.mySpringBatch.reader.java.MyDbPersonReaderConfig;
import tp.mySpringBatch.writer.java.MyCsvFilePersonWriterConfig;


@Configuration
@EnableAutoConfiguration //springBoot & spring-boot-starter-batch autoConfig (application.properties)
@Import({AutomaticSpringBootBatchJobRepositoryConfig.class,
	DatabaseToCsvJobConfig.class ,
	MyCsvFilePersonWriterConfig.class ,
	MyDbPersonReaderConfig.class,
	MyInputDbDataSourceConfig.class,
	MyOutputDbDataSourceConfig.class
	})
class InsertIntoCsvFromDBTestConfig{
}

@SpringBatchTest
@SpringBootTest(classes = { InsertIntoCsvFromDBTestConfig.class } )
@ActiveProfiles(profiles = {})
public class TestInsertIntoCsvFromDBJob extends AbstractBasicActiveTestJob {
	
	@Override
	public void postJobCheckings(){
	   this.verifSameContentExceptedResultFile(
			   "data/expected_output/csv/outputDataV2.csv", 
			   "data/output/csv/outputData.csv");
	}


}
