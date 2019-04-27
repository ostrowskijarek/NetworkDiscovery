package com.blogspot.itsystemengineer.Services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.blogspot.itsystemengineer.NetworkDiscovery.NetworkDiscoveryApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NetworkDiscoveryApplication.class)
public class CommandsServiceTest {

	@Autowired
	CommandsService cs;

	@Test
	public void execute() {
		String nmapCmd = cs.execute("C:\\Nmap\\nmap.exe","");
		assert(nmapCmd.contains("Usage: nmap [Scan Type(s)] [Options] {target specification}"));
	}

}
