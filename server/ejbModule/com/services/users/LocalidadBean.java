package com.services.users;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.entities.Localidad;

@Stateless
public class LocalidadBean implements LocalidadBeanRemote {

	public LocalidadBean() {
	}

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("PDT-Server");

	EntityManager em = emf.createEntityManager();

	Session session = em.unwrap(org.hibernate.Session.class);

	SessionFactory factory = (SessionFactory) session.getSessionFactory();

	@Override
	public void create(Localidad l) {
		session = factory.openSession();
		session.beginTransaction();
		em.persist(l);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<Localidad> read() {
		session = factory.openSession();
		session.beginTransaction();
		Set<Localidad> localidades = new HashSet<Localidad>(em.createNamedQuery("Localidad.findAll").getResultList());
		session.getTransaction().commit();
		session.close();
		return localidades;
	}

	@Override
	public void update(Localidad l) {
		session = factory.openSession();
		session.beginTransaction();
		em.merge(l);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void delete(Localidad l) {
		session = factory.openSession();
		session.beginTransaction();
		l = em.find(Localidad.class, l.getIdLocalidad());
		em.remove(l);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Localidad find(Long id) {
		session = factory.openSession();
		session.beginTransaction();
		Localidad l = new Localidad();
		Query query = em.createQuery("SELECT l FROM Localidad l WHERE l.id = :id");
		query.setParameter("id", id);
		l = (Localidad) query.getSingleResult();
		session.getTransaction().commit();
		session.close();
		return l;
	}

//	@Override
//	public Set<Localidad> readByDepartamento(Long id) {
//		session = factory.openSession();
//		session.beginTransaction();
//			Set<Localidad> localidades = new HashSet<Localidad>(em.createNamedQuery("Localidad.fidByDepartamento", Localidad.class)
//					.setParameter("id", id)
//					.getResultList());
//		session.getTransaction().commit();
//		session.close();
//		return localidades;
//	}

}
