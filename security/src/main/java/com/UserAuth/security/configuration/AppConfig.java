package com.UserAuth.security.configuration;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.UserAuth.security.services.CurrentUserService;
import com.UserAuth.security.services.SessionFilterService;
@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class AppConfig extends WebSecurityConfigurerAdapter{

	
	@Autowired
	private CurrentUserService curUserService;	
	@Autowired
	private SessionFilterService sessionFilterService;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(curUserService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http = http.cors().and().csrf().disable();
		http = http.exceptionHandling().authenticationEntryPoint(
				(request,response,ex) -> {
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED,ex.getMessage());
				}).and();
		http.authorizeRequests().antMatchers("/login").permitAll()
		.anyRequest().authenticated();
	
		
		http.addFilterBefore(sessionFilterService, UsernamePasswordAuthenticationFilter.class);
	}
	
	
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();

	}
}
