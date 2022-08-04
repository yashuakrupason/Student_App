package com.UserAuth.security.services;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import com.UserAuth.security.services.CurrentUserService;

import org.springframework.web.filter.OncePerRequestFilter;
@Service
public class SessionFilterService  extends OncePerRequestFilter{
	
	@Autowired
	SessionRegService sessionRegService;

	@Autowired
	CurrentUserService curUserService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String sessionId = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		if(sessionId == null || sessionId.length() <=0) {
			filterChain.doFilter(request, response);
			return;
		}
		
		final String username = sessionRegService.getUsernameForSessionId(sessionId);
		if(username == null) {
			filterChain.doFilter(request, response);
			return;
		}
		
		final CurrentUser currentUser = curUserService.loadUserByUsername(username);
		
		final UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(currentUser,null,currentUser.getAuthorities());
	
		auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(auth);
		filterChain.doFilter(request, response);

		
	}
	

}
