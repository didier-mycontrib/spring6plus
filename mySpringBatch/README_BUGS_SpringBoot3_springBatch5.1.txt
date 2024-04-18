Petits problèmes à peaufiner:
-----------------------------
   
* #temp workaround for step scope in job xml config file
spring.main.allow-bean-definition-overriding=true

* ...
   
=====================
Problèmes résolus: 

  * spring.batch.jdbc.initialize-schema=always pas bien pris en compte
  --> Solution :
    Surtout pas de @EnableBatchProcessing(dataSourceRef = "batchDataSource" ,
                       transactionManagerRef = "batchTransactionManager")
    Mais simplement préciser le datasource principal à utiliser par springBatch intégré à springBoot
    via 
    @Primary
	 @BatchDataSource
	 @Bean(value = "dataSource") ...
	 et grâce à cette bonne config , spring.batch.jdbc.initialize-schema=always est bien pris en compte

  * erreurs au redémarrage de fromPersonSerieCsvToXmlJob:
   - pas de bonne ré-execution de lecture csv et suite , erreur not-writable  person-2.csv ou .xml
   
   --> solution:
                  .shouldDeleteIfEmpty(true)
				  .shouldDeleteIfExists(true)
				  .forceSync(true)   pour writer csv
				  
				  .shouldDeleteIfEmpty(true)
	              .overwriteOutput(true)
	              .forceSync(true) pour writer XML
	              
	              ET
	              
	    stepExecution.getExecutionContext().putInt("personSerieCsvFileReader.read.count",0);//restore readCount of personSerieCsvFileReader to read new person-N+1.csv from begining
		stepExecution.getExecutionContext().putLong("csvFilePersonWriter.current.count",0);//if write in .csv
		stepExecution.getExecutionContext().putLong("csvFilePersonWriter.written",0);
		stepExecution.getExecutionContext().putLong("personXmlFileItemWriter.position",0); //if write in .xml
		stepExecution.getExecutionContext().putLong("personXmlFileItemWriter.record.count",0);
		
		    dans MySeriePrepareStepExecutionListener.beforeStep() pour réinitialiser les "restartable-reader/writer" prédéfinis après un redémarrage !!!!
   
   
* Anciennes erreurs dans MyXmlFilePersonWriterConfig :

		//PROBLEM with @JobScope or @StepScope : 
		//org.springframework.batch.item.WriterNotOpenException: Writer must be open before it can be written to

		---> solution: adujst return type of @Bean of @StepScope as /* NOT ItemWriter<Person> !!! */ ItemStreamWriter<Person>
		//IT IS IMPORTANT to set return type as ItemStreamWriter (which have a .open() method that will can be called)