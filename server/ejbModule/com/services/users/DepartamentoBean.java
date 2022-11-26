package com.services.users;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.entities.Departamento;
import com.entities.Localidad;

@Stateless
public class DepartamentoBean implements DepartamentoBeanRemote {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("PDT-Server");
	EntityManager em = emf.createEntityManager();

	Session session = em.unwrap(org.hibernate.Session.class);
	SessionFactory factory = (SessionFactory) session.getSessionFactory();

	public DepartamentoBean() {
	}

	@Override
	public void create(Departamento departamento) throws Exception {
		try {
			em.persist(departamento);
			em.flush();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void update(Departamento departamento) throws Exception {
		try {
			em.merge(departamento);
			em.flush();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void delete(Long id) throws Exception {
		Departamento departamento = em.find(Departamento.class, id);
		if (departamento == null) {
			throw new Exception("No se encontró el departamento");
		}
		try {
			em.remove(departamento);
			em.flush();
		} catch (Exception e) {
			throw new Exception("Error: " + e);
		}
	}

	@Override
	public List<Departamento> findAll() {
		session = factory.openSession();
		session.beginTransaction();
		TypedQuery<Departamento> query = em.createNamedQuery("Departamento.findAll", Departamento.class);
		List<Departamento> list = query.getResultList();
		System.out.println(list.size());
		session.getTransaction().commit();
		session.close();
		return list;
	}

	@Override
	public Departamento findById(Long id) {
		return (Departamento) em.find(Departamento.class, id);
	}

	@Override
	public List<Localidad> findByDepartamento(Long id) {
		List<Localidad> list = (List<Localidad>) em.createNamedQuery("Localidad.findByDepartamento", Localidad.class)
				.setParameter("id", id).getResultList();
		return list;
	}

}
