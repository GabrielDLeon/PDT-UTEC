package com.views;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.Itr;
import com.formdev.flatlaf.FlatLightLaf;
import com.services.users.ItrBeanRemote;
import com.singleton.BeanRemoteManager;
import com.singleton.RobotoFont;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.naming.NamingException;
import javax.swing.JButton;

public class ViewRegistroUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField inputNombre1;
	private JTextField inputNombre2;
	private JTextField inputApellido1;
	private JTextField inputApellido2;
	private JTextField inputCedula;
	private JTextField inputEmail;
	private JTextField inputEmailInstitucional;
	private JTextField textField;
	private JPasswordField inputClave;

	private JLabel lblApellido2;
	private JLabel lblTelefono;
	private JLabel lblEmailInstitucional;
	private JLabel lblClave;
	private JLabel lbItr;
	private JComboBox selectItr;
	private JButton btnCrear;
	
	private List<Itr> itrList;

	public static void main(String[] args) {
		FlatLightLaf.setup();
		Font roboto = RobotoFont.getRobotoFont();
		UIManager.getLookAndFeelDefaults().put("defaultFont", roboto);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewRegistroUsuario frame = new ViewRegistroUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void getItr() throws NamingException {
		ItrBeanRemote beanItr = BeanRemoteManager.getBeanItr(); 
		itrList = beanItr.findAll();
		itrList.size();
	}
	
	public ViewRegistroUsuario() {
		try {
			getItr();
		} catch (NamingException e) {
			System.out.println("No se pudo cargar la lista de ITR");
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblNombre1 = new JLabel("Primer Nombre");
		GridBagConstraints gbc_lblNombre1 = new GridBagConstraints();
		gbc_lblNombre1.anchor = GridBagConstraints.EAST;
		gbc_lblNombre1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre1.gridx = 1;
		gbc_lblNombre1.gridy = 1;
		panel.add(lblNombre1, gbc_lblNombre1);

		inputNombre1 = new JTextField();
		GridBagConstraints gbc_inputNombre1 = new GridBagConstraints();
		gbc_inputNombre1.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputNombre1.insets = new Insets(0, 0, 5, 5);
		gbc_inputNombre1.gridx = 2;
		gbc_inputNombre1.gridy = 1;
		panel.add(inputNombre1, gbc_inputNombre1);
		inputNombre1.setColumns(10);

		JLabel lblNombre2 = new JLabel("Segundo Nombre");
		GridBagConstraints gbc_lblNombre2 = new GridBagConstraints();
		gbc_lblNombre2.anchor = GridBagConstraints.EAST;
		gbc_lblNombre2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre2.gridx = 1;
		gbc_lblNombre2.gridy = 2;
		panel.add(lblNombre2, gbc_lblNombre2);

		inputNombre2 = new JTextField();
		GridBagConstraints gbc_inputNombre2 = new GridBagConstraints();
		gbc_inputNombre2.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputNombre2.insets = new Insets(0, 0, 5, 5);
		gbc_inputNombre2.gridx = 2;
		gbc_inputNombre2.gridy = 2;
		panel.add(inputNombre2, gbc_inputNombre2);
		inputNombre2.setColumns(10);

		JLabel lblApellido1 = new JLabel("Primer Apellido");
		GridBagConstraints gbc_lblApellido1 = new GridBagConstraints();
		gbc_lblApellido1.anchor = GridBagConstraints.EAST;
		gbc_lblApellido1.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellido1.gridx = 1;
		gbc_lblApellido1.gridy = 3;
		panel.add(lblApellido1, gbc_lblApellido1);

		inputApellido1 = new JTextField();
		GridBagConstraints gbc_inputApellido1 = new GridBagConstraints();
		gbc_inputApellido1.insets = new Insets(0, 0, 5, 5);
		gbc_inputApellido1.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputApellido1.gridx = 2;
		gbc_inputApellido1.gridy = 3;
		panel.add(inputApellido1, gbc_inputApellido1);
		inputApellido1.setColumns(10);

		lblApellido2 = new JLabel("Segundo Apellido");
		GridBagConstraints gbc_lblApellido2 = new GridBagConstraints();
		gbc_lblApellido2.anchor = GridBagConstraints.EAST;
		gbc_lblApellido2.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellido2.gridx = 1;
		gbc_lblApellido2.gridy = 4;
		panel.add(lblApellido2, gbc_lblApellido2);

		inputApellido2 = new JTextField();
		inputApellido2.setColumns(10);
		GridBagConstraints gbc_inputApellido2 = new GridBagConstraints();
		gbc_inputApellido2.insets = new Insets(0, 0, 5, 5);
		gbc_inputApellido2.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputApellido2.gridx = 2;
		gbc_inputApellido2.gridy = 4;
		panel.add(inputApellido2, gbc_inputApellido2);

		JLabel lblCedula = new JLabel("Cedula");
		GridBagConstraints gbc_lblCedula = new GridBagConstraints();
		gbc_lblCedula.insets = new Insets(0, 0, 5, 5);
		gbc_lblCedula.anchor = GridBagConstraints.EAST;
		gbc_lblCedula.gridx = 1;
		gbc_lblCedula.gridy = 5;
		panel.add(lblCedula, gbc_lblCedula);

		inputCedula = new JTextField();
		GridBagConstraints gbc_inputCedula = new GridBagConstraints();
		gbc_inputCedula.insets = new Insets(0, 0, 5, 5);
		gbc_inputCedula.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputCedula.gridx = 2;
		gbc_inputCedula.gridy = 5;
		panel.add(inputCedula, gbc_inputCedula);
		inputCedula.setColumns(10);

		JLabel lblEmail = new JLabel("Email Personal");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 6;
		panel.add(lblEmail, gbc_lblEmail);

		inputEmail = new JTextField();
		GridBagConstraints gbc_inputEmail = new GridBagConstraints();
		gbc_inputEmail.insets = new Insets(0, 0, 5, 5);
		gbc_inputEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputEmail.gridx = 2;
		gbc_inputEmail.gridy = 6;
		panel.add(inputEmail, gbc_inputEmail);
		inputEmail.setColumns(10);

		lblTelefono = new JLabel("Teléfono");
		GridBagConstraints gbc_lblTelefono = new GridBagConstraints();
		gbc_lblTelefono.anchor = GridBagConstraints.EAST;
		gbc_lblTelefono.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefono.gridx = 1;
		gbc_lblTelefono.gridy = 7;
		panel.add(lblTelefono, gbc_lblTelefono);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 7;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);

		lblEmailInstitucional = new JLabel("Email Institucional");
		GridBagConstraints gbc_lblEmailInstitucional = new GridBagConstraints();
		gbc_lblEmailInstitucional.anchor = GridBagConstraints.EAST;
		gbc_lblEmailInstitucional.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmailInstitucional.gridx = 1;
		gbc_lblEmailInstitucional.gridy = 8;
		panel.add(lblEmailInstitucional, gbc_lblEmailInstitucional);

		inputEmailInstitucional = new JTextField();
		GridBagConstraints gbc_inputEmailInstitucional = new GridBagConstraints();
		gbc_inputEmailInstitucional.insets = new Insets(0, 0, 5, 5);
		gbc_inputEmailInstitucional.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputEmailInstitucional.gridx = 2;
		gbc_inputEmailInstitucional.gridy = 8;
		panel.add(inputEmailInstitucional, gbc_inputEmailInstitucional);
		inputEmailInstitucional.setColumns(10);

		lblClave = new JLabel("Contraseña");
		GridBagConstraints gbc_lblClave = new GridBagConstraints();
		gbc_lblClave.anchor = GridBagConstraints.EAST;
		gbc_lblClave.insets = new Insets(0, 0, 5, 5);
		gbc_lblClave.gridx = 1;
		gbc_lblClave.gridy = 9;
		panel.add(lblClave, gbc_lblClave);

		inputClave = new JPasswordField();
		GridBagConstraints gbc_inputClave = new GridBagConstraints();
		gbc_inputClave.insets = new Insets(0, 0, 5, 5);
		gbc_inputClave.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputClave.gridx = 2;
		gbc_inputClave.gridy = 9;
		panel.add(inputClave, gbc_inputClave);

		lbItr = new JLabel("ITR");
		GridBagConstraints gbc_lbItr = new GridBagConstraints();
		gbc_lbItr.anchor = GridBagConstraints.EAST;
		gbc_lbItr.insets = new Insets(0, 0, 5, 5);
		gbc_lbItr.gridx = 1;
		gbc_lbItr.gridy = 10;
		panel.add(lbItr, gbc_lbItr);
		
		selectItr = new JComboBox();
		GridBagConstraints gbc_selectItr = new GridBagConstraints();
		gbc_selectItr.insets = new Insets(0, 0, 5, 5);
		gbc_selectItr.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectItr.gridx = 2;
		gbc_selectItr.gridy = 10;
		panel.add(selectItr, gbc_selectItr);
		//selectItr.addItem();
		
		btnCrear = new JButton("Crear Usuario");
		btnCrear.setToolTipText(
				"Al presionar este botón, se registrara el Usuario en el sistema a partir de los datos ingresados en el formulario.");
		GridBagConstraints gbc_btnCrear = new GridBagConstraints();
		gbc_btnCrear.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCrear.gridwidth = 2;
		gbc_btnCrear.insets = new Insets(0, 0, 0, 5);
		gbc_btnCrear.gridx = 1;
		gbc_btnCrear.gridy = 11;
		panel.add(btnCrear, gbc_btnCrear);

	}
}
