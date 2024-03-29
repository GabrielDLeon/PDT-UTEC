package com.entities;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@NamedQuery(name="Itr.findAll", query="SELECT i FROM Itr i")
public class Itr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ITR_IDITR_GENERATOR", sequenceName="SEQ_ITR")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ITR_IDITR_GENERATOR")
	@Column(name="ID_ITR")
	private long idItr;

	private String nombre;

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne()
	@JoinColumn(name="DEPARTAMENTO")
	private Departamento departamento;
	
	@ToString.Exclude
	@OneToMany(mappedBy="itr")
	private List<Usuario> usuarios;

	@Override
	public String toString() {
		return nombre;
	}

}