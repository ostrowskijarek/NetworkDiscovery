package com.blogspot.itsystemengineer.Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class CommandsServiceImpl implements CommandsService {
	@Override
	public String execute(String cmd, String param) {
		System.out.println("cmd: " + cmd);
		System.out.println("param: " + param);
		String result = "";
		String error = "";
		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(cmd, param);
			Process process = builder.start();
			result = convert(process.getInputStream());
			error = convert(process.getErrorStream());
			System.out.println("result: " + result);
			System.out.println("error: " + error);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	public String convert(InputStream inputStream) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			return br.lines().collect(Collectors.joining(System.lineSeparator()));
		}
	}

}
