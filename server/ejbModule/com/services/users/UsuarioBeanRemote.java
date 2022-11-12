package com.services.users;

import java.util.List;

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
	
	List<Tutor> findAllTutores();
	List<Estudiante> findAllEstudiantes();

	Tutor findTutor(Long id);
	Estudiante findEstudiante(Long id);
	Usuario findUsuario(Long id);
}
