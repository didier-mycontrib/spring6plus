package org.mycontrib.my_kafka_client.exchanges;

import org.mycontrib.hex.generic.util.json.JsonUtil;
import org.mycontrib.hex.generic.util.notification.Notification;
import org.mycontrib.my_kafka_client.data.Devise;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;

@Component
public class DeviseListener {
	
	@Value(value = "${spring.kafka.consumer.group-id}")
	private String groupId;
	
	@KafkaListener(topics = "devises", groupId = "${spring.kafka.consumer.group-id}")
	public void listenMygroup(String message) {
	    System.out.println("kafka-client-app: received Message in group " + groupId+ ": " + message);
	    Notification<Devise> notifDevise = JsonUtil.parse(message, new TypeReference<Notification<Devise>>() {});
	    System.out.println("kafka-client-app: received notifDevise : " + notifDevise);
	}
	
	
	

}
