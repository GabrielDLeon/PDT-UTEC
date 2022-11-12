package com.services.eventos;

import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.entities.Asistencia;
import com.entities.AsistenciaKey;
import com.entities.Estudiante;
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
	public void create(Evento evento, List<Estudiante> convocados) throws Exception {
		// TODO Hay que arreglar esto que no funciona
		session = factory.openSession();
		session.beginTransaction();
		try {
			for (Estudiante estudiante : convocados) {
				System.out.println(evento.getIdEvento());
				System.out.println(estudiante.getUsuario());
				Asistencia a = Asistencia.builder()
						.id(new AsistenciaKey(evento.getIdEvento(), estudiante.getIdUsuario()))
						.estudiante(estudiante)
						.evento(evento)
						.estado(EnumAsistenciaEstado.CONVOCADO)
						.build();
				em.merge(a);
				session.getTransaction().commit();
			}
			em.flush();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		session.close();
	}

	@Override
	public void update(List<Asistencia> asistencias) throws Exception {
		try {
			for (Asistencia asistencia : asistencias) {				
				em.merge(asistencia);
			}
			em.flush();	
		} catch (Exception e) {
			throw new Exception("No se pudo actualizar la Asistencia");
		}
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
