package com.hackathon.utils;

import javax.naming.NamingException;

import org.springframework.jndi.JndiTemplate;

public class EndPointReader {
	
	EndPointReader() {
		
	}

	private static EndPointReader instance;
	public static EndPointReader getInstance() {
		if(instance == null) {
			instance = new EndPointReader();
		}
		
		return instance;
	}
	private JndiTemplate jndi = null;
	
	public String getConfig(String key) {
		try {
			if(jndi == null) {
				jndi = new JndiTemplate();		
			}
			
			return (String) jndi.lookup(key);
		} catch (Exception e) {
			return "";
		}
	}
}
