package com.singleton;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.services.users.DepartamentoBeanRemote;
import com.services.users.ItrBeanRemote;
import com.services.users.UsuarioBeanRemote;

public class BeanRemoteManager {

	private static ItrBeanRemote beanItr;
	private static UsuarioBeanRemote beanUsuario;
	private static DepartamentoBeanRemote beanDepartamento;

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

}
