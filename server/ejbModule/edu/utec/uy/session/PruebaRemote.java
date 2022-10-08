package edu.utec.uy.session;

import javax.ejb.Remote;

@Remote
public interface PruebaRemote {
	String saludar(String nombre);
}
