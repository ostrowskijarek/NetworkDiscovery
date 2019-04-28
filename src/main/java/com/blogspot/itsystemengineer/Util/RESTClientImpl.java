package com.blogspot.itsystemengineer.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RESTClientImpl implements RESTClient {

	@Autowired
	private RestTemplate restTemplate;

	@Setter
	private String uri;

	@Setter
	private String body;

	public void get() {
		log.info("Call for Get() starts for uri: " + uri);
		restTemplate.getForObject(uri, String.class);
		log.info("Call for Get() finishes for uri: " + uri);
	}

	public void post() {
		log.info("Call for Post() starts for uri: " + uri);
		log.debug("with body: " + body);
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Content-Type", "application/text");
		HttpEntity<String> request = new HttpEntity<String>(body, headers);
		restTemplate.postForObject(uri, request, String.class);
		log.info("Call for Post() finishes for uri: " + uri);
	}

}
