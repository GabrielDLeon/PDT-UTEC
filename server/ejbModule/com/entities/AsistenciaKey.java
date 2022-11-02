package com.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The primary key class for the ASISTENCIAS database table.
 */

@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class AsistenciaKey implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private long evento;

	@Column(insertable=false, updatable=false)
	private long estudiante;

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AsistenciaKey)) {
			return false;
		}
		AsistenciaKey castOther = (AsistenciaKey)other;
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