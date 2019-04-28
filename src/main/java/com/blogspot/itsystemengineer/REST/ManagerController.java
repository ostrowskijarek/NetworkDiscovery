package com.blogspot.itsystemengineer.REST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogspot.itsystemengineer.Services.ManagerService;
import com.blogspot.itsystemengineer.Util.NetworkTools;

@RestController
public class ManagerController {

	@Autowired
	private ApplicationContext ctx;
	@Autowired
	private TaskExecutor taskExecutor;

	@Autowired
	private DiscoveryClient discoveryClient;
	@Autowired
	NetworkTools networkTools;

	@GetMapping("/start")
	public String scan(@RequestParam("ipScope") String ipScope) {

		List<ServiceInstance> list = discoveryClient.getInstances("Scanner");
		List<String> ipRanges = networkTools.split(ipScope, list.size());
		int i = 0;
		for (String range : ipRanges) {
			ServiceInstance scanner = list.get(i++);
			String uri = "http://" + scanner.getHost()+ ":" + scanner.getPort() + "/scan?ipScope=" + range;
			taskExecutor.execute(ctx.getBean(ManagerService.class, uri));
		}
		return "{\"status\":\"ok\"}";
	}

}
