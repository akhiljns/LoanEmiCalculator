package com.example.geektrustproblems;

import com.example.geektrustproblems.service.InputLoaderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GeektrustProblemsApplication {

	// public static void main(String[] args) {
	// SpringApplication.run(GeektrustProblemsApplication.class, args);
	// }

	private static InputLoaderService inputLoaderService;

	@Autowired
	public GeektrustProblemsApplication(InputLoaderService inputLoader) {
		GeektrustProblemsApplication.inputLoaderService = inputLoader;
	}

	public static void main(String[] args) {

		SpringApplication application = new SpringApplication(GeektrustProblemsApplication.class);
		application.setWebApplicationType(WebApplicationType.NONE);
		application.run(args);

		if (args.length > 0) {
			String filePath = args[0];
			inputLoaderService.loadInputFile(filePath);
		}

		// String filePath =
		// "D:/Programs/demo/geektrust-problems/geektrust-problems/src/main/resources/input1.txt";

		return;
	}

}
