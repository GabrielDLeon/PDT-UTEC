package com.services.eventos;

import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import com.entities.EventoEstado;

@Remote
public interface EventoEstadoBeanRemote {
	
	void create(EventoEstado estado) throws Exception;
	void update(EventoEstado estado) throws Exception;
	void delete(Long idEvento) throws Exception;
	EventoEstado findById(Long idEstado) throws Exception;
	List<EventoEstado> findAll();
	
}