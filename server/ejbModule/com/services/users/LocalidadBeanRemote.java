package com.services.users;

import java.util.Set;

import javax.ejb.Remote;

import com.entities.Departamento;
import com.entities.Localidad;

@Remote
public interface LocalidadBeanRemote {
	
	public void create(Localidad l);
	
	public Set<Localidad> read();
	
	public void update(Localidad l);
	
	public void delete(Localidad l);
	
	public Localidad find(Long id);
	
//	public Set<Localidad> readByDepartamento(Long idDepartamento);
	
}
