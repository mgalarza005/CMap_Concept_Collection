package com.CMapCreatorAssistant.domain;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloWorld {
	private String saludo;

	public String getSaludo() {
		return saludo;
	}

	public void setSaludo(String saludo) {
		this.saludo = saludo;
	} 
	   
	
	@RequestMapping(value="")
	public ResponseEntity<String> mostrar(){
		String aux ="Hello word!!!!"; 
		
		return new ResponseEntity<String>(aux, HttpStatus.OK);
	}
}
