package com.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
@ToString
@Builder
@Entity
@Table(name="ESTADOS_CONS")
@NamedQuery(name="EstadoConstancia.findAll", query="SELECT e FROM EstadoConstancia e")
public class EstadoConstancia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ESTADOS_CONS_IDESTADOSCONS_GENERATOR", sequenceName="SEQ_ESTADOS_CONS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ESTADOS_CONS_IDESTADOSCONS_GENERATOR")
	@Column(name="ID_ESTADOS_CONS")
	private long idEstadosCons;

	private String nombre;

}