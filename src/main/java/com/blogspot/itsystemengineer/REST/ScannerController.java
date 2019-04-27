package com.blogspot.itsystemengineer.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogspot.itsystemengineer.Services.ScanningService;

@RestController
public class ScannerController {

	@Autowired
	private ApplicationContext ctx;
	@Autowired
	private TaskExecutor taskExecutor;

	@GetMapping("/scan")
	public void scan(@RequestParam("ipScope") String ipScope) {
		taskExecutor.execute(ctx.getBean(ScanningService.class, ipScope));
	}
}
