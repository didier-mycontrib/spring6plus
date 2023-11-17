package org.mycontrib.hex.bank.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class AccountL0 {
	
    private String id;
	private String label;
	private Double balance;

}
