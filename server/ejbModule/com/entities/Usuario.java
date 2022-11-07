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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name="USUARIOS")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USUARIOS_IDUSUARIO_GENERATOR", sequenceName="SEQ_USUARIOS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USUARIOS_IDUSUARIO_GENERATOR")
	@Column(name="ID_USUARIO")
	private long idUsuario;
	
	@Column(nullable = false, unique = true)
	private String usuario;

	@Column(nullable = false)
	private String clave;
	
	@Column(nullable = false, unique = true)
	private String documento;

	@Column(nullable = false)
	private String apellido1;

	@Column(nullable = true)
	private String apellido2;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_NAC", nullable = false)
	private Date fechaNac;

	@Column(nullable = true)
	private String mail;

	@Column(name="MAIL_UTEC", unique = true, nullable = false)
	private String mailUtec;

	@Column(nullable = false)
	private String nombre1;

	@Column(nullable = true)
	private String nombre2;

	@Column(nullable = true)
	private String telefono;

	//bi-directional many-to-one association to Genero
	@ManyToOne
	@JoinColumn(name="GENERO", nullable = true)
	private Genero genero;

	//bi-directional many-to-one association to Itr
	@ManyToOne
	@JoinColumn(name="ITR", nullable = true)
	private Itr itr;

	//bi-directional many-to-one association to Localidad
	@ManyToOne
	@JoinColumn(name="LOCALIDAD", nullable = false)
	private Localidad localidad;
	
	//bi-directional many-to-one association to Departamento
	@ManyToOne
	@JoinColumn(name="DEPARTAMENTO", nullable = true)
	private Departamento departamento;

}