package com.blogspot.itsystemengineer.Util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class NetworkToolsImpl implements NetworkTools {

	@Override
	public List<String> split(String ipRange, int scannersCounter) {
		log.info("Called split(), ipRange: "+ipRange+", scannersCounter: " +scannersCounter);

		List<String> returnList = new ArrayList<>();

		String[] ipSplit = ipRange.split("\\.");
		String dot = ".";
		String hyphen = "-";

		String[] rangeOctet = ipSplit[3].split("-");
		int start = Integer.parseInt(rangeOctet[0]);
		int stop = Integer.parseInt(rangeOctet[1]);

		int range = (stop - start) / scannersCounter;

		for (int i = 0; i < scannersCounter; i++) {
			int iStart = start + i * range;
			int iStop = stop;
			if (i + 1 < scannersCounter) {
				iStop = start + (i + 1) * range;
			}
			String element = ipSplit[0] + dot + ipSplit[1] + dot + ipSplit[2] + dot + iStart + hyphen + iStop;
			returnList.add(element);

		}
		log.info("Returning: "+returnList.toString());
		return returnList;
	}

}
