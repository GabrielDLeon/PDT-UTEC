package com.services.users;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.entities.Usuario;

@Stateless
public class UsuarioBean implements UsuarioBeanRemote {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("PDT-Server");
	EntityManager em = emf.createEntityManager();
	
	Session session = em.unwrap(org.hibernate.Session.class);
	SessionFactory factory = session.getSessionFactory();

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
