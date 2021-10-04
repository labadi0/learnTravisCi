package entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Qualifier;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Transfer extends Operation {
	@Column(nullable = false, length = 40)
	//@JsonProperty("moneyTransfer")
	private double moneyTransfer;

	public Transfer(int id,double moneyTransfer) {
		super(id);
		this.moneyTransfer = moneyTransfer;

	}

	
	
	

}