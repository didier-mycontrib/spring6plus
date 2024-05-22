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
import tp.mySpringBatch.reader.java.MyCsvFilePersonReaderConfig;
import tp.mySpringBatch.reader.java.MyDbPersonReaderConfig;
import tp.mySpringBatch.writer.java.MyDbPersonWriterConfig;


@Configuration
@EnableAutoConfiguration //springBoot & spring-boot-starter-batch autoConfig (application.properties)
@Import({AutomaticSpringBootBatchJobRepositoryConfig.class,
	CsvToDatabaseJobConfig.class ,
	MyDbPersonWriterConfig.class ,
	MyDbPersonReaderConfig.class,
	MyCsvFilePersonReaderConfig.class,
	MyOutputDbDataSourceConfig.class,
	MyInputDbDataSourceConfig.class
	})
class InsertIntoDbFromCsvTestConfig{
}

@SpringBatchTest
@SpringBootTest(classes = { InsertIntoDbFromCsvTestConfig.class } )
@ActiveProfiles(profiles = {})
public class TestInsertIntoDbFromCsvJob extends AbstractBasicActiveTestJob {


}
