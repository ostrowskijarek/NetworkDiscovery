package com.blogspot.itsystemengineer.Services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.blogspot.itsystemengineer.NetworkDiscovery.NetworkDiscoveryApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NetworkDiscoveryApplication.class)
public class CommandsServiceTest {

	@Autowired
	CommandsService cs;

	@Test
	public void executeTest() {
		log.info("CommandsServiceTest executeTest() starts");
		String result = cs.execute("nmap", "localhost");
		log.debug("nmapCmd: " + result);
		assert (result.contains("nmap.org"));
		log.info("CommandsServiceTest executeTest() finishes");
	}

}
