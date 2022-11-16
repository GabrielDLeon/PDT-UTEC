package com.app.controllers;

import java.util.List;

import javax.naming.NamingException;

import com.app.singleton.BeanRemoteManager;
import com.entities.Asistencia;
import com.entities.Evento;
import com.entities.Usuario;
import com.services.eventos.AsistenciaBeanRemote;
import com.services.eventos.EventoBeanRemote;

public class AsistenciaBO {
	
	private AsistenciaBeanRemote beanAsistencia;
	private EventoBeanRemote beanEvento;

	public AsistenciaBO() {
		try {
			beanAsistencia = BeanRemoteManager.getBeanAsistencia();
			beanEvento = BeanRemoteManager.getBeanEvento();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
//	public String update(List<Asistencia> asistencias) {
//		try {
//			bean.update(asistencias);
//			return "Asistencias actualizadas correctamente!";
//		} catch (Exception e) {
//			return e.getMessage();
//		}
//	}
	
	public List<Asistencia> findByEvento(Long idEvento){
		List<Asistencia> convocados = beanAsistencia.findByEvento(idEvento);
		return convocados;
	}
	
	public String update(Evento evento) {
		try {
			beanEvento.update(evento);
			return "Evento modificado correctamente";
		} catch (Exception e) {
			return "Error al intentar modificar el Evento.\n" + e.getMessage();
		}
	}
}
