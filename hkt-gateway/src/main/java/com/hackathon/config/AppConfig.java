package com.hackathon.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.hackathon.auth.AuthticationManager;
import com.hackathon.cache.CacheManager;
import com.hackathon.filter.AuthenticationFilter;
import com.hackathon.filter.CorsFilter;
import com.hackathon.utils.RestClient;

@SpringBootApplication
@ComponentScan(basePackages = {"com"})
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
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CorsFilter corsFilter = new CorsFilter();
        registrationBean.addUrlPatterns("/*");
        registrationBean.setFilter(corsFilter);
        registrationBean.setOrder(2);
        return registrationBean;
    }
	@Bean
	public FilterRegistrationBean greetingFilterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setName("greeting");
		AuthenticationFilter authenticationFilter = new AuthenticationFilter();
		registrationBean.addUrlPatterns("/gateway/service/private/*");
		registrationBean.setFilter(authenticationFilter);
		registrationBean.setOrder(2);
		return registrationBean;
	}
	
}
