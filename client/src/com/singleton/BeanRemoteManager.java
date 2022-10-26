package com.singleton;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.services.eventos.EventoBeanRemote;
import com.services.users.DepartamentoBeanRemote;
import com.services.users.GeneroBeanRemote;
import com.services.users.ItrBeanRemote;
import com.services.users.UsuarioBeanRemote;

public class BeanRemoteManager {

	private static ItrBeanRemote beanItr;
	private static UsuarioBeanRemote beanUsuario;
	private static DepartamentoBeanRemote beanDepartamento;
	private static GeneroBeanRemote beanGenero;
	private static EventoBeanRemote beanEvento;

	private BeanRemoteManager() {

	}

	public static ItrBeanRemote getBeanItr() throws NamingException {
		beanItr = (ItrBeanRemote) InitialContext
				.doLookup("PDT-Server/ItrBean!com.services.users.ItrBeanRemote");
		return beanItr;
	}

	public static UsuarioBeanRemote getBeanUsuario() throws NamingException {
		beanUsuario = (UsuarioBeanRemote) InitialContext
				.doLookup("PDT-Server/UsuarioBean!com.services.users.UsuarioBeanRemote");
		return beanUsuario;
	}

	public static DepartamentoBeanRemote getBeanDepartamento() throws NamingException {
		beanDepartamento = (DepartamentoBeanRemote) InitialContext
				.doLookup("PDT-Server/DepartamentoBean!com.services.users.DepartamentoBeanRemote");
		return beanDepartamento;
	}

	public static GeneroBeanRemote getBeanGenero() throws NamingException {
		beanGenero = (GeneroBeanRemote) InitialContext
				.doLookup("PDT-Server/GeneroBean!com.services.users.GeneroBeanRemote");
		return beanGenero;
	}
	
	public static EventoBeanRemote getBeanEvento() throws NamingException {
		beanEvento = (EventoBeanRemote) InitialContext
				.doLookup("PDT-Server/EventoBean!com.services.eventos.EventoBeanRemote");
		return beanEvento;
	}

}
