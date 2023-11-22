package org.mycontrib.hex.bank.core.domain.entity;

import org.mycontrib.hex.generic.core.domain.entity.WithId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter @NoArgsConstructor
public class Customer implements WithId<String> {

	private String id;
	
	private String firstname;
	private String lastname;
	private String password;
	private ContactDetails contactDetails;//may be null if not known or not specified
	
	
	
	public Customer(String id, String firstname, String lastname, String password,ContactDetails contactDetails) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.contactDetails=contactDetails;
	}
	
	public Customer(String id, String firstname, String lastname, String password) {
		this(id,firstname,lastname, password,null);
	}
	
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", password=" + password
				+ "]";
	}


	@Override
	public String extractId() {
		return this.id;
	}

	@Override
	public Customer clone(){
		return new Customer(this.id,this.firstname,this.lastname,this.password,
				contactDetails==null?null:new ContactDetails(this.contactDetails.getEmail(),
						                                     this.contactDetails.getMobile(),
						                                     this.contactDetails.getMainAddress()));
	}




	
	
	
	

}
