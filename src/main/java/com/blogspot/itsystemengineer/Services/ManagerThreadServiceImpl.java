
package com.blogspot.itsystemengineer.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.blogspot.itsystemengineer.Util.RESTClient;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@Scope("prototype")
public class ManagerThreadServiceImpl implements ManagerThreadService {

	@Autowired
	RESTClient restClient;

	private String uri;

	ManagerThreadServiceImpl(String uri) {
		this.uri = uri;
	}

	@Override
	public void run() {
		log.info("ManagerService Thread starts for uri: "+uri);
		restClient.setUri(uri);
		restClient.get();
		log.info("ManagerService Thread finishes for uri: "+uri);
	}

}
