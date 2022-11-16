package com.services.eventos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.entities.EventoEstado;

@Stateless
public class EventoEstadoBean implements EventoEstadoBeanRemote {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("PDT-Server");
	EntityManager em = emf.createEntityManager();
	
    public EventoEstadoBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void create(EventoEstado estado) throws Exception {
		em.persist(estado);
		em.flush();
	}

	@Override
	public void update(EventoEstado estado) throws Exception {
		em.merge(estado);
		em.flush();
	}

	@Override
	public void delete(Long idEstado) throws Exception {
		EventoEstado estado = em.find(EventoEstado.class, idEstado);
		em.remove(estado);
		em.flush();
	}
	
	@Override
	public EventoEstado findById(Long idEstado) throws Exception {
		EventoEstado estado = em.find(EventoEstado.class, idEstado);
		if (estado == null) throw new Exception("No se encontró el Estado");
		return estado;
	}

	@Override
	public List<EventoEstado> findAll() {
		TypedQuery<EventoEstado> query = em.createNamedQuery("EventoEstado.findAll", EventoEstado.class);
		return query.getResultList();
	}

}
