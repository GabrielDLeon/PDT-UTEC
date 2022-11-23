package com.entities;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.NamedNativeQuery;

import com.enumerators.EnumAsistenciaEstado;

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
@NamedQueries({
		@NamedQuery(name = "Asistencia.find", query = "SELECT a FROM Asistencia a WHERE a.id.idEvento = :idEvento AND a.id.idEstudiante = :idEstudiante"),
		@NamedQuery(name = "Asistencia.findByEvento", query = "SELECT a FROM Asistencia a JOIN FETCH a.evento e WHERE e.idEvento = :id"),
		@NamedQuery(name = "Asistencia.findByStatus", query = "SELECT a FROM Asistencia a JOIN FETCH a.evento e WHERE e.idEvento = :id AND a.estado = :status") })
public class Asistencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AsistenciaKey id = new AsistenciaKey();

	public Asistencia(long idEvento, long idEstudiante, Evento evento, Estudiante estudiante,
			EnumAsistenciaEstado estado, double calificacion) {
		this.id = new AsistenciaKey(idEvento, idEstudiante);
		this.evento = evento;
		this.estudiante = estudiante;
		this.estado = estado;
		this.calificacion = calificacion;
	}

	// bi-directional many-to-one association to Evento
	@ManyToOne
	@MapsId("idEvento")
	@JoinColumn(name = "EVENTO")
	private Evento evento;

	// uni-directional many-to-one association to Estudiante
	@ManyToOne
	@MapsId("idEstudiante")
	@JoinColumn(name = "ESTUDIANTE")
	private Estudiante estudiante;

	// Atributos propios de la entidad Asistencias

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EnumAsistenciaEstado estado;

	@Column(nullable = true, length = 1, precision = 1, scale = 2)
	private double calificacion;

}