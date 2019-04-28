package com.blogspot.itsystemengineer.REST;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HealthCheckController {
	@GetMapping("/health-check")
	public ResponseEntity<String> myCustomCheck() {
		log.debug("HealthCheckController starts");
		String message = "Service status OK";
		log.debug("HealthCheckController finishes with status:" + message);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
