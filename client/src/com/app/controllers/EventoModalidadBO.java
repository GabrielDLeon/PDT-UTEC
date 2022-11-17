package com.app.controllers;

import java.util.List;

import javax.naming.NamingException;

import com.app.singleton.BeanRemoteManager;
import com.entities.EventoEstado;
import com.entities.EventoModalidad;
import com.services.eventos.EventoEstadoBeanRemote;
import com.services.eventos.EventoModalidadBeanRemote;

public class EventoModalidadBO {
	
	private EventoModalidadBeanRemote bean;
	
	public EventoModalidadBO() {
		try {
			bean = BeanRemoteManager.getBeanEventoModalidad();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public String validateNombre(String nombre) throws Exception {
		nombre = nombre.trim();
		if(nombre.isEmpty()) throw new Exception("El nombre no puede estar vacío");
		return nombre;
	}
	
	public String create(EventoModalidad modalidad) {
		try {
			modalidad.setNombre(validateNombre(modalidad.getNombre()));
			bean.create(modalidad);
			return "Modalidad de Evento creado exitosamente";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	public String update(EventoModalidad modalidad) {
		try {
			modalidad.setNombre(validateNombre(modalidad.getNombre()));
			bean.update(modalidad);
			return "Modalidad de Evento modificado exitosamente";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	public String delete(Long idModalidad) {
		try {
			bean.delete(idModalidad);
			return "Modalidad de Evento eliminado exitosamente";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	public EventoModalidad findById(Long idEvento) {
		try {
			EventoModalidad modalidad = bean.findById(idEvento);
			return modalidad;
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<EventoModalidad> findAll(){
		List<EventoModalidad> list = bean.findAll();
		return list;
	}
}
