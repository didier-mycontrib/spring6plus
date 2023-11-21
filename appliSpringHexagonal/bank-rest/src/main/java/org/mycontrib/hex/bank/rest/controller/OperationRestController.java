package org.mycontrib.hex.bank.rest.controller;

import java.time.LocalDate;
import java.util.List;

import org.mycontrib.hex.bank.domain.api.OperationService;
import org.mycontrib.hex.bank.domain.entity.Operation;
import org.mycontrib.hex.generic.domain.exception.NotFoundDomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/rest/bank-api/operation" , headers="Accept=application/json")
//@CrossOrigin permet d'ajouter des autorisations "CORS" pour que ce web service
//puisse être appelé en mode ajax depuis d'autres origines/url que http://localhost:8080
@CrossOrigin(origins = "*" , methods = { RequestMethod.GET , RequestMethod.POST})
public class OperationRestController {
	
	@Autowired
	private OperationService operationService;
	
	// URL= ./rest/bank-api/operation/a23b4...56
	@GetMapping("/{id}")
	public Operation getOperationDtoById(@PathVariable("id") String id) throws NotFoundDomainException {
			Operation operation = operationService.getById(id); //may throwing NotFoundDomainException
			return operation;
	}
	
	// URL= ./rest/bank-api/operation?accountId=1&firstDate=2023-10-01&lastDate=2023-11-24
	//   or ./rest/bank-api/operation?accountId=1&firstDate=2023-10-01 with default lastDate = today
	//   or ./rest/bank-api/operation?accountId=1 with default lastDate = today and default firstDate = now minus 2 months
	@GetMapping("")
	public List<Operation> getOperationDtoByCriteria(
			    @RequestParam(value="accountId",required=true) String accountId,
			    @RequestParam(value="firstDate",required=false) String firstDate,
			    @RequestParam(value="lastDate",required=false) String lastDate) 
					throws NotFoundDomainException {			
		    List<Operation> operations = null;
		    if(lastDate==null) lastDate=LocalDate.now().toString();
		    if(firstDate==null) firstDate=LocalDate.now().minusMonths(2).toString();
		    operations=operationService.queryOperationsForAccount(accountId, firstDate, lastDate);
		    return operations;
	}
			


}
