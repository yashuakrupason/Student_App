package com.UserAuth.security.configuration;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.UserAuth.security.filter.JWTFilter;
import com.UserAuth.security.services.CurrentUserService;
@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class AppConfig extends WebSecurityConfigurerAdapter{

	
	@Autowired
	private JWTFilter jwtFilter;
		

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		Class<? extends Filter> clazz = UsernamePasswordAuthenticationFilter.class;

		http = http.cors().and().csrf().disable();
		http.authorizeRequests().antMatchers("/auth").permitAll()
		.anyRequest().authenticated()
		.and()
		.exceptionHandling().and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(jwtFilter,clazz);
		
	}
	
	
	
	@Override
	@Bean(name= BeanIds.AUTHENTICATION_MANAGER)
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();

	}
}
