package org.mycontrib.appliSpringWeb.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @ToString
public class News {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

	private String text;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	public News(Long id, String text,Date date) {
		super();
		this.id = id;
		this.text = text;
		this.date = date;
	}

	public News(Long id, String text) {
		this(id,text,new Date());
	}
	

}
