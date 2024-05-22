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
import tp.mySpringBatch.processor.SimpleUppercasePersonProcessor;
import tp.mySpringBatch.reader.java.MyMemoryJobContextPersonReaderConfig;
import tp.mySpringBatch.writer.java.MyCsvFilePersonWriterConfig;


@Configuration
@EnableAutoConfiguration //springBoot & spring-boot-starter-batch autoConfig (application.properties)
@Import({AutomaticSpringBootBatchJobRepositoryConfig.class,
	     WithTaskletJobConfig.class ,
	     SimpleUppercasePersonProcessor.class ,
	     MyMemoryJobContextPersonReaderConfig.class,
	    MyCsvFilePersonWriterConfig.class})
class WithMyTaskletJobTestConfig{
}

@SpringBatchTest
@SpringBootTest(classes = { WithMyTaskletJobTestConfig.class } )
@ActiveProfiles(profiles = {})
public class TestWithMyTaskletJob extends AbstractBasicActiveTestJob {

	@Override
	public JobParametersBuilder initJobParametersWithBuilder(JobParametersBuilder jobParametersBuilder) {
		return jobParametersBuilder
		.addString("msg1", "_my_msg1_value_")//used by PrintJobParamMessageTaskletBean and some Reader/Writer
        .addString("enableUpperCase", "true");//used by SimpleUppercasePersonProcessor
	}

}
