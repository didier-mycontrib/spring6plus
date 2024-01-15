package org.mycontrib.mysecurity.chain.config;

import org.mycontrib.mysecurity.area.config.AreaConfig;
import org.mycontrib.mysecurity.area.config.AreasConfig;
import org.mycontrib.mysecurity.common.extension.MySecurityExtension;
import org.mycontrib.mysecurity.common.extension.WithSecurityFilterChainSubConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

import jakarta.servlet.DispatcherType;

/*
 @Component
 @Profile("withSecurity")
 public class SpecificWithSecurityMainFilterChainSubConfig implements WithSecurityFilterChainSubConfig {
 ...
 }
 can override this default implementation
 */

public class DefaultWithSecurityMainFilterChainSubConfig implements WithSecurityFilterChainSubConfig {
	
	private static Logger logger = LoggerFactory.getLogger(DefaultWithSecurityMainFilterChainSubConfig.class);
	
	protected AreasConfig areasConfig; //set by WebProtectedAreaConfigurer and .properties
	
	
	public DefaultWithSecurityMainFilterChainSubConfig(AreasConfig areasConfig){
		this.areasConfig=areasConfig;
	}
	
	
	public static AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry 
        addPermissionsFromAreaConfig(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorizationManagerRequestMatcherRegistry,
  		               AreaConfig areaConfig) {
		
		AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorizeRequestsWithPermissions = authorizationManagerRequestMatcherRegistry;
		
		if(areaConfig.getWhitelist().length>0)
			authorizeRequestsWithPermissions=authorizeRequestsWithPermissions
			.requestMatchers(areaConfig.getWhitelist()).permitAll();
		
		if(areaConfig.getBlacklist().length>0)
			authorizeRequestsWithPermissions=authorizeRequestsWithPermissions
			.requestMatchers(areaConfig.getBlacklist()).denyAll();
		
		if(areaConfig.getReadonlylist().length>0)
			authorizeRequestsWithPermissions=authorizeRequestsWithPermissions
			.requestMatchers(HttpMethod.GET, areaConfig.getReadonlylist()).permitAll();
		
		if(areaConfig.getProtectedlist().length>0)
			authorizeRequestsWithPermissions=authorizeRequestsWithPermissions
			.requestMatchers(areaConfig.getProtectedlist()).authenticated();
		
		return authorizeRequestsWithPermissions;
	}
	
	
	
	@Override
	public HttpSecurity prepareRestApiFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(
				auth ->  addPermissionsFromAreaConfig(
		    		      auth.requestMatchers(HttpMethod.POST, MySecurityExtension.DEFAULT_REST_STANDALONE_LOGIN_PATH).permitAll() 
		    		      , areasConfig.getRest())	
		    		   .requestMatchers( "/rest/**").authenticated()//by default
				 )
		    .cors( Customizer.withDefaults()) //enable CORS (avec @CrossOrigin sur class @RestController)
		    .csrf(((csrf) -> csrf.disable()));
		return http;
	}


	@Override
	public HttpSecurity prepareSpringMvcSiteFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(
				    		 auth -> addPermissionsFromAreaConfig(
				    		      auth.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
				    		          .requestMatchers( MySecurityExtension.DEFAULT_SITE_FORM_LOGIN_URI).permitAll()
				  		              .requestMatchers( MySecurityExtension.DEFAULT_SITE_FORM_LOGOUT_URI).permitAll() 			  		                   
				    		      ,areasConfig.getSite())
				    		 .requestMatchers( "/site/**").authenticated()//by default
				    		 )
				    .formLogin(formLogin -> formLogin.loginPage(MySecurityExtension.DEFAULT_SITE_FORM_LOGIN_URI).permitAll() );
				    /*.sessionManagement((sessions) -> sessions
							.requireExplicitAuthenticationStrategy(true)
						)
					*/ 
					//.csrf(csrf->csrf.disable());
				   // .csrf(Customizer.withDefaults());
		
		//logger.debug("prepareSpringMvcSiteFilterChain() called with MySecurityExtension.DEFAULT_SITE_FORM_LOGIN_URI="+MySecurityExtension.DEFAULT_SITE_FORM_LOGIN_URI);
		return http;
	};


	@Override
	public HttpSecurity prepareDefaultOtherWebPartFilterChain(HttpSecurity http) throws Exception {
	
		http.authorizeHttpRequests(
				    		 auth -> addPermissionsFromAreaConfig(
				    				                  addPermissionsFromAreaConfig(auth,areasConfig.getTools()),
				    				                  areasConfig.getOther())	
				    		                    .requestMatchers( "/jsp/**").permitAll()//ATTENTION: ajustement temporaire pour spring security 6 , AVEC PAGES JSP toujours en arrière plan de controller "/site/..." et dans partie other (order(3)) !!!!
				    		 					.requestMatchers( "/**").authenticated()//by default
				    		 )
				//.headers((headers) -> headers.disable() )//ok for h2-console
				.headers((headers) -> headers.frameOptions( frameOptions-> frameOptions.sameOrigin()) )//ok for h2-console
				.csrf(((csrf) -> csrf.disable())); //ok for h2-console
		return http;
	}

}
