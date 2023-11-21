package org.mycontrib.hex.bank.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 OneToOne : ContactDetails of Customer
 */

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name="contact_details")
public class ContactDetailsEntity {
	
	@Id	
	@Column(name="customer_id")
	private Long customerId; 
	
	private String email;
	private String mobile;
	private String mainAddress;
	
	@OneToOne(optional=false)
	@MapsId//@MapsId is better than @PrimaryKeyJoinColumn
	@JoinColumn(name = "customer_id")
	private CustomerEntity customer;
	
	
	@Override
	public String toString() {
		return "ContactDetails [email=" + email + ", mobile=" + mobile + ", mainAddress=" + mainAddress + "]";
	}



	public ContactDetailsEntity(String email, String mobile, String mainAddress) {
		super();
		this.email = email;
		this.mobile = mobile;
		this.mainAddress = mainAddress;
	}
	
	
}
