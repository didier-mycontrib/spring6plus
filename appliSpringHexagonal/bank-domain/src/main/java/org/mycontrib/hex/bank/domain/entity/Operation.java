package org.mycontrib.hex.bank.domain.entity;

import org.mycontrib.hex.generic.domain.entity.WithId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Getter @Setter @NoArgsConstructor
public class Operation implements WithId<String>{
	
	
	//private Long id;
	private String id;
	//private UUID id;
	private Double amount;
	private String label;
	private String timestamp;//au format YYYY-MM-ddTHH:MM:SS
	
	private Account account;
	
	

	public Operation(String id, Double amount, String label, String timestamp, Account account) {
		super();
		this.id = id;
		this.amount = amount;
		this.label = label;
		this.timestamp = timestamp;
		this.account = account;
	}
	
	public Operation(String id, Double amount, String label, String timestamp) {
		this(id,amount,label,timestamp,null);
	}

	@Override
	public String toString() {
		return "Operation [id=" + id + ", amount=" + amount + ", label=" + label + ", timestamp=" + timestamp + "]";
	}

	@Override
	public String extractId() {
		return id;
	}


	
}
