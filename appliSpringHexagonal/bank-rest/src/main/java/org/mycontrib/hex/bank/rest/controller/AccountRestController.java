package org.mycontrib.hex.bank.rest.controller;

import java.util.List;

import org.mycontrib.hex.bank.core.api.ag.AccountService;
import org.mycontrib.hex.bank.core.domain.entity.Account;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/rest/bank-api/account" , headers="Accept=application/json")
//@CrossOrigin permet d'ajouter des autorisations "CORS" pour que ce web service
//puisse être appelé en mode ajax depuis d'autres origines/url que http://localhost:8080
@CrossOrigin(origins = "*" , methods = { RequestMethod.GET , RequestMethod.POST})
public class AccountRestController {
	
	@Autowired
	private AccountService accountService;
	
	// URL= ./rest/bank-api/account/1_or_other_id
	//   or ./rest/bank-api/account/1?detailsLevel=0ou1ou2ouAutre
	@GetMapping("/{id}")
	public Account getAccountDtoById(@PathVariable("id") String id,
			@RequestParam(value="detailsLevel",required=false) Integer detailsLevel) throws NotFoundDomainException {
		    
			Account account = accountService.getById(id); //may throwing NotFoundDomainException
            
			return account;
	}
	// URL= ./rest/bank-api/account/
	//   or ./rest/bank-api/account?minBalance=0.0
	//   or ./rest/bank-api/account?customerId=1
	@GetMapping("")
	public List<Account> getAccountDtoByCriteria(
			    @RequestParam(value="minBalance",required=false) Double minBalance,
			    @RequestParam(value="customerId",required=false) String customerId) 
					throws NotFoundDomainException {			
		    List<Account> accounts = null;
		    if(customerId!=null)
		    	accounts=accountService.queryAccountsByCustomerOwnerships(customerId);
		    else
		      if(minBalance!=null)
			    	accounts=accountService.queryAccountsByMinimunBalance(minBalance);
		    else
		    	accounts=accountService.queryAll(); 
		    return accounts;
	}
			

	// URL= ./rest/bank-api/account/1_or_other_id
	// appelé en mode DELETE
	@DeleteMapping("/{id}")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
		accountService.deleteById(id);//may throwing NotFoundDomainException
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

		
		
	//URL: ./rest/bank-api/account
	//appelé en mode POST avec dans la partie invisible "body" de la requête:
	// { "id" : null , "label" : "compteQuiVaBien" , "balance" : 50.0 }
	// ou bien { "label" : "compteQuiVaBien" , "balance" : 50.0 }
	@PostMapping("" )
	public Account postAccount(@RequestBody Account newAccount) {
		Account savedAccount = accountService.create(newAccount);
		return savedAccount;
	}
		
			
	//exemple de fin d'URL: ./rest/bank-api/account
	//ou bien               ./rest/bank-api/account/5
	//appelé en mode PUT avec dans la partie invisible "body" de la requête:
	// { "id" : 5 , "label" : "compte5QueJaime" , "balance" : 150.0 }
		
	@PutMapping({"" , "/{id}" }) 
	public Account putAccountToUpdate(@RequestBody Account account , 
		      @PathVariable(value="id",required = false ) String idToUpdate) {
		if(account.getId()==null)	
			account.setId(idToUpdate);
		accountService.update(account); //remonte NotFoundException si pas trouvé
		return account;
	}

}
