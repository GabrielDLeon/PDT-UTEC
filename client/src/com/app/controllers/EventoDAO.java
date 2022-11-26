package com.app.controllers;

import java.time.LocalDateTime;
import java.util.List;

import javax.naming.NamingException;

import com.app.exceptions.TextFieldException;
import com.app.singleton.BeanRemoteManager;
import com.dto.EventoBusquedaVO;
import com.entities.Analista;
import com.entities.Evento;
import com.entities.Usuario;
import com.services.eventos.EventoBeanRemote;

public class EventoDAO {

	private EventoBeanRemote beanEvento;
	private Usuario usuario;

	public EventoDAO(Usuario usuario) {
		this.usuario = usuario;
		try {
			beanEvento = BeanRemoteManager.getBeanEvento();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public void validarFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws Exception
	{

		// Valida que las fechas no sean valores núlos
		if (fechaInicio == null || fechaFin == null) {
			throw new Exception("La fecha de Inicio y fecha de Fin no pudene estar vacías.");
		}

		// Valida que la fecha de inicio no se encuentre después que la fecha de fin
		if (fechaInicio.isAfter(fechaFin)) {
			throw new Exception("La fecha de Inicio no puede estar después de la fecha Fin.");
		}
		
		// Valida que las fechas no sean iguales
		if (fechaInicio.equals(fechaFin)) {
			throw new Exception("Ambas fechas no pueden coincidir en fecha y hora.");
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
	
	public List<Evento> search(EventoBusquedaVO vo) {
		List<Evento> result = beanEvento.search(vo);
		return result;
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
