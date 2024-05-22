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
import tp.mySpringBatch.step.java.SimplePrintMessageStepConfig;
import tp.mySpringBatch.tasklet.bean.PrintHelloWorldMessageTaskletBean;


@Configuration
@EnableAutoConfiguration //springBoot & spring-boot-starter-batch autoConfig (application.properties)
@Import({AutomaticSpringBootBatchJobRepositoryConfig.class})
@ComponentScan(basePackages = "tp.mySpringBatch.processor")
@ImportResource({"classpath:job/listenersCommonSubConfig.xml",
				"classpath:job/myCommonPrintTasklets.xml",
				"classpath:job/myFlowConditionalJob.xml"})
class SimpleConditionalStepsXmlTestConfig{
}

@SpringBatchTest
@SpringBootTest(classes = { SimpleConditionalStepsXmlTestConfig.class } )
@ActiveProfiles(profiles = {"xmlJobConfig"})
public class TestXmlSimpleConditionalStepsJob extends AbstractBasicActiveTestJob {


}
