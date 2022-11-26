package com.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
@Table(name = "DEPARTAMENTOS")
@NamedQuery(name = "Departamento.findAll", query = "SELECT d FROM Departamento d")
@NamedQuery(name = "Localidad.findByDepartamento", query = "SELECT l.idLocalidad, l.nombre FROM Localidad l JOIN Departamento d WHERE d.idDepartamento = :id")
public class Departamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "DEPARTAMENTOS_IDDEPARTAMENTO_GENERATOR", sequenceName = "SEQ_DEPARTAMENTOS")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEPARTAMENTOS_IDDEPARTAMENTO_GENERATOR")
	@Column(name = "ID_DEPARTAMENTO")
	private long idDepartamento;

	@Column(unique = true, nullable = false)
	private String nombre;

	@ToString.Exclude
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "departamento")
	private List<Itr> itrs;

	@ToString.Exclude
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "departamento")
	private List<Localidad> localidades;

	@Override
	public String toString() {
		return nombre;
	}

}