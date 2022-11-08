package com.app.controllers;

import javax.naming.NamingException;

import com.app.exceptions.TextFieldException;
import com.app.singleton.BeanRemoteManager;
import com.entities.Evento;
import com.services.eventos.EventoBeanRemote;

public class EventoBO {

	private EventoBeanRemote beanEvento;

	public EventoBO() {
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

	public String insert(Evento evento) throws Exception {	
		//beanEvento.create(evento);
		return "Evento creado correctamente";
	}
}
