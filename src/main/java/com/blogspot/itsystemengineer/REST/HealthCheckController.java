package com.blogspot.itsystemengineer.REST;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class HealthCheckController {
	@GetMapping("/health-check")
	public ResponseEntity<String> myCustomCheck() {
	    String message = "Service status OK";
	    return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
