package org.mycontrib.hex.bank.persistence.entity;

import org.mycontrib.hex.generic.core.domain.entity.WithId;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="devise")
@Getter @Setter @ToString @NoArgsConstructor
public class DeviseEntity {
	
	@Id //no auto-incr
	private String code; //"EUR" or "USD" or ...
	
	private String name; //"euro" or "dollar" or ...
	private Double rate; //nb unités pour 1 euro 
	
	public DeviseEntity(String code, String name, Double rate) {
		super();
		this.code = code;
		this.name = name;
		this.rate = rate;
	}
	
}
