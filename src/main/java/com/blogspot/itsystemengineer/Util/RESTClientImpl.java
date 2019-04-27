package com.blogspot.itsystemengineer.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import lombok.Setter;

public class RESTClientImpl implements RESTClient {

	@Autowired
	RestTemplate restTemplate;

	@Setter
	private String uri;
	
	@Setter
	private String body;

	public void get() {
		restTemplate.getForObject(uri, String.class);
	}
	
	public void post() {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Content-Type", "application/text");
		HttpEntity<String> request = new HttpEntity<String>(body, headers);
		restTemplate.postForObject(uri, request, String.class);
	}

}



