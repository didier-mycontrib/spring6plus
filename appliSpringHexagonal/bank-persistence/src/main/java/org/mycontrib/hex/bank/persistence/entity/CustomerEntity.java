package org.mycontrib.hex.bank.persistence.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name="customer")
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstname;
	private String lastname;
	private String password;
	
	@OneToOne(optional=true,mappedBy="customer",cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	private ContactDetailsEntity contactDetails;//may be null if not known or not specified or not attached
	
	@Override
	public String toString() {
		return "CustomerEntity [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", password="
				+ password + "]";
	}

	public CustomerEntity(Long id, String firstname, String lastname, String password,ContactDetailsEntity contactDetails) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.contactDetails=contactDetails;
	}
	
	public CustomerEntity(Long id, String firstname, String lastname, String password) {
		this(id,firstname,lastname,password,null);
	}
	
	
	
	
	
}