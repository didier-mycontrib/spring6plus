package org.mycontrib.appliSpringWeb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity

@Getter @Setter @NoArgsConstructor @ToString
public class Devise {
	@Id
	private String code;
	
	private String nom;
	
	private Double change;

	public Devise(String code, String nom, Double change) {
		super();
		this.code = code;
		this.nom = nom;
		this.change = change;
	}
	
	

}
