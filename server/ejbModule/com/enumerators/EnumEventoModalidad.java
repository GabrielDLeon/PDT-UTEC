package com.enumerators;

public enum EnumEventoModalidad {
	
	VIRTUAL("Modalidad Virtual"),
	PRESENCIAL("Modalidad Presencial"),
	SEMIPRESENCIAL("Modalidad Híbrida (Semipresencial)");
	
	private String nombre;

	EnumEventoModalidad(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}
}
