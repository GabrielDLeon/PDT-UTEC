package edu.utec.uy.session;

import javax.ejb.Stateful;

@Stateful
public class Prueba implements PruebaRemote {
    public Prueba() {}

	@Override
	public String saludar(String nombre) {
		return "Hola "+nombre+"!";
	}
    
}
