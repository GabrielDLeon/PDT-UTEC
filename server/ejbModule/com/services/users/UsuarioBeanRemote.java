package com.services.users;

import javax.ejb.Remote;

import com.dto.UsuarioVO;
import com.entities.Estudiante;
import com.entities.Tutor;
import com.entities.Usuario;

@Remote
public interface UsuarioBeanRemote {
	void create(Usuario usuario) throws Exception;
	void update(Usuario usuario) throws Exception;
	void delete(Long id) throws Exception;
	Tutor getTutor(Long id);
	Estudiante getEstudiante(Long id);
	Usuario getUsuario(Long id);
}
