package com.enumerators;

public enum EnumTutorArea {

	LTI("Licenciatura en Tecnología de la Información"), LOGISTICA("Logistica"), BIOMEDICINA("Ingeniería Biomédica"),
	AGROAMBIENTE("Ingeniería Agroambiental"), MUSICA("Ingeniería en Jazz y música recreativa"),
	TECNOLOGO_INFORMATICA("Tecnólogo en Informática"), MECATRONICA("Mecatrónica");

	private String nombre;

	EnumTutorArea(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return this.nombre;
	}

}
