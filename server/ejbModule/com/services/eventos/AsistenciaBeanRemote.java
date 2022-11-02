package com.services.eventos;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Asistencia;
import com.entities.Estudiante;
import com.entities.Evento;

@Remote
public interface AsistenciaBeanRemote {
	
	void create(Evento evento, List<Estudiante> convocados) throws Exception;
	void update(Evento evento, List<Estudiante> convocados) throws Exception;
	List<Asistencia> getConvocatoria(Evento evento) throws Exception;
	void clear(Evento evento);
	
}
