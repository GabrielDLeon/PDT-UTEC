package com.services.eventos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.dto.EventoBusquedaVO;
import com.entities.Estudiante;
import com.entities.Evento;
import com.entities.Tutor;
import com.enumerators.EnumAsistenciaEstado;
import com.enumerators.EnumEventoEstado;
import com.enumerators.EnumEventoModalidad;
import com.enumerators.EnumEventoTipo;

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
	public List<Evento> search(EventoBusquedaVO vo){
		String sql = "SELECT e FROM e ";
		
		List<String> where = new ArrayList<String>();
		if (!vo.getNombre().isEmpty()) where.add("nombre = " + vo.getNombre());
		if (vo.getModalidad() != null) where.add("modalidad = " + vo.getModalidad());
		if (vo.getTipo() != null) where.add("tipo = " + vo.getTipo());
		if (vo.getItr() != null) where.add("itr = " + vo.getItr().getIdItr());
		if (where.size()>0) sql += "WHERE ";
		
		for (String string : where) {
			sql += string + ", ";
		}
		System.out.println(sql);
		
//		TypedQuery<Evento> query = em.createNamedQuery(where, null);
		return null;
	}

	@Override
	public Evento findById(Long id) {
		return (Evento) em.find(Evento.class, id);
	}

	// Si eres Analista, llamas a este método
	@Override
	public List<Evento> findAll() {
		TypedQuery<Evento> query = em.createNamedQuery("Evento.findAll", Evento.class);
		return query.getResultList();
	}
	
	// Si eres Tutor, llamas a este método
	@Override
	public List<Evento> findByTutor(Long idTutor) {
		TypedQuery<Evento> query = em.createNamedQuery("Evento.findByTutor", Evento.class);
		System.out.println(idTutor);
		query.setParameter("id", idTutor);
		return query.getResultList();
	}
	
	@Override
	public List<Evento> findByItr(Long idItr) {
		TypedQuery<Evento> query = em.createNamedQuery("Evento.findByItr", Evento.class);
		query.setParameter("idItr", idItr);
		return query.getResultList();
	}
	
	// TODO Borrar luego de finalizar el desarrollo y las pruebas
	public Tutor getTutor(Long id){
		return (Tutor) em.find(Tutor.class, id);
	}

	// TODO Borrar luego de finalizar el desarrollo y las pruebas
	public Estudiante getEstudiante(Long id){
		return (Estudiante) em.find(Estudiante.class, id);
	}

	

}