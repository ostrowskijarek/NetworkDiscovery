
package com.blogspot.itsystemengineer.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.blogspot.itsystemengineer.Util.RESTClient;

@Service
@Scope("prototype")
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	RESTClient restClient;

	private String uri;

	ManagerServiceImpl(String uri) {
		this.uri = uri;
	}

	@Override
	public void run() {
		System.out.println("ManagerService Thread Started");
		System.out.println("URI: " + uri);
		restClient.setUri(uri);
		restClient.get();
	}

}
