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
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Departamento departamento) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Departamento departamento) throws Exception {
		// TODO Auto-generated method stub

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

}
