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
import tp.mySpringBatch.reader.java.MyDbPersonReaderWithPartitionConfig;
import tp.mySpringBatch.writer.java.MyCsvFilePersonWriterWithPartitionConfig;


@Configuration
@EnableAutoConfiguration //springBoot & spring-boot-starter-batch autoConfig (application.properties)
@Import({AutomaticSpringBootBatchJobRepositoryConfig.class,
	MyInputDbDataSourceConfig.class,
	MyOutputDbDataSourceConfig.class
	})
@ComponentScan(basePackages = "tp.mySpringBatch.processor")
@ImportResource({"classpath:job/listenersCommonSubConfig.xml",
				"classpath:job/csv_json_xml_CommonSubConfig.xml",
				"classpath:job/jdbcCommonSubConfig.xml",
				"classpath:job/myPartitionJob.xml"})
class MyPartitionJobXmlTestConfig{
}

@SpringBatchTest
@SpringBootTest(classes = { MyPartitionJobXmlTestConfig.class } )
@ActiveProfiles(profiles = {"xmlJobConfig"})
public class TestXmlMyPartitionJob extends AbstractBasicActiveTestJob {

	@Override
	public void postJobCheckings(){
	   this.verifSameContentExceptedResultFile(
			   "data/expected_output/csv/outputData_1-5.csv", 
			   "data/output/csv/outputData_1-5.csv");
	   this.verifSameContentExceptedResultFile(
			   "data/expected_output/csv/outputData_6-10.csv", 
			   "data/output/csv/outputData_6-10.csv");
	   this.verifSameContentExceptedResultFile(
			   "data/expected_output/csv/outputData_11-15.csv", 
			   "data/output/csv/outputData_11-15.csv");
	   this.verifSameContentExceptedResultFile(
			   "data/expected_output/csv/outputData_16-20.csv", 
			   "data/output/csv/outputData_16-20.csv");
	}


}
