package com.dto;

import java.io.Serializable;

import com.entities.Itr;
import com.entities.Usuario;
import com.enumerators.EnumTutorArea;
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
public class UsuarioBusquedaVO implements Serializable {

	private static final long serialVersionUID = -190982431483103996L;

	private String nombre;
	private String documento;
	private Itr itr;
	private int generacion;
	private EnumUsuarioEstado estado;
	private EnumTutorArea area;
	private Usuario usuario;

	@Override
	public String toString() {
		return "UsuarioBusquedaVO [nombre=" + nombre + ", documento=" + documento + ", itr=" + itr + ", generacion="
				+ generacion + ", estado=" + estado + ", area=" + area + ", usuario=" + usuario + "]";
	}

}
