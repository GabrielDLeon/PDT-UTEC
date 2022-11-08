package com.app.test;

import javax.naming.NamingException;

import com.app.singleton.BeanRemoteManager;
import com.dto.EstudianteVO;
import com.services.users.UsuarioBeanRemote;

public class UsuarioTest {
	
	private static UsuarioBeanRemote bean;
	
	public static void main(String[] args) {
		try {
			bean = BeanRemoteManager.getBeanUsuario();
			create();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void create() throws Exception {
		EstudianteVO e = EstudianteVO.builder()
				.usuario(null)
				.clave(null)
				.documento(null)
				.nombre1(null)
				.nombre2(null)
				.apellido1(null)
				.apellido2(null)
				.fechaNac(null)
				.mail(null)
				.mailUtec(null)
				.telefono(null)
				.genero(null)
				.itr(null)
				.localidad(null)
				.departamento(null)
				.generacion(null)
				.build();
		bean.create(null);
	}
	
}
