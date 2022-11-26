package com.dto;

import java.io.Serializable;

import com.entities.Itr;
import com.enumerators.EnumUsuarioEstado;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UsuarioBusquedaVO implements Serializable{
	
	private static final long serialVersionUID = -190982431483103996L;
	
	private Itr itr;
	private int generacion;
	private EnumUsuarioEstado estado;
	
	
}
