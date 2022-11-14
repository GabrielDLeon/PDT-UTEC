package com.dto;

import java.io.Serializable;

import com.entities.Itr;
import com.entities.Tutor;
import com.enumerators.EnumEventoEstado;
import com.enumerators.EnumEventoModalidad;
import com.enumerators.EnumEventoTipo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class EventoBusquedaVO implements Serializable {
	
	private static final long serialVersionUID = 2463961227341807754L;
	
	private String nombre;
	private EnumEventoEstado estado;
	private EnumEventoModalidad modalidad;
	private EnumEventoTipo tipo;
	private Itr itr;
	private Tutor tutor;
	
}
