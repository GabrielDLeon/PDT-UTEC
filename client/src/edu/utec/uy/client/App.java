package edu.utec.uy.client;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import edu.utec.uy.session.PruebaRemote;

public class App {

	public static void main(String[] args) throws NamingException {
		PruebaRemote bean = (PruebaRemote) InitialContext.doLookup("PDT-server/Prueba!edu.utec.uy.session.PruebaRemote");
		String saludo = bean.saludar("buenas");
		
		System.out.println(saludo);
		System.out.println("Excelente!");
		
	}

}
