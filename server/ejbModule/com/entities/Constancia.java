package com.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "CONSTANCIAS")
@NamedQuery(name = "Constancia.findAll", query = "SELECT c FROM Constancia c")
public class Constancia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "CONSTANCIAS_IDCONSTANCIA_GENERATOR", sequenceName = "SEQ_CONSTANCIAS")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONSTANCIAS_IDCONSTANCIA_GENERATOR")
	@Column(name = "ID_CONSTANCIA")
	private long idConstancia;

	private String detalle;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	// bi-directional many-to-one association to AccionConstancia
	@OneToMany(mappedBy = "constancia")
	private List<AccionConstancia> acciones;

	// uni-directional many-to-one association to ConstanciasTipo
	@ManyToOne
	@JoinColumn(name = "TIPO")
	private ConstanciasTipo tipo;

	// uni-directional many-to-one association to EstadoConstancia
	@ManyToOne
	@JoinColumn(name = "ESTADO")
	private EstadoConstancia estado;

	// bi-directional many-to-one association to Evento
	@ManyToOne
	@JoinColumn(name = "EVENTO")
	private Evento evento;

	// bi-directional many-to-one association to Estudiante
	@ManyToOne
	@JoinColumn(name = "ESTUDIANTE")
	private Estudiante estudiante;

	// Esto se generó automaticamente
	public AccionConstancia addAccione(AccionConstancia accione) {
		getAcciones().add(accione);
		accione.setConstancia(this);

		return accione;
	}

	public AccionConstancia removeAccione(AccionConstancia accione) {
		getAcciones().remove(accione);
		accione.setConstancia(null);

		return accione;
	}

}