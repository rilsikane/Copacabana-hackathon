package com.hackathon.utils;

import org.springframework.http.HttpHeaders;

public class HttpUtils {
	public static HttpHeaders getHttpHeaders(HttpHeaders addHeaders) {
		HttpHeaders headers = initHttpHeaders(addHeaders);
		return headers;
	}

	public static HttpHeaders initHttpHeaders(HttpHeaders headers) {
		if (headers == null) {
			headers = new HttpHeaders();
		}
		headers.add("CHANNEL", "GW");
		return headers;
	}
}
