package tp.mySpringBatch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@StepScope
//@JobScope
public class PrintJobParamMessageTasklet implements Tasklet{
	
	@Value("#{jobParameters['msg1']}") 
	private String msg1="default_msg1";
	

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		System.out.println(msg1);
		return RepeatStatus.FINISHED;
	}

}
