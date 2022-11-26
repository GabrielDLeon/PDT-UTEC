package com.app.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

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
import com.formdev.flatlaf.FlatDarkLaf;
import com.services.users.DepartamentoBeanRemote;
import com.services.users.GeneroBeanRemote;
import com.services.users.ItrBeanRemote;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JYearChooser;

public class viewCrudUsuario extends JFrame {

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
	private JButton btnModificar;

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
	private DefaultTableModel model = new DefaultTableModel();
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnEliminar;
	private JButton btnListar;

	public static void main(String[] args) {
		FlatDarkLaf.setup();
		Font roboto = RobotoFont.getNormal();
		UIManager.getLookAndFeelDefaults().put("defaultFont", roboto);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuario u = new Analista();
					viewCrudUsuario frame = new viewCrudUsuario(u);
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

	public viewCrudUsuario(Usuario u) throws NamingException {

		this.userType = u;

		try {
			getItr();
			getDepartamento();
			getGenero();
		} catch (NamingException e) {
			System.out.println("No se pudo cargar la lista de ITR");
		}

		model.addColumn("ID");
		model.addColumn("Nombre 1");
		model.addColumn("Nombre 2");
		model.addColumn("Apellido 1");
		model.addColumn("Apellido 2");
		model.addColumn("Genero");
		model.addColumn("Fecha Nac.");
		model.addColumn("Documento");
		model.addColumn("Telefono");
		model.addColumn("Correo");
		model.addColumn("Departamento");
		model.addColumn("Localidad");
		model.addColumn("ITR");
		model.addColumn("Usuario");
		model.addColumn("Contraseña");
		model.addColumn("Estado");

		setBounds(100, 100, 950, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBorder(null);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		lblRegistro = new JLabel("");
		lblRegistro.setBounds(0, 0, 0, 0);
		panel.add(lblRegistro);

		JLabel lblNombre1 = new JLabel("Primer Nombre");
		lblNombre1.setFont(new Font("Roboto", Font.PLAIN, 12));
		lblNombre1.setBounds(19, 8, 70, 14);
		panel.add(lblNombre1);

		inputNombre1 = new JTextField();
		inputNombre1.setFont(new Font("Roboto", Font.PLAIN, 12));
		inputNombre1.setBounds(109, 5, 133, 20);
		panel.add(inputNombre1);
		inputNombre1.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(247, 5, 491, 480);
		panel.add(scrollPane);

		table = new JTable(model);
		scrollPane.setViewportView(table);

		JLabel lblNombre2 = new JLabel("Segundo Nombre");
		lblNombre2.setFont(new Font("Roboto", Font.PLAIN, 12));
		lblNombre2.setBounds(13, 33, 82, 14);
		panel.add(lblNombre2);

		inputNombre2 = new JTextField();
		inputNombre2.setFont(new Font("Roboto", Font.PLAIN, 12));
		inputNombre2.setBounds(109, 30, 133, 20);
		panel.add(inputNombre2);
		inputNombre2.setColumns(10);

		JLabel lblApellido1 = new JLabel("Primer Apellido");
		lblApellido1.setFont(new Font("Roboto", Font.PLAIN, 12));
		lblApellido1.setBounds(19, 58, 70, 14);
		panel.add(lblApellido1);

		inputApellido1 = new JTextField();
		inputApellido1.setFont(new Font("Roboto", Font.PLAIN, 12));
		inputApellido1.setBounds(109, 55, 133, 20);
		panel.add(inputApellido1);
		inputApellido1.setColumns(10);

		lblApellido2 = new JLabel("Segundo Apellido");
		lblApellido2.setFont(new Font("Roboto", Font.PLAIN, 12));
		lblApellido2.setBounds(13, 83, 82, 14);
		panel.add(lblApellido2);

		inputApellido2 = new JTextField();
		inputApellido2.setFont(new Font("Roboto", Font.PLAIN, 12));
		inputApellido2.setBounds(109, 80, 133, 20);
		inputApellido2.setColumns(10);
		panel.add(inputApellido2);

		lblGenero = new JLabel("Genero");
		lblGenero.setFont(new Font("Roboto", Font.PLAIN, 12));
		lblGenero.setBounds(37, 109, 35, 14);
		panel.add(lblGenero);
		
		ArrayList<Genero> generos = (ArrayList<Genero>) getGenero();

		selectGenero = new JComboBox(generos.toArray());
		selectGenero.setFont(new Font("Roboto", Font.PLAIN, 12));
		selectGenero.setBounds(109, 105, 133, 22);
		panel.add(selectGenero);

		lblNacimiento = new JLabel("Fecha de Nacimiento");
		lblNacimiento.setFont(new Font("Roboto", Font.PLAIN, 12));
		lblNacimiento.setBounds(5, 135, 99, 14);
		panel.add(lblNacimiento);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(109, 132, 133, 20);
		panel.add(dateChooser);

		JLabel lblCedula = new JLabel("Cedula");
		lblCedula.setFont(new Font("Roboto", Font.PLAIN, 12));
		lblCedula.setBounds(38, 160, 33, 14);
		panel.add(lblCedula);

		inputCedula = new JTextField();
		inputCedula.setFont(new Font("Roboto", Font.PLAIN, 12));
		inputCedula.setBounds(109, 157, 133, 20);
		panel.add(inputCedula);
		inputCedula.setColumns(10);

		JLabel lblEmail = new JLabel("Email Personal");
		lblEmail.setFont(new Font("Roboto", Font.PLAIN, 12));
		lblEmail.setBounds(20, 185, 68, 14);
		panel.add(lblEmail);

		inputEmail = new JTextField();
		inputEmail.setFont(new Font("Roboto", Font.PLAIN, 12));
		inputEmail.setBounds(109, 182, 133, 20);
		panel.add(inputEmail);
		inputEmail.setColumns(10);

		lblTelefono = new JLabel("Teléfono");
		lblTelefono.setFont(new Font("Roboto", Font.PLAIN, 12));
		lblTelefono.setBounds(33, 210, 42, 14);
		panel.add(lblTelefono);

		inputTelefono = new JTextField();
		inputTelefono.setFont(new Font("Roboto", Font.PLAIN, 12));
		inputTelefono.setBounds(109, 207, 133, 20);
		panel.add(inputTelefono);
		inputTelefono.setColumns(10);

		lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Roboto", Font.PLAIN, 12));
		lblUsuario.setBounds(36, 235, 36, 14);
		panel.add(lblUsuario);

		inputUsuario = new JTextField();
		inputUsuario.setFont(new Font("Roboto", Font.PLAIN, 12));
		inputUsuario.setBounds(109, 232, 133, 20);
		panel.add(inputUsuario);
		inputUsuario.setColumns(10);

		lblClave = new JLabel("Contraseña");
		lblClave.setFont(new Font("Roboto", Font.PLAIN, 12));
		lblClave.setBounds(26, 260, 56, 14);
		panel.add(lblClave);

		inputClave = new JPasswordField();
		inputClave.setFont(new Font("Roboto", Font.PLAIN, 12));
		inputClave.setBounds(109, 257, 133, 20);
		panel.add(inputClave);

		lbItr = new JLabel("ITR");
		lbItr.setFont(new Font("Roboto", Font.PLAIN, 12));
		lbItr.setBounds(46, 286, 17, 14);
		panel.add(lbItr);
		
		ArrayList<Itr> itrs = (ArrayList<Itr>) getItr();

		selectItr = new JComboBox(itrs.toArray());
		selectItr.setFont(new Font("Roboto", Font.PLAIN, 12));
		selectItr.setBounds(109, 282, 133, 22);
		panel.add(selectItr);

		lblDepartamento = new JLabel("Departamento");
		lblDepartamento.setFont(new Font("Roboto", Font.PLAIN, 12));
		lblDepartamento.setBounds(20, 313, 69, 14);
		panel.add(lblDepartamento);

		ArrayList<Departamento> departamentos = (ArrayList<Departamento>) getDepartamento();
		
		selectDepartamento = new JComboBox(departamentos.toArray());
		selectDepartamento.setFont(new Font("Roboto", Font.PLAIN, 12));
		selectDepartamento.setBounds(109, 309, 133, 22);
		selectDepartamento.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				Departamento d = (Departamento) selectDepartamento.getSelectedItem();
				List<Localidad> lista = d.getLocalidades();
				selectLocalidad.setModel(new DefaultComboBoxModel<Localidad>(lista.toArray(new Localidad[0])));
			}
		});
		panel.add(selectDepartamento);

		lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setFont(new Font("Roboto", Font.PLAIN, 12));
		lblLocalidad.setBounds(32, 340, 44, 14);
		panel.add(lblLocalidad);
		selectLocalidad.setFont(new Font("Roboto", Font.PLAIN, 12));
		selectLocalidad.setBounds(109, 336, 133, 22);
		panel.add(selectLocalidad);

		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(5, 462, 99, 23);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Usuario u = 
			}
		});

		btnModificar.setToolTipText("");
		panel.add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(109, 462, 69, 23);
		btnEliminar.setToolTipText("");
		panel.add(btnEliminar);

		btnListar = new JButton("Listar");
		btnListar.setBounds(183, 462, 59, 23);
		btnListar.setToolTipText("");
		panel.add(btnListar);

		if (userType.getClass() == Estudiante.class) {
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
			panel.add(yearChooser, gbc_yearChooser);
		}

		if (userType.getClass() == Tutor.class) {
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
			panel.add(selectTipo, gbc_selectTipo);
		}

	}

	public LocalDateTime convertToLocalDateTime(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	/*
	 * protected void register() {
	 * 
	 * if(userType.getClass() == Estudiante.class) { Usuario e =
	 * Estudiante.builder() .apellido1(inputApellido1.getText())
	 * .apellido2(inputApellido2.getText()) .nombre1(inputNombre1.getText())
	 * .nombre2(inputNombre2.getText()) .genero((Genero)
	 * selectGenero.getSelectedItem()) .fechaNac(dateChooser.getDate())
	 * .documento(inputCedula.getText()) .mail(inputEmail.getText())
	 * .telefono(inputTelefono.getText()) .usuario(inputUsuario.getText())
	 * .clave(inputClave.getText()) .itr((Itr) selectItr.getSelectedItem())
	 * .departamento((Departamento) selectDepartamento.getSelectedItem())
	 * .localidad((Localidad) selectLocalidad.getSelectedItem())
	 * .generacion(yearChooser.getValue()) .estado(EnumUsuarioEstado.PENDIENTE)
	 * .build(); try { bo.create(e); JOptionPane.showMessageDialog(null,
	 * "Se ha creado el usuario exitosamente, queda a la espera de ser habilitado por un Analista"
	 * ); } catch (Exception e1) { JOptionPane.showMessageDialog(null,
	 * "No se a podido crear el usuario " + e1.getMessage()); }
	 * 
	 * } else if (userType.getClass() == Tutor.class) {
	 * 
	 * @SuppressWarnings("deprecation") Usuario t = Tutor.builder()
	 * .apellido1(inputApellido1.getText()) .apellido2(inputApellido2.getText())
	 * .nombre1(inputNombre1.getText()) .nombre2(inputNombre2.getText())
	 * .genero((Genero) selectGenero.getSelectedItem())
	 * .fechaNac(dateChooser.getDate()) .documento(inputCedula.getText())
	 * .mail(inputEmail.getText()) .telefono(inputTelefono.getText())
	 * .usuario(inputUsuario.getText()) .clave(inputClave.getText()) .itr((Itr)
	 * selectItr.getSelectedItem()) .departamento((Departamento)
	 * selectDepartamento.getSelectedItem()) .localidad((Localidad)
	 * selectLocalidad.getSelectedItem()) .area((EnumTutorArea)
	 * selectArea.getSelectedItem()) .tipo((EnumTutorTipo)
	 * selectTipo.getSelectedItem()) .estado(EnumUsuarioEstado.PENDIENTE) .build();
	 * try { bo.create(t); JOptionPane.showMessageDialog(null,
	 * "Se ha creado el usuario exitosamente, queda a la espera de ser habilitado por un Analista"
	 * ); } catch (Exception e1) { JOptionPane.showMessageDialog(null,
	 * "No se a podido crear el usuario " + e1.getMessage()); }
	 * 
	 * } }
	 */

	
	/*
	 * row[0] = u.getIdUsuario();
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
	 * */
	
}