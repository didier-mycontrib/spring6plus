package tp.mySpringBatch.listener;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class MyRestartableSerieStepExecutionListener implements StepExecutionListener {

	private int lastReady = 0;
	private int serieSize = 0;
	private int sIndex = 0;
	
	@Override
	public void beforeStep(StepExecution stepExecution) {
		//1. retreive lastReady and lastReady (stored in getJobExecutionContext by previous step "CheckSerieTasklet"
		lastReady=  stepExecution.getJobExecution().getExecutionContext().getInt("lastReady",0);
		serieSize = stepExecution.getJobExecution().getExecutionContext().getInt("serieSize",0);
		System.out.println("#### MySeriePrepareStepExecutionListener.beforeStep(): lastReady="+lastReady +" serieSize=" + serieSize + " read from  job execution context ####" );
		
		//2. try to retreive sIndex in stepExecutionStep (if after restart) 
		int sIndex = stepExecution.getExecutionContext().getInt("sIndex",0);
		System.out.println("#### MySeriePrepareStepExecutionListener.beforeStep(): sIndex=" + sIndex);
		
		//3 incrementation of sIndex for work of this step 
		//NB: #{stepExecutionContext[sIndex]} is used in @StepScope personSerieCsvFileReader of MyCsvFilePersonReaderConfig
		//NB: #{stepExecutionContext[sIndex]} is used in @StepScope csvFilePersonSerieWriter of MyCsvFilePersonWriterConfig
		if(sIndex==0) {
			sIndex=1;
		}else {
			stepExecution.setStatus(BatchStatus.STARTED);
			if(sIndex < lastReady )
				sIndex++;
			else {
				//stepExecution.setStatus(BatchStatus.STOPPED);
				return;
			}
		}
		
		//4 store new value of sIndex in stepExecutionStep (for work of this step and for next restart)
		stepExecution.getExecutionContext().putInt("sIndex",sIndex);
		System.out.println("#### MySeriePrepareStepExecutionListener.beforeStep(): sIndex="+sIndex +" stored in step execution context" );
		
		stepExecution.getExecutionContext().putInt("personSerieCsvFileReader.read.count",0);//restore readCount of personSerieCsvFileReader to read new person-N+1.csv from begining
		stepExecution.getExecutionContext().putLong("csvFilePersonWriter.current.count",0);//if write in .csv
		stepExecution.getExecutionContext().putLong("csvFilePersonWriter.written",0);
		stepExecution.getExecutionContext().putLong("personXmlFileItemWriter.position",0); //if write in .xml
		stepExecution.getExecutionContext().putLong("personXmlFileItemWriter.record.count",0);
		System.out.println("#### MySeriePrepareStepExecutionListener.beforeStep():"+ stepExecution.getExecutionContext().toString() + " ###" );
	}

	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		BatchStatus batchStatus;
		if(sIndex<serieSize)
			batchStatus= BatchStatus.STOPPED; //STOPPED , WAITING For RESTART
		else
			batchStatus = BatchStatus.COMPLETED;
		stepExecution.setStatus(batchStatus); //to influence RESTARTING
		System.out.println("#### MySeriePrepareStepExecutionListener.afterStep() setting status="+batchStatus+
				           " and with stepExecution=" + stepExecution.toString() + " ####");
		return StepExecutionListener.super.afterStep(stepExecution);
	}
    

}
