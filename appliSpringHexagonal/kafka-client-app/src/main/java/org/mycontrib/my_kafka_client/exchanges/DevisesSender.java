package org.mycontrib.my_kafka_client.exchanges;

import java.util.concurrent.CompletableFuture;

import org.mycontrib.hex.generic.util.json.JsonUtil;
import org.mycontrib.hex.generic.util.notification.Notification;
import org.mycontrib.my_kafka_client.data.Devise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DevisesSender {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	
	@PostConstruct
	public void autoStart(){
		//sendMessage("message in the bottle sent at " + LocalDateTime.now().toString());
		//sendDeviseMessage(new Devise("USD","Dollar",1.012345));
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
	
	public void sendDeviseMessage(Devise devise) {
		sendMessage(JsonUtil.stringify(new Notification<Devise>(devise,Notification.UPDATED,"mygroup")));
	}

}
