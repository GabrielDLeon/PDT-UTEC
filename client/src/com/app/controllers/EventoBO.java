package com.app.controllers;

import java.util.List;

import javax.naming.NamingException;

import com.app.exceptions.TextFieldException;
import com.app.singleton.BeanRemoteManager;
import com.entities.Analista;
import com.entities.Evento;
import com.entities.Usuario;
import com.services.eventos.EventoBeanRemote;

public class EventoBO {

	private EventoBeanRemote beanEvento;
	private Usuario usuario;

	public EventoBO(Usuario usuario) {
		this.usuario = usuario;
		try {
			beanEvento = BeanRemoteManager.getBeanEvento();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public void validarNombre(TextFieldException e) throws TextFieldException {
		String value = e.getCampo().getText();
		if (value.isEmpty()) {
			e.error("El campo Nombre no puede estar vacío");
			throw e;
		}
		if (value.length() < 3 || value.length() > 40) {
			e.error("Debe tener entre 3 y 40 carácteres");
			throw e;
		}
		e.solve();
	}

	public void validarLocalizacion(TextFieldException e) throws TextFieldException {
		String value = e.getCampo().getText();
		if (!value.isEmpty()) {
			if (value.length() < 3 || value.length() > 40) {
				e.error("Debe tener entre 3 y 40 carácteres");
				throw e;
			}
		}
		e.solve();
	}

	public Evento find(Long id) {
		Evento evento = beanEvento.findById(id);
		return evento;
	}
	
	public List<Evento> getList() {
		boolean result = usuario.getClass().equals(Analista.class);
		List<Evento> eventos = result
				? beanEvento.findAll()
				: beanEvento.findByTutor(usuario.getIdUsuario());
		return eventos;
	}

	public String create(Evento evento) {
		try {
			beanEvento.create(evento);
			return "Evento creado correctamente";
		} catch (Exception e) {
			return "Error al intentar crear el Evento.\n" + e.getMessage();
		}
		
	}
	
	public String update(Evento evento) throws Exception {
		try {
			beanEvento.update(evento);
			return "Evento modificado correctamente";
		} catch (Exception e) {
			return "Error al intentar modificar el Evento.\n" + e.getMessage();
		}
	}
	
	public String delete(Long id) {
		try {
			beanEvento.delete(id);
			return "Evento eliminado correctamente";
		} catch (Exception e) {
			return "Error al intentar eliminar el Evento.\n" + e.getMessage();
		}
	}
	
}
