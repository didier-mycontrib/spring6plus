package org.mycontrib.hex.bank.rest.controller;

import org.mycontrib.hex.bank.domain.api.ag.AccountService;
import org.mycontrib.hex.bank.domain.entity.Account;
import org.mycontrib.hex.bank.rest.adapter.DtoConverter;
import org.mycontrib.hex.bank.rest.dto.AccountL0;
import org.mycontrib.hex.generic.domain.exception.NotFoundDomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	//   or ./rest/bank-api/account/1?detailLevel=1ou2ouAutre
	@GetMapping("/{id}")
	public AccountL0 getAccountDtoById(@PathVariable("id") String id,
			@RequestParam(value="detailLevel",required=false) Integer detailLevel) throws NotFoundDomainException {
			Account account = accountService.getById(id); //may throwing NotFoundDomainException
			return DtoConverter.INSTANCE.map(account,AccountL0.class);
	}
			

	// URL= ./rest/bank-api/account/1_or_other_id
	// appelé en mode DELETE
	@DeleteMapping("/{id}")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
		accountService.deleteById(id);//may throwing NotFoundDomainException
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
/*
		//exemple de fin d'URL: ./rest/api-bank/compte
		//                      ./rest/api-bank/compte?soldeMini=0
	    //                      ./rest/api-bank/compte?customerId=1
		@GetMapping("" )
		@PreAuthorize("hasRole('ROLE_CUSTOMER') || hasRole('ROLE_ADMIN')")
		public List<CompteL1> getComptes(
				 @RequestParam(value="soldeMini",required=false) Double soldeMini,
				 @RequestParam(value="customerId",required=false) Long customerId){
			if(soldeMini!=null)
				return dtoConverter.map(
						serviceCompte.rechercherSelonSoldeMini(soldeMini),CompteL1.class);
			else if(customerId!=null)
			    return dtoConverter.map(
			    		serviceCompte.rechercherComptesDuClient(customerId),CompteL1.class);
			else
			 return serviceCompte.searchAllDto(CompteL1.class);
		}
		
		
		
		//exemple de fin d'URL: ./rest/api-bank/compte
		//appelé en mode POST avec dans la partie invisible "body" de la requête:
		// { "numero" : null , "label" : "compteQuiVaBien" , "solde" : 50.0 , "numeroClient" : 1}
		// ou bien { "label" : "compteQuiVaBien" , "solde" : 50.0  , "numeroClient" : null}
		@PostMapping("" ) //NOUVELLE VERSION avec CompteDtoEx et .numeroClient (éventuellement null)
		@PreAuthorize("hasRole('ROLE_ADMIN')")
		public CompteL1 postCompte(@RequestBody CompteL1 nouveauCompte) {
		    //on s'appuie ici sur la méthode spécifique ci dessous du serviceCompte
			return serviceCompte.saveNewFromDto(nouveauCompte);
		}
		
		
			
			//exemple de fin d'URL: ./rest/api-bank/compte
			//ou bien               ./rest/api-bank/compte/5
			//appelé en mode PUT avec dans la partie invisible "body" de la requête:
			// { "numero" : 5 , "label" : "compte5QueJaime" , "solde" : 150.0 , "numeroClient" : null}
			//ou bien {  "label" : "compte5QueJaime" , "solde" : 150.0 , "numeroClient" : 1}
			@PutMapping({"" , "/{id}" }) 
			@PreAuthorize("hasRole('ROLE_ADMIN')")
			public CompteL1 putCompteToUpdate(@RequestBody CompteL1 compteDto , 
					      @PathVariable(value="id",required = false ) Long idToUpdate) {
				    if(compteDto.getNumero()==null)	compteDto.setNumero(idToUpdate);
					serviceCompte.updateExistingFromDto(compteDto); //remonte NotFoundException si pas trouvé
					return compteDto;
			}
*/
}
