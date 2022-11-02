package com.app.test;

import java.util.List;

import javax.naming.NamingException;

import com.app.singleton.BeanRemoteManager;
import com.entities.Asistencia;
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
			Evento evento = beanEvento.findById(52L);
			System.out.println(evento.toString());
			System.out.println("Convocatoria:");
			List<Asistencia> convocatoria = beanAsistencia.getConvocatoria(evento);
			System.out.println(convocatoria);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
