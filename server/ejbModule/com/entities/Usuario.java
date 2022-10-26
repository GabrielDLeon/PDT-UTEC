package com.entities;

import java.io.Serializable;
import javax.persistence.*;

import javax.persistence.ManyToOne;
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

	private String apellido1;

	private String apellido2;

	private String clave;

	private String documento;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_NAC")
	private Date fechaNac;

	private String mail;

	@Column(name="MAIL_UTEC")
	private String mailUtec;

	private String nombre1;

	private String nombre2;

	private String telefono;

	private String usuario;

	//bi-directional many-to-one association to Genero
	@ManyToOne
	@JoinColumn(name="GENERO")
	private Genero genero;

	//bi-directional many-to-one association to Itr
	@ManyToOne
	@JoinColumn(name="ITR")
	private Itr itr;

	//bi-directional many-to-one association to Localidad
	@ManyToOne
	@JoinColumn(name="LOCALIDAD")
	private Localidad localidad;

}