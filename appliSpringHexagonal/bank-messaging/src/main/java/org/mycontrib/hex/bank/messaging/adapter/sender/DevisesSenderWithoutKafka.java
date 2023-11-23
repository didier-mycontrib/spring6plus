package org.mycontrib.hex.bank.messaging.adapter.sender;

import org.mycontrib.hex.bank.core.domain.entity.Devise;
import org.mycontrib.hex.generic.util.json.JsonUtil;
import org.mycontrib.hex.generic.util.notification.Notification;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;



@Component
@Profile("!withKafka")
public class DevisesSenderWithoutKafka implements DevisesSender {
	
	
	public void sendMessage(String message) {
	       System.out.println("Sent message in console= " + message );
	}
	
	public void sendDeviseMessage(Devise devise,String eventType) {
		sendMessage(JsonUtil.stringify(new Notification<Devise>(devise,eventType,"bank")));
	}

}
