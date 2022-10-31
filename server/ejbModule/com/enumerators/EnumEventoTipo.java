package com.enumerators;

public enum EnumEventoTipo {

	JORNADA_PRESENCIAL("Jornada Presencial"),
	PRUEBA_FINAL("Prueba Final"),
	EXAMEN("Exámen"),
	DEFENSA_PROYECTO("Defensa de Proyecto");

	private String nombre;

	EnumEventoTipo(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return this.nombre;
	}
	
}