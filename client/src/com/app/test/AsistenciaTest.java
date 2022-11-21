package com.app.test;

import java.util.List;
import java.util.LinkedList;

import javax.naming.NamingException;

import com.app.controllers.AsistenciaBO;
import com.app.singleton.BeanRemoteManager;
import com.entities.Estudiante;
import com.entities.Evento;
import com.services.eventos.AsistenciaBeanRemote;
import com.services.eventos.EventoBeanRemote;
public class AsistenciaTest {

	static AsistenciaBeanRemote beanAsistencia;
	static EventoBeanRemote beanEvento;
	static AsistenciaBO bo = new AsistenciaBO();
	
	public static void main(String[] args) {
		try {
			beanAsistencia = BeanRemoteManager.getBeanAsistencia();	
			beanEvento = BeanRemoteManager.getBeanEvento();
			System.out.println("Bean obtenido!");
			workspace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static void workspace() {
		String mensaje = bo.delete(952L, 3L);
		System.out.println(mensaje);
	}
	
	/*
	 * private static void workspace() {
		try {
			Estudiante usuario = BeanRemoteManager.getBeanUsuario().findEstudiante(2L);
			System.out.println(usuario.getAsistencias());
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	 * */
	
	private static void getConvocatoria() {
		try {
			Evento evento = beanEvento.findById(952L);

			Estudiante e1 = beanEvento.getEstudiante(1L);
			Estudiante e2 = beanEvento.getEstudiante(2L);
			Estudiante e3 = beanEvento.getEstudiante(3L);
			
			System.out.println("Evento 1");
			List<Estudiante> lista = new LinkedList<Estudiante>();
			lista.add(e1);
			lista.add(e2);
			lista.add(e3);
			System.out.println(lista);
			
			try {
				bo.create(evento, lista);
				System.out.println("Asistencias registradas");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
