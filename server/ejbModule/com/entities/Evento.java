package com.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.enumerators.EnumEventoModalidad;
import com.enumerators.EnumEventoTipo;

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

	@Column(name="NOMBRE")
	private String nombre;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_FIN")
	private Date fechaFin;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_INICIO")
	private Date fechaInicio;

	private EnumEventoModalidad modalidad;
	
	private EnumEventoTipo tipo;

	@ManyToOne
	@JoinColumn(name = "itr")
	private Itr itr;
	
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

}