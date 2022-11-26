package com.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Table(name = "ACCION_CONS")
@NamedQuery(name = "AccionConstancia.findAll", query = "SELECT a FROM AccionConstancia a")
public class AccionConstancia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ACCION_CONS_IDACCIONCONS_GENERATOR", sequenceName = "SEQ_ACCION_CONS")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCION_CONS_IDACCIONCONS_GENERATOR")
	@Column(name = "ID_ACCION_CONS")
	private long idAccionCons;

	private String detalle;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	// bi-directional many-to-one association to Constancia
	@ManyToOne
	@JoinColumn(name = "CONSTANCIA")
	private Constancia constancia;

	// bi-directional many-to-one association to Analista
	@ManyToOne
	@JoinColumn(name = "ANALISTA")
	private Analista analista;

}