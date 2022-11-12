package com.app.singleton;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.services.eventos.AsistenciaBeanRemote;
import com.services.eventos.EventoBeanRemote;
import com.services.users.DepartamentoBeanRemote;
import com.services.users.ItrBeanRemote;
import com.services.users.UsuarioBeanRemote;

public class BeanRemoteManager {

	private static UsuarioBeanRemote beanUsuario;
	private static ItrBeanRemote beanItr;
	private static DepartamentoBeanRemote beanDepartamento;
	private static EventoBeanRemote beanEvento;
	private static AsistenciaBeanRemote beanAsistencia;

	private BeanRemoteManager() {

	}

	public static UsuarioBeanRemote getBeanUsuario() throws NamingException {
		beanUsuario = (UsuarioBeanRemote) InitialContext
				.doLookup("PDT-Server/UsuarioBean!com.services.users.UsuarioBeanRemote");
		return beanUsuario;
	}

	public static ItrBeanRemote getBeanItr() throws NamingException {
		beanItr = (ItrBeanRemote) InitialContext.doLookup("PDT-Server/ItrBean!com.services.users.ItrBeanRemote");
		return beanItr;
	}

	public static DepartamentoBeanRemote getBeanDepartamento() throws NamingException {
		beanDepartamento = (DepartamentoBeanRemote) InitialContext
				.doLookup("PDT-Server/DepartamentoBean!com.services.users.DepartamentoBeanRemote");
		return beanDepartamento;
	}

	public static EventoBeanRemote getBeanEvento() throws NamingException {
		beanEvento = (EventoBeanRemote) InitialContext
				.doLookup("PDT-Server/EventoBean!com.services.eventos.EventoBeanRemote");
		return beanEvento;
	}

	public static AsistenciaBeanRemote getBeanAsistencia() throws NamingException {
		beanAsistencia = (AsistenciaBeanRemote) InitialContext
				.doLookup("PDT-Server/AsistenciaBean!com.services.eventos.AsistenciaBeanRemote");
		return beanAsistencia;
	}

}
