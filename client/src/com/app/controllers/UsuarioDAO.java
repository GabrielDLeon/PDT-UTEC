package com.app.controllers;

import java.util.List;

import javax.naming.NamingException;

import com.app.singleton.BeanRemoteManager;
import com.dto.UsuarioBusquedaVO;
import com.entities.Estudiante;
import com.entities.Tutor;
import com.entities.Usuario;
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
	
	public Usuario findUsuario(Long id) {
		Usuario u = beanUsuario.findUsuario(id);
		return u;
	}
	
	public Estudiante findEstudiante(Long id) {
		Estudiante e = beanUsuario.findEstudiante(id);
		return e;
	}
	
	public void update(Usuario u) {
		try {
			beanUsuario.update(u);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete(Long id) {
		try {
			beanUsuario.delete(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Tutor> getAllTutores(){
		List<Tutor> tutores = beanUsuario.findAllTutores();
		return tutores;
	}
	
	public List<Estudiante> getAllEstudiantes(){
		List<Estudiante> estudiantes = beanUsuario.findAllEstudiantes();
		return estudiantes;
	}
	
	public List<Usuario> getAllUsuarios(){
		List<Usuario> usuarios = beanUsuario.findAll();
		return usuarios;
	}
	
	public List<Usuario> search(UsuarioBusquedaVO vo) {
		List<Usuario> result = beanUsuario.busqueda(vo);
		return result;
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

	public void create(Usuario u) throws Exception {
		if (checkIsEmpty(u.getNombre1()) || checkIsEmpty(u.getApellido1()) || checkIsEmpty(u.getClave()) || checkIsEmpty(u.getUsuario()) || checkIsEmpty(u.getDocumento())) {
			throw new Exception("No pueden existir campos obligatorios vacíos");
		}
		beanUsuario.create(u);
	}
	
	private boolean checkIsEmpty(String s) {
		return s.trim().isEmpty();
	}
	
}
