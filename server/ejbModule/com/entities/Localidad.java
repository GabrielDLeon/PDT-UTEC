package com.entities;

import java.io.Serializable;
import javax.persistence.*;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name="LOCALIDADES")
@NamedQuery(name="Localidad.findAll", query="SELECT l FROM Localidad l")
public class Localidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LOCALIDADES_IDLOCALIDAD_GENERATOR", sequenceName="SEQ_LOCALIDADES")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LOCALIDADES_IDLOCALIDAD_GENERATOR")
	@Column(name="ID_LOCALIDAD")
	private long idLocalidad;

	private String nombre;

	//bi-directional many-to-one association to Departamento
	@ManyToOne
	@JoinColumn(name="DEPARTAMENTO")
	private Departamento departamento;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="localidad")
	private List<Usuario> usuarios;

	
	// Esto se generó automaticamente
	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setLocalidad(this);
		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setLocalidad(null);
		return usuario;
	}

}