package com.app.test;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.naming.NamingException;

import com.app.singleton.BeanRemoteManager;
import com.entities.Evento;
import com.entities.Itr;
import com.entities.Tutor;
import com.enumerators.EnumAsistenciaEstado;
import com.enumerators.EnumEventoModalidad;
import com.enumerators.EnumEventoTipo;
import com.services.eventos.AsistenciaBeanRemote;
import com.services.eventos.EventoBeanRemote;
import com.services.users.ItrBeanRemote;

public class EventoTest {

	static EventoBeanRemote beanEvento;
	static ItrBeanRemote beanItr;
	static AsistenciaBeanRemote beanAsistencia;

	public static void main(String[] args) {
		try {
			beanItr = BeanRemoteManager.getBeanItr();
			beanEvento = BeanRemoteManager.getBeanEvento();
			beanAsistencia = BeanRemoteManager.getBeanAsistencia();
			delete(1052L);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static void create() {
		List<Tutor> listaTutores = new LinkedList<Tutor>();
		//Depende de cuantos Tutores tengas en la BD
		listaTutores.add(beanEvento.getTutor(3L));
		//listaTutores.add(beanEvento.getTutor(4L));
		
		LocalDateTime time = LocalDateTime.now();
		Itr itr = beanItr.findById(1L);
		
		Evento evento = Evento.builder()
				.nombre("Exámen Patrones de Diseño")
				.fechaInicio(time)
				.fechaFin(time)
				.modalidad(EnumEventoModalidad.PRESENCIAL)
				.tipo(EnumEventoTipo.EXAMEN)
				.itr(itr)
				.tutores(listaTutores)
				.build();
		try {
			beanEvento.create(evento);
			System.out.println("Evento creado!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void update() {
		try {
			Evento evento = beanEvento.findById(1L);
			evento.setNombre("Jornada Presencial 25/06");
			evento.setTipo(EnumEventoTipo.JORNADA_PRESENCIAL);
			evento.setModalidad(EnumEventoModalidad.PRESENCIAL);
			beanEvento.update(evento);
			System.out.println("Evento actualizado!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void delete(Long id) {
		try {
			beanEvento.delete(id);
			System.out.println("Evento eliminado!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void getEventos() {
		List<Evento> lista = beanEvento.findAll();
		for (Evento evento : lista) {
			System.out.println(evento.toString());
		}
	}
}
