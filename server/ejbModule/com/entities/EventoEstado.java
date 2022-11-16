package com.entities;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="EVENTOS_ESTADOS")
@NamedQuery(name="EventoEstado.findAll", query="SELECT e FROM EventoEstado e")
public class EventoEstado implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@SequenceGenerator(name="SEQ_EVENTO_ESTADO", sequenceName="SEQ_EVENTO_ESTADO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_EVENTO_ESTADO")
	@Column(name = "ID_ESTADO")
	private Long idEstado;
	
	@Column(unique = true, nullable = false)
	private String nombre;

	@Override
	public String toString() {
		return nombre;
	}
	
	
}
