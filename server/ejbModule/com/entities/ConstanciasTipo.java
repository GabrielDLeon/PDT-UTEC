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
@Table(name="CONSTANCIAS_TIPO")
@NamedQuery(name="ConstanciasTipo.findAll", query="SELECT c FROM ConstanciasTipo c")
public class ConstanciasTipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CONSTANCIAS_TIPO_IDTIPO_GENERATOR", sequenceName="SEQ_CONSTANCIAS_TIPO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONSTANCIAS_TIPO_IDTIPO_GENERATOR")
	@Column(name="ID_TIPO")
	private long idTipo;

	private String nombre;

}