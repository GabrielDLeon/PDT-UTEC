package com.services.users;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.entities.Itr;

@Stateless
public class ItrBean implements ItrBeanRemote {

	@PersistenceContext
	EntityManager em;
	
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
	public List<Itr> findAll() {
		TypedQuery<Itr> query = em.createNamedQuery("Itr.findAll", Itr.class);
		List<Itr> list = query.getResultList();
		list.size();
		return list;
	}

}
