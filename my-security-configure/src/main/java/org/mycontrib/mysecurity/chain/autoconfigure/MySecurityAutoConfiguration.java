package org.mycontrib.mysecurity.chain.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
/*
NB: cette classe est référencée dans le fichier
META-INF/spring.factories (de src/main/resources)
org.springframework.boot.autoconfigure.EnableAutoConfiguration=org.mycontrib.mysecurity.autoconfigure.MySecurityAutoConfiguration
(ou bien = AutoConfig1,...,AutoConfigN
selon les spécifications suivantes:
https://docs.spring.io/spring-boot/docs/2.1.18.RELEASE/reference/html/boot-features-developing-auto-configuration.html
*/
@Configuration
@ComponentScan({ "org.mycontrib.mysecurity.chain.config",
	             "org.mycontrib.mysecurity.area.config"})
public class MySecurityAutoConfiguration {
	
	private static Logger logger = LoggerFactory.getLogger(MySecurityAutoConfiguration.class);
	
	public MySecurityAutoConfiguration() {
	     logger.debug("org.mycontrib.mysecurity.chain.autoconfigure.MySecurityAutoConfiguration loaded (my-security-configure)");
	}
}
