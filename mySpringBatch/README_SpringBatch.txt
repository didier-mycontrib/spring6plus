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