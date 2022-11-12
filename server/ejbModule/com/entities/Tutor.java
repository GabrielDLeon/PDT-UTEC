package com.entities;

import java.io.Serializable;

import javax.persistence.*;

import com.enumerators.EnumTutorArea;

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
@ToString
@SuperBuilder
@Entity
@Table(name="USER_TUTORES")
@PrimaryKeyJoinColumn(name = "USUARIO")
@NamedQuery(name="Tutor.findAll", query="SELECT t FROM Tutor t")
public class Tutor extends Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	private String tipo;

	@Enumerated(EnumType.STRING)
	private EnumTutorArea area;


}