package tp.mySpringBatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MySpringBatchApplication implements CommandLineRunner {

  private final JobLauncher jobLauncher;
  private final ApplicationContext applicationContext;

  public MySpringBatchApplication(JobLauncher jobLauncher,
		                          ApplicationContext applicationContext) {
	//injection by constructor  
    this.jobLauncher = jobLauncher;
    this.applicationContext = applicationContext;
  }

  public static void main(String[] args) {
    SpringApplication.run(MySpringBatchApplication.class, args);
  }

  @Override //from CommandLineRunner interface (run automatically)
  public void run(String... args) throws Exception {

	//Job job = (Job) applicationContext.getBean("myHelloWorldJob");
    //Job job = (Job) applicationContext.getBean("copyFromCsvToCsvJob");
    //Job job = (Job) applicationContext.getBean("fromCsvToXmlJob");
	// Job job = (Job) applicationContext.getBean("fromCsvToJsonJob");
	// Job job = (Job) applicationContext.getBean("fromXmlToCsvJob");
	  Job job = (Job) applicationContext.getBean("fromJsonToXmlJob");
    //Job job = (Job) applicationContext.getBean("insertIntoDbFromCsvJob");
	//Job job = (Job) applicationContext.getBean("insertIntoCsvFromDbJob");
	//Job job = (Job) applicationContext.getBean("withMyTaskletJob");

    JobParameters jobParameters = new JobParametersBuilder()
        .addString("JobID", String.valueOf(System.currentTimeMillis()))
        .toJobParameters();

    var jobExecution = jobLauncher.run(job, jobParameters);

    var batchStatus = jobExecution.getStatus();
    while (batchStatus.isRunning()) {
      System.out.println("Still running...");
      Thread.sleep(5000L);
    }
  }
}
