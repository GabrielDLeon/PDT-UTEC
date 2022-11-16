package com.services.eventos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.entities.EventoEstado;
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
	public void delete(Long idEstado) throws Exception {
		EventoEstado estado = em.find(EventoEstado.class, idEstado);
		em.remove(estado);
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

}
