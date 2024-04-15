JobParameters may be specific to a job instance , 
java properties are specific to a whole process instance
---------
NB: some "execution bean" like "ItemReader" , "Tasklet" , "Processor" , "ItemWriter"
should be implemented as "spring Bean" (with @Bean or @Component) in order
to enable access of "jobParameter" .
-------------
@StepScope and @JobScope are required to use @Value("#{jobParameters['...']}") 
and cannot be used on simple java instance (not seen as spring bean).
-----------
NB: @StepScope and @JobScope are required to use @Value("#{jobParameters['...']}") 
should be used on "execution_bean" like "ItemReader" , "Tasklet" , "Processor" , "ItemWriter"
but cannot be used on "Step or Job configuration bean". 
-----------
Rappels importants (comportement spring):
default qualifier = beanName
default beanName = methodName (prefixed by @Bean) or classNameWithFirstCharLowercase if @Component
----------
Multiple @Qualifier may be used . ex: @qualifier("csv") @Qualifier("person")

==================
fichiers plats hybrides :
c'est un fichier .csv dont certaines parties (délimitées par ";")
sont elles mêmes décompables en
   - sous sous parties éventuellement délimitées par un autres delimiteur (ex: !")
   ou bien
   - sous sous parties sont à analyser en position fixe (ex: code pays sur 2ou3 caractères , codePostal sur 12 caractères , ville sur 24 caractères , numero et rue sur 32 caractères)

====================
URL/liens WEB sur SpringBatch:

https://fr.slideshare.net/obazoud/spring-batch-avance

https://www.baeldung.com/spring-batch-testing-job

https://techblog.ingeniance.fr/planifier-et-monitorer-un-batch-spring-a-laide-de-quartz-job-scheduler/