package com.example.restservice;

import java.time.LocalDate;
import materiels.Categorie;
import java.util.ArrayList;
import java.util.HashMap;
import materiels.Assurance;
import materiels.Compte;
import materiels.Transaction;
import model.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class CompteControlleur  {

	@GetMapping("/compte/lister")
	public ResponseEntity<Object> getCategories() throws Exception{            
            return new ResponseEntity<>(Categorie.getListCategorie(), HttpStatus.OK);
	} 
        @PostMapping(value = "/compte/recharger")
	public ResponseEntity<Object> recharger(@RequestBody Compte id) { 
            try{
                id.setDate(LocalDate.now().toString());
                Client cp=Client.FindByToken(id.getClient().getToken());
                id.setClient(cp);
                id.save();
                return new ResponseEntity<>("Inserer avec succes", HttpStatus.OK);
            }
            catch(Exception aaa){
                aaa.printStackTrace();
                return new ResponseEntity<>("{\"message\": \""+aaa.getMessage()+"\"}", HttpStatus.OK);
            }
	} 
	@PutMapping("/compte/valider")
	public ResponseEntity<Object> valider(@RequestParam(name="id")String id) {            
            try{
                Compte.update("true", id);
            }
            catch(Exception aaa){
                aaa.printStackTrace();
                return new ResponseEntity<>("{\"message\": \""+aaa.getMessage()+"\"}", HttpStatus.OK);
            }
            return new ResponseEntity<>("Valider avec succes", HttpStatus.OK);
	} 
	@PostMapping("/transaction/encherir")
	public ResponseEntity<Object> miser(@RequestBody Transaction transact) throws Exception{            
            try{
                transact.save();
            }
            catch(Exception aaa){
                aaa.printStackTrace();
                return new ResponseEntity<>("{\"message\": \""+aaa.getMessage()+"\"}", HttpStatus.OK);
            }
            return new ResponseEntity<>("Miser avec succes", HttpStatus.OK);
	} 
}