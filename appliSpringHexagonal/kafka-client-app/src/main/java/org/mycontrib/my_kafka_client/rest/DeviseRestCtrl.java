package org.mycontrib.my_kafka_client.rest;

import org.mycontrib.my_kafka_client.data.Devise;
import org.mycontrib.my_kafka_client.exchanges.DevisesSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/rest/api-devise/admin-devise" , headers="Accept=application/json")
public class DeviseRestCtrl {
	
	@Autowired
	private DevisesSender devisesSender;
	
	//http://localhost:8585/kafka-client-app/rest/api-devise/admin-devise
	//POST { "code": "USD" , "name": "dollar" , "rate" : 1.1 }
    //this will send a message to update devise rate .
	@PostMapping("") 
	public Devise postDevise(@RequestBody Devise nouvelleDevise) {
		devisesSender.sendDeviseMessage(nouvelleDevise);
		return nouvelleDevise;
	}
	
}
