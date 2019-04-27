package com.blogspot.itsystemengineer.NetworkDiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.blogspot")
@SpringBootApplication
public class NetworkDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetworkDiscoveryApplication.class, args);
	}

}
