package com.enumerators;

public enum EnumEventoEstado {
	EXACTA("Fecha Exacta"),
	DESDE("Desde esta Fecha"),
	HASTA("Hasta esta Fecha");
	
	private String nombre;

	EnumEventoEstado(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return this.nombre;
	}
}
