package org.mycontrib.hex.bank.rest.controller;

import org.mycontrib.hex.bank.core.api.ag.AccountService;
import org.mycontrib.hex.bank.rest.dto.TransferRequest;
import org.mycontrib.hex.bank.rest.dto.TransferResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/rest/bank-api/movement" , headers="Accept=application/json")
//@CrossOrigin permet d'ajouter des autorisations "CORS" pour que ce web service
//puisse être appelé en mode ajax depuis d'autres origines/url que http://localhost:8080
@CrossOrigin(origins = "*" , methods = { RequestMethod.GET , RequestMethod.POST})
public class MovementRestController {
	
	@Autowired
	private AccountService accountService;
	
	//@PostMapping("deposit" )
	//....
	
	//@PostMapping("withdraw" )
	//....
		
	//URL: ./rest/bank-api/transfer
	//appelé en mode POST avec dans la partie invisible "body" de la requête:
	// { "amount" : 50.0 , "sourceAccountId" : "1" , "targetAccountId" : "2" }
	@PostMapping("transfer" )
	public TransferResponse postTransfert(@RequestBody TransferRequest transferRequest) {
		TransferResponse transferResponse = new TransferResponse(transferRequest.getAmount(),
				                                                 transferRequest.getSourceAccountId(),
				                                                 transferRequest.getTargetAccountId());
		try {
			accountService.transfer(transferRequest.getAmount(),
					                transferRequest.getSourceAccountId(),
					                transferRequest.getTargetAccountId());
			transferResponse.setStatus(true);
			transferResponse.setMessage("successful transfer done");
		} catch (Exception e) {
			//e.printStackTrace();
			transferResponse.setStatus(false);
			transferResponse.setMessage("transfer failed");
		}
		return transferResponse;
	}
		
			
	

}
