package org.mycontrib.my_kafka_client.exchanges;

import org.mycontrib.hex.generic.util.json.JsonUtil;
import org.mycontrib.my_kafka_client.data.Devise;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//@Component
public class DeviseListener {
	
	//@KafkaListener(topics = "devises", groupId = "mygroup")
	public void listenMygroup(String message) {
	    System.out.println("kafka-client-app: received Message in group mygroup: " + message);
	    Devise devise = JsonUtil.parse(message, Devise.class);
	    System.out.println("kafka-client-app: received Devise in group mygroup: " + devise);
	}
	
	
	

}
