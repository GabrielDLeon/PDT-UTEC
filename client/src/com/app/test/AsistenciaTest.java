package com.app.test;

import java.util.LinkedList;
import java.util.List;

import javax.naming.NamingException;

import com.app.singleton.BeanRemoteManager;
import com.entities.Asistencia;
import com.entities.Estudiante;
import com.entities.Evento;
import com.services.eventos.AsistenciaBeanRemote;
import com.services.eventos.EventoBeanRemote;

public class AsistenciaTest {
	static AsistenciaBeanRemote beanAsistencia;
	static EventoBeanRemote beanEvento;
	
	public static void main(String[] args) {
		try {
			beanAsistencia = BeanRemoteManager.getBeanAsistencia();	
			beanEvento = BeanRemoteManager.getBeanEvento();
			System.out.println("Bean obtenido!");
			getConvocatoria();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static void getConvocatoria() {
		try {
			Evento evento = beanEvento.findById(1L);

			System.out.println("Convocatoria:");
			Estudiante e1 = beanEvento.getEstudiante(1L);
			Estudiante e2 = beanEvento.getEstudiante(2L);
			Estudiante e3 = beanEvento.getEstudiante(3L);
			
			System.out.println("Evento 1");
			List<Estudiante> lista = new LinkedList<Estudiante>(); 
			lista.add(e1);
			lista.add(e2);
			lista.add(e3);
			lista.forEach(System.out::println);
			
			beanAsistencia.create(evento, lista);
			
			System.out.println("Asistencia registrada para el evento 1");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
