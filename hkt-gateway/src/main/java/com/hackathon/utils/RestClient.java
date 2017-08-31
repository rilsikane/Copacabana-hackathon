package com.hackathon.utils;

import java.util.Map;
import java.util.Map.Entry;

import javax.xml.ws.WebServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Component
public class RestClient {

	@Autowired
	private RestTemplate restTemplate;

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public <T> T post(String url, Map<String, String> requestParams, Class<T> clazz) throws Exception {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		if (requestParams != null) {
			for (Entry<String, String> entry : requestParams.entrySet()) {
				map.add(entry.getKey(), entry.getValue());
			}
		}
		return restTemplate.postForObject(url, map, clazz);
	}

	public <T> T post(String url, Object obj, Class<T> clazz) throws Exception {
		return post(url, null, obj, clazz);
	}

	public <T> T post(String url, HttpHeaders addHeaders, Object obj, Class<T> clazz) throws Exception {
		HttpHeaders headers = HttpUtils.getHttpHeaders(addHeaders);
		HttpEntity<Object> httpEntity = new HttpEntity<Object>(obj, headers);
		try {
			ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, clazz);
			return responseEntity.getBody();
		} catch (HttpClientErrorException e) {
			throw new WebServiceException(e);
		} catch (HttpServerErrorException e) {
			throw new WebServiceException(e);
		} catch (RestClientException e) {
			throw new WebServiceException(e);
		} catch (Exception e) {
			throw new WebServiceException(e);
		}
	}

	public <T> T postUpload(String url, HttpHeaders addHeaders, Map<String, String> requestParams, final MultipartFile file, Class<T> clazz) throws Exception {
		HttpHeaders headers = HttpUtils.getHttpHeaders(addHeaders);
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<String, Object>();
		if (requestParams != null) {
			for (Entry<String, String> entry : requestParams.entrySet()) {
				multiValueMap.add(entry.getKey(), entry.getValue());
			}
		}
		ByteArrayResource byteArrayResource = null;
		if (file != null) {
			byteArrayResource = new ByteArrayResource(file.getBytes()) {
				@Override
				public String getFilename() {
					return file.getOriginalFilename();
				}
			};
			multiValueMap.add(file.getName(), byteArrayResource);
		}

		HttpEntity<Object> httpEntity = new HttpEntity<Object>(multiValueMap, headers);
		try {
			ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, clazz);
			return responseEntity.getBody();
		} catch (HttpClientErrorException e) {
			throw new WebServiceException(e);
		} catch (HttpServerErrorException e) {
			throw new WebServiceException(e);
		} catch (RestClientException e) {
			throw new WebServiceException(e);
		} catch (Exception e) {
			throw new WebServiceException(e);
		} finally {
			ObjectUtils.destroyObject(byteArrayResource);
		}
	}

	public <T> T get(String url, Class<T> clazz) throws Exception {
		return get(url, clazz);
	}

	public <T> T get(String url, HttpHeaders addHeaders, Class<T> clazz) throws Exception {
		HttpHeaders headers = HttpUtils.getHttpHeaders(addHeaders);
		HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		try {
			ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, clazz, map);
			return responseEntity.getBody();
		} catch (HttpClientErrorException e) {
			throw new WebServiceException(e);
		} catch (HttpServerErrorException e) {
			throw new WebServiceException(e);
		} catch (RestClientException e) {
			throw new WebServiceException(e);
		} catch (Exception e) {
			throw new WebServiceException(e);
		}
	}

}