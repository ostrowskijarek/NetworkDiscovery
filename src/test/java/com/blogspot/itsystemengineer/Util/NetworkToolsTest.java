package com.blogspot.itsystemengineer.Util;

import java.util.List;
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
public class NetworkToolsTest {
	
	
	@Autowired
	NetworkTools networkTools;
	
	@Test
	public void splitTest() {
		log.info("NetworkToolsTest splitTest() starts");
		int scannersCounter = 4;
		String ipRange = "192.168.1.100-150";
		List<String> list = networkTools.split(ipRange, scannersCounter);
		assert(list.size()==4);		
		assert(list.get(0).equals("192.168.1.100-112"));
		assert(list.get(1).equals("192.168.1.112-124"));
		assert(list.get(2).equals("192.168.1.124-136"));
		assert(list.get(3).equals("192.168.1.136-150"));
		log.info("NetworkToolsTest splitTest() finishes");

	}

}
