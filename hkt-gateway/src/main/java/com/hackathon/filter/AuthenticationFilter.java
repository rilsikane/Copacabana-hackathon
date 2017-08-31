package com.hackathon.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hackathon.auth.AuthticationManager;
import com.hackathon.utils.GWSConstants;



public class AuthenticationFilter implements Filter {

	private AuthticationManager authticationManager;

	public AuthenticationFilter() {
	}

	@Override
	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;

		request.setCharacterEncoding(GWSConstants.ENCODING.UTF_8);

		final String token = request.getHeader(GWSConstants.TOKEN);
		try {
			if ("POST".equals(request.getMethod()) && !authticationManager.checkUserAuthData(token)) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, HEAD, OPTIONS");
//        response.setHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
        
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.authticationManager = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext()).getBean(AuthticationManager.class);
	}

}
