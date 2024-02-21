package tp.mySpringBatch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class PrintMessageTasklet implements Tasklet{
	
	private String message;

	public void setMessage(String message) {
	      this.message = message;
    }
	
	public PrintMessageTasklet(String message) {
		super();
		this.message = message;
	}

	public PrintMessageTasklet() {
		this("?");
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		System.out.println(message);
		return RepeatStatus.FINISHED;
	}

}
