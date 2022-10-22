package com.client;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.entities.Usuario;
import com.services.users.UsuarioBeanRemote;

public class App {
	
	public static void main(String[] args) throws NamingException {
		
		UsuarioBeanRemote beanUsuario = (UsuarioBeanRemote) InitialContext.doLookup("PDT-Server/UsuarioBean!com.services.users.UsuarioBeanRemote");
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

}
