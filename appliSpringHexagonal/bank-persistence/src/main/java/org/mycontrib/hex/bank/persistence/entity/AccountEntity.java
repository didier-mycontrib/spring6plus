package org.mycontrib.hex.bank.persistence.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	
	@OneToMany(mappedBy="account",fetch = FetchType.LAZY)
	private List<AccountOwnershipEntity> accountOwnerships = new ArrayList<AccountOwnershipEntity>();
	
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