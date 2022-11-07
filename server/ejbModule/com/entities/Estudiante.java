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
@Table(name="USER_ESTUDIANTES")
@NamedQuery(name="Estudiante.findAll", query="SELECT e FROM Estudiante e")
public class Estudiante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USER_ESTUDIANTES_USUARIO_GENERATOR", sequenceName="SEQ_USER_ESTUDIANTES")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_ESTUDIANTES_USUARIO_GENERATOR")
	private long usuario;

	@Column(length = 20, nullable = true)
	private String generacion;

	//bi-directional many-to-one association to Constancia
	@ToString.Exclude
	@OneToMany(mappedBy="estudiante")
	private List<Constancia> constancias;

	//bi-directional one-to-many association to Asistencia
	@ToString.Exclude
	@OneToMany(mappedBy="estudiante")
	List<Asistencia> asistencias;
	
}