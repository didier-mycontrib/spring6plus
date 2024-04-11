package tp.mySpringBatch.job.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import tp.mySpringBatch.MySpringBatchApplication;
import tp.mySpringBatch.job.AbstractBasicTestJob;

@SpringBatchTest
@SpringBootTest(classes = { MySpringBatchApplication.class} )
//@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class TestFileToFileJobViaJavaConf extends AbstractBasicTestJob {
	
	@Override
	public JobParameters initJobParameters() {
		return new JobParametersBuilder()
				.addLong("timeStampOfJobInstance", System.currentTimeMillis())//Necessary for running several instances of a same job (each jobInstance must have a parameter that changes)
				.addString("msg1", "_my_msg1_value_")//used by PrintJobParamMessageTaskletBean and some Reader/Writer
		        .addString("enableUpperCase", "true")//used by SimpleUppercasePersonProcessor
				.toJobParameters();
	}
	
	public TestFileToFileJobViaJavaConf() {
		/*super("copyFromCsvToCsvJob",
		"fromFixedPosTxtToCsvJob",
		"fromCsvToXmlJob",
		"fromCsvToJsonJob",
		"fromCsvToFixedPosTxtJob",
		"fromXmlToCsvJob",
		"fromJsonToXmlJob"); // jobNames to test
       */
		super(/*"fromCsvWithSkipsErrorsToJsonJob"*/);
		//super("fromCsvToJsonWithRetryJob"); //WITH BUG !!!!
	}
	
	@Test //unit test of a single/individual Step 
	public void testStepCsvWithSkipsErrorsToJson() throws Exception{
		prepareJobFromJobName("fromCsvWithSkipsErrorsToJsonJob");//even if it is a unit test of an individual unit test , a job must be set in jobLauncherTestUtils
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
