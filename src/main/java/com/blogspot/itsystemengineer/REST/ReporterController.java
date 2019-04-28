package com.blogspot.itsystemengineer.REST;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReporterController {

	@PostMapping("/report")
	public String scan(HttpEntity<String> httpEntity) {
		String output = httpEntity.getBody();
		System.out.println("Retrieved Output: " + output);
		return "{\"status\":\"ok\"}";
	}

}
