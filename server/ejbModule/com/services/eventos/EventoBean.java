package com.services.eventos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.entities.Evento;

@Stateless
public class EventoBean implements EventoBeanRemote {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("PDT-Server");
	EntityManager em = emf.createEntityManager();
	
	Session session = em.unwrap(org.hibernate.Session.class);
	SessionFactory factory = session.getSessionFactory();
	
    public EventoBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void create(Evento evento) throws Exception {
		session = factory.openSession();
		session.beginTransaction();
		try {
			em.persist(evento);
			em.flush();			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void update(Evento evento) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Evento findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Evento> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}