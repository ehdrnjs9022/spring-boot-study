package com.kh.start.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfigure {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		//return httpSecurity.formLogin().disable().build();
		
		/*
		 * return httpSecurity.formLogin(new
		 * Customizer<FormLoginConfigurer<HttpSecurity>>() {
		 * 
		 * @Override public void customize(FormLoginConfigurer<HttpSecurity> t) {
		 * t.disable(); } }).build();
		 
		 
		 * Cross Site Request Forgery
		 * 
		 * <img src="http://우리도메인/logout"/>
		 * 
		 * <form action=http://우리도메인/logout" action="post">
		 *  <input type="hidden" value="admin" name="userId/>
		 * 	<button> 눌러보세요 </button>
		 *</form>
		 * 
		 */
		
		
		
		return httpSecurity.formLogin(AbstractHttpConfigurer::disable)
							.httpBasic(AbstractHttpConfigurer::disable)
							.csrf(AbstractHttpConfigurer::disable)
							.authorizeHttpRequests(request -> {
								request.requestMatchers(HttpMethod.POST,"/auth/login","/members").permitAll();
								request.requestMatchers("/admin/**").hasRole("ADMIN");
								request.requestMatchers(HttpMethod.PUT, "/members").authenticated();
							})
							.build();
		
		
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authconfig) throws Exception {
		return authconfig.getAuthenticationManager();
	}
	
	
	
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
