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
import tp.mySpringBatch.tasklet.bean.PrintJobParamMessageTaskletBean;


@Configuration
@EnableAutoConfiguration //springBoot & spring-boot-starter-batch autoConfig (application.properties)
@Import({AutomaticSpringBootBatchJobRepositoryConfig.class,
	     HelloWorldWithParameterJobConfig.class ,
	    SimplePrintMessageStepConfig.class ,
	    PrintHelloWorldMessageTaskletBean.class,
		PrintJobParamMessageTaskletBean.class})
class HelloWorldJobWithParametersTestConfig{
}

@SpringBatchTest
@SpringBootTest(classes = { HelloWorldJobWithParametersTestConfig.class } )
@ActiveProfiles(profiles = {})
public class TestHelloWorldJobWithParameters extends AbstractBasicActiveTestJob {

	@Override
	public JobParametersBuilder initJobParametersWithBuilder(JobParametersBuilder jobParametersBuilder) {
		return jobParametersBuilder
		.addString("msg1", "_my_msg1_value_")//used by PrintJobParamMessageTaskletBean and some Reader/Writer
        .addString("enableUpperCase", "true");//used by SimpleUppercasePersonProcessor
	}


}


/*
FileSystemResource expectedResult = new FileSystemResource(EXPECTED_OUTPUT);
FileSystemResource actualResult = new FileSystemResource(TEST_OUTPUT);
AssertFile.assertFileEquals(expectedResult, actualResult);
*/