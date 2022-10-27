package com.entities;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name="EVENTOS")
@NamedQuery(name="Evento.findAll", query="SELECT e FROM Evento e")
public class Evento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EVENTOS_IDEVENTOS_GENERATOR", sequenceName="SEQ_EVENTOS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EVENTOS_IDEVENTOS_GENERATOR")
	@Column(name="ID_EVENTOS")
	private long idEventos;

	@Temporal(TemporalType.DATE)
	@Column(name="FINAL")
	private Date final_;

	@Temporal(TemporalType.DATE)
	private Date inicio;

	private String titulo;

	//bi-directional many-to-one association to Asistencia
	@OneToMany(mappedBy="evento")
	private List<Asistencia> asistencias;

	//bi-directional many-to-one association to Constancia
	@OneToMany(mappedBy="evento")
	private List<Constancia> constancias;

	//bi-directional many-to-many association to Analista
	@ManyToMany(mappedBy="eventos")
	private List<Analista> analistas;

	//uni-directional many-to-many association to Tutor
	@ManyToMany
	@JoinTable(
		name="RESPONSABLES"
		, joinColumns={
			@JoinColumn(name="EVENTO")
			}
		, inverseJoinColumns={
			@JoinColumn(name="TUTOR")
			}
		)
	private List<Tutor> tutor;

	// Se generó automaticamente
	public Asistencia addAsistencia(Asistencia asistencia) {
		getAsistencias().add(asistencia);
		asistencia.setEvento(this);

		return asistencia;
	}

	public Asistencia removeAsistencia(Asistencia asistencia) {
		getAsistencias().remove(asistencia);
		asistencia.setEvento(null);

		return asistencia;
	}

	public Constancia addConstancia(Constancia constancia) {
		getConstancias().add(constancia);
		constancia.setEvento(this);

		return constancia;
	}

	public Constancia removeConstancia(Constancia constancia) {
		getConstancias().remove(constancia);
		constancia.setEvento(null);

		return constancia;
	}

}