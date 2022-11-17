package com.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.enumerators.EnumUsuarioEstado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
@Entity
@Table(name="USUARIOS")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public abstract class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USUARIOS_IDUSUARIO_GENERATOR", sequenceName="SEQ_USUARIOS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USUARIOS_IDUSUARIO_GENERATOR")
	@Column(name="ID_USUARIO")
	private long idUsuario;
	
	@Column(unique = true)
	private String usuario;

	private String clave;
	
	@Column(unique = true)
	private String documento;

	private String apellido1;

	private String apellido2;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_NAC")
	private Date fechaNac;

	private String mail;

	@Column(name="MAIL_UTEC", unique = true)
	private String mailUtec;

	private String nombre1;

	private String nombre2;

	private String telefono;

	//bi-directional many-to-one association to Genero
	@ManyToOne
	@JoinColumn(name="GENERO")
	private Genero genero;
	
	@Enumerated(value = EnumType.STRING)
	private EnumUsuarioEstado estado;

	//bi-directional many-to-one association to Itr
	@ManyToOne
	@JoinColumn(name="ITR")
	private Itr itr;

	//bi-directional many-to-one association to Localidad
	@ManyToOne
	@JoinColumn(name="LOCALIDAD")
	private Localidad localidad;
	
	//bi-directional many-to-one association to Departamento
	@ManyToOne
	@JoinColumn(name="DEPARTAMENTO")
	private Departamento departamento;

}