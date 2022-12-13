package com.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.enumerators.EnumUsuarioEstado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
@Entity
@Table(name = "USUARIOS")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
public abstract class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "USUARIOS_IDUSUARIO_GENERATOR", sequenceName = "SEQ_USUARIOS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIOS_IDUSUARIO_GENERATOR")
	@Column(name = "ID_USUARIO")
	private long idUsuario;

	@Column(unique = true)
	private String usuario;

	private String clave;

	@Column(unique = true)
	private String documento;

	private String apellido1;

	private String apellido2;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_NAC")
	private Date fechaNac;

	private String mail;

	@Column(name = "MAIL_UTEC", unique = true)
	private String mailUtec;

	private String nombre1;

	private String nombre2;

	private String telefono;

	// bi-directional many-to-one association to Genero
	@ManyToOne
	@JoinColumn(name = "GENERO")
	private Genero genero;

	@Enumerated(value = EnumType.STRING)
	private EnumUsuarioEstado estado;

	// bi-directional many-to-one association to Itr
	@ManyToOne
	@JoinColumn(name = "ITR")
	private Itr itr;

	// bi-directional many-to-one association to Localidad
	@ManyToOne
	@JoinColumn(name = "LOCALIDAD")
	private Localidad localidad;

	// bi-directional many-to-one association to Departamento
	@ManyToOne
	@JoinColumn(name = "DEPARTAMENTO")
	private Departamento departamento;

}