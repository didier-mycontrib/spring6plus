package org.mycontrib.mysecurity.realm.config.default_users;

import org.mycontrib.mysecurity.common.extension.MySecurityDefaultUsersSimpleConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Profile("withSecurity")
public class MySecurityDefaultUsersConfig {
	
	@Bean
	@ConditionalOnMissingBean(type= { "org.mycontrib.mysecurity.common.extension.MySecurityDefaultUsersSimpleConfigurer"})
	MySecurityDefaultUsersSimpleConfigurer  defaultMySecuritySimpleConfigurer(BCryptPasswordEncoder passwordEncoder) {
		return new MySecurityDefaultUsersSimpleConfigurerDefaultImpl(passwordEncoder);
	}

}
