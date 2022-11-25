package com.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.enumerators.EnumAsistenciaEstado;

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
@Table(name = "ASISTENCIAS")
@NamedQueries({
	@NamedQuery(name = "Asistencia.findByEvento", query = "SELECT a FROM Asistencia a JOIN a.evento e WHERE e.idEvento = :id"),
	@NamedQuery(name = "Asistencia.findByStatus", query = "SELECT a FROM Asistencia a JOIN a.evento e WHERE e.idEvento = :id AND a.estado = :status")
})
public class Asistencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AsistenciaKey id = new AsistenciaKey();
	
	public Asistencia(long idEvento, long idEstudiante, Evento evento, Estudiante estudiante, EnumAsistenciaEstado estado, BigDecimal calificacion) {
		this.id = new AsistenciaKey(idEvento, idEstudiante);
		this.evento = evento;
		this.estudiante = estudiante;
		this.estado = estado;
		this.calificacion = calificacion;
	}
	
	// bi-directional many-to-one association to Evento
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@MapsId("idEvento")
	@JoinColumn(name = "EVENTO")
	private Evento evento;

	// uni-directional many-to-one association to Estudiante
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@MapsId("idEstudiante")
	@JoinColumn(name = "ESTUDIANTE")
	private Estudiante estudiante;

	// Atributos propios de la entidad Asistencias
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EnumAsistenciaEstado estado;

	@Column(nullable = true)
	private BigDecimal calificacion;
	
}