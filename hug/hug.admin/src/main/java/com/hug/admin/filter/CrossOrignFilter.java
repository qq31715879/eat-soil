package com.hug.admin.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class CrossOrignFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	            // CORS "pre-flight" request
	            response.addHeader("Access-Control-Allow-Origin", "*");
	            response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
	        filterChain.doFilter(request, response);
	}

}
