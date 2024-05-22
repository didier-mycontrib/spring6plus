package tp.mySpringBatch.job.java;

import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import tp.mySpringBatch.config.AutomaticSpringBootBatchJobRepositoryConfig;
import tp.mySpringBatch.job.AbstractBasicActiveTestJob;
import tp.mySpringBatch.step.java.SimplePrintMessageStepConfig;
import tp.mySpringBatch.tasklet.bean.PrintHelloWorldMessageTaskletBean;


@Configuration
@EnableAutoConfiguration //springBoot & spring-boot-starter-batch autoConfig (application.properties)
@Import({AutomaticSpringBootBatchJobRepositoryConfig.class,
	MyConditionalFlowJobConfig.class})
class SimpleConditionalStepsTestConfig{
}

@SpringBatchTest
@SpringBootTest(classes = { SimpleConditionalStepsTestConfig.class } )
@ActiveProfiles(profiles = {})
public class TestSimpleConditionalStepsJob extends AbstractBasicActiveTestJob {


}
