package com.services.users;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.entities.Usuario;

@Stateless
public class UsuarioBean implements UsuarioBeanRemote {
	
	@PersistenceContext
	private EntityManager em;

	public UsuarioBean() {
	}

	public void create(Usuario usuario) throws Exception {
		try {
			em.persist(usuario);
			em.flush();			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void update(Usuario usuario) throws Exception {
		try {
			em.merge(usuario);
			em.flush();	
		} catch (Exception e) {
			throw new Exception("No se pudo crear el Usuario");
		}
	}
	
	public void delete(Long id) throws Exception {
		try {
			Usuario usuario = em.find(Usuario.class, id);
			em.remove(usuario);
			em.flush();
		} catch (Exception e) {
			throw new Exception("No se pudo eliminar el Usuario");
		}
	}
}
