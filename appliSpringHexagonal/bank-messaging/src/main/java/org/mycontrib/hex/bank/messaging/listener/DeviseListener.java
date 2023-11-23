package org.mycontrib.hex.bank.messaging.listener;

import org.mycontrib.hex.bank.core.api.ag.DeviseService;
import org.mycontrib.hex.bank.core.domain.entity.Devise;
import org.mycontrib.hex.generic.core.exception.NotFoundDomainException;
import org.mycontrib.hex.generic.util.json.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Profile("withKafka")
public class DeviseListener {
	
	@Autowired
	private DeviseService deviseService;
	
	@KafkaListener(topics = "devises", groupId = "mygroup")
	public void listenMygroup(String message) {
	    System.out.println("bank-messaging: received Message in group mygroup: " + message);
	    Devise devise = JsonUtil.parse(message, Devise.class);
	    System.out.println("bank-messaging: received Devise in group mygroup: " + devise);
	    try {
			deviseService.update(devise);
			System.out.println("devise was updated");
		} catch (NotFoundDomainException e) {
			System.err.println("devise cannot be updated: " + e.getMessage());
		}
	}
	
	
	

}
