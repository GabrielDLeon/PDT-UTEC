package com.app.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarkLaf;
import com.app.controllers.UsuarioBO;
import com.app.singleton.RobotoFont;
import com.entities.Analista;
import com.entities.Estudiante;
import com.entities.Tutor;
import com.entities.Usuario;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private UsuarioBO uBO = new UsuarioBO();
	
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
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 30, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 5.0, 5.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblTitulo = new JLabel("Inicio de Sesión");
		lblTitulo.setFont(RobotoFont.getTitulo());
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.gridwidth = 2;
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitulo.gridx = 1;
		gbc_lblTitulo.gridy = 1;
		panel.add(lblTitulo, gbc_lblTitulo);

		JLabel lblUsuario = new JLabel("Nombre de Usuario");
		GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
		gbc_lblUsuario.gridwidth = 2;
		gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuario.gridx = 1;
		gbc_lblUsuario.gridy = 3;
		panel.add(lblUsuario, gbc_lblUsuario);

		inputUsuario = new JTextField();
		GridBagConstraints gbc_inputUsuario = new GridBagConstraints();
		gbc_inputUsuario.gridwidth = 2;
		gbc_inputUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_inputUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputUsuario.gridx = 1;
		gbc_inputUsuario.gridy = 4;
		panel.add(inputUsuario, gbc_inputUsuario);
		inputUsuario.setColumns(10);

		JLabel lblClave = new JLabel("Contraseña");
		GridBagConstraints gbc_lblClave = new GridBagConstraints();
		gbc_lblClave.gridwidth = 2;
		gbc_lblClave.insets = new Insets(0, 0, 5, 5);
		gbc_lblClave.gridx = 1;
		gbc_lblClave.gridy = 5;
		panel.add(lblClave, gbc_lblClave);

		inputClave = new JPasswordField();
		GridBagConstraints gbc_inputClave = new GridBagConstraints();
		gbc_inputClave.gridwidth = 2;
		gbc_inputClave.insets = new Insets(0, 0, 5, 5);
		gbc_inputClave.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputClave.gridx = 1;
		gbc_inputClave.gridy = 6;
		panel.add(inputClave, gbc_inputClave);
		
		lblRespuesta = new JLabel(" ");
		lblRespuesta.setForeground(Color.RED);
		GridBagConstraints gbc_lblRespuesta = new GridBagConstraints();
		gbc_lblRespuesta.gridwidth = 2;
		gbc_lblRespuesta.insets = new Insets(0, 0, 5, 5);
		gbc_lblRespuesta.gridx = 1;
		gbc_lblRespuesta.gridy = 7;
		panel.add(lblRespuesta, gbc_lblRespuesta);
		
				JButton btnLogin = new JButton("Iniciar Sesión");
				btnLogin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						login();
					}
				});
				
				GridBagConstraints gbc_btnLogin = new GridBagConstraints();
				gbc_btnLogin.gridwidth = 2;
				gbc_btnLogin.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnLogin.insets = new Insets(0, 0, 5, 5);
				gbc_btnLogin.gridx = 1;
				gbc_btnLogin.gridy = 8;
				panel.add(btnLogin, gbc_btnLogin);
		
		JLabel lblRegistro = new JLabel("Aun no estas registrado?");
		GridBagConstraints gbc_lblRegistro = new GridBagConstraints();
		gbc_lblRegistro.gridwidth = 2;
		gbc_lblRegistro.insets = new Insets(0, 0, 5, 5);
		gbc_lblRegistro.gridx = 1;
		gbc_lblRegistro.gridy = 9;
		panel.add(lblRegistro, gbc_lblRegistro);
		
		JButton btnEstudiante = new JButton("Soy Estudiante");
		btnEstudiante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		GridBagConstraints gbc_btnEstudiante = new GridBagConstraints();
		gbc_btnEstudiante.fill = GridBagConstraints.BOTH;
		gbc_btnEstudiante.insets = new Insets(0, 0, 0, 5);
		gbc_btnEstudiante.gridx = 1;
		gbc_btnEstudiante.gridy = 10;
		panel.add(btnEstudiante, gbc_btnEstudiante);
		
		JButton btnTutor = new JButton("Soy Tutor");
		GridBagConstraints gbc_btnTutor = new GridBagConstraints();
		gbc_btnTutor.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnTutor.insets = new Insets(0, 0, 0, 5);
		gbc_btnTutor.gridx = 2;
		gbc_btnTutor.gridy = 10;
		panel.add(btnTutor, gbc_btnTutor);
	}

	protected void register() {
		// TODO Realizar la implementación del Register()
		lblRespuesta.setText("Debe ingresar todos los campos");
	}

	@SuppressWarnings("deprecation")
	protected void login() {

		try {
			Usuario u = uBO.login(inputUsuario.getText(), inputClave.getText());
			
			if(u.getClass().equals(Analista.class)) {
				JOptionPane.showMessageDialog(null, "Analista");
			} else if (u.getClass().equals(Tutor.class)) {
				JOptionPane.showMessageDialog(null, "Tutor");
			} else if (u.getClass().equals(Estudiante.class)) {
				JOptionPane.showMessageDialog(null, "Estudiante");
			}
			
		} catch (Exception e) {
			lblRespuesta.setText(e.getMessage());
		}
		
	}

}