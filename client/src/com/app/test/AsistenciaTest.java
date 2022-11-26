package com.app.test;

import java.util.List;

import javax.naming.NamingException;

import java.util.ArrayList;
import java.util.LinkedList;

import com.app.controllers.AsistenciaDAO;
import com.app.singleton.BeanRemoteManager;
import com.entities.Estudiante;
import com.entities.Evento;
import com.services.eventos.AsistenciaBeanRemote;
import com.services.eventos.EventoBeanRemote;
public class AsistenciaTest {

	static AsistenciaBeanRemote beanAsistencia;
	static EventoBeanRemote beanEvento;
	static AsistenciaDAO bo = new AsistenciaDAO();
	
	public static void main(String[] args) {
		try {
			beanAsistencia = BeanRemoteManager.getBeanAsistencia();	
			beanEvento = BeanRemoteManager.getBeanEvento();
			System.out.println("Bean obtenido!");
			workspace();
			System.out.println("Workspace Ejecutado!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void workspace() throws Exception {
//		create();
//		update();
		delete();
	}
	
	private static void create() throws Exception {
		Evento evento = beanEvento.findById(1L);
		List<Estudiante> convocados = new ArrayList<Estudiante>();
		convocados.add(BeanRemoteManager.getBeanUsuario().findEstudiante(1L));
		convocados.add(BeanRemoteManager.getBeanUsuario().findEstudiante(2L));
		convocados.add(BeanRemoteManager.getBeanUsuario().findEstudiante(3L));
		bo.create(evento, convocados);
		System.out.println("Asistencias registradas");
	}
	
	private static void update() {
		List<Asistencia> result = bo.findByEvento(1L);
		Asistencia item = result.get(0);
		item.setCalificacion(3.56);
		String mensaje = bo.update(item);
		System.out.println(mensaje);
		
		result.get(1).setCalificacion(4.98);
		result.get(2).setCalificacion(2.22);
		mensaje = bo.update(result);
		System.out.println(mensaje);
	}
	
	private static void delete() {
		String mensaje = bo.delete(1L, 2L);
		System.out.println(mensaje);
	}
	
}
