package org.mycontrib.hex.bank.core.domain.entity;

import org.mycontrib.hex.generic.core.domain.entity.WithId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @NoArgsConstructor
public class Devise implements WithId<String>{
	
	private String code; //"EUR" or "USD" or ...
	private String name; //"euro" or "dollar" or ...
	private Double rate; //nb unités pour 1 euro 
	
	public Devise(String code, String name, Double rate) {
		super();
		this.code = code;
		this.name = name;
		this.rate = rate;
	}

	@Override
	public String extractId() {
		return code;
	}
	
	
}
