package org.mycontrib.hex.bank.messaging.adapter.sender;

import java.util.concurrent.CompletableFuture;

import org.mycontrib.hex.bank.core.domain.entity.Devise;
import org.mycontrib.hex.generic.util.json.JsonUtil;
import org.mycontrib.hex.generic.util.notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;



@Component
@Profile("withKafka")
public class DevisesSenderWithKafka implements DevisesSender {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	
	//@PostConstruct
	public void autoStart(){
		//sendDeviseMessage(new Notification<Devise>(new Devise("USD","Dollar",1.012345),"updated","bank"));
	}

	public void sendMessage(String message) {
		String topicName="devises";
		CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);
	     future.whenComplete((result, ex) -> {
	         if (ex == null) {
	             System.out.println("Sent message=[" + message + 
	                 "] with offset=[" + result.getRecordMetadata().offset() + "]");
	         } else {
	             System.out.println("Unable to send message=[" + 
	                 message + "] due to : " + ex.getMessage());
	         }
	     });
	}
	
	public void sendDeviseMessage(Devise devise,String eventType) {
		sendMessage(JsonUtil.stringify(new Notification<Devise>(devise,eventType,"bank")));
	}

}
