package com.app.controllers;

import java.util.List;

import javax.naming.NamingException;

import com.app.singleton.BeanRemoteManager;
import com.entities.Asistencia;
import com.services.eventos.AsistenciaBeanRemote;

public class AsistenciaBO {
	
	AsistenciaBeanRemote bean;

	public AsistenciaBO() {
		try {
			bean = BeanRemoteManager.getBeanAsistencia();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public String update(List<Asistencia> asistencias) {
		try {
			bean.update(asistencias);
			return "Asistencias actualizadas correctamente!";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
}
