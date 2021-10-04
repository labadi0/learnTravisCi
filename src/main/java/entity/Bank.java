package entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data

@NoArgsConstructor
public class Bank {
	@Id 
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int idBank;
	@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Client.class)
	private List<Client> clients = new ArrayList<Client>();
	@Column(nullable = false,length = 40)
	private String bankName;
	public Bank(int idBank, List<Client> clients, String bankName) {
		super();
		this.idBank = idBank;
		this.clients = clients;
		this.bankName = bankName;
	}
	
	
	
}
