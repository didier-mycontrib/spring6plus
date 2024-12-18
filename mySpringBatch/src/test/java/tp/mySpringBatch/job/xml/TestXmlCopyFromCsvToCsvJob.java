package tp.mySpringBatch.job.xml;

import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ActiveProfiles;

import tp.mySpringBatch.config.AutomaticSpringBootBatchJobRepositoryConfig;
import tp.mySpringBatch.job.AbstractBasicActiveTestJob;
import tp.mySpringBatch.processor.SimpleUppercasePersonProcessor;
import tp.mySpringBatch.reader.java.MyCsvFilePersonReaderConfig;
import tp.mySpringBatch.writer.java.MyCsvFilePersonWriterConfig;


@Configuration
@EnableAutoConfiguration //springBoot & spring-boot-starter-batch autoConfig (application.properties)
@Import({AutomaticSpringBootBatchJobRepositoryConfig.class
	})
@ComponentScan(basePackages = "tp.mySpringBatch.processor")
@ImportResource({"classpath:job/listenersCommonSubConfig.xml",
				"classpath:job/csv_json_xml_CommonSubConfig.xml",
				"classpath:job/copyFromCsvToCsvJob.xml"})
class CopyFromCsvToCsvXmlTestConfig{
}

@SpringBatchTest
@SpringBootTest(classes = { CopyFromCsvToCsvXmlTestConfig.class } )
@ActiveProfiles(profiles = {"xmlJobConfig"})
public class TestXmlCopyFromCsvToCsvJob extends AbstractBasicActiveTestJob {

	@Override
	public JobParametersBuilder initJobParametersWithBuilder(JobParametersBuilder jobParametersBuilder) {
		return jobParametersBuilder
		.addString("msg1", "_my_msg1_value_")//used by PrintJobParamMessageTaskletBean and some Reader/Writer
        .addString("enableUpperCase", "true");//used by SimpleUppercasePersonProcessor
	}
	
	@Override
	public void postJobCheckings(){
	   this.verifSameContentExceptedResultFile(
			   "data/expected_output/csv/outputDataV1.csv", 
			   "data/output/csv/outputData.csv");
	}

}
