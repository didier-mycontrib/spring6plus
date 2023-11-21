package org.mycontrib.hex.bank.persistence.entity;

import java.time.LocalDate;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter 
@Table(name="account_ownersphip")
@NamedQuery(name="AccountOwnershipEntity.accountListFromCustomerId",
            query="SELECT ao.account FROM AccountOwnershipEntity ao WHERE ao.pk.customerId=?1")
public class AccountOwnershipEntity {
	
	@EmbeddedId
	private AccountOwnershipCompositePk pk;
	//pk with subparts .accountId and .customerId
	
	private LocalDate beginDate;
	private LocalDate endDate; //may be null
	
	@ManyToOne
	@JoinColumn(name="accountId")
	@MapsId("accountId") //pk.accountId
	private AccountEntity account;
	
	@ManyToOne
	@JoinColumn(name="customerId")
	@MapsId("customerId") //pk.customerId
	private CustomerEntity customer;
	

	
	@Override
	public String toString() {
		return "AccountOwnership [accountId=" + pk.getAccountId() + ", customerId=" + pk.getCustomerId() + ", beginDate=" + beginDate
				+ ", endDate=" + endDate + "]";
	}

	public AccountOwnershipEntity(AccountEntity account, CustomerEntity customer,LocalDate beginDate, LocalDate endDate) {
		this.account=account;
		this.customer=customer;
		this.pk = new AccountOwnershipCompositePk(account.getId(),customer.getId());
		this.beginDate = beginDate;
		this.endDate = endDate;
	}
	
	
	public AccountOwnershipEntity(AccountEntity account, CustomerEntity customer, LocalDate beginDate) {
		this(account,customer,beginDate,null);
	}
	
	public AccountOwnershipEntity(AccountEntity account, CustomerEntity customer) {
		this(account,customer,LocalDate.now());
	}
	
	public AccountOwnershipEntity() {
		super();
	}
	
	
}
