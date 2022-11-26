package com.dto;

import com.enumerators.EnumTutorArea;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@ToString
public class TutorVO extends UsuarioVO {

	public TutorVO(UsuarioVOBuilder<?, ?> b) {
		super(b);
	}

	private EnumTutorArea area;

	private String tipo;

}
