package com.enumerators;

public enum EnumAsistenciaEstado {

	CONVOCADO("Convocado"),
	ASISTENCIA("Asistencia"),
	MEDIA_ASISTENCIA("Media Asistencia"),
	AUSENCIA("Ausencia"),
	AUSENCIA_JUSTIFICADA("Ausencia Justificada");

	private String nombre;

	EnumAsistenciaEstado(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return this.nombre;
	}

}
