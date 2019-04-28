package com.blogspot.itsystemengineer.NetworkDiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@ComponentScan("com.blogspot")
@SpringBootApplication
public class NetworkDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetworkDiscoveryApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
}
