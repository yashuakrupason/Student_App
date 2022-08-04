package com.UserAuth.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.UserAuth.security.jwt.JwtUtil;
import com.UserAuth.security.services.CurrentUser;
import com.UserAuth.security.services.CurrentUserService;

@Component
public class JWTFilter extends OncePerRequestFilter {
	@Autowired
	public JwtUtil util;
	@Autowired
	public CurrentUserService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
		String token=null;
		String username=null;
		if(authHeader!=null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
			username = util.extractUsername(token);
		}
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			CurrentUser userDetails = userDetailsService.loadUserByUsername(username);
			if(util.validateToken(token, userDetails)){
				UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(
						userDetails,null,userDetails.getAuthorities());
				upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(upat);
				
			}
		}
		filterChain.doFilter(request, response);
	}

}
