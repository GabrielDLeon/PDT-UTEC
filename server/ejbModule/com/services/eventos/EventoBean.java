package com.services.eventos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.entities.Evento;
import com.entities.Tutor;

@Stateless
public class EventoBean implements EventoBeanRemote {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("PDT-Server");
	EntityManager em = emf.createEntityManager();
	
	Session session = em.unwrap(org.hibernate.Session.class);
	SessionFactory factory = session.getSessionFactory();
	
    public EventoBean() {
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
		try {
			em.merge(evento);
			em.flush();
		} catch (Exception e) {
			 throw new Exception("No se pudo actualizar el Evento");
		}
	}

	@Override
	public void delete(Long id) throws Exception {
		try {
			Evento evento = findById(id);
			em.remove(evento);
			em.flush();				
		} catch (Exception e) {
			throw new Exception("No se pudo eliminar el evento");
		}
	}

	@Override
	public Evento findById(Long id) {
		return (Evento) em.find(Evento.class, id);
	}

	@Override
	public List<Evento> findAll() {
		Query query = em.createNamedQuery("Evento.findAll", Evento.class);
		return query.getResultList();
	}
	
	// Esto después se borra, está para las pruebas
	public Tutor getTutor(Long id){
		return (Tutor) em.find(Tutor.class, id);
	}

}