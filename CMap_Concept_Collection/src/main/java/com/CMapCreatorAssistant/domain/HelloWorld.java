package com.CMapCreatorAssistant.domain;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloWorld {
	private String concepts="";
	//private String saludo;

	   
	/*
	@RequestMapping(value="")
	public ResponseEntity<String> mostrar(){
		String aux ="Hello word!!!!"; 
		
		return new ResponseEntity<String>(aux, HttpStatus.OK);
	}
	*/
	public void getConcepts() throws IOException {
		String s;
		BufferedReader bf = new BufferedReader(new FileReader("C:\\Users\\MIKEL1\\git\\CMap_Concept_Collection\\.git\\CMap_Concept_Collection\\src\\main\\resources\\templates\\clusterakEginda.txt"));


             
		while ((s = bf.readLine()) != null) {
			concepts += s + "\n";
		}
		
		bf.close();
	}
	
	@RequestMapping("/")
	public String home(Model model) throws IOException {
			
	
		
		model.addAttribute("title", "Home page");
		
		
		return "home";
	}
	
	@RequestMapping("/concepts")
	public String About(Model model) throws IOException {
		getConcepts();
		
		model.addAttribute("title", "Concepts");
		model.addAttribute("concepts", concepts);
		return "concepts";
	}
}
