package com.entities;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name="USER_ANALISTAS")
@NamedQuery(name="Analista.findAll", query="SELECT a FROM Analista a")
public class Analista implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USER_ANALISTAS_USUARIO_GENERATOR", sequenceName="SEQ_USER_ANALISTAS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_ANALISTAS_USUARIO_GENERATOR")
	private long usuario;

	//bi-directional many-to-one association to AccionConstancia
	@OneToMany(mappedBy="analista")
	private List<AccionConstancia> accionCons;

	//bi-directional many-to-many association to Evento
	@ManyToMany
	@JoinTable(
		name="GESTIONES"
		, joinColumns={
			@JoinColumn(name="ANALISTA")
			}
		, inverseJoinColumns={
			@JoinColumn(name="EVENTO")
			}
		)
	private List<Evento> eventos;

	// Esto tira error
	//uni-directional one-to-one association to Usuario
	/*
	@OneToOne
	@JoinColumn(name="USUARIO")
	private Usuario usuario;
	*/

	public AccionConstancia addAccionCon(AccionConstancia accionCon) {
		getAccionCons().add(accionCon);
		accionCon.setAnalista(this);

		return accionCon;
	}

	public AccionConstancia removeAccionCon(AccionConstancia accionCon) {
		getAccionCons().remove(accionCon);
		accionCon.setAnalista(null);

		return accionCon;
	}

}