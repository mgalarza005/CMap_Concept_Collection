package com.CMapCreatorAssistant.domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Launcher {
	int au;
	private static ModelAndView modelAndViewCon3;
	private String concepts;
	private String paths;
	private String content;
	private String filename;
	private String weigths;
	private String term;
	//private String saludo;
	
	@Autowired
	CMapData CMapRepository;

	public Launcher() throws IOException {
		super();
		this.au = 0;
		this.concepts = getConcepts();
		this.paths = getPaths();
		this.weigths = getWeigths();
	}


	public String getConcepts() throws IOException {
		HashMap <Integer, String> map = new HashMap <Integer, String> ();
		String s1;
		String c="";
		BufferedReader bf1 = new BufferedReader(new FileReader("C:\\Users\\MIKEL1\\git\\CMap_Concept_Collection\\.git\\CMap_Concept_Collection\\src\\main\\resources\\static\\files\\clusterDone.txt"));
		while ((s1 = bf1.readLine()) != null) { 
			Integer conceptWeigth=0;
			String[] aux = s1.split(":");
			String[] terms = aux[1].split(", ");
			for(int i=0; i<terms.length; i++) {
				conceptWeigth += getTermWeight(terms[i]);
			}
			map.put(conceptWeigth, s1);
		}
		SortedSet<Integer> keys = new TreeSet<>(Collections.reverseOrder());
		keys.addAll(map.keySet());
		for (Integer key : keys) { 
			   c+= map.get(key) + "\n";
			  
		}
		
		bf1.close();
		return c;
	}
	

	public String getWeigths() throws IOException {
		String jsonWeigths="";
		String s1;
		BufferedReader bf1 = new BufferedReader(new FileReader("C:\\Users\\MIKEL1\\git\\CMap_Concept_Collection\\.git\\CMap_Concept_Collection\\src\\main\\resources\\static\\files\\clusterDone.txt"));
		while ((s1 = bf1.readLine()) != null) {
			Integer conceptWeigth=0;
			String concept=null;
			String[] aux = s1.split(":");
			concept = aux[0];
			String[] terms = aux[1].split(", ");
			for(int i=0; i<terms.length; i++) {
				conceptWeigth += getTermWeight(terms[i]);
				
			}
			jsonWeigths += concept.replace("*", "")+" "+conceptWeigth+"\n";

		}
		bf1.close();
		return jsonWeigths;
	}
	
private static Integer getTermWeight(String term) throws IOException {
		
		BufferedReader bf2 = new BufferedReader(new FileReader("C:\\Users\\MIKEL1\\git\\CMap_Concept_Collection\\.git\\CMap_Concept_Collection\\src\\main\\resources\\static\\files\\termTable.txt"));
		String s2;
		String[] aux1=null;
		while ((s2 = bf2.readLine()) != null) {
			if (s2.contains(term)) {
				String[] aux= s2.split("kopurua:");
				aux1 = aux[1].split("   ");
				
				
			}
		}
		bf2.close();
		return Integer.parseInt(aux1[1]);	
	}
	
	public String getPaths() throws IOException {
		String s1;
		String p="";
		BufferedReader bf1 = new BufferedReader(new FileReader("C:\\Users\\MIKEL1\\git\\CMap_Concept_Collection\\.git\\CMap_Concept_Collection\\src\\main\\resources\\static\\files\\termTable.txt"));
		while ((s1 = bf1.readLine()) != null) {
			p += s1 + "\n";
		}

		bf1.close();
		return p;
	}

	@RequestMapping (value = "/codefiles/{fname}", method = RequestMethod.GET)
	public ResponseEntity<Object> redirectToExternalUrl(@PathVariable String fname) throws URISyntaxException {
	    
		Codefile cf = CMapRepository.findByfileName(fname);
		this.content = cf.getContent();
		this.filename=fname;
		
				
		URI uri = new URI("/f");
	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setLocation(uri);
	    return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
	}
	
	
	@RequestMapping (value = "/features/{term}", method = RequestMethod.GET)
	public ResponseEntity<Object> redirectToExternalUrl2(@PathVariable String term) throws URISyntaxException {
	    
		//Codefile cf = CMapRepository.findByfileName(fname);
		//this.content = cf.getContent();
		this.term=term;
		
				
		URI uri = new URI("/term");
	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setLocation(uri);
	    return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
	}
		
	@RequestMapping("/concepts")
	public ModelAndView Concepts(Model model1) throws IOException {
		
		model1.addAttribute("title", "Concepts");
		model1.addAttribute("concepts", concepts);
		model1.addAttribute("paths", paths);
		model1.addAttribute("weigths", weigths);
		
		ModelAndView modelAndViewCon1 = new ModelAndView("concepts");
		return modelAndViewCon1;
	}
	
	@RequestMapping("/features")
	public ModelAndView Feature(Model model1) throws IOException {
		
		
		ModelAndView modelAndViewCon = new ModelAndView("features");
		return modelAndViewCon;
	}
	
	@RequestMapping("/f")
	public ModelAndView FC(Model model1) throws IOException {
		
		
		System.out.println("Edukia: " + content);
		System.out.println("File-a: " + filename);
		
		
		model1.addAttribute("title", "Concepts");
		model1.addAttribute("concepts", content);
		model1.addAttribute("paths", filename);
		
		ModelAndView modelAndViewCon= new ModelAndView("f");
		return modelAndViewCon;
	}
	
	@RequestMapping("/term")
	public ModelAndView TermSimilarity(Model model1) throws IOException {

		model1.addAttribute("title", "Similarity");
		model1.addAttribute("term", term);
		ModelAndView modelAndViewCon= new ModelAndView("features");
		return modelAndViewCon;
	}


	@RequestMapping("/codefiles")
	public List<Codefile> codefiles(){
		return CMapRepository.findAll();
	}

	@RequestMapping("/home")
	public ModelAndView home(Model model) throws IOException {
		model.addAttribute("title", "Home page");
		
		ModelAndView modelAndView = new ModelAndView("home");
		return modelAndView;
	}

	
	@RequestMapping("/cloud")
	public ModelAndView Cloud(Model model2) throws IOException {
		//getConcepts();
		//getPaths();

		model2.addAttribute("title", "Concept Cloud");
		model2.addAttribute("concepts", concepts);
		model2.addAttribute("weigths", weigths);
		
		ModelAndView modelAndViewCon2 = new ModelAndView("conceptCloud");
		return modelAndViewCon2;
	}
	@RequestMapping("/about")
	public ModelAndView About(Model model3) throws IOException {
		//getConcepts();
		model3.addAttribute("concepts", concepts);
		modelAndViewCon3 = new ModelAndView("about");
		return modelAndViewCon3;
		
				
		
	}
	
	

	
	
	
}
