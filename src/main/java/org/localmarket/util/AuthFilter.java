package org.localmarket.util;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Logging filter to set up logging context for every incoming request
 *
 * @author mkurian
 *
 */
public class AuthFilter implements Filter {
	public FilterConfig filterConfig;

	public void doFilter(final ServletRequest request,
			final ServletResponse response, FilterChain chain)
			throws java.io.IOException, javax.servlet.ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
    	String authHeader = httpRequest.getHeader("Authorization");
    	//Authorization user=userId,token=token
    	String userId = "";
    	try{
    		userId = authHeader.split(",")[0].split("=")[1];
    		String token = authHeader.split(",")[1].split("=")[1];
    		
    		TokenManager.validLogin(userId, token);
    	}catch(Exception ex){
    		throw new ServletException(ex.getMessage());
    	}
		chain.doFilter(request, response);
	}

	public void init(final FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	public void destroy() {
		
	}

}
