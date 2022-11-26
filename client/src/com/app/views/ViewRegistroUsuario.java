package com.app.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.app.controllers.UsuarioDAO;
import com.app.singleton.RobotoFont;
import com.entities.Analista;
import com.entities.Departamento;
import com.entities.Estudiante;
import com.entities.Genero;
import com.entities.Itr;
import com.entities.Localidad;
import com.entities.Tutor;
import com.entities.Usuario;
import com.enumerators.EnumTutorArea;
import com.enumerators.EnumTutorTipo;
import com.enumerators.EnumUsuarioEstado;
import com.formdev.flatlaf.FlatDarkLaf;
import com.services.users.DepartamentoBeanRemote;
import com.services.users.GeneroBeanRemote;
import com.services.users.ItrBeanRemote;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JYearChooser;

public class ViewRegistroUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField inputNombre1;
	private JTextField inputNombre2;
	private JTextField inputApellido1;
	private JTextField inputApellido2;
	private JTextField inputCedula;
	private JTextField inputEmail;
	private JTextField inputUsuario;
	private JTextField inputTelefono;
	private JPasswordField inputClave;

	private JLabel lblApellido2;
	private JLabel lblTelefono;
	private JLabel lblUsuario;
	private JLabel lblClave;
	private JLabel lbItr;
	private JComboBox<Itr> selectItr;
	private JButton btnCrear;

	private List<Itr> itrList;
	private List<Departamento> departamentoList;
	private Set<Localidad> localidadList;
	private List<Genero> generoList;
	private List<Localidad> localidades;
	
	
	private JComboBox selectDepartamento;
	private JLabel lblDepartamento;
	private JComboBox<Localidad> selectLocalidad = new JComboBox<Localidad>();
	private JLabel lblLocalidad;
	private JComboBox selectGenero;
	private JLabel lblGenero;
	private JLabel lblNacimiento;
	private JDateChooser dateChooser;
	private JLabel lblRegistro;
	
	private Usuario userType;
	private JYearChooser yearChooser;
	private JLabel lblGeneracion;
	private JLabel lblArea;
	private JLabel lblTipo;
	private JComboBox selectArea;
	private JComboBox selectTipo;
	
	private UsuarioDAO bo = new UsuarioDAO();

	public static void main(String[] args) {
		FlatDarkLaf.setup();
		Font roboto = RobotoFont.getNormal();
		UIManager.getLookAndFeelDefaults().put("defaultFont", roboto);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuario u = new Analista();
					ViewRegistroUsuario frame = new ViewRegistroUsuario(u);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public List<Itr> getItr() throws NamingException {
		ItrBeanRemote beanItr = (ItrBeanRemote) InitialContext
				.doLookup("ejb:/PDT-Server/ItrBean!com.services.users.ItrBeanRemote");
		return itrList = beanItr.findAll();
	}
	
	public Itr getItrById(Long id) throws NamingException {
		ItrBeanRemote beanItr = (ItrBeanRemote) InitialContext
				.doLookup("ejb:/PDT-Server/ItrBean!com.services.users.ItrBeanRemote");
		return beanItr.findById(id);
	}
	
	public List<Departamento> getDepartamento() throws NamingException {
		DepartamentoBeanRemote beanDepartamento = (DepartamentoBeanRemote) InitialContext
				.doLookup("ejb:/PDT-Server/DepartamentoBean!com.services.users.DepartamentoBeanRemote");
		return departamentoList = beanDepartamento.findAll();
	}
	
	public Departamento getDepartamentoById(Long id) throws NamingException {
		DepartamentoBeanRemote beanDepartamento = (DepartamentoBeanRemote) InitialContext
				.doLookup("ejb:/PDT-Server/DepartamentoBean!com.services.users.DepartamentoBeanRemote");
		return beanDepartamento.findById(id);
	}
	
	public List<Genero> getGenero() throws NamingException {
		GeneroBeanRemote beanGenero = (GeneroBeanRemote) InitialContext
				.doLookup("ejb:/PDT-Server/GeneroBean!com.services.users.GeneroBeanRemote");
		return generoList = beanGenero.findAll();
	}
	
	public ViewRegistroUsuario(Usuario u) throws NamingException {
		
		this.userType = u;
		
		try {
			getItr();
			getDepartamento();
			getGenero();
		} catch (NamingException e) {
			System.out.println("No se pudo cargar la lista de ITR");
		}
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		panel.setLayout(gbl_panel);
		
		lblRegistro = new JLabel("");
		GridBagConstraints gbc_lblRegistro = new GridBagConstraints();
		gbc_lblRegistro.insets = new Insets(0, 0, 5, 5);
		gbc_lblRegistro.gridx = 2;
		gbc_lblRegistro.gridy = 1;
		panel.add(lblRegistro, gbc_lblRegistro);

		JLabel lblNombre1 = new JLabel("Primer Nombre");
		GridBagConstraints gbc_lblNombre1 = new GridBagConstraints();
		gbc_lblNombre1.anchor = GridBagConstraints.EAST;
		gbc_lblNombre1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre1.gridx = 1;
		gbc_lblNombre1.gridy = 3;
		panel.add(lblNombre1, gbc_lblNombre1);

		inputNombre1 = new JTextField();
		GridBagConstraints gbc_inputNombre1 = new GridBagConstraints();
		gbc_inputNombre1.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputNombre1.insets = new Insets(0, 0, 5, 5);
		gbc_inputNombre1.gridx = 2;
		gbc_inputNombre1.gridy = 3;
		panel.add(inputNombre1, gbc_inputNombre1);
		inputNombre1.setColumns(10);

		JLabel lblNombre2 = new JLabel("Segundo Nombre");
		GridBagConstraints gbc_lblNombre2 = new GridBagConstraints();
		gbc_lblNombre2.anchor = GridBagConstraints.EAST;
		gbc_lblNombre2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre2.gridx = 1;
		gbc_lblNombre2.gridy = 4;
		panel.add(lblNombre2, gbc_lblNombre2);

		inputNombre2 = new JTextField();
		GridBagConstraints gbc_inputNombre2 = new GridBagConstraints();
		gbc_inputNombre2.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputNombre2.insets = new Insets(0, 0, 5, 5);
		gbc_inputNombre2.gridx = 2;
		gbc_inputNombre2.gridy = 4;
		panel.add(inputNombre2, gbc_inputNombre2);
		inputNombre2.setColumns(10);

		JLabel lblApellido1 = new JLabel("Primer Apellido");
		GridBagConstraints gbc_lblApellido1 = new GridBagConstraints();
		gbc_lblApellido1.anchor = GridBagConstraints.EAST;
		gbc_lblApellido1.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellido1.gridx = 1;
		gbc_lblApellido1.gridy = 5;
		panel.add(lblApellido1, gbc_lblApellido1);

		inputApellido1 = new JTextField();
		GridBagConstraints gbc_inputApellido1 = new GridBagConstraints();
		gbc_inputApellido1.insets = new Insets(0, 0, 5, 5);
		gbc_inputApellido1.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputApellido1.gridx = 2;
		gbc_inputApellido1.gridy = 5;
		panel.add(inputApellido1, gbc_inputApellido1);
		inputApellido1.setColumns(10);

		lblApellido2 = new JLabel("Segundo Apellido");
		GridBagConstraints gbc_lblApellido2 = new GridBagConstraints();
		gbc_lblApellido2.anchor = GridBagConstraints.EAST;
		gbc_lblApellido2.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellido2.gridx = 1;
		gbc_lblApellido2.gridy = 6;
		panel.add(lblApellido2, gbc_lblApellido2);

		inputApellido2 = new JTextField();
		inputApellido2.setColumns(10);
		GridBagConstraints gbc_inputApellido2 = new GridBagConstraints();
		gbc_inputApellido2.insets = new Insets(0, 0, 5, 5);
		gbc_inputApellido2.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputApellido2.gridx = 2;
		gbc_inputApellido2.gridy = 6;
		panel.add(inputApellido2, gbc_inputApellido2);
		
		lblGenero = new JLabel("Genero");
		GridBagConstraints gbc_lblGenero = new GridBagConstraints();
		gbc_lblGenero.insets = new Insets(0, 0, 5, 5);
		gbc_lblGenero.anchor = GridBagConstraints.EAST;
		gbc_lblGenero.gridx = 1;
		gbc_lblGenero.gridy = 7;
		panel.add(lblGenero, gbc_lblGenero);
		
		ArrayList<Genero> generos = (ArrayList<Genero>) getGenero();
		
		selectGenero = new JComboBox(generos.toArray());
		GridBagConstraints gbc_selectGenero = new GridBagConstraints();
		gbc_selectGenero.insets = new Insets(0, 0, 5, 5);
		gbc_selectGenero.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectGenero.gridx = 2;
		gbc_selectGenero.gridy = 7;
		panel.add(selectGenero, gbc_selectGenero);
		
		lblNacimiento = new JLabel("Fecha de Nacimiento");
		GridBagConstraints gbc_lblNacimiento = new GridBagConstraints();
		gbc_lblNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_lblNacimiento.gridx = 1;
		gbc_lblNacimiento.gridy = 8;
		panel.add(lblNacimiento, gbc_lblNacimiento);
		
		dateChooser = new JDateChooser();
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateChooser.gridx = 2;
		gbc_dateChooser.gridy = 8;
		panel.add(dateChooser, gbc_dateChooser);

		JLabel lblCedula = new JLabel("Cedula");
		GridBagConstraints gbc_lblCedula = new GridBagConstraints();
		gbc_lblCedula.insets = new Insets(0, 0, 5, 5);
		gbc_lblCedula.anchor = GridBagConstraints.EAST;
		gbc_lblCedula.gridx = 1;
		gbc_lblCedula.gridy = 9;
		panel.add(lblCedula, gbc_lblCedula);

		inputCedula = new JTextField();
		GridBagConstraints gbc_inputCedula = new GridBagConstraints();
		gbc_inputCedula.insets = new Insets(0, 0, 5, 5);
		gbc_inputCedula.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputCedula.gridx = 2;
		gbc_inputCedula.gridy = 9;
		panel.add(inputCedula, gbc_inputCedula);
		inputCedula.setColumns(10);

		JLabel lblEmail = new JLabel("Email Personal");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 10;
		panel.add(lblEmail, gbc_lblEmail);

		inputEmail = new JTextField();
		GridBagConstraints gbc_inputEmail = new GridBagConstraints();
		gbc_inputEmail.insets = new Insets(0, 0, 5, 5);
		gbc_inputEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputEmail.gridx = 2;
		gbc_inputEmail.gridy = 10;
		panel.add(inputEmail, gbc_inputEmail);
		inputEmail.setColumns(10);

		lblTelefono = new JLabel("Teléfono");
		GridBagConstraints gbc_lblTelefono = new GridBagConstraints();
		gbc_lblTelefono.anchor = GridBagConstraints.EAST;
		gbc_lblTelefono.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefono.gridx = 1;
		gbc_lblTelefono.gridy = 11;
		panel.add(lblTelefono, gbc_lblTelefono);

		inputTelefono = new JTextField();
		GridBagConstraints gbc_inputTelefono = new GridBagConstraints();
		gbc_inputTelefono.insets = new Insets(0, 0, 5, 5);
		gbc_inputTelefono.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputTelefono.gridx = 2;
		gbc_inputTelefono.gridy = 11;
		panel.add(inputTelefono, gbc_inputTelefono);
		inputTelefono.setColumns(10);

		lblUsuario = new JLabel("Usuario");
		GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
		gbc_lblUsuario.anchor = GridBagConstraints.EAST;
		gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuario.gridx = 1;
		gbc_lblUsuario.gridy = 12;
		panel.add(lblUsuario, gbc_lblUsuario);

		inputUsuario = new JTextField();
		GridBagConstraints gbc_inputUsuario = new GridBagConstraints();
		gbc_inputUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_inputUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputUsuario.gridx = 2;
		gbc_inputUsuario.gridy = 12;
		panel.add(inputUsuario, gbc_inputUsuario);
		inputUsuario.setColumns(10);

		lblClave = new JLabel("Contraseña");
		GridBagConstraints gbc_lblClave = new GridBagConstraints();
		gbc_lblClave.anchor = GridBagConstraints.EAST;
		gbc_lblClave.insets = new Insets(0, 0, 5, 5);
		gbc_lblClave.gridx = 1;
		gbc_lblClave.gridy = 13;
		panel.add(lblClave, gbc_lblClave);

		inputClave = new JPasswordField();
		GridBagConstraints gbc_inputClave = new GridBagConstraints();
		gbc_inputClave.insets = new Insets(0, 0, 5, 5);
		gbc_inputClave.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputClave.gridx = 2;
		gbc_inputClave.gridy = 13;
		panel.add(inputClave, gbc_inputClave);

		lbItr = new JLabel("ITR");
		GridBagConstraints gbc_lbItr = new GridBagConstraints();
		gbc_lbItr.anchor = GridBagConstraints.EAST;
		gbc_lbItr.insets = new Insets(0, 0, 5, 5);
		gbc_lbItr.gridx = 1;
		gbc_lbItr.gridy = 14;
		panel.add(lbItr, gbc_lbItr);

		ArrayList<Itr> itrs = (ArrayList<Itr>) getItr();

		selectItr = new JComboBox(itrs.toArray());
		GridBagConstraints gbc_selectItr = new GridBagConstraints();
		gbc_selectItr.insets = new Insets(0, 0, 5, 5);
		gbc_selectItr.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectItr.gridx = 2;
		gbc_selectItr.gridy = 14;
		panel.add(selectItr, gbc_selectItr);
		
		ArrayList<Departamento> departamentos = (ArrayList<Departamento>) getDepartamento();
		
		lblDepartamento = new JLabel("Departamento");
		GridBagConstraints gbc_lblDepartamento = new GridBagConstraints();
		gbc_lblDepartamento.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartamento.anchor = GridBagConstraints.EAST;
		gbc_lblDepartamento.gridx = 1;
		gbc_lblDepartamento.gridy = 15;
		panel.add(lblDepartamento, gbc_lblDepartamento);
		
		selectDepartamento = new JComboBox(departamentos.toArray());
		selectDepartamento.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				Departamento d = (Departamento) selectDepartamento.getSelectedItem();
				List<Localidad> lista = d.getLocalidades();
				selectLocalidad.setModel(new DefaultComboBoxModel<Localidad>(lista.toArray(new Localidad[0])));
			}
		});
		GridBagConstraints gbc_selectDepartamento = new GridBagConstraints();
		gbc_selectDepartamento.insets = new Insets(0, 0, 5, 5);
		gbc_selectDepartamento.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectDepartamento.gridx = 2;
		gbc_selectDepartamento.gridy = 15;
		panel.add(selectDepartamento, gbc_selectDepartamento);
		
		lblLocalidad = new JLabel("Localidad");
		GridBagConstraints gbc_lblLocalidad = new GridBagConstraints();
		gbc_lblLocalidad.insets = new Insets(0, 0, 5, 5);
		gbc_lblLocalidad.anchor = GridBagConstraints.EAST;
		gbc_lblLocalidad.gridx = 1;
		gbc_lblLocalidad.gridy = 16;
		panel.add(lblLocalidad, gbc_lblLocalidad);
		
		GridBagConstraints gbc_selectLocalidad = new GridBagConstraints();
		gbc_selectLocalidad.insets = new Insets(0, 0, 5, 5);
		gbc_selectLocalidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectLocalidad.gridx = 2;
		gbc_selectLocalidad.gridy = 16;
		panel.add(selectLocalidad, gbc_selectLocalidad);

		btnCrear = new JButton("Registrarse");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		
		if(userType.getClass() == Estudiante.class) {
		lblGeneracion = new JLabel("Generacion");
		GridBagConstraints gbc_lblGeneracion = new GridBagConstraints();
		gbc_lblGeneracion.anchor = GridBagConstraints.EAST;
		gbc_lblGeneracion.insets = new Insets(0, 0, 5, 5);
		gbc_lblGeneracion.gridx = 1;
		gbc_lblGeneracion.gridy = 17;
		panel.add(lblGeneracion, gbc_lblGeneracion);
		
		yearChooser = new JYearChooser();
		GridBagConstraints gbc_yearChooser = new GridBagConstraints();
		gbc_yearChooser.insets = new Insets(0, 0, 5, 5);
		gbc_yearChooser.fill = GridBagConstraints.HORIZONTAL;
		gbc_yearChooser.gridx = 2;
		gbc_yearChooser.gridy = 17;
		panel.add(yearChooser, gbc_yearChooser);}
		
		if(userType.getClass() == Tutor.class) {
		lblArea = new JLabel("Area");
		GridBagConstraints gbc_lblArea = new GridBagConstraints();
		gbc_lblArea.anchor = GridBagConstraints.EAST;
		gbc_lblArea.insets = new Insets(0, 0, 5, 5);
		gbc_lblArea.gridx = 1;
		gbc_lblArea.gridy = 17;
		panel.add(lblArea, gbc_lblArea);
		
		selectArea = new JComboBox(EnumTutorArea.values());
		GridBagConstraints gbc_selectArea = new GridBagConstraints();
		gbc_selectArea.insets = new Insets(0, 0, 5, 5);
		gbc_selectArea.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectArea.gridx = 2;
		gbc_selectArea.gridy = 17;
		panel.add(selectArea, gbc_selectArea);
		
		lblTipo = new JLabel("Tipo");
		GridBagConstraints gbc_lblTipo = new GridBagConstraints();
		gbc_lblTipo.anchor = GridBagConstraints.EAST;
		gbc_lblTipo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipo.gridx = 1;
		gbc_lblTipo.gridy = 18;
		panel.add(lblTipo, gbc_lblTipo);
		
		selectTipo = new JComboBox(EnumTutorTipo.values());
		GridBagConstraints gbc_selectTipo = new GridBagConstraints();
		gbc_selectTipo.insets = new Insets(0, 0, 5, 5);
		gbc_selectTipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectTipo.gridx = 2;
		gbc_selectTipo.gridy = 18;
		panel.add(selectTipo, gbc_selectTipo);}
		
		btnCrear.setToolTipText(
				"Al presionar este botón, se registrara el Usuario en el sistema a partir de los datos ingresados en el formulario.");
		GridBagConstraints gbc_btnCrear = new GridBagConstraints();
		gbc_btnCrear.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCrear.insets = new Insets(0, 0, 0, 5);
		gbc_btnCrear.gridx = 2;
		gbc_btnCrear.gridy = 20;
		panel.add(btnCrear, gbc_btnCrear);

	}

	public LocalDateTime convertToLocalDateTime(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDateTime();
	}
	
	protected void register() {
		
		if(userType.getClass() == Estudiante.class) {
		Usuario e = Estudiante.builder()
				.apellido1(inputApellido1.getText())
				.apellido2(inputApellido2.getText())
				.nombre1(inputNombre1.getText())
				.nombre2(inputNombre2.getText())
				.genero((Genero) selectGenero.getSelectedItem())
				.fechaNac(dateChooser.getDate())
				.documento(inputCedula.getText())
				.mail(inputEmail.getText())
				.telefono(inputTelefono.getText())
				.usuario(inputUsuario.getText())
				.clave(inputClave.getText())
				.itr((Itr) selectItr.getSelectedItem())
				.departamento((Departamento) selectDepartamento.getSelectedItem())
				.localidad((Localidad) selectLocalidad.getSelectedItem())
				.generacion(yearChooser.getValue())
				.estado(EnumUsuarioEstado.PENDIENTE)
				.build();
			try {
				bo.create(e);
				JOptionPane.showMessageDialog(null, "Se ha creado el usuario exitosamente, queda a la espera de ser habilitado por un Analista");
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "No se a podido crear el usuario " + e1.getMessage());
			}
		
		} else if (userType.getClass() == Tutor.class) {	
			@SuppressWarnings("deprecation")
			Usuario t = Tutor.builder()
					.apellido1(inputApellido1.getText())
					.apellido2(inputApellido2.getText())
					.nombre1(inputNombre1.getText())
					.nombre2(inputNombre2.getText())
					.genero((Genero) selectGenero.getSelectedItem())
					.fechaNac(dateChooser.getDate())
					.documento(inputCedula.getText())
					.mail(inputEmail.getText())
					.telefono(inputTelefono.getText())
					.usuario(inputUsuario.getText())
					.clave(inputClave.getText())
					.itr((Itr) selectItr.getSelectedItem())
					.departamento((Departamento) selectDepartamento.getSelectedItem())
					.localidad((Localidad) selectLocalidad.getSelectedItem())
					.area((EnumTutorArea) selectArea.getSelectedItem())
					.tipo((EnumTutorTipo) selectTipo.getSelectedItem())
					.estado(EnumUsuarioEstado.PENDIENTE)
					.build();
				try {
					bo.create(t);
					JOptionPane.showMessageDialog(null, "Se ha creado el usuario exitosamente, queda a la espera de ser habilitado por un Analista");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "No se a podido crear el usuario " + e1.getMessage());
				}

		}
	}

}