package tp.mySpringBatch;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication()
public class MySpringBatchApplication implements CommandLineRunner {

  private final JobLauncher jobLauncher;
  private final ApplicationContext applicationContext;
  private static boolean launchRunAfterMain=false;
  
  public static void initProfiles() {
		//java .... -Dspring.profiles.active=reInit,dev
		String profilsActifs  = System.getProperty("spring.profiles.active");
		if(profilsActifs!=null) {
			System.out.println("spring.profiles.active="+profilsActifs);
		}else {
			//String defaultProfils  = "xmlJobConfig";
			String defaultProfils  = "";
			System.setProperty("spring.profiles.default", defaultProfils);
			System.out.println("spring.profiles.default="+defaultProfils);
		}
	}

  @Autowired
  public MySpringBatchApplication(JobLauncher jobLauncher,
		                          ApplicationContext applicationContext) {
	//injection by constructor  
    this.jobLauncher = jobLauncher;
    this.applicationContext = applicationContext;
  }

  public static void main(String[] args) {
		initProfiles();
		launchRunAfterMain=true;
		SpringApplication.run(MySpringBatchApplication.class, args);
  }

  @Override //from CommandLineRunner interface (run automatically)
  public void run(String... args) throws Exception {
	  if(launchRunAfterMain)
	      runAfterMain(args);
  }
  
  public void runAfterMain(String... args) throws Exception {
	  
	/*
	 NB: if xmlJobConfig profile is activated ,
	 from xml job config ... (job/myHelloWorldJob.xml and SomeJobsFromXmlConfig)  
	 */

	  //Job job = (Job) applicationContext.getBean("myHelloWorldJob");
	  //Job job = (Job) applicationContext.getBean("myHelloWorldWithParameterJob");
      //Job job = (Job) applicationContext.getBean("copyFromCsvToCsvJob");
	  //Job job = (Job) applicationContext.getBean("fromFixedPosTxtToCsvJob");
      // Job job = (Job) applicationContext.getBean("fromCsvToXmlJob");
	  //Job job = (Job) applicationContext.getBean("fromCsvToJsonJob");
	  //Job job = (Job) applicationContext.getBean("fromCsvToFixedPosTxtJob");
	  //Job job = (Job) applicationContext.getBean("fromXmlToCsvJob");
	  //Job job = (Job) applicationContext.getBean("fromJsonToXmlJob");
      // Job job = (Job) applicationContext.getBean("insertIntoDbFromCsvJob");
	  // Job job = (Job) applicationContext.getBean("insertIntoCsvFromDbJob");
	  //Job job = (Job) applicationContext.getBean("withMyTaskletJob");
	  
	  //Job job  = (Job) applicationContext.getBean("mySimpleSequentialStepsJob");
	  //Job job  = (Job) applicationContext.getBean("mySimpleConditionalStepsJob");
	  //Job job  = (Job) applicationContext.getBean("myPartitionJob");
	  
	  Job job = (Job) applicationContext.getBean("generateDbDataSetJob");

    JobParameters jobParameters = new JobParametersBuilder()
        .addLong("timeStampOfJobInstance", System.currentTimeMillis())//Necessary for running several instances of a same job (each jobInstance must have a parameter that changes)
        .addString("msg1", "_my_msg1_value_")//used by PrintJobParamMessageTaskletBean and some Reader/Writer
        .addString("enableUpperCase", "true")//used by SimpleUppercasePersonProcessor
        .addLong("dataSetSize", 200L) //used by generateDbDataSetJob
        .toJobParameters();

    var jobExecution = jobLauncher.run(job, jobParameters);

    var batchStatus = jobExecution.getStatus();
    while (batchStatus.isRunning()) {
      System.out.println("Still running...");
      Thread.sleep(5000L);
    }
  }
}
