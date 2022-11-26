package com.app.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.app.singleton.BeanRemoteManager;
import com.entities.Asistencia;
import com.entities.AsistenciaKey;
import com.entities.Estudiante;
import com.entities.Evento;
import com.enumerators.EnumAsistenciaEstado;
import com.services.eventos.AsistenciaBeanRemote;
import com.services.eventos.EventoBeanRemote;

public class AsistenciaDAO {
	
	private AsistenciaBeanRemote beanAsistencia;
	private EventoBeanRemote beanEvento;

	public AsistenciaDAO() {
		try {
			beanAsistencia = BeanRemoteManager.getBeanAsistencia();
			beanEvento = BeanRemoteManager.getBeanEvento();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public void create(Evento evento, List<Estudiante> estudiantes) throws Exception{
		if (evento == null) throw new Exception("El Evento no puede estar vacío");
		if (estudiantes == null || estudiantes.isEmpty()) throw new Exception("La lista de Estudiantes no puede estar vacía");
		
		List<Asistencia> convocatoria = new ArrayList<Asistencia>();
		for (Estudiante estudiante : estudiantes) {
			Asistencia a = Asistencia.builder()
					.id(new AsistenciaKey(evento.getIdEvento(), estudiante.getIdUsuario()))
					.estudiante(estudiante)
					.evento(evento)
					.estado(EnumAsistenciaEstado.CONVOCADO)
					.build();
			convocatoria.add(a);
		}
		beanAsistencia.create(evento, convocatoria);
	}
	
	public String update(Asistencia asistencia) {
		try {
			beanAsistencia.update(asistencia);
			return "Asistencia actualizada correctamente!";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	public String update(List<Asistencia> asistencias) {
		try {
			for (Asistencia asistencia : asistencias) {
				beanAsistencia.update(asistencia);
			}
			return "Asistencias actualizadas correctamente!";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	public String delete(Long idEvento, Long idEstudiante) {
		try {
			beanAsistencia.delete(idEvento, idEstudiante);
			return "Asistencia eliminada correctamente!";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	public List<Asistencia> findByEvento(Long idEvento){
		List<Asistencia> convocados = beanAsistencia.findByEvento(idEvento);
		return convocados;
	}
}
