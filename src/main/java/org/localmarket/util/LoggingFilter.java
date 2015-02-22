package org.localmarket.util;

import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.MDC;

/**
 * Logging filter to set up logging context for every incoming request
 *
 * @author mkurian
 *
 */
public class LoggingFilter implements Filter {
	public FilterConfig filterConfig;

	public void doFilter(final ServletRequest request,
			final ServletResponse response, FilterChain chain)
			throws java.io.IOException, javax.servlet.ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
//		String realmId = httpRequest.getHeader("realmId");
//    	String authHeader = httpRequest.getHeader("Authorization");
//    	String authId = "";
//    	try{
//    		authId = authHeader.split("=")[1].split(",")[0];
//    	}catch(Exception ex){
//    		//ignore exception, could not get authId for logging
//    	}

		MDC.put("requestId", UUID.randomUUID().toString());
//		MDC.put("user", realmId != null? realmId : authId);
		MDC.put("API", httpRequest.getRequestURI());
		chain.doFilter(request, response);
	}

	public void init(final FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	public void destroy() {

		MDC.remove("requestId");
//		MDC.remove("user");
		MDC.remove("API");
	}

}
