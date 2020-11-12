package com.CMapCreatorAssistant.domain;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HelloWorld {
	private String concepts="";
	//private String saludo;

	@Autowired
	CMapRepository CMapRepository;


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
	
	//Bakar bat lortzeko
	@RequestMapping("/codefiles/{fname}")
	public List<Codefile> showFile(@PathVariable String fname){
		return CMapRepository.findByfileNameContaining(fname);
	}


	@RequestMapping("/codefiles")
	public List<Codefile> codefiles(){
		return CMapRepository.findAll();
	}

	@RequestMapping("/")
	public ModelAndView home(Model model) throws IOException {
		model.addAttribute("title", "Home page");
		ModelAndView modelAndView = new ModelAndView("home");
		return modelAndView;
	}

	@RequestMapping("/concepts")
	public ModelAndView About(Model model) throws IOException {
		getConcepts();

		model.addAttribute("title", "Concepts");
		model.addAttribute("concepts", concepts);
		ModelAndView modelAndViewCon = new ModelAndView("concepts");
		return modelAndViewCon;
	}
}
