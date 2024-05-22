package tp.mySpringBatch.job.java;

import org.springframework.batch.core.JobParametersBuilder;
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
	    HelloWorldJobConfig.class ,
	    SimplePrintMessageStepConfig.class ,
		PrintHelloWorldMessageTaskletBean.class})
class HelloWorldJobTestConfig{
}

@SpringBatchTest
@SpringBootTest(classes = { HelloWorldJobTestConfig.class } )
@ActiveProfiles(profiles = {})
public class TestHelloWorldJob extends AbstractBasicActiveTestJob {

	@Override
	public JobParametersBuilder initJobParametersWithBuilder(JobParametersBuilder jobParametersBuilder) {
		return jobParametersBuilder
		.addString("msg1", "_my_msg1_value_")//used by PrintJobParamMessageTaskletBean and some Reader/Writer
        .addString("enableUpperCase", "true");//used by SimpleUppercasePersonProcessor
	}
}
