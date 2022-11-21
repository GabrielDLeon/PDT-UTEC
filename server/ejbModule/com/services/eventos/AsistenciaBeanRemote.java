package com.services.eventos;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Asistencia;
import com.entities.Estudiante;
import com.entities.Evento;
import com.enumerators.EnumAsistenciaEstado;

@Remote
public interface AsistenciaBeanRemote {
	
//	void create(Evento evento, List<Estudiante> convocados) throws Exception;
//	void create(Evento evento) throws Exception;
//	void update(List<Asistencia> asistencias) throws Exception;
	
	
	void create(Evento evento, List<Asistencia> convocados) throws Exception;
	void update(Asistencia asistencia) throws Exception;
	void delete(Long idEvento, Long idEstudiante) throws Exception;

	void findById(Long idEvento, Long idEstudiante);
	List<Asistencia> findByEvento(Long idEvento);
	List<Asistencia> findByStatus(Long idEvento, EnumAsistenciaEstado status);
	void clear(Evento evento);
	
}
