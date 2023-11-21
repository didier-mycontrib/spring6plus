package org.mycontrib.hex.bank.domain.entity;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class AccountOwnership {
	private String accountId;
	private String customerId;
	private String beginDate;
	private String endDate; //may be null
	
	@Override
	public String toString() {
		return "AccountOwnership [accountId=" + accountId + ", customerId=" + customerId + ", beginDate=" + beginDate
				+ ", endDate=" + endDate + "]";
	}

	public AccountOwnership(String accountId, String customerId, String beginDate, String endDate) {
		super();
		this.accountId = accountId;
		this.customerId = customerId;
		this.beginDate = beginDate;
		this.endDate = endDate;
	}
	
	public AccountOwnership(String accountId, String customerId, String beginDate) {
		this(accountId,customerId,beginDate,null);
	}
	
	public AccountOwnership(String accountId, String customerId) {
		this(accountId,customerId,LocalDateTime.now().toString(),null);
	}
	
	
}
