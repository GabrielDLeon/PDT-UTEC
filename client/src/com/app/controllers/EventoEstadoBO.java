package com.app.controllers;

import java.util.List;

import javax.naming.NamingException;

import com.app.singleton.BeanRemoteManager;
import com.entities.EventoEstado;
import com.services.eventos.EventoEstadoBeanRemote;

public class EventoEstadoBO {
	
	private EventoEstadoBeanRemote bean;
	
	public EventoEstadoBO() {
		try {
			bean = BeanRemoteManager.getBeanEventoEstado();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public String validateNombre(String nombre) throws Exception {
		nombre = nombre.trim();
		if(nombre.isEmpty()) throw new Exception("El nombre no puede estar vacío");
		return nombre;
	}

	public String create(EventoEstado estado) {
		try {
			estado.setNombre(validateNombre(estado.getNombre()));
			bean.create(estado);
			return "Estado de Evento creado exitosamente";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	public String update(EventoEstado estado) {
		try {
			estado.setNombre(validateNombre(estado.getNombre()));
			bean.update(estado);
			return "Estado de Evento modificado exitosamente";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	public String delete(Long idEstado) {
		try {
			bean.delete(idEstado);
			return "Estado de Evento eliminado exitosamente";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	public EventoEstado findById(Long idEvento) {
		try {
			EventoEstado estado = bean.findById(idEvento);
			return estado;
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<EventoEstado> findByStatus(boolean estado){
		List<EventoEstado> list = bean.findAllByStatus(estado);
		return list;
	}
	
	public List<EventoEstado> findAll(){
		List<EventoEstado> list = bean.findAll();
		return list;
	}
}
