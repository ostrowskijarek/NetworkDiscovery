package com.blogspot.itsystemengineer.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import com.blogspot.itsystemengineer.Util.NetworkTools;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private ApplicationContext ctx;
	@Autowired
	private TaskExecutor taskExecutor;
	@Autowired
	private DiscoveryClient discoveryClient;
	@Autowired
	NetworkTools networkTools;
	
	public void distributeLoad(String ipScope) {
		log.info("distributeLoad() starts for ipScope: " +ipScope);
		List<ServiceInstance> list = discoveryClient.getInstances("Scanner");
		List<String> ipRanges = networkTools.split(ipScope, list.size());
		int i = 0;
		for (String range : ipRanges) {
			log.debug("range: " +range);
			ServiceInstance scanner = list.get(i++);
			String uri = "http://" + scanner.getHost() + ":" + scanner.getPort() + "/scan?ipScope=" + range;
			log.debug("uri: " +uri);
			taskExecutor.execute(ctx.getBean(ManagerThreadService.class, uri));
		}
		log.info("distributeLoad() finishes for ipScope: " +ipScope);
	}
}
