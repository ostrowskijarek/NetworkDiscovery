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
	String nmapCmd;
	
	@Test
	public void execute() {
		if (System.getProperty("os.name").contains("windows")) {
		 nmapCmd = cs.execute("C:\\Nmap\\nmap.exe","");
		} else {
		 nmapCmd = cs.execute("nmap","");
		}
		System.out.println("nmapCmd: "+nmapCmd);
		assert(nmapCmd.contains("nmap.org"));
	}

}
