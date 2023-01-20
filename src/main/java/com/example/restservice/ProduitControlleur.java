/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.restservice;



import dao.ProduitDao;
import java.util.HashMap;
import java.util.List;
import materiels.Enchere;
import materiels.Kilometrage;
import materiels.Produit;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class ProduitControlleur  {

	@GetMapping("/categorie/{id}")
	public ResponseEntity<Object> getKilometrageVehicule(@PathVariable int id) throws Exception{
            List<Produit> pdd=ProduitDao.getProduitBy(id);
            return new ResponseEntity<>(pdd, HttpStatus.OK);
	}
	@GetMapping("/enchere/{id}")
	public Enchere getEnchere(@PathVariable int id) throws Exception{
            Enchere pdd=ProduitDao.getEnchere(id);
            return pdd;
	}
    
       @PostMapping("/kilometrages") 
       public Kilometrage saveKilometrageVehicule(@RequestBody Kilometrage kilometrage) throws Exception{           
            return kilometrage.save();
	}
       
       
}
