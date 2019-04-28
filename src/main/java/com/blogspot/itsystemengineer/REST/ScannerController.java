package com.blogspot.itsystemengineer.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogspot.itsystemengineer.Services.ScanningThreadService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
public class ScannerController {

	@Autowired
	private ApplicationContext ctx;
	@Autowired
	private TaskExecutor taskExecutor;

	@GetMapping("/scan")
	public String scan(@RequestParam("ipScope") String ipScope) {
		log.info("ScannerController starts for ipScope: "+ipScope);
		taskExecutor.execute(ctx.getBean(ScanningThreadService.class, ipScope));
		log.info("ScannerController finishes for ipScope: "+ipScope);
		return "{\"status\":\"ok\"}";
	}
}
