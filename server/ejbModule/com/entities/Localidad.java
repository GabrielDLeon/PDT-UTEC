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
@Table(name="LOCALIDADES", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"nombre", "departamento"})
})
@NamedQuery(name="Localidad.findAll", query="SELECT l FROM Localidad l")
//@NamedQuery(name ="Localidad.findByDepartamento", query="SELECT l FROM Localidad l JOIN l.departamento d WHERE d.idDepartamento = :id")
public class Localidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LOCALIDADES_IDLOCALIDAD_GENERATOR", sequenceName="SEQ_LOCALIDADES")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LOCALIDADES_IDLOCALIDAD_GENERATOR")
	@Column(name="ID_LOCALIDAD")
	private long idLocalidad;

	@Column(length = 50, nullable = false)
	private String nombre;

	//bi-directional many-to-one association to Departamento
	@ManyToOne
	@JoinColumn(name="DEPARTAMENTO")
	private Departamento departamento;

	//bi-directional many-to-one association to Usuario
	@ToString.Exclude
	@OneToMany(mappedBy="localidad")
	private List<Usuario> usuarios;
	
	@Override
	public String toString() {
		return nombre;
	}

}