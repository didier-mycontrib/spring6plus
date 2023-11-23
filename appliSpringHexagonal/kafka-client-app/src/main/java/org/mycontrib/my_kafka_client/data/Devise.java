package org.mycontrib.my_kafka_client.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @NoArgsConstructor
public class Devise {
	
	private String code; //"EUR" or "USD" or ...
	private String name; //"euro" or "dollar" or ...
	private Double rate; //nb unités pour 1 euro 
	
	public Devise(String code, String name, Double rate) {
		super();
		this.code = code;
		this.name = name;
		this.rate = rate;
	}
	
	
}
