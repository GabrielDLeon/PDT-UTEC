package com.app.controllers;

import java.util.List;

import javax.naming.NamingException;

import com.app.singleton.BeanRemoteManager;
import com.entities.Estudiante;
import com.entities.Tutor;
import com.services.users.UsuarioBeanRemote;

public class UsuarioDAO {

	private UsuarioBeanRemote beanUsuario;
	
	public UsuarioDAO() {
		try {
			beanUsuario = BeanRemoteManager.getBeanUsuario();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public Tutor findTutor(Long id) {
		Tutor tutor = beanUsuario.findTutor(id);
		return tutor;
	}
	
	public List<Tutor> getAllTutores(){
		List<Tutor> tutores = beanUsuario.findAllTutores();
		return tutores;
	}
	
	public List<Estudiante> getAllEstudiantes(){
		List<Estudiante> estudiantes = beanUsuario.findAllEstudiantes();
		return estudiantes;
	}
	
}
