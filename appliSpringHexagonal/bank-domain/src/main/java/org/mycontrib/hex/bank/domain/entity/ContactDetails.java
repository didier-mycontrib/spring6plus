package org.mycontrib.hex.bank.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ContactDetails {
	private String email;
	private String mobile;
	private String mainAddress;
	
	
	
	@Override
	public String toString() {
		return "ContactDetails [email=" + email + ", mobile=" + mobile + ", mainAddress=" + mainAddress + "]";
	}



	public ContactDetails(String email, String mobile, String mainAddress) {
		super();
		this.email = email;
		this.mobile = mobile;
		this.mainAddress = mainAddress;
	}
	
	
}
