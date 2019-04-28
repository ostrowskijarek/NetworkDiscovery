package com.blogspot.itsystemengineer.REST;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ReporterController {

	@PostMapping("/report")
	public String scan(HttpEntity<String> httpEntity) {
		log.info("ReporterController starts");
		String output = httpEntity.getBody();
		log.debug("Retrieved Output: " + output);
		log.info("ReporterController finishes");
		return "{\"status\":\"ok\"}";
	}

}
