package tp.appliSpring.core.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="client")
@NamedQuery(name="Client.findAll",query="SELECT c FROM Client c")
@NamedQuery(name="Client.findWithAccountById",
            query="SELECT c FROM Client c LEFT JOIN FETCH c.comptes WHERE c.numero = ?1")
@Getter @Setter
@NoArgsConstructor
public class Client {
	
	//un client aura souvent plusieurs comptes
	//bien que rare , un compte peut être associé à plusieurs client (ex: co-propriété)
	//many-to-many , avec coté principal "client" et coté secondaire "compte"
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "client_compte" ,
	    joinColumns = { @JoinColumn(name="numClient")} ,
	    inverseJoinColumns = { @JoinColumn(name="numCompte")}
	)
	@JsonIgnore //pour ignorer .comptes lorsque le client java sera transformé en client json
	            //MAIS c'est beaucoup moins bien que les DTO
	private List<Compte> comptes=new ArrayList<>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numero;
	private String prenom;
	private String nom;
	private String adresse;
	private String email;
	//...
	
	
	public Client(Long numero, String prenom, String nom, String adresse, String email) {
		super();
		this.numero = numero;
		this.prenom = prenom;
		this.nom = nom;
		this.adresse = adresse;
		this.email = email;
	}
	

	@Override
	public String toString() {
		return "Client [numero=" + numero + ", prenom=" + prenom + ", nom=" + nom + ", adresse=" + adresse + ", email="
				+ email + "]";
	}


}
