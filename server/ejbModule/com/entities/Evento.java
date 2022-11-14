package com.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.enumerators.EnumEventoModalidad;
import com.enumerators.EnumEventoTipo;

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
@Builder
@Entity
@Table(name="EVENTOS", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"NOMBRE", "FECHA_INICIO"})
})
@NamedQueries({
	@NamedQuery(name="Evento.findAll", query="SELECT e FROM Evento e"),
	@NamedQuery(name="Evento.findByTutor", query="SELECT e FROM Evento e JOIN e.tutores t WHERE t.idUsuario = :id ORDER BY e.idEvento"),
	@NamedQuery(name="Evento.findByItr", query="SELECT e FROM Evento e JOIN e.itr i WHERE i.idItr = :idItr ORDER BY e.idEvento")
})
public class Evento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EVENTOS_IDEVENTOS_GENERATOR", sequenceName="SEQ_EVENTOS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EVENTOS_IDEVENTOS_GENERATOR")
	@Column(name="ID_EVENTOS")
	private long idEvento;

	@Column(nullable = false)
	private String nombre;

	@Column(name = "FECHA_INICIO", columnDefinition = "TIMESTAMP", nullable = false)
	private LocalDateTime fechaInicio;

	@Column(name = "FECHA_FIN", columnDefinition = "TIMESTAMP", nullable = true)
	private LocalDateTime fechaFin;

	@Column(nullable = true)
	private String localizacion;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EnumEventoModalidad modalidad;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EnumEventoTipo tipo;
	
	/*
	 * TODO: Verificar que sea necesario su utilización
	 * @Column(nullable = false)
	 * @Enumerated(EnumType.STRING)
	 * private EnumEventoEstado estado;
	*/

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne
	@JoinColumn(name = "itr")
	private Itr itr;
	
	//bi-directional many-to-one association to Asistencia
	@ToString.Exclude
	@OneToMany(mappedBy="evento", cascade = CascadeType.ALL)
	private List<Asistencia> asistencias;

	//bi-directional many-to-one association to Constancia
	@ToString.Exclude
	@OneToMany(mappedBy="evento")
	private List<Constancia> constancias;

	//bi-directional many-to-many association to Analista
	@LazyCollection(LazyCollectionOption.FALSE)
	@ToString.Exclude
	@ManyToMany(mappedBy="eventos")
	private List<Analista> analistas;

	//uni-directional many-to-many association to Tutor
	@ManyToMany
	@ToString.Exclude
	@JoinTable(
		name="RESPONSABLES"
		, joinColumns={
			@JoinColumn(name="EVENTO")
			}
		, inverseJoinColumns={
			@JoinColumn(name="TUTOR")
			}
		)
	private List<Tutor> tutores;

	public String toStringAll() {
		return "Evento [idEvento=" + idEvento + ", nombre=" + nombre + ", fechaInicio=" + fechaInicio + ", fechaFin="
				+ fechaFin + ", modalidad=" + modalidad + ", tipo=" + tipo + ", itr=" + itr + "]";
	}

}