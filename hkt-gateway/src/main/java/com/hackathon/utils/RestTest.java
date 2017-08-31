package com.hackathon.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.WebServiceException;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class RestTest {

	public static void main(String[] args) throws IOException {
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new FormHttpMessageConverter());
		messageConverters.add(new StringHttpMessageConverter());
		messageConverters.add(new MappingJackson2HttpMessageConverter());
		messageConverters.add(new ByteArrayHttpMessageConverter());
		RestTemplate restTemplate = new RestTemplate();

		restTemplate.setMessageConverters(messageConverters);

		restTemplate.setRequestFactory(SSLUtil.getSimpleClientHttpRequestFactory());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		//headers.add(headerName, headerValue);
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		ByteArrayResource contentsAsResource = new ByteArrayResource(FileUtils.readFileToByteArray(new File("C:/test.js"))) {
			@Override
			public String getFilename() {
				return "ddd.cc";
			}
		};

		map.add("files", contentsAsResource);
		map.add("dd", "dd");
		HttpEntity<Object> httpEntity = new HttpEntity<Object>(map, headers);

		String url = "http://localhost:8180/MNFEGWSWeb/services/gateway/post-file.service";
		//String url = "http://localhost:8180/MNFEGWSWeb/backend-service/post-file.service";

		try {
			ResponseEntity<Object> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Object.class);
			System.out.println(responseEntity.getBody());

			//Object  responseEntity = restTemplate.postForObject(url, httpEntity, Object.class);
			System.out.println(responseEntity);
		} catch (HttpClientErrorException e) {
			throw new WebServiceException(e);
		} catch (HttpServerErrorException e) {
			throw new WebServiceException(e);
		} catch (RestClientException e) {
			throw new WebServiceException(e);
		} catch (Exception e) {
			throw new WebServiceException(e);
		} finally {
		}
	}

}
