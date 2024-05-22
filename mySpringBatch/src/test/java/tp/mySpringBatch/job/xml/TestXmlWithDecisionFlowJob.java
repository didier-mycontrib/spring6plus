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
import tp.mySpringBatch.job.AbstractBasicActiveTestJob;
import tp.mySpringBatch.processor.SimpleUppercasePersonProcessor;
import tp.mySpringBatch.reader.java.MyCsvFilePersonReaderConfig;
import tp.mySpringBatch.step.java.StepCsvWithSkipsErrorsToJsonConfig;
import tp.mySpringBatch.writer.java.MyJsonFilePersonWriterConfig;


@Configuration
@EnableAutoConfiguration //springBoot & spring-boot-starter-batch autoConfig (application.properties)
@Import({AutomaticSpringBootBatchJobRepositoryConfig.class})
@ComponentScan(basePackages = "tp.mySpringBatch.processor")
@ImportResource({"classpath:job/listenersCommonSubConfig.xml",
				"classpath:job/csv_json_xml_CommonSubConfig.xml",
				"classpath:job/myCommonPrintTasklets.xml",
				"classpath:job/myFlowDecisionJob.xml"})
class WithDecisionFlowXmlTestConfig{
}

@SpringBatchTest
@SpringBootTest(classes = { WithDecisionFlowXmlTestConfig.class } )
@ActiveProfiles(profiles = {"xmlJobConfig"})
public class TestXmlWithDecisionFlowJob extends AbstractBasicActiveTestJob {


}
