package com.services.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.dto.UsuarioBusquedaVO;
import com.entities.Analista;
import com.entities.Estudiante;
import com.entities.Evento;
import com.entities.Itr;
import com.entities.Tutor;
import com.entities.Usuario;
import com.enumerators.EnumUsuarioEstado;
import com.enumerators.EnumUsuarioTipo;

@Stateless
public class UsuarioBean implements UsuarioBeanRemote {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("PDT-Server");
	EntityManager em = emf.createEntityManager();

	Session session = em.unwrap(org.hibernate.Session.class);
	SessionFactory factory = session.getSessionFactory();

	public UsuarioBean() {
	}

	@Override
	public void create(Usuario usuario) throws Exception {
		System.out.println("SE INTENTA PERSISTIR");
		try {
			em.persist(usuario);
			em.flush();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void update(Usuario usuario) throws Exception {
		try {
			em.merge(usuario);
			em.flush();
		} catch (Exception e) {
			throw new Exception("No se pudo crear el Usuario");
		}
	}

	@Override
	public void delete(Long id) throws Exception {
		try {
			Usuario usuario = em.find(Usuario.class, id);
			em.remove(usuario);
			em.flush();
		} catch (Exception e) {
			throw new Exception("No se pudo eliminar el Usuario");
		}
	}

	@Override
	public List<Tutor> findAllTutores() {
		TypedQuery<Tutor> query = em.createNamedQuery("Tutor.findAll", Tutor.class);
		System.out.println(query.getResultList());
		return query.getResultList();
	}

	@Override
	public List<Estudiante> findAllEstudiantes() {
		TypedQuery<Estudiante> query = em.createNamedQuery("Estudiante.findAll", Estudiante.class);
		return query.getResultList();
	}

	@Override
	public Tutor findTutor(Long id) {
		return (Tutor) em.find(Tutor.class, id);
	}

	@Override
	public Usuario findUsuario(Long id) {
		return (Usuario) em.find(Usuario.class, id);
	}

	@Override
	public Estudiante findEstudiante(Long id) {
		return (Estudiante) em.find(Estudiante.class, id);
	}

	@Override
	public Usuario login(String user, String password) throws Exception, NoResultException {
		session = factory.openSession();
		session.beginTransaction();
		Usuario us;
		try {
			us = (Usuario) em.createQuery("SELECT u FROM Usuario u WHERE u.usuario = :value")
					.setParameter("value", user).getSingleResult();
		} catch (NoResultException n) {
			throw new Exception("No se encontro el usuario");
		}

		session.getTransaction().commit();
		try {
			if (us.getClave().equals(password)) {

				if (us.getEstado().equals(EnumUsuarioEstado.ACTIVO)) {
					return us;
				} else {
					throw new Exception("El Usuario no se encuentra habilitado");
				}
			} else {
				throw new Exception("Usuario y/o contraseña incorrectos");
			}

		} catch (Exception e) {
			session.close();
			throw new Exception(e.getMessage());
		}

	}

	@Override
	public List<Usuario> findAll() {
		TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findAll", Usuario.class);
		System.out.println(query.getResultList());
		return query.getResultList();
	}

	@Override
	public List<Usuario> search(UsuarioBusquedaVO vo) {
		CriteriaBuilder qb = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> query = qb.createQuery(Usuario.class);
		Class<? extends Usuario> type;
		if (vo.getUsuario() == null) type = Usuario.class;
		else type = vo.getUsuario().getClass();
		var root = query.from(type);
		query.select(root);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (vo.getItr() != null) {
			Join<Usuario, Itr> joinItr = root.join("itr", JoinType.INNER);
			predicates.add(qb.equal(joinItr.get("idItr"), vo.getItr().getIdItr()));
		}
		
		EnumUsuarioEstado estado = vo.getEstado();
		if (estado != null) {
			if(estado.equals(EnumUsuarioEstado.ACTIVO)) {
				predicates.add(qb.equal(root.get("estado"), vo.getEstado()));
			} else if(estado.equals(EnumUsuarioEstado.PENDIENTE)) {
				predicates.add(qb.equal(root.get("estado"), vo.getEstado()));
			} else if(estado.equals(EnumUsuarioEstado.ELIMINADO)) {
				predicates.add(qb.equal(root.get("estado"), vo.getEstado()));
			}
		}
		
		if (vo.getArea() != null) {
			predicates.add(qb.equal(root.get("area"), vo.getArea()));
		}
		
		if (vo.getNombre() != null && !vo.getNombre().trim().isEmpty()) {
			predicates.add(qb.like(root.get("nombre1"), "%" + vo.getNombre() + "%"));
		}
		
		if (vo.getDocumento() != null &&!vo.getDocumento().trim().isEmpty()) {
			predicates.add(qb.like(root.get("documento"), vo.getDocumento() + "%"));
		}
		
		if (vo.getUsuario().getClass().equals(Estudiante.class) && vo.getGeneracion() != 0) {
			predicates.add(qb.equal(root.get("generacion"), vo.getGeneracion()));
		}
		
		
		query.where(qb.and(predicates.toArray(new Predicate[predicates.size()])));
		return em.createQuery(query).getResultList();
	}

}
