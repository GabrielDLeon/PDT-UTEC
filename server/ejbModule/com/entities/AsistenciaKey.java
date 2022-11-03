package com.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The primary key class for the ASISTENCIAS database table.
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class AsistenciaKey implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "EVENTO")
	private long idEvento;

	@Column(name = "ESTUDIANTE")
	private long idEstudiante;

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AsistenciaKey)) {
			return false;
		}
		AsistenciaKey castOther = (AsistenciaKey)other;
		return 
			(this.idEvento == castOther.idEvento)
			&& (this.idEstudiante == castOther.idEstudiante);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.idEvento ^ (this.idEvento >>> 32)));
		hash = hash * prime + ((int) (this.idEstudiante ^ (this.idEstudiante >>> 32)));
		
		return hash;
	}
}