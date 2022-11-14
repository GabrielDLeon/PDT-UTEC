package com.entities;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

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