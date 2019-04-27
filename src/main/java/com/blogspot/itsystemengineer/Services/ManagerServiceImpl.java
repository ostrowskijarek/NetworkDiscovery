
package com.blogspot.itsystemengineer.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.blogspot.itsystemengineer.Util.NetworkTools;
import com.blogspot.itsystemengineer.Util.RESTClient;

import lombok.Getter;
import lombok.Setter;

@Service
@Scope("prototype")
public class ManagerServiceImpl implements ManagerService {
	@Autowired
	private DiscoveryClient discoveryClient;
	@Autowired
	NetworkTools networkTools;
	@Autowired
	RESTClient restClient;

	@Getter
	@Setter
	private int scannersCounter;
	@Getter
	@Setter
	private String ipRange;
	@Getter
	@Setter
	private List<String> subnetSubset;

	ManagerServiceImpl(String ipRange) {
		this.ipRange = ipRange;
	}

	@Override
	public void run() {

		List<ServiceInstance> list = discoveryClient.getInstances("Scanner");
		List<String> ipRanges = networkTools.split(ipRange, list.size());
		int i = 0;
		for (String range : ipRanges) {
			restClient.setUri(list.get(i++).getHost() + "/scan/ipScope=" + range);
			restClient.get();
		}

	}

}
