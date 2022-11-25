package com.services.eventos;

import java.util.List;

import javax.ejb.Remote;

import com.entities.EventoModalidad;

@Remote
public interface EventoModalidadBeanRemote {
	
	void create(EventoModalidad modalidad) throws Exception;
	void update(EventoModalidad modalidad) throws Exception;
	void delete(Long idModalidad) throws Exception;
	EventoModalidad findById(Long idModalidad) throws Exception;
	List<EventoModalidad> findAll();
	List<EventoModalidad> findAllByStatus(boolean estado);
	
}