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

		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			System.out.println("Windows");
			nmapCmd = cs.execute("C:\\Nmap\\nmap.exe", "127.0.0.1");
		} else {
			System.out.println("Not Windows");
			nmapCmd = cs.execute("nmap", "127.0.0.1");
		}
		System.out.println("nmapCmd: " + nmapCmd);
		assert (nmapCmd.contains("nmap.org"));
	}

}
