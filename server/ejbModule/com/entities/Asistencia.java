package com.entities;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "ASISTENCIAS")
@NamedQuery(name = "Asistencia.findAll", query = "SELECT a FROM Asistencia a")
public class Asistencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AsistenciaPK id;

	private BigDecimal asistencia;

	private BigDecimal calificacion;

	// bi-directional many-to-one association to Evento
	@ManyToOne
	@JoinColumn(name = "EVENTO")
	private Evento evento;

	// uni-directional many-to-one association to Estudiante
	@ManyToOne
	@JoinColumn(name = "ESTUDIANTE")
	private Estudiante estudiante;

}