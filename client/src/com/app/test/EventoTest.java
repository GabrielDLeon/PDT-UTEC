package com.app.test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.naming.NamingException;

import com.app.singleton.BeanRemoteManager;
import com.entities.Evento;
import com.entities.Itr;
import com.entities.Tutor;
import com.enumerators.EnumEventoModalidad;
import com.enumerators.EnumEventoTipo;
import com.services.eventos.EventoBeanRemote;
import com.services.users.ItrBeanRemote;

public class EventoTest {

	static EventoBeanRemote beanEvento;
	static ItrBeanRemote beanItr;

	public static void main(String[] args) {
		try {
			beanItr = BeanRemoteManager.getBeanItr();
			beanEvento = BeanRemoteManager.getBeanEvento();	
			create();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static void create() {
		List<Tutor> listaTutores = new LinkedList<Tutor>();
		//Depende de cuantos Tutores tengas en la BD
		//listaTutores.add(beanEvento.getTutor(1L));
		//listaTutores.add(beanEvento.getTutor(2L));
		
		Itr itr = beanItr.findById(2L);
		
		Evento evento = Evento.builder()
				.nombre("Exámen de Java (Virtual)")
				.fechaInicio(new Date())
				.fechaFin(new Date())
				.modalidad(EnumEventoModalidad.VIRTUAL)
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

	private static void delete() {
		try {
			beanEvento.delete(1L);
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
