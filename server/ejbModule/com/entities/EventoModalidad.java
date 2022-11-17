package com.entities;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="EVENTOS_MODALIDADES")
@NamedQuery(name="EventoModalidad.findAll", query="SELECT m FROM EventoModalidad m")
public class EventoModalidad implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@SequenceGenerator(name="SEQ_EVENTO_MODALIDAD", sequenceName="SEQ_EVENTO_MODALIDAD")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_EVENTO_MODALIDAD")
	@Column(name = "ID_MODALIDAD")
	private Long idModalidad;
	
	@Column(unique = true, nullable = false)
	private String nombre;

	@Override
	public String toString() {
		return nombre;
	}
	
	
}
