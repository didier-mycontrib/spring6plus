package org.mycontrib.hex.bank.core.domain.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.mycontrib.hex.generic.core.domain.entity.WithId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class Account implements WithId<String>{

	private static Double authorizedOverdraft  = -500.0; //decouvertAutorise
	
	//private UUID id;
	//private Long id;
	private String id;
	
	private String label;
	private Double balance;
	
	private List<AccountOwnership> accountOwnerships = new ArrayList<AccountOwnership>();
	
	public void addCustomerOwnership(String customerId) {
		AccountOwnership accountOwnership= new AccountOwnership(id,customerId,LocalDate.now().toString(),null);
		accountOwnerships.add(accountOwnership);
	}
	
	
	public Account(String id, String label, Double balance) {
		this.id = id;
		this.label = label;
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "Account [id=" + id + ", label=" + label + ", balance=" + balance + "]";
	}
	
	public void credit(Double amount) {
		this.balance += amount;
	}
	
	public void debit(Double amount) {
		this.balance -= amount;
	}
	
	public static Double getAuthorizedOverdraft() {
		return authorizedOverdraft;
	}

	@Override
	public int hashCode() {
		return Objects.hash(balance, id, label);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(balance, other.balance) && Objects.equals(id, other.id)
				&& Objects.equals(label, other.label);
	}

	@Override
	public String extractId() {
		return this.id;
	}


}
