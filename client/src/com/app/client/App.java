package com.app.client;

import javax.naming.NamingException;

import com.entities.Usuario;
import com.services.users.DepartamentoBeanRemote;
import com.services.users.UsuarioBeanRemote;
import com.app.singleton.BeanRemoteManager;

public class App {
	
	public static void main(String[] args) throws NamingException {
		UsuarioBeanRemote beanUsuario = BeanRemoteManager.getBeanUsuario(); 
		try {
			Usuario usuario = Usuario.builder()
					.documento("46879543")
					.nombre1("Giancarlo")
					.nombre2("Giuseppe")
					.apellido1("Alessandro")
					.apellido2("Esposito")
					.build();
			
			beanUsuario.create(usuario);
			System.out.println("Usuario creado");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getDepartamentos() throws NamingException {
		DepartamentoBeanRemote bean = BeanRemoteManager.getBeanDepartamento();
		System.out.println("Bean cargado correctamente!");
		bean.findAll();
		System.out.println("Operación realizada!");
	}
}
