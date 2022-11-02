package com.services.eventos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.entities.Asistencia;
import com.entities.Estudiante;
import com.entities.Evento;

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
	public void create(Evento evento, List<Estudiante> convocados) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Evento evento, List<Estudiante> convocados) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Asistencia> getConvocatoria(Evento evento) throws Exception {
		Query query = em.createNamedQuery("Asistencia.findAll", Asistencia.class);
		return query.getResultList();
	}

	@Override
	public void clear(Evento evento) {
		// TODO Auto-generated method stub
		
	}

}
