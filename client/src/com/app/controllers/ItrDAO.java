package com.app.controllers;

import java.util.List;

import javax.naming.NamingException;

import com.app.singleton.BeanRemoteManager;
import com.entities.Itr;
import com.services.users.ItrBeanRemote;

public class ItrDAO {
	
	private ItrBeanRemote beanItr;
	
	public ItrDAO() {
		try {
			beanItr = BeanRemoteManager.getBeanItr();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public void create(Itr i) {
		try {
			beanItr.create(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(Itr i) {
		try {
			beanItr.update(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Long id) {
		try {
			beanItr.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Itr> getAll(){
		List<Itr> itrs = beanItr.findAll();
		return itrs;
	}

}
