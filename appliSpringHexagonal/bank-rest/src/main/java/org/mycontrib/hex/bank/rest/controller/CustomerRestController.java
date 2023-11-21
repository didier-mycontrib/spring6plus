package org.mycontrib.hex.bank.rest.controller;

import java.util.List;

import org.mycontrib.hex.bank.domain.api.ag.CustomerService;
import org.mycontrib.hex.bank.domain.entity.Customer;
import org.mycontrib.hex.generic.domain.exception.NotFoundDomainException;
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
@RequestMapping(value="/rest/bank-api/customer" , headers="Accept=application/json")
//@CrossOrigin permet d'ajouter des autorisations "CORS" pour que ce web service
//puisse être appelé en mode ajax depuis d'autres origines/url que http://localhost:8080
@CrossOrigin(origins = "*" , methods = { RequestMethod.GET , RequestMethod.POST})
public class CustomerRestController {
	
	@Autowired
	private CustomerService customerService;
	
	// URL= ./rest/bank-api/customer/1_or_other_id
	//   or ./rest/bank-api/customer/1?withDetails=contact
	@GetMapping("/{id}")
	public Customer getCustomerDtoById(@PathVariable("id") String id,
			@RequestParam(value="withDetails",required=false) String withDetails) throws NotFoundDomainException {
		    Customer customer = null;
		    if(withDetails!=null)
			     customer = customerService.getById(id,withDetails); //may throwing NotFoundDomainException
		    else
		    	 customer = customerService.getById(id);
			customer=customer.clone();
			customer.setPassword(null); //DO NOT RETURN/DISPLAY PASSWORD (VERY CONFIDENTIAL)
			return customer;
	}
	// URL= ./rest/bank-api/customer/
	//   or ./rest/bank-api/customer?firstname=jean&lastname=bon
	@GetMapping("")
	public List<Customer> getCustomerDtoByCriteria(
			    @RequestParam(value="firstname",required=false) String  firstname,
			    @RequestParam(value="lastname",required=false) String  lastname) 
					throws NotFoundDomainException {			
		    List<Customer> customers = null;
		    if(firstname!=null && lastname!=null)
		    	customers=customerService.queryByFirstnameAndLastname(firstname, lastname);
		    else
		    	customers=customerService.queryAll(); 
			//return DtoConverter.INSTANCE.map(customers,CustomerL0.class);
		    return customers;
	}
			

	// URL= ./rest/bank-api/customer/1_or_other_id
	// appelé en mode DELETE
	@DeleteMapping("/{id}")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
		customerService.deleteById(id);//may throwing NotFoundDomainException
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

		
		
	//URL: ./rest/bank-api/customer
	//appelé en mode POST avec dans la partie invisible "body" de la requête:
	// { "id" : null , "label" : "compteQuiVaBien" , "balance" : 50.0 }
	// ou bien { "label" : "compteQuiVaBien" , "balance" : 50.0 }
	@PostMapping("" )
	public Customer postCustomer(@RequestBody Customer newCustomer) {
		Customer savedCustomer = customerService.create(newCustomer);
		return savedCustomer;
	}
		
			
	//exemple de fin d'URL: ./rest/bank-api/customer
	//ou bien               ./rest/bank-api/customer/5
	//appelé en mode PUT avec dans la partie invisible "body" de la requête:
	// { "id" : 5 , "label" : "compte5QueJaime" , "balance" : 150.0 }
		
	@PutMapping({"" , "/{id}" }) 
	public Customer putCustomerToUpdate(@RequestBody Customer customer , 
		      @PathVariable(value="id",required = false ) String idToUpdate) {
		if(customer.getId()==null)	
			customer.setId(idToUpdate);
		customerService.update(customer); //remonte NotFoundException si pas trouvé
		return customer;
	}

}
