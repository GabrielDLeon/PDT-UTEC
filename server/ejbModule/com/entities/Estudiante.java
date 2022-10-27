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

	private String generacion;

	//bi-directional many-to-one association to Constancia
	@OneToMany(mappedBy="estudiante")
	private List<Constancia> constancias;

	// Esto tira error
	//uni-directional one-to-one association to Usuario
	/*
	@OneToOne
	@JoinColumn(name="USUARIO")
	private Usuario usuario;
	*/
	
	// Se generó automaticamente
	public Constancia addConstancia(Constancia constancia) {
		getConstancias().add(constancia);
		constancia.setEstudiante(this);

		return constancia;
	}

	public Constancia removeConstancia(Constancia constancia) {
		getConstancias().remove(constancia);
		constancia.setEstudiante(null);

		return constancia;
	}

}