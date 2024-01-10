package org.mycontrib.mysecurity.chain.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("!withSecurity")
@EnableWebSecurity
public class WithoutSecurityMainFilterChainConfig {
	
	
	private static Logger logger = LoggerFactory.getLogger(WithoutSecurityMainFilterChainConfig.class);

	@Bean
	protected SecurityFilterChain configureAndBuildSecurityFilterChain(final HttpSecurity http) throws Exception {
		
		logger.info("!withSecurity , WithoutSecurityMainFilterChainConfig loaded");
		
		http.authorizeHttpRequests(
				 auth -> auth.requestMatchers( "/**").permitAll()				    
				 )
		    .cors( Customizer.withDefaults()) //enable CORS (avec @CrossOrigin sur class @RestController)
		    .headers((headers) -> headers.frameOptions( frameOptions-> frameOptions.sameOrigin()) )
		    .csrf(((csrf) -> csrf.disable())); 
		
		return http.build();
	} 
}
