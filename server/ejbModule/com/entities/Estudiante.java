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
@Table(name="USER_ESTUDIANTES")
@PrimaryKeyJoinColumn(name = "USUARIO")
@NamedQuery(name="Estudiante.findAll", query="SELECT e FROM Estudiante e")
public class Estudiante extends Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(length = 20)
	private String generacion;

	//bi-directional many-to-one association to Constancia
	@ToString.Exclude
	@OneToMany(mappedBy="estudiante")
	private List<Constancia> constancias;

	//bi-directional one-to-many association to Asistencia
	@ToString.Exclude
	@OneToMany(mappedBy="estudiante", orphanRemoval = true)
	List<Asistencia> asistencias;

	public Estudiante removeAsitencia(Asistencia asistencia) {
		asistencias.remove(asistencia);
		return this;
	}
}