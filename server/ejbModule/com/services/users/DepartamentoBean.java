package com.services.users;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.entities.Departamento;

@Stateless
public class DepartamentoBean implements DepartamentoBeanRemote {
	
	@PersistenceContext
	EntityManager em;
	
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
		TypedQuery<Departamento> query = em.createNamedQuery("Departamento.findAll", Departamento.class);
		List<Departamento> list = query.getResultList();
		return list;
	}

}
