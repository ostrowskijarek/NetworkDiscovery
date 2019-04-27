
package com.blogspot.itsystemengineer.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.blogspot.itsystemengineer.Util.RESTClient;

@Service
@Scope("prototype")
public class ScanningServiceImpl implements ScanningService {

	@Autowired
	CommandsService cs;

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	RESTClient restClient;

	String ipScope;

	ScanningServiceImpl(String ipScope) {
		this.ipScope = ipScope;
	}

	@Override
	public void run() {
		String output = cs.execute("C:\\Nmap\\nmap.exe", ipScope);
		List<ServiceInstance> list = discoveryClient.getInstances("Manager");
		restClient.setUri(list.get(0).getHost()+"/report");
		restClient.setBody(output);
	}

}
