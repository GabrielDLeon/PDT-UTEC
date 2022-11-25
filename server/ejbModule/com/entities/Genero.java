package com.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
@Table(name="GENEROS")
@NamedQuery(name="Genero.findAll", query="SELECT g FROM Genero g")
public class Genero implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GENEROS_IDGENERO_GENERATOR", sequenceName="SEQ_GENEROS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GENEROS_IDGENERO_GENERATOR")
	@Column(name="ID_GENERO")
	private long idGenero;

	@Column(unique = true, nullable = false)
	private String nombre;

	@ToString.Exclude
	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="genero")
	private List<Usuario> usuarios;
	
	@Override
	public String toString() {
		return nombre;
	}

}