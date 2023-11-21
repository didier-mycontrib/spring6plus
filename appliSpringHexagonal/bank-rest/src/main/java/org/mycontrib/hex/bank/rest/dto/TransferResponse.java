package org.mycontrib.hex.bank.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @NoArgsConstructor
public class TransferResponse {
	private Double amount;
	private String sourceAccountId;
	private String targetAccountId;
	private Boolean status;
	private String message;
	
	public TransferResponse(Double amount, String sourceAccountId, String targetAccountId, Boolean status,
			String message) {
		this.amount = amount;
		this.sourceAccountId = sourceAccountId;
		this.targetAccountId = targetAccountId;
		this.status = status;
		this.message = message;
	}
	
	public TransferResponse(Double amount, String sourceAccountId, String targetAccountId) {
		this(amount,sourceAccountId,targetAccountId,null,null);
	}
	
	
}
