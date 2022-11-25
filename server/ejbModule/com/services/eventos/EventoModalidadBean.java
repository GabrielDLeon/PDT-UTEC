package com.services.eventos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.entities.EventoModalidad;

@Stateless
public class EventoModalidadBean implements EventoModalidadBeanRemote {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("PDT-Server");
	EntityManager em = emf.createEntityManager();
	
    public EventoModalidadBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void create(EventoModalidad modalidad) throws Exception {
		em.persist(modalidad);
		em.flush();
	}

	@Override
	public void update(EventoModalidad modalidad) throws Exception {
		em.merge(modalidad);
		em.flush();
	}

	@Override
	public void delete(Long idModalidad) throws Exception {
		EventoModalidad modalidad = em.find(EventoModalidad.class, idModalidad);
		modalidad.setActivo(false);
		em.merge(modalidad);
		em.flush();
	}
	
	@Override
	public EventoModalidad findById(Long idModalidad) throws Exception {
		EventoModalidad estado = em.find(EventoModalidad.class, idModalidad);
		if (estado == null) throw new Exception("No se encontró la Modalidad");
		return estado;
	}

	@Override
	public List<EventoModalidad> findAll() {
		TypedQuery<EventoModalidad> query = em.createNamedQuery("EventoModalidad.findAll", EventoModalidad.class);
		return query.getResultList();
	}
	
	@Override
	public List<EventoModalidad> findAllByStatus(boolean estado) {
		TypedQuery<EventoModalidad> query = em.createNamedQuery("EventoModalidad.findAllByStatus", EventoModalidad.class);
		query.setParameter("status", estado);
		return query.getResultList();
	}

}
