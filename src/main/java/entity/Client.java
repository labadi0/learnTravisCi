package entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data

@NoArgsConstructor

@ToString
public class Client {
	@Id 
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false,length = 40)
	private String name;
	
	@Column(nullable = false,length = 40)
	private String rib;
	
	@Column(nullable = false,length = 40)
	private double amount;
	
	@OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Operation.class)
	private Operation operation;

	public Client(int id, String name, String rib, double amount, Operation operation) {
		super();
		this.id = id;
		this.name = name;
		this.rib = rib;
		this.amount = amount;
		this.operation = operation;
	}



	
}
