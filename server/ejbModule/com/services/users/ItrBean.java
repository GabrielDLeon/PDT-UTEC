package com.services.users;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.entities.Itr;

@Stateless
public class ItrBean implements ItrBeanRemote {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("PDT-Server");
	EntityManager em = emf.createEntityManager();

	Session session = em.unwrap(org.hibernate.Session.class);
	SessionFactory factory = session.getSessionFactory();

	public ItrBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create(Itr itr) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Itr itr) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Long id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Itr findById(Long id) {
		return (Itr) em.find(Itr.class, id);
	}

	@Override
	public List<Itr> findAll() {
		session = factory.openSession();
		session.beginTransaction();
		TypedQuery<Itr> query = em.createNamedQuery("Itr.findAll", Itr.class);
		List<Itr> list = query.getResultList();
		session.getTransaction().commit();
		session.close();
		return list;
	}

}
