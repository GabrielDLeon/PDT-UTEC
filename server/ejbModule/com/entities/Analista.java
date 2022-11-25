package com.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
@Entity
@Table(name="USER_ANALISTAS")
@PrimaryKeyJoinColumn(name = "USUARIO")
@NamedQuery(name="Analista.findAll", query="SELECT a FROM Analista a")
public class Analista extends Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

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

}