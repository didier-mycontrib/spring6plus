package org.mycontrib.appliSpringWeb.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

@NamedQuery(name = "Compte.findBySoldeMini", 
            query = "SELECT c FROM Compte c WHERE c.solde>= ?1")
@NamedQuery(name = "Compte.findByIdWithOperations" ,
		    query = "SELECT c FROM Compte c LEFT JOIN FETCH c.operations WHERE c.numero= ?1")
//NB: le mot clef FETCH permet de remonter tout de suite en mémoire
//les éléments de la collection des operations (même en mode LAZY imposé par spring-data)
@Getter @Setter @NoArgsConstructor
public class Compte {
	
	@Transient //@Transient (de javax.persistence) signifie 
	//ne pas sauvegarder le champ en base de doonnes
	private static Double decouvertAutorise = -500.0;
	
	public static Double getDecouvertAutorise() {
		return decouvertAutorise;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numero;
	

	private String label;
	
	private Double solde;
	
	@ManyToOne
	//@JoinColumn(name = "id_customer") //pas evolutif vers many-many
	@JoinTable(name="CompteCustomer",
		joinColumns={@JoinColumn(name="num_compte")},
		inverseJoinColumns={@JoinColumn(name="id_customer")})
	private Customer customer;
	
	//@JsonIgnore 
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "compte" , cascade = CascadeType.ALL)
	//@OneToMany(fetch = FetchType.EAGER, mappedBy = "compte")
	private List<Operation> operations; //+get/set
	
	

	public Compte(Long numero, String label, Double solde) {
		super();
		this.numero = numero;
		this.label = label;
		this.solde = solde;
	}



	@Override
	public String toString() {
		return "Compte [numero=" + numero + ", label=" + label + ", solde=" + solde + "]";
	}
	
	
	

}
