package com.services.users;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Itr;

@Remote
public interface ItrBeanRemote {
	void create(Itr itr) throws Exception;
	void update(Itr itr) throws Exception;
	void delete(Long id) throws Exception;
	List<Itr> findAll();
}
