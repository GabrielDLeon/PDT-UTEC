package com.services.eventos;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Evento;

@Remote
public interface EventoBeanRemote {
	void create(Evento evento) throws Exception;
	void update(Evento evento) throws Exception;
	void delete(Long id) throws Exception;
	Evento findById(Long id);
	List<Evento> findAll();
}