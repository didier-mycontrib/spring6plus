package tp.mySpringBatch.job.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("xmlJobConfig")
@ImportResource({"classpath:job/globalCommonConfig.xml",
	             "classpath:job/myHelloWorldJob.xml",
	             "classpath:job/myHelloWorldWithParameterJob.xml",
	             "classpath:job/insertIntoDbFromCsvJob.xml",
	             "classpath:job/insertIntoCsvFromDbJob.xml",
	             "classpath:job/copyFromCsvToCsvJob.xml",
	             "classpath:job/fromCsvToXmlJob.xml",
	             "classpath:job/fromCsvToFixedPosTxtJob.xml",
	             "classpath:job/fromFixedPosTxtToCsvJob.xml",
	             "classpath:job/fromJsonToXmlJob.xml",
	             "classpath:job/fromXmlToCsvJob.xml",
	             "classpath:job/fromCsvToJsonJob.xml",
	             "classpath:job/fromCsvToJsonWithRetryJob.xml",
	             "classpath:job/fromCsvWithSkipErrorsToJsonJob.xml",
	             "classpath:job/withMyTaskletJob.xml",
	             "classpath:job/myCommonPrintTasklets.xml",
	             "classpath:job/myFlowSequentialJob.xml",
	             "classpath:job/myFlowConditionalJob.xml",
	             "classpath:job/myFlowDecisionJob.xml",
	             "classpath:job/myPartitionJob.xml"})
public class SomeJobsFromXmlConfig {

  public static final Logger logger = LoggerFactory.getLogger(SomeJobsFromXmlConfig.class);

  


}