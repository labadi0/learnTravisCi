package entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data

@NoArgsConstructor
@ToString
public class Consultation extends Operation {
	@Column(nullable = false,length = 40)
	private int numberConsultation;

	public Consultation(int id ,int numberConsultation) {
		super(id);
		this.numberConsultation = numberConsultation;
	}
	


	


}
