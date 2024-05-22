package tp.mySpringBatch.job.java;

import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import tp.mySpringBatch.config.AutomaticSpringBootBatchJobRepositoryConfig;
import tp.mySpringBatch.job.AbstractBasicActiveTestJob;
import tp.mySpringBatch.processor.SimpleUppercasePersonProcessor;
import tp.mySpringBatch.reader.java.MyCsvFilePersonReaderConfig;
import tp.mySpringBatch.step.java.StepCsvWithSkipsErrorsToJsonConfig;
import tp.mySpringBatch.writer.java.MyJsonFilePersonWriterConfig;


@Configuration
@EnableAutoConfiguration //springBoot & spring-boot-starter-batch autoConfig (application.properties)
@Import({AutomaticSpringBootBatchJobRepositoryConfig.class,
	MyDecisionFlowJobConfig.class,
	StepCsvWithSkipsErrorsToJsonConfig.class,
	MyJsonFilePersonWriterConfig.class ,
	MyCsvFilePersonReaderConfig.class,
	SimpleUppercasePersonProcessor.class,
	})
class WithDecisionFlowTestConfig{
}

@SpringBatchTest
@SpringBootTest(classes = { WithDecisionFlowTestConfig.class } )
@ActiveProfiles(profiles = {})
public class TestWithDecisionFlowJob extends AbstractBasicActiveTestJob {


}
