package org.mycontrib.hex.bank.persistence.entity;

import java.io.Serializable;

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
	
	private Long accountId;
	private Long customerId;
}
