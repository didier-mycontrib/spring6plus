package org.mycontrib.appliSpringWeb.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

@NamedQuery(name="Customer.findByIdWithComptes" ,
            query="SELECT c FROM Customer c LEFT JOIN FETCH c.comptes WHERE c.id = ?1")
@Getter @Setter @NoArgsConstructor
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstname;
	private String lastname;
	private String password;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY , mappedBy="customer")
	private List<Compte> comptes=new ArrayList<>();
	
	public Customer(Long id, String firstname, String lastname, String password) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
	}
	
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", password=" + password
				+ "]";
	}




	
	
	
	

}
