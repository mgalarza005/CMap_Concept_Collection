package com.CMapCreatorAssistant.domain;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class WebApplication implements CommandLineRunner {

	private static Logger log=LoggerFactory.getLogger(WebApplication.class);	
	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
	
	
	
	@Override
	public void run(String... args) throws Exception{
		log.warn("Idazteko gaitasuna");
	}

}
