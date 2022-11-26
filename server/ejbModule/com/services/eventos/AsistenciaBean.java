package com.services.eventos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.entities.Asistencia;
import com.entities.AsistenciaKey;
import com.entities.Evento;
import com.enumerators.EnumAsistenciaEstado;

@Stateless
public class AsistenciaBean implements AsistenciaBeanRemote {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("PDT-Server");
	EntityManager em = emf.createEntityManager();
	
	Session session = em.unwrap(org.hibernate.Session.class);
	SessionFactory factory = session.getSessionFactory();
	
    public AsistenciaBean() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void create(Evento evento, List<Asistencia> convocados) throws Exception {
    	session = factory.openSession();
		session.beginTransaction();
		for (Asistencia asistencia : convocados) {
			em.merge(asistencia);
		}
		session.getTransaction().commit();
		session.close();
    }
    
    @Override
    public void update(Asistencia asistencia) throws Exception {
    	session = factory.openSession();
		session.beginTransaction();
		em.merge(asistencia);
		session.getTransaction().commit();
		session.close();
    }
    
    @Override
    public void delete(Long idEvento, Long idEstudiante) throws Exception {
    	session = factory.openSession();
    	session.beginTransaction();
    	
    	AsistenciaKey key = new AsistenciaKey(idEvento, idEstudiante);
    	Asistencia asistencia = em.find(Asistencia.class, key);
    	if (asistencia == null) throw new Exception("No se encontró el registro de Asistencia");

    	Evento evento = em.find(Evento.class, idEvento);
    	evento.removeAsitencia(asistencia);
    	em.remove(asistencia);
    	
    	em.flush();
    	session.getTransaction().commit();
		session.close();
    }
    
    @Override
    public void findById(Long idEvento, Long idEstudiante) {
    	TypedQuery<Asistencia> query = em.createNamedQuery("Asistencia.find", Asistencia.class);
    	query.setParameter("idEvento", query);
    	query.setParameter("idEstudiante", query);
    }

    @Override
	public List<Asistencia> findByEstudiante(Long idEstudiante) {
		TypedQuery<Asistencia> query = em.createNamedQuery("Asistencia.findByEstudiante", Asistencia.class);
		query.setParameter("id", idEstudiante);
		return query.getResultList();
	}
    
	@Override
	public List<Asistencia> findByEvento(Long idEvento) {
		TypedQuery<Asistencia> query = em.createNamedQuery("Asistencia.findByEvento", Asistencia.class);
		query.setParameter("id", idEvento);
		return query.getResultList();
	}

	@Override
	public List<Asistencia> findByStatus(Long idEvento, EnumAsistenciaEstado status) {
		TypedQuery<Asistencia> query = em.createNamedQuery("Asistencia.findByStatus", Asistencia.class);
		query.setParameter("id", idEvento);
		query.setParameter("status", status);
		return query.getResultList();
	}
	
	@Override
	public void clear(Evento evento) {
		// TODO Auto-generated method stub
		
	}

	

}
