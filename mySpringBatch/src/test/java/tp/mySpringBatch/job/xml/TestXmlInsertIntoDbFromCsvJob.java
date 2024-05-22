package tp.mySpringBatch.job.xml;

import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
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
	MyInputDbDataSourceConfig.class,
	MyOutputDbDataSourceConfig.class
})
@ComponentScan(basePackages = "tp.mySpringBatch.processor")
@ImportResource({"classpath:job/listenersCommonSubConfig.xml",
	        "classpath:job/jdbcCommonSubConfig.xml",
			"classpath:job/csv_json_xml_CommonSubConfig.xml",
			"classpath:job/insertIntoDbFromCsvJob.xml"})
class InsertIntoDbFromCsvXmlTestConfig{
}

@SpringBatchTest
@SpringBootTest(classes = { InsertIntoDbFromCsvXmlTestConfig.class } )
@ActiveProfiles(profiles = {"xmlJobConfig"})
public class TestXmlInsertIntoDbFromCsvJob extends AbstractBasicActiveTestJob {


}
