package com.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@ToString
public class EstudianteVO extends UsuarioVO {

	public EstudianteVO(UsuarioVOBuilder<?, ?> b) {
		super(b);
	}
	
	private String generacion;

}
