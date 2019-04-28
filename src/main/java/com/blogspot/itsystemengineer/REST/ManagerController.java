package com.blogspot.itsystemengineer.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogspot.itsystemengineer.Services.ManagerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ManagerController {

	@Autowired
	private ManagerService managerService;

	@GetMapping("/start")
	public String scan(@RequestParam("ipScope") String ipScope) {
		log.info("ManagerService starts for ipScope: " + ipScope);
		managerService.distributeLoad(ipScope);
		log.info("ManagerService finishes for ipScope: " + ipScope);
		return "{\"status\":\"ok\"}";
	}

}
