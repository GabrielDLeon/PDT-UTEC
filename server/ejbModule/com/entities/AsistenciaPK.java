package com.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ASISTENCIAS database table.
 * 
 */
@Embeddable
public class AsistenciaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private long evento;

	@Column(insertable=false, updatable=false)
	private long estudiante;

	public AsistenciaPK() {
	}
	public long getEvento() {
		return this.evento;
	}
	public void setEvento(long evento) {
		this.evento = evento;
	}
	public long getEstudiante() {
		return this.estudiante;
	}
	public void setEstudiante(long estudiante) {
		this.estudiante = estudiante;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AsistenciaPK)) {
			return false;
		}
		AsistenciaPK castOther = (AsistenciaPK)other;
		return 
			(this.evento == castOther.evento)
			&& (this.estudiante == castOther.estudiante);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.evento ^ (this.evento >>> 32)));
		hash = hash * prime + ((int) (this.estudiante ^ (this.estudiante >>> 32)));
		
		return hash;
	}
}