package com.services.users;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Departamento;

@Remote
public interface DepartamentoBeanRemote {
	void create(Departamento departamento) throws Exception;
	void update(Departamento departamento) throws Exception;
	void delete(Departamento departamento) throws Exception;
	List<Departamento> findAll();
}
