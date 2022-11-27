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
	private JTextField inputMail;
	private JTextField inputUsuario;
	private JTextField inputTelefono;
	private JPasswordField inputClave;

	private JLabel lblApellido2;
	private JLabel lblTelefono;
	private JLabel lblUsuario;
	private JLabel lblClave;
	private JLabel lbItr;
	private JButton btnCrear;

	private List<Itr> itrList;
	private List<Departamento> departamentoList;
	private Set<Localidad> localidadList;
	private List<Genero> generoList;
	private List<Localidad> localidades;
	
	private JComboBox<Itr> selectItr = new JComboBox<Itr>();
	private JComboBox<Departamento> selectDepartamento = new JComboBox<Departamento>();
	private JComboBox<Genero> selectGenero = new JComboBox<Genero>();
	private JComboBox<EnumTutorArea> selectArea = new JComboBox<EnumTutorArea>();
	private JComboBox<EnumTutorTipo> selectTipo = new JComboBox<EnumTutorTipo>();
	private JComboBox<Localidad> selectLocalidad = new JComboBox<Localidad>();
	
	private JLabel lblDepartamento;
	private JLabel lblLocalidad;
	private JLabel lblGenero;
	private JLabel lblNacimiento;
	private JDateChooser dateChooser;
	
	private Usuario userType;
	private JYearChooser yearChooser;
	private JLabel lblGeneracion;
	private JLabel lblArea;
	private JLabel lblTipo;
	
	private UsuarioDAO bo = new UsuarioDAO();
	private JLabel lblMailInstitucional;
	private JTextField inputMailInstitucional;
	private JLabel lblTitulo;
	private JButton btnClean;

	public static void main(String[] args) {
		FlatDarkLaf.setup();
		Font roboto = RobotoFont.getNormal();
		UIManager.getLookAndFeelDefaults().put("defaultFont", roboto);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuario u = new Tutor();
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
		setLocationRelativeTo(null);
		this.userType = u;
		setup();
		setBounds(100, 100, 640, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 15, 223, 222, 15, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 15, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		panel.setLayout(gbl_panel);
		
		lblTitulo = new JLabel("Registro de Usuario");
		lblTitulo.setFont(RobotoFont.getTitulo());
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.gridwidth = 2;
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitulo.gridx = 1;
		gbc_lblTitulo.gridy = 1;
		panel.add(lblTitulo, gbc_lblTitulo);
				
		JLabel lblCedula = new JLabel("Documento (*)");
		GridBagConstraints gbc_lblCedula = new GridBagConstraints();
		gbc_lblCedula.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCedula.gridwidth = 2;
		gbc_lblCedula.insets = new Insets(0, 0, 5, 5);
		gbc_lblCedula.gridx = 1;
		gbc_lblCedula.gridy = 2;
		panel.add(lblCedula, gbc_lblCedula);

		inputCedula = new JTextField();
		GridBagConstraints gbc_inputCedula = new GridBagConstraints();
		gbc_inputCedula.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputCedula.gridwidth = 2;
		gbc_inputCedula.insets = new Insets(0, 0, 5, 5);
		gbc_inputCedula.gridx = 1;
		gbc_inputCedula.gridy = 3;
		panel.add(inputCedula, gbc_inputCedula);
		inputCedula.setColumns(10);
		
		JLabel lblNombre1 = new JLabel("Primer Nombre (*)");
		GridBagConstraints gbc_lblNombre1 = new GridBagConstraints();
		gbc_lblNombre1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNombre1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre1.gridx = 1;
		gbc_lblNombre1.gridy = 4;
		panel.add(lblNombre1, gbc_lblNombre1);
		
		JLabel lblNombre2 = new JLabel("Segundo Nombre");
		GridBagConstraints gbc_lblNombre2 = new GridBagConstraints();
		gbc_lblNombre2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNombre2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre2.gridx = 2;
		gbc_lblNombre2.gridy = 4;
		panel.add(lblNombre2, gbc_lblNombre2);
		
		inputNombre1 = new JTextField();
		GridBagConstraints gbc_inputNombre1 = new GridBagConstraints();
		gbc_inputNombre1.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputNombre1.insets = new Insets(0, 0, 5, 5);
		gbc_inputNombre1.gridx = 1;
		gbc_inputNombre1.gridy = 5;
		panel.add(inputNombre1, gbc_inputNombre1);
		inputNombre1.setColumns(10);

		inputNombre2 = new JTextField();
		GridBagConstraints gbc_inputNombre2 = new GridBagConstraints();
		gbc_inputNombre2.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputNombre2.insets = new Insets(0, 0, 5, 5);
		gbc_inputNombre2.gridx = 2;
		gbc_inputNombre2.gridy = 5;
		panel.add(inputNombre2, gbc_inputNombre2);
		inputNombre2.setColumns(10);

		JLabel lblApellido1 = new JLabel("Primer Apellido (*)");
		GridBagConstraints gbc_lblApellido1 = new GridBagConstraints();
		gbc_lblApellido1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblApellido1.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellido1.gridx = 1;
		gbc_lblApellido1.gridy = 6;
		panel.add(lblApellido1, gbc_lblApellido1);
		
		lblApellido2 = new JLabel("Segundo Apellido");
		GridBagConstraints gbc_lblApellido2 = new GridBagConstraints();
		gbc_lblApellido2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblApellido2.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellido2.gridx = 2;
		gbc_lblApellido2.gridy = 6;
		panel.add(lblApellido2, gbc_lblApellido2);
		
		inputApellido1 = new JTextField();
		GridBagConstraints gbc_inputApellido1 = new GridBagConstraints();
		gbc_inputApellido1.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputApellido1.insets = new Insets(0, 0, 5, 5);
		gbc_inputApellido1.gridx = 1;
		gbc_inputApellido1.gridy = 7;
		panel.add(inputApellido1, gbc_inputApellido1);
		inputApellido1.setColumns(10);

		inputApellido2 = new JTextField();
		inputApellido2.setColumns(10);
		GridBagConstraints gbc_inputApellido2 = new GridBagConstraints();
		gbc_inputApellido2.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputApellido2.insets = new Insets(0, 0, 5, 5);
		gbc_inputApellido2.gridx = 2;
		gbc_inputApellido2.gridy = 7;
		panel.add(inputApellido2, gbc_inputApellido2);
		
		lblGenero = new JLabel("Genero (*)");
		GridBagConstraints gbc_lblGenero = new GridBagConstraints();
		gbc_lblGenero.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblGenero.insets = new Insets(0, 0, 5, 5);
		gbc_lblGenero.gridx = 1;
		gbc_lblGenero.gridy = 8;
		panel.add(lblGenero, gbc_lblGenero);
		
		lblNacimiento = new JLabel("Fecha de Nacimiento (*)");
		GridBagConstraints gbc_lblNacimiento = new GridBagConstraints();
		gbc_lblNacimiento.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNacimiento.gridwidth = 2;
		gbc_lblNacimiento.insets = new Insets(0, 0, 5, 0);
		gbc_lblNacimiento.gridx = 2;
		gbc_lblNacimiento.gridy = 8;
		panel.add(lblNacimiento, gbc_lblNacimiento);
		
		GridBagConstraints gbc_selectGenero = new GridBagConstraints();
		gbc_selectGenero.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectGenero.insets = new Insets(0, 0, 5, 5);
		gbc_selectGenero.gridx = 1;
		gbc_selectGenero.gridy = 9;
		panel.add(selectGenero, gbc_selectGenero);
		
		dateChooser = new JDateChooser();
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.gridx = 2;
		gbc_dateChooser.gridy = 9;
		panel.add(dateChooser, gbc_dateChooser);
		
		lblMailInstitucional = new JLabel("Email Institucional");
		GridBagConstraints gbc_lblMailInstitucional = new GridBagConstraints();
		gbc_lblMailInstitucional.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMailInstitucional.insets = new Insets(0, 0, 5, 5);
		gbc_lblMailInstitucional.gridx = 1;
		gbc_lblMailInstitucional.gridy = 10;
		panel.add(lblMailInstitucional, gbc_lblMailInstitucional);
		
		JLabel lblMail = new JLabel("Email Personal");
		GridBagConstraints gbc_lblMail = new GridBagConstraints();
		gbc_lblMail.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMail.insets = new Insets(0, 0, 5, 5);
		gbc_lblMail.gridx = 2;
		gbc_lblMail.gridy = 10;
		panel.add(lblMail, gbc_lblMail);
		
		inputMailInstitucional = new JTextField();
		GridBagConstraints gbc_inputMailInstitucional = new GridBagConstraints();
		gbc_inputMailInstitucional.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputMailInstitucional.insets = new Insets(0, 0, 5, 5);
		gbc_inputMailInstitucional.gridx = 1;
		gbc_inputMailInstitucional.gridy = 11;
		panel.add(inputMailInstitucional, gbc_inputMailInstitucional);
		inputMailInstitucional.setColumns(10);
		
		inputMail = new JTextField();
		GridBagConstraints gbc_inputMail = new GridBagConstraints();
		gbc_inputMail.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputMail.insets = new Insets(0, 0, 5, 5);
		gbc_inputMail.gridx = 2;
		gbc_inputMail.gridy = 11;
		panel.add(inputMail, gbc_inputMail);
		inputMail.setColumns(10);

		lblTelefono = new JLabel("Teléfono");
		GridBagConstraints gbc_lblTelefono = new GridBagConstraints();
		gbc_lblTelefono.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTelefono.gridwidth = 2;
		gbc_lblTelefono.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefono.gridx = 1;
		gbc_lblTelefono.gridy = 12;
		panel.add(lblTelefono, gbc_lblTelefono);
		
		inputTelefono = new JTextField();
		GridBagConstraints gbc_inputTelefono = new GridBagConstraints();
		gbc_inputTelefono.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputTelefono.gridwidth = 2;
		gbc_inputTelefono.insets = new Insets(0, 0, 5, 5);
		gbc_inputTelefono.gridx = 1;
		gbc_inputTelefono.gridy = 13;
		panel.add(inputTelefono, gbc_inputTelefono);
		inputTelefono.setColumns(10);

		lblUsuario = new JLabel("Nombre de Usuario (*)");
		GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
		gbc_lblUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuario.gridx = 1;
		gbc_lblUsuario.gridy = 14;
		panel.add(lblUsuario, gbc_lblUsuario);
		
		lblClave = new JLabel("Contraseña (*)");
		GridBagConstraints gbc_lblClave = new GridBagConstraints();
		gbc_lblClave.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblClave.insets = new Insets(0, 0, 5, 5);
		gbc_lblClave.gridx = 2;
		gbc_lblClave.gridy = 14;
		panel.add(lblClave, gbc_lblClave);

		inputUsuario = new JTextField();
		GridBagConstraints gbc_inputUsuario = new GridBagConstraints();
		gbc_inputUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_inputUsuario.gridx = 1;
		gbc_inputUsuario.gridy = 15;
		panel.add(inputUsuario, gbc_inputUsuario);
		inputUsuario.setColumns(10);
		
		inputClave = new JPasswordField();
		GridBagConstraints gbc_inputClave = new GridBagConstraints();
		gbc_inputClave.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputClave.insets = new Insets(0, 0, 5, 5);
		gbc_inputClave.gridx = 2;
		gbc_inputClave.gridy = 15;
		panel.add(inputClave, gbc_inputClave);
		
		lbItr = new JLabel("ITR");
		GridBagConstraints gbc_lbItr = new GridBagConstraints();
		gbc_lbItr.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbItr.gridwidth = 2;
		gbc_lbItr.insets = new Insets(0, 0, 5, 5);
		gbc_lbItr.gridx = 1;
		gbc_lbItr.gridy = 16;
		panel.add(lbItr, gbc_lbItr);

		GridBagConstraints gbc_selectItr = new GridBagConstraints();
		gbc_selectItr.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectItr.gridwidth = 2;
		gbc_selectItr.insets = new Insets(0, 0, 5, 5);
		gbc_selectItr.gridx = 1;
		gbc_selectItr.gridy = 17;
		panel.add(selectItr, gbc_selectItr);
		
		if(userType.getClass().equals(Estudiante.class)) {
			lblGeneracion = new JLabel("Generacion");
			GridBagConstraints gbc_lblGeneracion = new GridBagConstraints();
			gbc_lblGeneracion.gridwidth = 2;
			gbc_lblGeneracion.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblGeneracion.insets = new Insets(0, 0, 5, 5);
			gbc_lblGeneracion.gridx = 1;
			gbc_lblGeneracion.gridy = 20;
			panel.add(lblGeneracion, gbc_lblGeneracion);
			
			yearChooser = new JYearChooser();
			GridBagConstraints gbc_yearChooser = new GridBagConstraints();
			gbc_yearChooser.insets = new Insets(0, 0, 5, 5);
			gbc_yearChooser.gridwidth = 2;
			gbc_yearChooser.fill = GridBagConstraints.HORIZONTAL;
			gbc_yearChooser.gridx = 1;
			gbc_yearChooser.gridy = 21;
			panel.add(yearChooser, gbc_yearChooser);
			
		} else if(userType.getClass().equals(Tutor.class)) {
			lblArea = new JLabel("Area");
			GridBagConstraints gbc_lblArea = new GridBagConstraints();
			gbc_lblArea.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblArea.insets = new Insets(0, 0, 5, 5);
			gbc_lblArea.gridx = 1;
			gbc_lblArea.gridy = 20;
			panel.add(lblArea, gbc_lblArea);
			
			GridBagConstraints gbc_selectArea = new GridBagConstraints();
			gbc_selectArea.insets = new Insets(0, 0, 5, 5);
			gbc_selectArea.fill = GridBagConstraints.HORIZONTAL;
			gbc_selectArea.gridx = 1;
			gbc_selectArea.gridy = 21;
			panel.add(selectArea, gbc_selectArea);
			
			lblTipo = new JLabel("Tipo");
			GridBagConstraints gbc_lblTipo = new GridBagConstraints();
			gbc_lblTipo.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblTipo.insets = new Insets(0, 0, 5, 5);
			gbc_lblTipo.gridx = 2;
			gbc_lblTipo.gridy = 20;
			panel.add(lblTipo, gbc_lblTipo);
	
			GridBagConstraints gbc_selectTipo = new GridBagConstraints();
			gbc_selectTipo.insets = new Insets(0, 0, 5, 5);
			gbc_selectTipo.fill = GridBagConstraints.HORIZONTAL;
			gbc_selectTipo.gridx = 2;
			gbc_selectTipo.gridy = 21;
			panel.add(selectTipo, gbc_selectTipo);
		}
		
		lblDepartamento = new JLabel("Departamento");
		GridBagConstraints gbc_lblDepartamento = new GridBagConstraints();
		gbc_lblDepartamento.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblDepartamento.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartamento.gridx = 1;
		gbc_lblDepartamento.gridy = 18;
		panel.add(lblDepartamento, gbc_lblDepartamento);
		
		selectDepartamento.setSelectedItem(null);
		selectDepartamento.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				Departamento d = (Departamento) selectDepartamento.getSelectedItem();
				List<Localidad> lista = d.getLocalidades();
				selectLocalidad.setModel(new DefaultComboBoxModel<Localidad>(lista.toArray(new Localidad[0])));
				
			}
		});
		
		lblLocalidad = new JLabel("Localidad");
		GridBagConstraints gbc_lblLocalidad = new GridBagConstraints();
		gbc_lblLocalidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblLocalidad.insets = new Insets(0, 0, 5, 5);
		gbc_lblLocalidad.gridx = 2;
		gbc_lblLocalidad.gridy = 18;
		panel.add(lblLocalidad, gbc_lblLocalidad);
		GridBagConstraints gbc_selectDepartamento = new GridBagConstraints();
		gbc_selectDepartamento.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectDepartamento.insets = new Insets(0, 0, 5, 5);
		gbc_selectDepartamento.gridx = 1;
		gbc_selectDepartamento.gridy = 19;
		panel.add(selectDepartamento, gbc_selectDepartamento);
		
		GridBagConstraints gbc_selectLocalidad = new GridBagConstraints();
		gbc_selectLocalidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectLocalidad.insets = new Insets(0, 0, 5, 5);
		gbc_selectLocalidad.gridx = 2;
		gbc_selectLocalidad.gridy = 19;
		panel.add(selectLocalidad, gbc_selectLocalidad);
				
		btnCrear = new JButton("Registrarse");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		
		btnCrear.setToolTipText(
				"Al presionar este botón, se registrara el Usuario en el sistema a partir de los datos ingresados en el formulario.");
		GridBagConstraints gbc_btnCrear = new GridBagConstraints();
		gbc_btnCrear.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCrear.insets = new Insets(0, 0, 5, 5);
		gbc_btnCrear.gridx = 1;
		gbc_btnCrear.gridy = 22;
		panel.add(btnCrear, gbc_btnCrear);
		
		btnClean = new JButton("Limpiar");
		btnClean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarInput();
			}
		});
		GridBagConstraints gbc_btnClean = new GridBagConstraints();
		gbc_btnClean.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnClean.insets = new Insets(0, 0, 5, 5);
		gbc_btnClean.gridx = 2;
		gbc_btnClean.gridy = 22;
		panel.add(btnClean, gbc_btnClean);

		limpiarInput();
	}

	private void setup() {
		try {
			setupComboBox();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public LocalDateTime convertToLocalDateTime(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDateTime();
	}
	
	private void setupComboBox() throws NamingException {
		ArrayList<Genero> generos = (ArrayList<Genero>) getGenero();
		selectGenero = new JComboBox(generos.toArray());
		
		ArrayList<Itr> itrs = (ArrayList<Itr>) getItr();
		selectItr = new JComboBox(itrs.toArray());
		
		ArrayList<Departamento> departamentos = (ArrayList<Departamento>) getDepartamento();
		selectDepartamento = new JComboBox(departamentos.toArray());
		
		selectArea = new JComboBox<EnumTutorArea>(EnumTutorArea.values());
		
		selectTipo = new JComboBox<EnumTutorTipo>(EnumTutorTipo.values());
	}
	
	protected void register() {
		Usuario e = null;
		if(userType.getClass() == Estudiante.class) {
			e = Estudiante.builder().generacion(yearChooser.getValue()).build();
		} else if (userType.getClass() == Tutor.class) {
			e = Tutor.builder()
					.area((EnumTutorArea) selectArea.getSelectedItem())
					.tipo((EnumTutorTipo) selectTipo.getSelectedItem())
					.estado(EnumUsuarioEstado.PENDIENTE)
					.build();
		}
		e.setApellido1(inputApellido1.getText());
		e.setApellido2(inputApellido2.getText());
		e.setNombre1(inputNombre1.getText());
		e.setNombre2(inputNombre2.getText());
		e.setGenero((Genero) selectGenero.getSelectedItem());
		e.setFechaNac(dateChooser.getDate());
		e.setDocumento(inputCedula.getText());
		e.setMail(inputMail.getText());
		e.setTelefono(inputTelefono.getText());
		e.setUsuario(inputUsuario.getText());
		e.setClave(inputClave.getText());
		e.setItr((Itr) selectItr.getSelectedItem());
		e.setDepartamento((Departamento) selectDepartamento.getSelectedItem());
		e.setLocalidad((Localidad) selectLocalidad.getSelectedItem());
		e.setEstado(EnumUsuarioEstado.PENDIENTE);
		
		try {
			bo.create(e);
			limpiarInput();
			JOptionPane.showMessageDialog(null, "Se ha creado el usuario exitosamente, queda a la espera de ser habilitado por un Analista");
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "No se a podido crear el usuario " + e1.getMessage());
		}
	}

	private void limpiarInput() {
		inputNombre1.setText("");
		inputNombre2.setText("");
		inputApellido1.setText("");
		inputApellido2.setText("");
		inputCedula.setText("");
		inputMail.setText("");
		inputTelefono.setText("");
		inputUsuario.setText("");
		inputClave.setText("");
		selectTipo.setSelectedItem(null);
		selectGenero.setSelectedItem(null);
		selectArea.setSelectedItem(null);
		selectItr.setSelectedItem(null);
	}
	
}