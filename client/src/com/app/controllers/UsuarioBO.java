package com.app.controllers;

import java.util.List;

import javax.naming.NamingException;

import com.app.singleton.BeanRemoteManager;
import com.entities.Estudiante;
import com.entities.Tutor;
import com.entities.Usuario;
import com.services.users.UsuarioBeanRemote;

public class UsuarioBO {

	private UsuarioBeanRemote beanUsuario;
	
	public UsuarioBO() {
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
	
	public Usuario login(String user, String password) throws Exception{
		try {
			if (user.trim().isEmpty() || password.trim().isEmpty()) {
				throw new Exception ("Debe de completar todos los campos");
			}
			Usuario u = beanUsuario.login(user, password);
			return u;
		} catch (Exception e) {
			throw new Exception (e.getMessage());
		}
	}

	public void create(Usuario u) {
		// TODO Auto-generated method stub
		try {
			beanUsuario.create(u);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
