package org.mycontrib.hex.bank.domain.entity;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Getter @Setter @NoArgsConstructor
public class Operation {
	
	
	private Long idOp;
	//private String idOp;
	//private UUID idOp;
	private Double montant;
	private String label;
	private String date;
	
	private Account compte;
	
	

	public Operation(/*UUID*/Long idOp, Double montant, String label, String date, Account compte) {
		super();
		this.idOp = idOp;
		this.montant = montant;
		this.label = label;
		this.date = date;
		this.compte = compte;
	}
	
	public Operation(/*UUID*/Long idOp, Double montant, String label, String date) {
		this(idOp,montant,label,date,null);
	}


	@Override
	public String toString() {
		return "Operation [idOp=" + idOp + ", montant=" + montant + ", label=" + label + ", date=" + date + "]";
	}

}
