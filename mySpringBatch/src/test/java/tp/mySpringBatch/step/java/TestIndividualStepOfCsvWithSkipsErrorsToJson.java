package tp.mySpringBatch.step.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import tp.mySpringBatch.config.AutomaticSpringBootBatchJobRepositoryConfig;
import tp.mySpringBatch.job.AbstractBasicTestJobHelper;
import tp.mySpringBatch.job.java.MyBasicCsvWithSkipsErrorsToJsonJobConfig;
import tp.mySpringBatch.processor.SimpleUppercasePersonProcessor;
import tp.mySpringBatch.reader.java.MyCsvFilePersonReaderConfig;
import tp.mySpringBatch.writer.java.MyJsonFilePersonWriterConfig;

@Configuration
@EnableAutoConfiguration //springBoot & spring-boot-starter-batch autoConfig (application.properties)
@Import({AutomaticSpringBootBatchJobRepositoryConfig.class,
	MyBasicCsvWithSkipsErrorsToJsonJobConfig.class ,
	StepCsvWithSkipsErrorsToJsonConfig.class,
	MyJsonFilePersonWriterConfig.class ,
	MyCsvFilePersonReaderConfig.class,
	SimpleUppercasePersonProcessor.class
	})
class FromCsvWithSkipsErrorsToJsonTestConfig{
}




@SpringBatchTest
@SpringBootTest(classes = { FromCsvWithSkipsErrorsToJsonTestConfig.class } )
@ActiveProfiles(profiles = {})
public class TestIndividualStepOfCsvWithSkipsErrorsToJson extends AbstractBasicTestJobHelper  {
	

	@Test //unit test of a single/individual Step 
	public void testStepCsvWithSkipsErrorsToJson() throws Exception{
        //even if it is a unit test of an individual unit test , a job must be set in jobLauncherTestUtils
		//this is done by test config (import ...)
		
		JobExecution jobExecution = jobLauncherTestUtils.launchStep(
			      "stepCsvWithSkipsErrorsToJson", initJobParameters()); //excepted set name = full_name of the step integrated in the job
		Collection<StepExecution> actualStepExecutions = jobExecution.getStepExecutions();
		assertTrue(actualStepExecutions.size()==1);
		StepExecution stepExecution = actualStepExecutions.iterator().next();//first of collection
		long skipCount=stepExecution.getReadSkipCount();
		logger.debug("** skipCount="+skipCount );
		assertTrue(skipCount==5); //fichier data/input/inputDataWithErrors.csv with 5 errors
		
		long writeCount=stepExecution.getWriteCount();
		logger.debug("** writeCount="+skipCount );
		assertTrue(writeCount>=5);//fichier data/input/inputDataWithErrors.csv with 5 good lines
		                          //so data/output/outputData.csv must have 5 lines (+header)
		
		ExitStatus actualJobExitStatus = jobExecution.getExitStatus();
		assertEquals("COMPLETED", actualJobExitStatus.getExitCode());
	}
	
}
