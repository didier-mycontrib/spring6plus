package org.mycontrib.hex.bank.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @NoArgsConstructor
public class TransferRequest {
	
	private Double amount;
	private String sourceAccountId;
	private String targetAccountId;
	
	public TransferRequest(Double amount, String sourceAccountId, String targetAccountId) {
		super();
		this.amount = amount;
		this.sourceAccountId = sourceAccountId;
		this.targetAccountId = targetAccountId;
	}
	
}
