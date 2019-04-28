
package com.blogspot.itsystemengineer.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.blogspot.itsystemengineer.Util.RESTClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Scope("prototype")
public class ScanningThreadServiceImpl implements ScanningThreadService {

	@Autowired
	CommandsService cs;

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	RESTClient restClient;

	String ipScope;

	ScanningThreadServiceImpl(String ipScope) {
		this.ipScope = ipScope;
	}

	private String nmapCmd;

	@Override
	public void run() {
		log.info("Scanning Thread starts for ipScope: " + ipScope);
		nmapCmd = "nmap";
		String body = cs.execute(nmapCmd, ipScope);
		List<ServiceInstance> list = discoveryClient.getInstances("Manager");
		String uri = "http://" + list.get(0).getHost() + ":" + list.get(0).getPort() + "/report";
		log.debug("setting uri: " + uri);
		restClient.setUri(uri);
		log.debug("setting body:" + body);
		restClient.setBody(body);
		restClient.post();
		log.info("Scanning Thread finishes for ipScope: " + ipScope);
	}

}
