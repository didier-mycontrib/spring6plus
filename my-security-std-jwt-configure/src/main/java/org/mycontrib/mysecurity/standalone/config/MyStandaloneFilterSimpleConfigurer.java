package org.mycontrib.mysecurity.standalone.config;

import org.mycontrib.mysecurity.common.MyFilterChainSimpleConfigurer;
import org.mycontrib.mysecurity.jwt.util.JwtAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@Profile("withSecurity")
@Qualifier("StandaloneJwt")
public class MyStandaloneFilterSimpleConfigurer implements MyFilterChainSimpleConfigurer {
	
	private static Logger logger = LoggerFactory.getLogger(MyStandaloneFilterSimpleConfigurer.class);

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	private AuthenticationEntryPoint getRestAuthenticationEntryPoint() {
        return new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED);
    }
	
	@Override
	public HttpSecurity configureEndOfSecurityChain(HttpSecurity http)
			throws Exception {
		
		
		logger.debug("MyStandaloneFilterSimpleConfigurer.configureEndOfSecurityChain() called for registering jwtAuthenticationFilter");
		
		
		return http.exceptionHandling(eh -> eh.authenticationEntryPoint(getRestAuthenticationEntryPoint())
				    /*eH -> eH.defaultAuthenticationEntryPointFor(
						getRestAuthenticationEntryPoint(), new AntPathRequestMatcher("/rest/**")
				    )*/)
				   .sessionManagement(sM->sM.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				   .addFilterBefore(jwtAuthenticationFilter,  UsernamePasswordAuthenticationFilter.class);
	}

}
