package com.hackathon.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.hackathon.auth.AuthticationManager;
import com.hackathon.cache.CacheManager;
import com.hackathon.filter.AuthenticationFilter;
import com.hackathon.utils.RestClient;

@Configuration
public class AppConfig {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public CacheManager cacheManager() {
		return new CacheManager();
	}

	@Bean
	public AuthticationManager authticationManager() {
		return new AuthticationManager();
	}
	@Bean
	public RestClient restClient(){
		return new RestClient();
	}

	@Bean
	public FilterRegistrationBean greetingFilterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setName("greeting");
		AuthenticationFilter authenticationFilter = new AuthenticationFilter();
		registrationBean.addUrlPatterns("/gateway/service/*");
		registrationBean.setFilter(authenticationFilter);
		registrationBean.setOrder(1);
		return registrationBean;
	}
}
