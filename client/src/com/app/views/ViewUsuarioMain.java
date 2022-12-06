package com.app.views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

import com.app.controllers.UsuarioDAO;
import com.dto.UsuarioBusquedaVO;
import com.entities.Analista;
import com.entities.Departamento;
import com.entities.Estudiante;
import com.entities.Genero;
import com.entities.Itr;
import com.entities.Localidad;
import com.entities.Tutor;
import com.entities.Usuario;
import com.enumerators.EnumUsuarioEstado;
import com.services.users.DepartamentoBeanRemote;
import com.services.users.GeneroBeanRemote;
import com.services.users.ItrBeanRemote;
import com.toedter.calendar.JDateChooser;

public class ViewUsuarioMain extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	private DefaultTableModel model = new DefaultTableModel();
	private UsuarioDAO dao = new UsuarioDAO();
	private Usuario userType;

	private JTextField txtNombre1;
	private JTextField txtNombre2;
	private JTextField txtApellido1;
	private JTextField txtApellido2;
	private JTextField txtCedula;
	private JTextField txtEmail;
	private JTextField txtTelefono;
	private JTextField txtUsuario;
	private JPasswordField passwordField;
	private CustomTable table;
	private JComboBox<Genero> selectGenero;
	private JDateChooser dateChooser;
	private JComboBox<Itr> selectItr;
	private JComboBox<Departamento> selectDepartamento;
	private JComboBox<Localidad> selectLocalidad;
	private JComboBox selectEstado;
	private JButton btnListar;
	private JButton btnModificar;
	private JLabel lblUsuario;
	
	private List<Usuario> usuarios;
	
	private JComboBox filterItr;
	private JComboBox filterEstado;
	
	private List<Genero> generoList;
	private List<Itr> itrList;
	private List<Departamento> departamentoList;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuario u = new Analista();
					ViewUsuarioMain frame = new ViewUsuarioMain(u);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public List<Genero> getGenero() throws NamingException {
		GeneroBeanRemote beanGenero = (GeneroBeanRemote) InitialContext
				.doLookup("ejb:/PDT-Server/GeneroBean!com.services.users.GeneroBeanRemote");
		return generoList = beanGenero.findAll();
	}
	
	public List<Itr> getItr() throws NamingException {
		ItrBeanRemote beanItr = (ItrBeanRemote) InitialContext
				.doLookup("ejb:/PDT-Server/ItrBean!com.services.users.ItrBeanRemote");
		return itrList = beanItr.findAll();
	}
	
	public List<Departamento> getDepartamento() throws NamingException {
		DepartamentoBeanRemote beanDepartamento = (DepartamentoBeanRemote) InitialContext
				.doLookup("ejb:/PDT-Server/DepartamentoBean!com.services.users.DepartamentoBeanRemote");
		return departamentoList = beanDepartamento.findAll();
	}
	
	public void refreshTable(UsuarioBusquedaVO vo) {
		table.model.setRowCount(0);
		if (vo != null) {
			usuarios = dao.search(vo);
		} else {
			fillTable();
		}
	}
	
	public void search() {
		UsuarioBusquedaVO vo = UsuarioBusquedaVO.builder()
				.itr((Itr) filterItr.getSelectedItem())
				.estado((EnumUsuarioEstado) filterEstado.getSelectedItem())
				.build();
		refreshTable(vo);
	}
	
	protected void fillFilteredTable() {
		table.model.setRowCount(0);
		for (Usuario u : usuarios) {
			Object[] row = new Object[5];
			row[0] = u;
			row[1] = u.getIdUsuario();
			row[2] = u.getDocumento();
			row[3] = u.getNombre1() + u.getApellido1();
			row[4] = u.getEstado();
			table.model.addRow(row);
		}
	}

	@SuppressWarnings("unchecked")
	public ViewUsuarioMain(Usuario u) throws NamingException {
		
		this.userType = u;
		
		setMaximizable(true);

		getContentPane().setFont(new Font("Roboto", Font.PLAIN, 12));
		setBounds(100, 100, 1001, 650);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 30, 30, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 28, 0, 0, 0, 28, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		this.setBorder(null);
		BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
		ui.setNorthPane(null);

		setBorder(null);

		JLabel lblNombre1 = new JLabel("Primer Nombre");
		lblNombre1.setFont(new Font("Roboto", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNombre1 = new GridBagConstraints();
		gbc_lblNombre1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre1.gridx = 1;
		gbc_lblNombre1.gridy = 1;
		getContentPane().add(lblNombre1, gbc_lblNombre1);

		txtNombre1 = new JTextField();
		txtNombre1.setFont(new Font("Roboto", Font.PLAIN, 12));
		txtNombre1.setColumns(10);
		GridBagConstraints gbc_txtNombre1 = new GridBagConstraints();
		gbc_txtNombre1.gridwidth = 4;
		gbc_txtNombre1.insets = new Insets(0, 0, 5, 5);
		gbc_txtNombre1.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombre1.gridx = 3;
		gbc_txtNombre1.gridy = 1;
		getContentPane().add(txtNombre1, gbc_txtNombre1);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 17;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 8;
		gbc_scrollPane.gridy = 1;
		getContentPane().add(scrollPane, gbc_scrollPane);

		
		if (userType.getClass() == Analista.class) {
			table = new CustomTable();
			table.setColumns("Usuario","ID","Documento","Nombre","Estado");
			scrollPane.setViewportView(table);
			table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent lse) {
					if (!lse.getValueIsAdjusting()) {
						int row = table.getSelectedRow();
						if (row >= 0) {
							fillInputFromTable(row);
						} else {
							limpiarInput();
						}
					}
				}
			});
		}
		

		JLabel lblNombre2 = new JLabel("Segundo Nombre");
		lblNombre2.setFont(new Font("Roboto", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNombre2 = new GridBagConstraints();
		gbc_lblNombre2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre2.gridx = 1;
		gbc_lblNombre2.gridy = 2;
		getContentPane().add(lblNombre2, gbc_lblNombre2);

		txtNombre2 = new JTextField();
		txtNombre2.setFont(new Font("Roboto", Font.PLAIN, 12));
		txtNombre2.setColumns(10);
		GridBagConstraints gbc_txtNombre2 = new GridBagConstraints();
		gbc_txtNombre2.anchor = GridBagConstraints.NORTH;
		gbc_txtNombre2.gridwidth = 4;
		gbc_txtNombre2.insets = new Insets(0, 0, 5, 5);
		gbc_txtNombre2.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombre2.gridx = 3;
		gbc_txtNombre2.gridy = 2;
		getContentPane().add(txtNombre2, gbc_txtNombre2);

		JLabel lblApellido1 = new JLabel("Primer Apellido");
		lblApellido1.setFont(new Font("Roboto", Font.PLAIN, 12));
		GridBagConstraints gbc_lblApellido1 = new GridBagConstraints();
		gbc_lblApellido1.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellido1.gridx = 1;
		gbc_lblApellido1.gridy = 3;
		getContentPane().add(lblApellido1, gbc_lblApellido1);

		txtApellido1 = new JTextField();
		txtApellido1.setFont(new Font("Roboto", Font.PLAIN, 12));
		txtApellido1.setColumns(10);
		GridBagConstraints gbc_txtApellido1 = new GridBagConstraints();
		gbc_txtApellido1.gridwidth = 4;
		gbc_txtApellido1.insets = new Insets(0, 0, 5, 5);
		gbc_txtApellido1.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtApellido1.gridx = 3;
		gbc_txtApellido1.gridy = 3;
		getContentPane().add(txtApellido1, gbc_txtApellido1);

		JLabel lblApellido2 = new JLabel("Segundo Apellido");
		lblApellido2.setFont(new Font("Roboto", Font.PLAIN, 12));
		GridBagConstraints gbc_lblApellido2 = new GridBagConstraints();
		gbc_lblApellido2.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellido2.gridx = 1;
		gbc_lblApellido2.gridy = 4;
		getContentPane().add(lblApellido2, gbc_lblApellido2);

		txtApellido2 = new JTextField();
		txtApellido2.setFont(new Font("Roboto", Font.PLAIN, 12));
		txtApellido2.setColumns(10);
		GridBagConstraints gbc_txtApellido2 = new GridBagConstraints();
		gbc_txtApellido2.gridwidth = 4;
		gbc_txtApellido2.insets = new Insets(0, 0, 5, 5);
		gbc_txtApellido2.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtApellido2.gridx = 3;
		gbc_txtApellido2.gridy = 4;
		getContentPane().add(txtApellido2, gbc_txtApellido2);

		JLabel lblGenero = new JLabel("Genero");
		lblGenero.setFont(new Font("Roboto", Font.PLAIN, 12));
		GridBagConstraints gbc_lblGenero = new GridBagConstraints();
		gbc_lblGenero.insets = new Insets(0, 0, 5, 5);
		gbc_lblGenero.gridx = 1;
		gbc_lblGenero.gridy = 5;
		getContentPane().add(lblGenero, gbc_lblGenero);

		ArrayList<Genero> generos = (ArrayList<Genero>) getGenero();
		
		selectGenero = new JComboBox(generos.toArray());
		selectGenero.setFont(new Font("Roboto", Font.PLAIN, 12));
		GridBagConstraints gbc_selectGenero = new GridBagConstraints();
		gbc_selectGenero.gridwidth = 4;
		gbc_selectGenero.insets = new Insets(0, 0, 5, 5);
		gbc_selectGenero.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectGenero.gridx = 3;
		gbc_selectGenero.gridy = 5;
		getContentPane().add(selectGenero, gbc_selectGenero);

		JLabel lblNacimiento = new JLabel("Fecha de Nacimiento");
		lblNacimiento.setFont(new Font("Roboto", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNacimiento = new GridBagConstraints();
		gbc_lblNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_lblNacimiento.gridx = 1;
		gbc_lblNacimiento.gridy = 6;
		getContentPane().add(lblNacimiento, gbc_lblNacimiento);

		dateChooser = new JDateChooser();
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.gridwidth = 4;
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.fill = GridBagConstraints.BOTH;
		gbc_dateChooser.gridx = 3;
		gbc_dateChooser.gridy = 6;
		getContentPane().add(dateChooser, gbc_dateChooser);

		JLabel lblCedula = new JLabel("Cedula");
		lblCedula.setFont(new Font("Roboto", Font.PLAIN, 12));
		GridBagConstraints gbc_lblCedula = new GridBagConstraints();
		gbc_lblCedula.insets = new Insets(0, 0, 5, 5);
		gbc_lblCedula.gridx = 1;
		gbc_lblCedula.gridy = 7;
		getContentPane().add(lblCedula, gbc_lblCedula);

		txtCedula = new JTextField();
		txtCedula.setFont(new Font("Roboto", Font.PLAIN, 12));
		txtCedula.setColumns(10);
		GridBagConstraints gbc_txtCedula = new GridBagConstraints();
		gbc_txtCedula.gridwidth = 4;
		gbc_txtCedula.insets = new Insets(0, 0, 5, 5);
		gbc_txtCedula.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCedula.gridx = 3;
		gbc_txtCedula.gridy = 7;
		getContentPane().add(txtCedula, gbc_txtCedula);

		JLabel lblEmail = new JLabel("Email Personal");
		lblEmail.setFont(new Font("Roboto", Font.PLAIN, 12));
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 8;
		getContentPane().add(lblEmail, gbc_lblEmail);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Roboto", Font.PLAIN, 12));
		txtEmail.setColumns(10);
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.gridwidth = 4;
		gbc_txtEmail.insets = new Insets(0, 0, 5, 5);
		gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmail.gridx = 3;
		gbc_txtEmail.gridy = 8;
		getContentPane().add(txtEmail, gbc_txtEmail);

		JLabel lblTelefono = new JLabel("Teléfono");
		lblTelefono.setFont(new Font("Roboto", Font.PLAIN, 12));
		GridBagConstraints gbc_lblTelefono = new GridBagConstraints();
		gbc_lblTelefono.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefono.gridx = 1;
		gbc_lblTelefono.gridy = 9;
		getContentPane().add(lblTelefono, gbc_lblTelefono);

		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font("Roboto", Font.PLAIN, 12));
		txtTelefono.setColumns(10);
		GridBagConstraints gbc_txtTelefono = new GridBagConstraints();
		gbc_txtTelefono.gridwidth = 4;
		gbc_txtTelefono.insets = new Insets(0, 0, 5, 5);
		gbc_txtTelefono.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTelefono.gridx = 3;
		gbc_txtTelefono.gridy = 9;
		getContentPane().add(txtTelefono, gbc_txtTelefono);

		if(userType.getClass() == Analista.class) {
			lblUsuario = new JLabel("Usuario");
			lblUsuario.setFont(new Font("Roboto", Font.PLAIN, 12));
			
			txtUsuario = new JTextField();
			txtUsuario.setFont(new Font("Roboto", Font.PLAIN, 12));
			txtUsuario.setColumns(10);
			GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
			gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
			gbc_lblUsuario.gridx = 1;
			gbc_lblUsuario.gridy = 10;
			getContentPane().add(lblUsuario, gbc_lblUsuario);
			GridBagConstraints gbc_txtUsuario = new GridBagConstraints();
			gbc_txtUsuario.gridwidth = 3;
			gbc_txtUsuario.insets = new Insets(0, 0, 5, 5);
			gbc_txtUsuario.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtUsuario.gridx = 3;
			gbc_txtUsuario.gridy = 10;
			getContentPane().add(txtUsuario, gbc_txtUsuario);
		}
		


		JLabel lblClave = new JLabel("Contraseña");
		lblClave.setFont(new Font("Roboto", Font.PLAIN, 12));
		GridBagConstraints gbc_lblClave = new GridBagConstraints();
		gbc_lblClave.insets = new Insets(0, 0, 5, 5);
		gbc_lblClave.gridx = 1;
		gbc_lblClave.gridy = 11;
		getContentPane().add(lblClave, gbc_lblClave);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Roboto", Font.PLAIN, 12));
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 4;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 3;
		gbc_passwordField.gridy = 11;
		getContentPane().add(passwordField, gbc_passwordField);

		JLabel lbItr = new JLabel("ITR");
		lbItr.setFont(new Font("Roboto", Font.PLAIN, 12));
		GridBagConstraints gbc_lbItr = new GridBagConstraints();
		gbc_lbItr.insets = new Insets(0, 0, 5, 5);
		gbc_lbItr.gridx = 1;
		gbc_lbItr.gridy = 12;
		getContentPane().add(lbItr, gbc_lbItr);
		
		ArrayList<Itr> itrs = (ArrayList<Itr>) getItr();

		selectItr = new JComboBox(itrs.toArray());
		selectItr.setFont(new Font("Roboto", Font.PLAIN, 12));
		GridBagConstraints gbc_selectItr = new GridBagConstraints();
		gbc_selectItr.gridwidth = 4;
		gbc_selectItr.insets = new Insets(0, 0, 5, 5);
		gbc_selectItr.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectItr.gridx = 3;
		gbc_selectItr.gridy = 12;
		getContentPane().add(selectItr, gbc_selectItr);

		JLabel lblDepartamento = new JLabel("Departamento");
		lblDepartamento.setFont(new Font("Roboto", Font.PLAIN, 12));
		GridBagConstraints gbc_lblDepartamento = new GridBagConstraints();
		gbc_lblDepartamento.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartamento.gridx = 1;
		gbc_lblDepartamento.gridy = 13;
		getContentPane().add(lblDepartamento, gbc_lblDepartamento);

		ArrayList<Departamento> departamentos = (ArrayList<Departamento>) getDepartamento();
		
		selectDepartamento = new JComboBox(departamentos.toArray());
		selectDepartamento.setFont(new Font("Roboto", Font.PLAIN, 12));
		selectDepartamento.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				Departamento d = (Departamento) selectDepartamento.getSelectedItem();
				List<Localidad> lista = d.getLocalidades();
				selectLocalidad.setModel(new DefaultComboBoxModel<Localidad>(lista.toArray(new Localidad[0])));
			}
		});
		GridBagConstraints gbc_selectDepartamento = new GridBagConstraints();
		gbc_selectDepartamento.gridwidth = 4;
		gbc_selectDepartamento.insets = new Insets(0, 0, 5, 5);
		gbc_selectDepartamento.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectDepartamento.gridx = 3;
		gbc_selectDepartamento.gridy = 13;
		getContentPane().add(selectDepartamento, gbc_selectDepartamento);

		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setFont(new Font("Roboto", Font.PLAIN, 12));
		GridBagConstraints gbc_lblLocalidad = new GridBagConstraints();
		gbc_lblLocalidad.insets = new Insets(0, 0, 5, 5);
		gbc_lblLocalidad.gridx = 1;
		gbc_lblLocalidad.gridy = 14;
		getContentPane().add(lblLocalidad, gbc_lblLocalidad);

		selectLocalidad = new JComboBox<Localidad>();
		selectLocalidad.setFont(new Font("Roboto", Font.PLAIN, 12));
		GridBagConstraints gbc_selectLocalidad = new GridBagConstraints();
		gbc_selectLocalidad.gridwidth = 4;
		gbc_selectLocalidad.insets = new Insets(0, 0, 5, 5);
		gbc_selectLocalidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectLocalidad.gridx = 3;
		gbc_selectLocalidad.gridy = 14;
		getContentPane().add(selectLocalidad, gbc_selectLocalidad);

		if (userType.getClass() == Analista.class) {
			btnListar = new JButton("Listar");
			GridBagConstraints gbc_btnListar = new GridBagConstraints();
			gbc_btnListar.insets = new Insets(0, 0, 0, 5);
			gbc_btnListar.gridx = 3;
			gbc_btnListar.gridy = 17;
			getContentPane().add(btnListar, gbc_btnListar);
			btnListar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					fillTable();
				}
			});
		}
		
		
		
		if (userType.getClass() == Analista.class) {
			selectEstado = new JComboBox(EnumUsuarioEstado.values());
			selectEstado.setFont(new Font("Roboto", Font.PLAIN, 12));
			JLabel lblEstado = new JLabel("Estado");
			lblEstado.setFont(new Font("Roboto", Font.PLAIN, 12));
			GridBagConstraints gbc_lblEstado = new GridBagConstraints();
			gbc_lblEstado.insets = new Insets(0, 0, 5, 5);
			gbc_lblEstado.gridx = 1;
			gbc_lblEstado.gridy = 15;
			getContentPane().add(lblEstado, gbc_lblEstado);
			GridBagConstraints gbc_selectEstado = new GridBagConstraints();
			gbc_selectEstado.gridwidth = 3;
			gbc_selectEstado.insets = new Insets(0, 0, 5, 5);
			gbc_selectEstado.fill = GridBagConstraints.HORIZONTAL;
			gbc_selectEstado.gridx = 3;
			gbc_selectEstado.gridy = 15;
			getContentPane().add(selectEstado, gbc_selectEstado);
		}
		
		

		if (userType.getClass() == Analista.class) {
			
			btnModificar = new JButton("Modificar");
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Usuario u = dao.findUsuario(getIdFromTable());
					u.setNombre1(txtNombre1.getText());
					u.setNombre2(txtNombre2.getText());
					u.setApellido1(txtApellido1.getText());
					u.setApellido2(txtApellido2.getText());
					u.setGenero((Genero) selectGenero.getSelectedItem());
					u.setFechaNac(dateChooser.getDate());
					u.setDocumento(txtCedula.getText());
					u.setTelefono(txtTelefono.getText());
					u.setMail(txtEmail.getText());
					u.setDepartamento((Departamento) selectDepartamento.getSelectedItem());
					u.setLocalidad((Localidad) selectLocalidad.getSelectedItem());
					u.setItr((Itr) selectItr.getSelectedItem());
					u.setUsuario(txtUsuario.getText());
					u.setClave(passwordField.getText());
					u.setEstado((EnumUsuarioEstado) selectEstado.getSelectedItem());
					
					dao.update(u);
					
				}
			});
			
		} else if (userType.getClass() == Tutor.class || userType.getClass() == Estudiante.class) {
			btnModificar = new JButton("Modificar");
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Usuario u = userType;
					u.setNombre1(txtNombre1.getText());
					u.setNombre2(txtNombre2.getText());
					u.setApellido1(txtApellido1.getText());
					u.setApellido2(txtApellido2.getText());
					u.setGenero((Genero) selectGenero.getSelectedItem());
					u.setFechaNac(dateChooser.getDate());
					u.setDocumento(txtCedula.getText());
					u.setTelefono(txtTelefono.getText());
					u.setMail(txtEmail.getText());
					u.setDepartamento((Departamento) selectDepartamento.getSelectedItem());
					u.setLocalidad((Localidad) selectLocalidad.getSelectedItem());
					u.setItr((Itr) selectItr.getSelectedItem());
//					u.setUsuario(txtUsuario.getText());
					u.setClave(passwordField.getText());
//					u.setEstado((EnumUsuarioEstado) selectEstado.getSelectedItem());
					
					dao.update(u);
					
				}
			});
		} 
		
		
		GridBagConstraints gbc_btnModificar = new GridBagConstraints();
		gbc_btnModificar.insets = new Insets(0, 0, 0, 5);
		gbc_btnModificar.gridx = 4;
		gbc_btnModificar.gridy = 17;
		getContentPane().add(btnModificar, gbc_btnModificar);
		
		if(userType.getClass() == Analista.class) {
		
		JLabel lblFiltrar = new JLabel("Filtrar");
		GridBagConstraints gbc_lblFiltrar = new GridBagConstraints();
		gbc_lblFiltrar.gridwidth = 4;
		gbc_lblFiltrar.insets = new Insets(0, 0, 5, 5);
		gbc_lblFiltrar.gridx = 1;
		gbc_lblFiltrar.gridy = 18;
		getContentPane().add(lblFiltrar, gbc_lblFiltrar);
		
		JLabel lblItr = new JLabel("Filtrar por ITR");
		GridBagConstraints gbc_lblItr = new GridBagConstraints();
		gbc_lblItr.insets = new Insets(0, 0, 5, 5);
		gbc_lblItr.gridx = 1;
		gbc_lblItr.gridy = 19;
		getContentPane().add(lblItr, gbc_lblItr);
		
		filterItr = new JComboBox(itrs.toArray());
		filterItr.setFont(new Font("Roboto", Font.PLAIN, 12));
		GridBagConstraints gbc_filterItr = new GridBagConstraints();
		gbc_filterItr.gridwidth = 2;
		gbc_filterItr.insets = new Insets(0, 0, 5, 5);
		gbc_filterItr.fill = GridBagConstraints.HORIZONTAL;
		gbc_filterItr.gridx = 3;
		gbc_filterItr.gridy = 19;
		getContentPane().add(filterItr, gbc_filterItr);
		
		JLabel lblFiltrarPorEstado = new JLabel("Filtrar por Estado");
		GridBagConstraints gbc_lblFiltrarPorEstado = new GridBagConstraints();
		gbc_lblFiltrarPorEstado.insets = new Insets(0, 0, 5, 5);
		gbc_lblFiltrarPorEstado.gridx = 1;
		gbc_lblFiltrarPorEstado.gridy = 20;
		getContentPane().add(lblFiltrarPorEstado, gbc_lblFiltrarPorEstado);
		
		filterEstado = new JComboBox(EnumUsuarioEstado.values());
		filterEstado.setFont(new Font("Roboto", Font.PLAIN, 12));
		GridBagConstraints gbc_filterEstado = new GridBagConstraints();
		gbc_filterEstado.gridwidth = 2;
		gbc_filterEstado.insets = new Insets(0, 0, 5, 5);
		gbc_filterEstado.fill = GridBagConstraints.HORIZONTAL;
		gbc_filterEstado.gridx = 3;
		gbc_filterEstado.gridy = 20;
		getContentPane().add(filterEstado, gbc_filterEstado);
		
		JButton btnFiltar = new JButton("Filtrar");
		btnFiltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
				fillFilteredTable();
			}
		});
		GridBagConstraints gbc_btnFiltar = new GridBagConstraints();
		gbc_btnFiltar.insets = new Insets(0, 0, 0, 5);
		gbc_btnFiltar.gridx = 3;
		gbc_btnFiltar.gridy = 21;
		getContentPane().add(btnFiltar, gbc_btnFiltar);
		
		JButton btnLimpiar = new JButton("Limpiar Filtros");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshTable(null);
			}
		});
		GridBagConstraints gbc_btnLimpiar = new GridBagConstraints();
		gbc_btnLimpiar.insets = new Insets(0, 0, 0, 5);
		gbc_btnLimpiar.gridx = 4;
		gbc_btnLimpiar.gridy = 21;
		getContentPane().add(btnLimpiar, gbc_btnLimpiar);

		if (userType.getClass() == Analista.class) {
			
			JButton btnEliminar = new JButton("Eliminar");
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dao.delete(getIdFromTable());
				}
			});
			GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
			gbc_btnEliminar.insets = new Insets(0, 0, 0, 5);
			gbc_btnEliminar.gridx = 5;
			gbc_btnEliminar.gridy = 17;
			getContentPane().add(btnEliminar, gbc_btnEliminar);
		}
		
		}

	}

	protected void fillTable() {

		ArrayList<Estudiante> estudiantes = (ArrayList<Estudiante>) dao.getAllEstudiantes();
			ArrayList<Estudiante> est = new ArrayList<>(estudiantes);
		
		ArrayList<Tutor> tutores = (ArrayList<Tutor>) dao.getAllTutores();
			ArrayList<Tutor> tut = new ArrayList<>(tutores);

		table.model.setRowCount(0);
		
		for (Usuario u : est) {
			Object[] row = new Object[5];
			row[0] = u;
			row[1] = u.getIdUsuario();
			row[2] = u.getDocumento();
			row[3] = u.getNombre1() + u.getApellido1();
			row[4] = u.getEstado();
			table.model.addRow(row);
		}

		for (Usuario u : tut) {

			Object[] row = new Object[16];
			row[0] = u.getIdUsuario();
			row[1] = u.getNombre1();
			row[2] = u.getNombre2();
			row[3] = u.getApellido1();
			row[4] = u.getApellido2();
			row[5] = u.getGenero();
			row[6] = u.getFechaNac();
			row[7] = u.getDocumento();
			row[8] = u.getTelefono();
			row[9] = u.getMail();
			row[10] = u.getDepartamento();
			row[11] = u.getLocalidad();
			row[12] = u.getItr();
			row[13] = u.getUsuario();
			row[14] = u.getClave();
			row[15] = u.getEstado();
			table.model.addRow(row);

		}
	}

	private void limpiarInput() {
		txtNombre1.setText("");
		txtNombre2.setText("");
		txtApellido1.setText("");
		txtApellido2.setText("");
		txtCedula.setText("");
		txtEmail.setText("");
		txtTelefono.setText("");
		txtUsuario.setText("");
		passwordField.setText("");
	}
	
	
	public void fillInputFromTable(int row) {
		Usuario usuario = (Usuario) table.getValueAt(0, row);
		txtNombre1.setText(usuario.getNombre1());
		txtNombre2.setText(usuario.getNombre2());
		txtApellido1.setText(usuario.getApellido1());
		txtApellido2.setText(usuario.getApellido2());
		dateChooser.setDate(usuario.getFechaNac());
		txtCedula.setText(usuario.getDocumento());
		txtTelefono.setText(usuario.getTelefono());
		txtEmail.setText(usuario.getMail());
		txtUsuario.setText(usuario.getUsuario());
		passwordField.setText(usuario.getClave());
		
		try {
			/*selectGenero.setSelectedItem((Genero)table.getValueAt(row, 5));
			selectDepartamento.setSelectedItem((Departamento)table.getValueAt(row, 10));
			selectLocalidad.setSelectedItem((Localidad)table.getValueAt(row, 11));
			selectItr.setSelectedItem((Itr)table.getValueAt(row, 12));
			selectEstado.setSelectedItem(table.getValueAt(row, 15));
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected Long getIdFromTable() {
		int row = table.getSelectedRow();
		Long id = (Long) table.getValueAt(row, 0);
		return id;
	}
	
}