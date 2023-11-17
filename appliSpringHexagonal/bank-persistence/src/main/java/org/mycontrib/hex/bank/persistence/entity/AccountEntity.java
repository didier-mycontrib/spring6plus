package org.mycontrib.hex.bank.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name="account")
public class AccountEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String label;
	private Double balance;
	@Override
	
	public String toString() {
		return "AccountEntity [id=" + id + ", label=" + label + ", balance=" + balance + "]";
	}
	public AccountEntity(Long id, String label, Double balance) {
		this.id = id;
		this.label = label;
		this.balance = balance;
	}
	
	
	
	
}