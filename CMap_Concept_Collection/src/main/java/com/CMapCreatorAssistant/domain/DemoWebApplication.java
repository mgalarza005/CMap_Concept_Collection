package com.CMapCreatorAssistant.domain;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class DemoWebApplication implements CommandLineRunner {

	private static Logger log=LoggerFactory.getLogger(DemoWebApplication.class);	
	public static void main(String[] args) {
		SpringApplication.run(DemoWebApplication.class, args);
	}
	
	
	
	@Override
	public void run(String... args) throws Exception{
		log.warn("Idazteko gaitasuna");
	}

}
