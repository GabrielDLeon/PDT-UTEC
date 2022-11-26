package com.dto;

import java.util.Date;

import com.entities.Departamento;
import com.entities.Genero;
import com.entities.Itr;
import com.entities.Localidad;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@ToString
public abstract class UsuarioVO {

	private long idUsuario;

	private String usuario;
	private String clave;

	private String documento;
	private String nombre1;
	private String nombre2;
	private String apellido1;
	private String apellido2;

	private Date fechaNac;

	private String mail;
	private String mailUtec;

	private String telefono;

	private Genero genero;

	private Itr itr;

	private Localidad localidad;

	private Departamento departamento;

}
