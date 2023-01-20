package com.example.restservice;

import materiels.Categorie;
import java.util.ArrayList;
import java.util.HashMap;
import materiels.Assurance;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class CategorieController  {

	@GetMapping("/categors")
	public ResponseEntity<Object> getCategories() throws Exception{
            
            return new ResponseEntity<>(Categorie.getListCategorie(), HttpStatus.OK);
	} 
}