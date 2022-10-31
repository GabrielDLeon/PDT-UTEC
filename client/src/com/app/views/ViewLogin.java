package com.app.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarkLaf;
import com.app.singleton.RobotoFont;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ViewLogin extends JFrame {

	private JPanel contentPane;
	private JTextField inputUsuario;
	private JPasswordField inputClave;
	private JLabel lblRespuesta;
	
	public static void main(String[] args) {
		FlatDarkLaf.setup();
		UIManager.getLookAndFeelDefaults().put("defaultFont", RobotoFont.getNormal());
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewLogin frame = new ViewLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ViewLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 30, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblTitulo = new JLabel("Inicio de Sesión");
		lblTitulo.setFont(RobotoFont.getTitulo());
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitulo.gridx = 1;
		gbc_lblTitulo.gridy = 1;
		panel.add(lblTitulo, gbc_lblTitulo);

		JLabel lblUsuario = new JLabel("Nombre de Usuario");
		GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
		gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuario.gridx = 1;
		gbc_lblUsuario.gridy = 3;
		panel.add(lblUsuario, gbc_lblUsuario);

		inputUsuario = new JTextField();
		GridBagConstraints gbc_inputUsuario = new GridBagConstraints();
		gbc_inputUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_inputUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputUsuario.gridx = 1;
		gbc_inputUsuario.gridy = 4;
		panel.add(inputUsuario, gbc_inputUsuario);
		inputUsuario.setColumns(10);

		JLabel lblClave = new JLabel("Contraseña");
		GridBagConstraints gbc_lblClave = new GridBagConstraints();
		gbc_lblClave.insets = new Insets(0, 0, 5, 5);
		gbc_lblClave.gridx = 1;
		gbc_lblClave.gridy = 5;
		panel.add(lblClave, gbc_lblClave);

		inputClave = new JPasswordField();
		GridBagConstraints gbc_inputClave = new GridBagConstraints();
		gbc_inputClave.insets = new Insets(0, 0, 5, 5);
		gbc_inputClave.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputClave.gridx = 1;
		gbc_inputClave.gridy = 6;
		panel.add(inputClave, gbc_inputClave);

		JButton btnLogin = new JButton("Iniciar Sesión");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		
		lblRespuesta = new JLabel(" ");
		lblRespuesta.setForeground(Color.RED);
		GridBagConstraints gbc_lblRespuesta = new GridBagConstraints();
		gbc_lblRespuesta.insets = new Insets(0, 0, 5, 5);
		gbc_lblRespuesta.gridx = 1;
		gbc_lblRespuesta.gridy = 7;
		panel.add(lblRespuesta, gbc_lblRespuesta);
		
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLogin.insets = new Insets(0, 0, 5, 5);
		gbc_btnLogin.gridx = 1;
		gbc_btnLogin.gridy = 8;
		panel.add(btnLogin, gbc_btnLogin);
		
		JButton btnRegistrar = new JButton("Registrarse");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		GridBagConstraints gbc_btnRegistrar = new GridBagConstraints();
		gbc_btnRegistrar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRegistrar.insets = new Insets(0, 0, 5, 5);
		gbc_btnRegistrar.gridx = 1;
		gbc_btnRegistrar.gridy = 9;
		panel.add(btnRegistrar, gbc_btnRegistrar);
	}

	protected void register() {
		// TODO Realizar la implementación del Register()
		lblRespuesta.setText("Debe ingresar todos los campos");
	}

	protected void login() {
		// TODO Realizar la implementación del Login()
		lblRespuesta.setText("Usuario y/o contraseña incorrectos");
	}

}