package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Inheritance (strategy = InheritanceType.JOINED)
@JsonTypeInfo( use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "operationName")
@JsonSubTypes({ @Type(value = Transfer.class, name = "transfer"), @Type(value = Consultation.class, name = "consultation") })
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Operation {
		@Id 
		@GeneratedValue (strategy = GenerationType.AUTO)
		private int id;
		
		//private String operationName;
		
		
		
		
}
	