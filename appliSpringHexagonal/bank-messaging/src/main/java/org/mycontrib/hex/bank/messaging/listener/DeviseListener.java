package org.mycontrib.hex.bank.messaging.listener;

import org.mycontrib.hex.bank.core.api.ag.DeviseService;
import org.mycontrib.hex.bank.core.domain.entity.Devise;
import org.mycontrib.hex.generic.core.exception.NotFoundDomainException;
import org.mycontrib.hex.generic.util.json.JsonUtil;
import org.mycontrib.hex.generic.util.notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;

@Component
@Profile("withKafka")
public class DeviseListener {
	
	@Value(value = "${spring.kafka.consumer.group-id}")
	private String groupId;
	
	@Autowired
	private DeviseService deviseService;
	
	@KafkaListener(topics = "devises", groupId = "${spring.kafka.consumer.group-id}" )
	public void listenMygroup(String message) {
	    System.out.println("bank-messaging: received Message in group " + groupId+ ": " + message);
	    Notification<Devise> notifDevise = JsonUtil.parse(message, new TypeReference<Notification<Devise>>() {});
	    System.out.println("bank-messaging: received notifDevise : " + notifDevise);
	    try {
	    	Devise devise= notifDevise.getContent();
	    	if(Notification.UPDATED.equals(notifDevise.getType())) {
	    		if("bank".equals(notifDevise.getFrom())) {
	    			 System.out.println("devise already updated by this app=bank");
	    		}else {
			   deviseService.update(devise);
			   System.out.println("devise was now updated");
	    	   }
	    	}
		} catch (NotFoundDomainException e) {
			System.err.println("devise cannot be updated: " + e.getMessage());
		}
	}
	
	
	

}
