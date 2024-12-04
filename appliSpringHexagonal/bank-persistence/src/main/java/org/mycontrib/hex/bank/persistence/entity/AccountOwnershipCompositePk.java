package org.mycontrib.hex.bank.persistence.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class AccountOwnershipCompositePk implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Column(name="accound_id")
	private Long accountId;

	@Column(name="customer_id")
	private Long customerId;
}
