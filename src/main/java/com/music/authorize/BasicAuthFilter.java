package com.music.authorize;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.WebApplicationContextUtils;

@WebFilter(filterName = "authentication-filter")
public class BasicAuthFilter implements Filter {

	BasicAuthService basicAuthService;

	public BasicAuthFilter() {
		// TODO Auto-generated constructor stub
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			String authCredentials = httpServletRequest
					.getHeader("Authorization");

			boolean authStatus = basicAuthService.authenticate(authCredentials);
			if (authStatus) {
				chain.doFilter(request, response);
			} else {
				if (response instanceof HttpServletResponse) {
					HttpServletResponse httpServletResponse = (HttpServletResponse) response;
					httpServletResponse
							.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				}
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.basicAuthService = (BasicAuthService) WebApplicationContextUtils
				.getRequiredWebApplicationContext(fConfig.getServletContext())
				.getBean("basicAuthService");
	}

}
