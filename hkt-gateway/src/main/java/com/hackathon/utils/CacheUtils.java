package com.hackathon.utils;

import java.util.HashMap;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.hackathon.cache.CacheManager;
import com.hackathon.config.AppConfig;

public class CacheUtils {
	public static CacheManager getAuthKey() {
		  AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);   
		  return  (CacheManager) context.getBean("cacheManager");
		 }
}
