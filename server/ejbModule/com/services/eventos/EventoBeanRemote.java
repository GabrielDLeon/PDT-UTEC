package com.services.eventos;

import java.util.List;

import javax.ejb.Remote;

import com.dto.EventoBusquedaVO;
import com.entities.Estudiante;
import com.entities.Evento;
import com.entities.EventoEstado;
import com.entities.Tutor;
import com.enumerators.EnumEventoEstado;
import com.enumerators.EnumEventoModalidad;
import com.enumerators.EnumEventoTipo;

@Remote
public interface EventoBeanRemote {
	void create(Evento evento) throws Exception;
	void update(Evento evento) throws Exception;
	void delete(Long id) throws Exception;
	Evento findById(Long id);
	List<Evento> findAll();
	List<Evento> findByTutor(Long idTutor);
	List<Evento> findByItr(Long idItr);
	
	// Esto después se borra
	Tutor getTutor(Long id);
	Estudiante getEstudiante(Long id);
	List<Evento> search(EventoBusquedaVO vo);
	
}