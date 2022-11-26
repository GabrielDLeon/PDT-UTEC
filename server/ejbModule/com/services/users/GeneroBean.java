package com.services.users;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.entities.Genero;

@Stateless
public class GeneroBean implements GeneroBeanRemote {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("PDT-Server");
	EntityManager em = emf.createEntityManager();

	Session session = em.unwrap(org.hibernate.Session.class);
	SessionFactory factory = session.getSessionFactory();

	public GeneroBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create(Genero genero) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Genero genero) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Long id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Genero> findAll() {
		session = factory.openSession();
		session.beginTransaction();

		TypedQuery<Genero> query = em.createNamedQuery("Genero.findAll", Genero.class);
		List<Genero> list = query.getResultList();

		System.out.println(list.size());

		session.getTransaction().commit();
		session.close();

		return list;
	}

}