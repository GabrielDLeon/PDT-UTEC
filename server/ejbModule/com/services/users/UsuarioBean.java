package com.services.users;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.entities.Estudiante;
import com.entities.Tutor;
import com.entities.Usuario;
import com.enumerators.EnumUsuarioEstado;

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
		System.out.println(query.getResultList());
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
			throw new Exception (e.getMessage());
		}

	}

	@Override
	public List<Usuario> findAll() {
		TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findAll", Usuario.class);
		System.out.println(query.getResultList());
		return query.getResultList();
	}

//	private Usuario buildGeneric(UsuarioVO vo) {
//		Usuario user = null;
//		user.setUsuario(null);
//		user.setClave(null);
//		user.setUsuario(vo.getUsuario());
//		user.setClave(vo.getClave());
//		user.setDocumento(vo.getDocumento());
//		user.setNombre1(vo.getNombre1());
//		user.setNombre2(vo.getNombre2());
//		user.setApellido1(vo.getApellido1());
//		user.setApellido2(vo.getApellido2());
//		user.setFechaNac(vo.getFechaNac());
//		user.setMail(vo.getMail());
//		user.setMailUtec(vo.getMailUtec());
//		user.setTelefono(vo.getTelefono());
//		user.setGenero(vo.getGenero());
//		user.setItr(vo.getItr());
//		user.setLocalidad(vo.getLocalidad());
//		user.setDepartamento(vo.getDepartamento());
//		return user;
//	}
//	
//	private Usuario buildEstudiante(EstudianteVO vo) {
//		Usuario estudiante = buildGeneric(vo);
//		estudiante = Estudiante.builder()
//				.generacion(vo.getGeneracion())
//				.build();
//		return estudiante;
//	}
//	
//	private Usuario buildTutor(TutorVO vo) {
//		Usuario tutor = buildGeneric(vo);
//		tutor = Tutor.builder()
//				.area(vo.getArea())
//				.tipo(vo.getTipo())
//				.build();
//		return tutor;
//	}
}
