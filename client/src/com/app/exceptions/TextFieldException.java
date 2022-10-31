package com.app.exceptions;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.formdev.flatlaf.FlatClientProperties;

public class TextFieldException extends Exception {

	private JTextField campo;
	private JLabel texto;
	private String mensaje;

	public TextFieldException(JTextField campo, JLabel texto) {
		this.campo = campo;
		this.texto = texto;
	}
	
	public void error(String mensaje) {
		campo.putClientProperty(FlatClientProperties.OUTLINE, "error");
		texto.setText(mensaje);
	}
	
	public void solve() {
		campo.putClientProperty(FlatClientProperties.OUTLINE, "");
		texto.setText(" ");
	}

	public JTextField getCampo() {
		return campo;
	}

	public JLabel getTexto() {
		return texto;
	}

	@Override
	public String getMessage() {
		return mensaje;
	}
	
}
