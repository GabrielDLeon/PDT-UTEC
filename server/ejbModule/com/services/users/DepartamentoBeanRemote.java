package com.services.users;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Departamento;
import com.entities.Localidad;

@Remote
public interface DepartamentoBeanRemote {
	void create(Departamento departamento) throws Exception;
	void update(Departamento departamento) throws Exception;
	void delete(Long id) throws Exception;
	List<Departamento> findAll();
	Departamento findById(Long id);
	List<Localidad> findByDepartamento(Long id);
}
