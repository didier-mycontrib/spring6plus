package org.mycontrib.hex.bank.persistence.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Getter @Setter @NoArgsConstructor
@Table(name="operation")
public class OperationEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	private Double amount;
	private String label;
	private String timestamp;//au format YYYY-MM-ddTHH:MM:SS
	
	@ManyToOne
	@JoinColumn(name="account_id")
	private AccountEntity account;
	
	

	public OperationEntity(UUID id, Double amount, String label, String timestamp, AccountEntity account) {
		super();
		this.id = id;
		this.amount = amount;
		this.label = label;
		this.timestamp = timestamp;
		this.account = account;
	}
	
	public OperationEntity(UUID id, Double amount, String label, String timestamp) {
		this(id,amount,label,timestamp,null);
	}

	@Override
	public String toString() {
		return "Operation [id=" + id + ", amount=" + amount + ", label=" + label + ", timestamp=" + timestamp + "]";
	}

	
}
