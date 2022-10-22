package com.services.users;

import javax.ejb.Remote;

import com.entities.Usuario;

@Remote
public interface UsuarioBeanRemote {
	void create(Usuario usuario) throws Exception;
	void update(Usuario usuario) throws Exception;
	void delete(Long id) throws Exception;
}
