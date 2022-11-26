package com.entities;

import java.io.Serializable;
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
import javax.persistence.UniqueConstraint;

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
@Table(name = "LOCALIDADES", uniqueConstraints = { @UniqueConstraint(columnNames = { "nombre", "departamento" }) })
@NamedQuery(name = "Localidad.findAll", query = "SELECT l FROM Localidad l")
//@NamedQuery(name ="Localidad.findByDepartamento", query="SELECT l FROM Localidad l JOIN l.departamento d WHERE d.idDepartamento = :id")
public class Localidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "LOCALIDADES_IDLOCALIDAD_GENERATOR", sequenceName = "SEQ_LOCALIDADES")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOCALIDADES_IDLOCALIDAD_GENERATOR")
	@Column(name = "ID_LOCALIDAD")
	private long idLocalidad;

	@Column(length = 50, nullable = false)
	private String nombre;

	// bi-directional many-to-one association to Departamento
	@ManyToOne
	@JoinColumn(name = "DEPARTAMENTO")
	private Departamento departamento;

	// bi-directional many-to-one association to Usuario
	@ToString.Exclude
	@OneToMany(mappedBy = "localidad")
	private List<Usuario> usuarios;

	@Override
	public String toString() {
		return nombre;
	}

}