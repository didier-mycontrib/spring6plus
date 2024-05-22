package tp.mySpringBatch.job.xml;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ActiveProfiles;

import tp.mySpringBatch.config.AutomaticSpringBootBatchJobRepositoryConfig;
import tp.mySpringBatch.datasource.MyInputDbDataSourceConfig;
import tp.mySpringBatch.datasource.MyOutputDbDataSourceConfig;
import tp.mySpringBatch.job.AbstractBasicActiveTestJob;


@Configuration
@EnableAutoConfiguration //springBoot & spring-boot-starter-batch autoConfig (application.properties)
@Import({ AutomaticSpringBootBatchJobRepositoryConfig.class /*, 
	      MyInputDbDataSourceConfig.class , 
	      MyOutputDbDataSourceConfig.class*/ })
@ImportResource({"classpath:job/listenersCommonSubConfig.xml",
				"classpath:job/myHelloWorldWithParameterJob.xml"})
class HelloWorldJobWithParametersXmlTestConfig{
	
}

@SpringBatchTest
@SpringBootTest(classes = { HelloWorldJobWithParametersXmlTestConfig.class } )
@ActiveProfiles(profiles = {"xmlJobConfig"})
public class TestXmlHelloWorldJobWithParameters extends AbstractBasicActiveTestJob {
	
	@Override
	public JobParametersBuilder initJobParametersWithBuilder(JobParametersBuilder jobParametersBuilder) {
		return jobParametersBuilder
		.addString("msg1", "_my_msg1_value_")//used by PrintJobParamMessageTaskletBean and some Reader/Writer
        .addString("enableUpperCase", "true");//used by SimpleUppercasePersonProcessor
	}
}
