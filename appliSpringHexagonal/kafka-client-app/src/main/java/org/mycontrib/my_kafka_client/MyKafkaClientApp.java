package org.mycontrib.my_kafka_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyKafkaClientApp {
	
public static void main(String[] args) {
		
		System.setProperty("spring.profiles.active", "");
		SpringApplication.run(MyKafkaClientApp.class, args);
		
		System.out.println("http://localhost:8585/kafka-client-app");
	}

}
