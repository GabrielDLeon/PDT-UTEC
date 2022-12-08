package com.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.enumerators.EnumTutorArea;
import com.enumerators.EnumTutorTipo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "USER_TUTORES")
@PrimaryKeyJoinColumn(name = "USUARIO")
@NamedQuery(name = "Tutor.findAll", query = "SELECT t FROM Tutor t")
public class Tutor extends Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	private EnumTutorTipo tipo;

	@Enumerated(EnumType.STRING)
	private EnumTutorArea area;

	@Override
	public String toString() {
		return getDocumento() + " - " + getNombre1() + " " + getApellido1();
	}
	
}