package com.entities;

import java.io.Serializable;

import javax.persistence.*;

import com.enumerators.EnumTutorArea;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name="USER_TUTORES")
@NamedQuery(name="Tutor.findAll", query="SELECT t FROM Tutor t")
public class Tutor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USER_TUTORES_USUARIO_GENERATOR", sequenceName="SEQ_USER_TUTORES")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_TUTORES_USUARIO_GENERATOR")
	private long usuario;

	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	private EnumTutorArea area;

	@Column(nullable = true)
	private String tipo;

}