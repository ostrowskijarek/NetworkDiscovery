
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

	String nmap;

	@Override
	public void run() {

		System.out.println("ipScope: " + ipScope);

		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			System.out.println("Windows");
			nmap = "C:\\Nmap\\nmap.exe";
		} else {
			System.out.println("Not Windows");
			nmap = "nmap";
		}
		String output = cs.execute(nmap, ipScope);
		List<ServiceInstance> list = discoveryClient.getInstances("Manager");
		String uri = "http://"+list.get(0).getHost() + ":" + list.get(0).getPort() + "/report";
		System.out.println("URI: "+uri);
		restClient.setUri(uri);
		restClient.setBody(output);
		restClient.post();
	}

}
