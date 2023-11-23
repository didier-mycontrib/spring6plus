package org.mycontrib.hex.bank.rest.controller;

import java.util.List;

import org.mycontrib.hex.bank.core.api.ag.DeviseService;
import org.mycontrib.hex.bank.core.domain.entity.Devise;
import org.mycontrib.hex.bank.core.spi.DeviseNotifier;
import org.mycontrib.hex.generic.core.exception.NotFoundDomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/rest/bank-api/devise" , headers="Accept=application/json")
//@CrossOrigin permet d'ajouter des autorisations "CORS" pour que ce web service
//puisse être appelé en mode ajax depuis d'autres origines/url que http://localhost:8080
@CrossOrigin(origins = "*" , methods = { RequestMethod.GET , RequestMethod.POST})
public class DeviseRestController {
	
	@Autowired
	private DeviseService deviseService;
	
	@Autowired
	private DeviseNotifier deviseNotifier;
	
	// URL= ./rest/bank-api/devise/USD_or_EUR_or_else
	@GetMapping("/{id}")
	public Devise getDeviseDtoById(@PathVariable("id") String id) throws NotFoundDomainException {
			Devise devise = deviseService.getById(id); //may throwing NotFoundDomainException
			return devise;
	}
	
	// URL= ./rest/bank-api/devise/
	@GetMapping("")
	public List<Devise> getDeviseDtoByCriteria() 
					throws NotFoundDomainException {			
		    List<Devise> devises = null;
		    devises=deviseService.queryAll(); 
		    return devises;
	}
			

	// URL= ./rest/bank-api/devise/USD_or_other_id
	// appelé en mode DELETE
	@DeleteMapping("/{id}")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
		deviseService.deleteById(id);//may throwing NotFoundDomainException
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

		
		
	//URL: ./rest/bank-api/devise
	//appelé en mode POST avec dans la partie invisible "body" de la requête:
	// { "code": "MS1" , "name": "monnaie_singe_1" , "rate" : 12345.6789 }
	@PostMapping("" )
	public Devise postDevise(@RequestBody Devise newDevise) {
		Devise savedDevise = deviseService.create(newDevise);
		return savedDevise;
	}
		
			
	//exemple de fin d'URL: ./rest/bank-api/devise
	//ou bien               ./rest/bank-api/devise/USD
	//appelé en mode PUT avec dans la partie invisible "body" de la requête:
	// { "code": "USD" , "name": "dollar" , "rate" : 1.1 }
		
	@PutMapping({"" , "/{id}" }) 
	public Devise putDeviseToUpdate(@RequestBody Devise devise , 
		      @PathVariable(value="id",required = false ) String idToUpdate) {
		if(devise.getCode()==null)	
			devise.setCode(idToUpdate);
		deviseService.update(devise); //remonte NotFoundException si pas trouvé
		deviseNotifier.notifyNewState(devise);
		return devise;
	}

}
