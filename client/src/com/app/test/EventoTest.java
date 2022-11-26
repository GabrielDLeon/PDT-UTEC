package com.app.test;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.naming.NamingException;
import javax.swing.JOptionPane;

import com.app.controllers.EventoDAO;
import com.app.controllers.EventoEstadoDAO;
import com.app.singleton.BeanRemoteManager;
import com.entities.Analista;
import com.entities.Asistencia;
import com.entities.Evento;
import com.entities.EventoEstado;
import com.entities.Itr;
import com.entities.Tutor;
import com.enumerators.EnumAsistenciaEstado;
import com.enumerators.EnumEventoModalidad;
import com.enumerators.EnumEventoTipo;
import com.services.eventos.AsistenciaBeanRemote;
import com.services.eventos.EventoBeanRemote;
import com.services.eventos.EventoEstadoBeanRemote;
import com.services.users.ItrBeanRemote;
import com.services.users.UsuarioBeanRemote;

public class EventoTest {

	static EventoBeanRemote beanEvento;
	static ItrBeanRemote beanItr;
	static AsistenciaBeanRemote beanAsistencia;
	static EventoEstadoBeanRemote beanEventoEstado;
	static UsuarioBeanRemote beanUsuario;

	public static void main(String[] args) {
		try {
			beanItr = BeanRemoteManager.getBeanItr();
			beanEvento = BeanRemoteManager.getBeanEvento();
			beanEventoEstado = BeanRemoteManager.getBeanEventoEstado();
			beanAsistencia = BeanRemoteManager.getBeanAsistencia();
			beanUsuario = BeanRemoteManager.getBeanUsuario();
			workspace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static void workspace() {
		EventoDAO bo = new EventoDAO(new Analista());
		LocalDateTime date = LocalDateTime.now();
		bo.create(Evento.builder()
				.nombre("Prueba XD")
				.tipo(EnumEventoTipo.DEFENSA_PROYECTO)
				.fechaInicio(date)
				.build());
	}
}