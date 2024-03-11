package tp.appliSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AppliSpringBootApplication  extends SpringBootServletInitializer {
	//NB: hériter de SpringBootServletInitializer n'est utile que pour un déploiement en mode ".war"
	//vers un serveur JEE tel que tomcat

	//La méthode main() est le point d'entrée de l'application en mode "standalone" (.jar)
	public static void main(String[] args) {
		//SpringApplication.run(AppliSpringBootApplication.class, args);
		SpringApplication app = new SpringApplication(AppliSpringBootApplication.class);
		//app.setAdditionalProfiles("dev"); // "dev" , "logPerf"
		app.setAdditionalProfiles("dev","withSecurity"); 
		//app.setAdditionalProfiles("prod");
		ConfigurableApplicationContext context = app.run(args);
		//context.getBean("...")
		System.out.println("http://localhost:8080/appliSpringBoot");
	}

	//NB: la méthode .configure() n'est utile que pour configurer un démarrage dans un serveur JEE (ex: tomcat)
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		 builder.profiles("dev");//setting profiles here or with system properties of the server (ex: tomcat)
		 return builder.sources(AppliSpringBootApplication.class);
	}
	
	

}
