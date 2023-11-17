package org.mycontrib.hex.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HexBankApplication {

	public static void main(String[] args) {
		
		System.setProperty("spring.profiles.active", "h2,init,withSecurity");
		//System.setProperty("spring.profiles.active", "h2,init");
		SpringApplication.run(HexBankApplication.class, args);
		
		
		System.out.println("http://localhost:8080/bank-app");
	}

}
