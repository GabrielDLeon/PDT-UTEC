package com.services.eventos;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.entities.Estudiante;
import com.entities.Evento;
import com.entities.Tutor;
import com.enumerators.EnumAsistenciaEstado;

@Stateless
public class EventoBean implements EventoBeanRemote {
	AsistenciaBean asistenciaBean = new AsistenciaBean();
	
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
			session.getTransaction().commit();
			em.flush();			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		session.close();
	}

	@Override
	public void update(Evento evento) throws Exception {
		session = factory.openSession();
		session.beginTransaction();
		try {
			em.merge(evento);
			session.getTransaction().commit();
			em.flush();
		} catch (Exception e) {
			 throw new Exception("No se pudo actualizar el Evento");
		}
		session.close();
	}

	@Override
	public void delete(Long id) throws Exception {
		Evento evento = findById(id);
		
		// empezado, es true cuando el evento ya empezó
		boolean empezado = evento.getFechaInicio().isBefore(LocalDateTime.now());
		
		// participaron, es true cuando el evento ya cuenta con estudiantes el estado TRUE (1) en el campo ASISTENCIA
		boolean participaron = asistenciaBean.findByStatus(id, EnumAsistenciaEstado.ASISTENCIA).size() > 0;
		
		// Verifica que el evento no haya empezado y no tenga estudiantes participando (1)
		if (empezado && participaron) {
			System.out.println("No se pudo eliminar el evento. El evento ya inició y ya cuenta con participación de los estudiantes.");
			throw new Exception("No se pudo eliminar el evento. El evento ya inició y ya cuenta con participación de los estudiantes.");
		}
		
		try {
			em.remove(evento);
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("No se pudo eliminar el evento");
		}
	}

	@Override
	public Evento findById(Long id) {
		return (Evento) em.find(Evento.class, id);
	}

	@Override
	public List<Evento> findAll() {
		TypedQuery<Evento> query = em.createNamedQuery("Evento.findAll", Evento.class);
		return query.getResultList();
	}
	
	@Override
	public List<Evento> findByTutor(Long idTutor) {
		TypedQuery<Evento> query = em.createNamedQuery("Evento.findByTutor", Evento.class);
		query.setParameter("id", idTutor);
		return query.getResultList();
	}
	
	// Esto después se borra, está para las pruebas
	public Tutor getTutor(Long id){
		return (Tutor) em.find(Tutor.class, id);
	}
	
	public Estudiante getEstudiante(Long id){
		return (Estudiante) em.find(Estudiante.class, id);
	}

	

}