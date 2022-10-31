package com.services.users;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Genero;

@Remote
public interface GeneroBeanRemote {
	void create(Genero genero) throws Exception;
	void update(Genero genero) throws Exception;
	void delete(Long id) throws Exception;
	List<Genero> findAll();
}