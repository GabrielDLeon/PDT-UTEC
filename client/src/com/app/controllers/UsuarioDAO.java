package com.app.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.app.singleton.BeanRemoteManager;
import com.dto.UsuarioBusquedaVO;
import com.entities.Estudiante;
import com.entities.Tutor;
import com.entities.Usuario;
import com.services.users.UsuarioBeanRemote;

public class UsuarioDAO {

	private UsuarioBeanRemote bean;
	
	public UsuarioDAO() {
		try {
			bean = BeanRemoteManager.getBeanUsuario();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public Tutor findTutor(Long id) {
		Tutor tutor = bean.findTutor(id);
		return tutor;
	}
	
	public Usuario findUsuario(Long id) {
		Usuario u = bean.findUsuario(id);
		return u;
	}
	
	public Estudiante findEstudiante(Long id) {
		Estudiante e = bean.findEstudiante(id);
		return e;
	}
	
	public void update(Usuario u) {
		try {
			bean.update(u);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete(Long id) {
		try {
			bean.delete(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Tutor> getAllTutores(){
		List<Tutor> tutores = bean.findAllTutores();
		return tutores;
	}
	
	public List<Estudiante> getAllEstudiantes(){
		List<Estudiante> estudiantes = bean.findAllEstudiantes();
		return estudiantes;
	}
	
	public List<Usuario> getAllUsuarios(){
		List<Usuario> usuarios = bean.findAll();
		return usuarios;
	}
	
	public List<Usuario> search(UsuarioBusquedaVO vo) {
		List<Usuario> result = bean.search(vo);
		return result;
	}
	
	public List<Estudiante> searchEstudiantes(UsuarioBusquedaVO vo) {
		vo.setUsuario(new Estudiante());
		List<Usuario> result = bean.search(vo);
		List<Estudiante> estudiantes = new ArrayList<Estudiante>();
		for (Usuario u : result) {
			estudiantes.add((Estudiante) u);
		}
		return estudiantes;
	}
	
	public List<Tutor> searchTutores(UsuarioBusquedaVO vo) {
		vo.setUsuario(new Tutor());
		List<Usuario> result = bean.search(vo);
		List<Tutor> tutores = new ArrayList<Tutor>();
		for (Usuario u : result) {
			tutores.add((Tutor) u);
		}
		return tutores;
	}
	
	public Usuario login(String user, String password) throws Exception{
		try {
			if (user.trim().isEmpty() || password.trim().isEmpty()) {
				throw new Exception ("Debe de completar todos los campos");
			}
			Usuario u = bean.login(user, password);
			return u;
		} catch (Exception e) {
			throw new Exception (e.getMessage());
		}
	}

	public void create(Usuario u) throws Exception {
		if (checkIsEmpty(u.getNombre1()) || checkIsEmpty(u.getApellido1()) || checkIsEmpty(u.getClave()) || checkIsEmpty(u.getUsuario()) || checkIsEmpty(u.getDocumento())) {
			throw new Exception("No pueden existir campos obligatorios vacíos");
		}
		bean.create(u);
	}
	
	private boolean checkIsEmpty(String s) {
		return s.trim().isEmpty();
	}
	
}
