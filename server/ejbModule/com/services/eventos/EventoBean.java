package com.services.eventos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.dto.EventoBusquedaVO;
import com.entities.Estudiante;
import com.entities.Evento;
import com.entities.EventoEstado;
import com.entities.EventoModalidad;
import com.entities.Itr;
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
			throw new Exception("No se pudo actualizar el Evento "+e.getMessage());
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
		var qb = em.getCriteriaBuilder();
		var query = qb.createQuery(Evento.class);
		var root = query.from(Evento.class);
		query.select(root);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		if (!vo.getNombre().trim().isEmpty())
	    	predicates.add(qb.like(root.get("nombre"), vo.getNombre()+"%"));
	    
		if (vo.getModalidad() != null)
	    	predicates.add(qb.equal(root.get("modalidad"), vo.getModalidad()));
	    
		if (vo.getTipo() != null)
			predicates.add(qb.equal(root.get("tipo"), vo.getTipo()));
		
		/*
		EnumEventoEstado estado = vo.getEstado();
		if (estado != null) {
			Path<LocalDateTime> dateStartPath = root.get("fechaInicio");
			Path<LocalDateTime> dateFinishPath = root.get("fechaFin");
			LocalDateTime now = LocalDateTime.now();
			if (estado.equals(EnumEventoEstado.FUTURO)){
				predicates.add(qb.greaterThan(dateStartPath, now));
			} else if (estado.equals(EnumEventoEstado.CORRIENTE)) {
				predicates.add(qb.lessThan(dateStartPath, now));
				predicates.add(qb.greaterThan(dateFinishPath, now));
			} else if (estado.equals(EnumEventoEstado.FINALIZADO)) {
				predicates.add(qb.lessThan(dateFinishPath, now));
			}
		}
		*/
		
		if (vo.getEstado() != null) {
			Join<Evento, EventoEstado> joinEstado = root.join("estado", JoinType.INNER);
			predicates.add(qb.equal(joinEstado.get("idEstado"), vo.getEstado().getIdEstado()));
		}
		
		if (vo.getModalidad() != null) {
			Join<Evento, EventoModalidad> joinModalidad = root.join("modalidad", JoinType.INNER);
			predicates.add(qb.equal(joinModalidad.get("idModalidad"), vo.getModalidad().getIdModalidad()));
		}
		
		if (vo.getItr() != null) {
			Join<Evento, Itr> joinItr = root.join("itr", JoinType.INNER);
			predicates.add(qb.equal(joinItr.get("idItr"), vo.getItr().getIdItr()));
		}
		
		if (vo.getTutor() != null) {
			Join<Evento, Tutor> joinTutor = root.join("tutores", JoinType.INNER);
			predicates.add(qb.equal(joinTutor.get("idUsuario"), vo.getTutor().getIdUsuario()));
		}
		
		query.where(qb.and(predicates.toArray(new Predicate[predicates.size()])));
		return em.createQuery(query).getResultList();
	}

	@Override
	public Evento findById(Long id) {
		return (Evento) em.find(Evento.class, id);
	}

	@Override
	public List<Evento> findAll() {
		// Si eres Analista, llamas a este método
		TypedQuery<Evento> query = em.createNamedQuery("Evento.findAll", Evento.class);
		return query.getResultList();
	}
	
	@Override
	public List<Evento> findByTutor(Long idTutor) {
		// Si eres Tutor, llamas a este método
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