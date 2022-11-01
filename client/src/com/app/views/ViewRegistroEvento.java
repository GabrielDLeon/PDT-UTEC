package com.app.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.Evento;
import com.entities.Itr;
import com.enumerators.EnumEventoModalidad;
import com.enumerators.EnumEventoTipo;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.services.users.ItrBeanRemote;
import com.app.bo.EventoBO;
import com.app.exceptions.TextFieldException;
import com.app.singleton.BeanRemoteManager;
import com.app.singleton.RobotoFont;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.naming.NamingException;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;

public class ViewRegistroEvento extends JFrame {

	private JPanel contentPane;
	private JTextField inputNombre;
	private JTextField textField_1;
	private JTextField inputLocalizacion;
	private JTextField textField;
	private JLabel lblResNombre, lblResLocalizacion, lblResFechaFin, lblResFechaInicio;

	private Map<String, JTextField> fieldsMap = new HashMap<>();

	private JComboBox<EnumEventoTipo> selectTipo = new JComboBox<EnumEventoTipo>();
	private JComboBox<EnumEventoModalidad> selectModalidad = new JComboBox<EnumEventoModalidad>();
	private JComboBox<Itr> selectItr = new JComboBox<Itr>();

	private EventoBO bo = new EventoBO();

	public static void main(String[] args) {
		FlatLaf.registerCustomDefaultsSource("com.app.themes");
		FlatLightLaf.setup();
		UIManager.getLookAndFeelDefaults().put("defaultFont", RobotoFont.getNormal());
		UIManager.put(args, args);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewRegistroEvento frame = new ViewRegistroEvento();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	protected void ingresar() {
		// TODO Realizar la implementación del Ingresar()
		boolean result = validarDatos();
		if (result) {				
			Evento evento = Evento.builder()
					.nombre(inputNombre.getText().trim())
					.fechaInicio(null)
					.fechaFin(null)
					.itr((Itr) selectItr.getSelectedItem())
					.modalidad((EnumEventoModalidad) selectModalidad.getSelectedItem())
					.tipo((EnumEventoTipo) selectTipo.getSelectedItem())
					.tutores(null)
					.build();
			try {
				bo.insert(evento);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	private boolean validarDatos() {
		boolean result = true;
		
		try {
			TextFieldException eNombre = new TextFieldException(inputNombre, lblResNombre);
			bo.validarNombre(eNombre);
		} catch (TextFieldException e) {
			result = false;
		}
		
		try {
			TextFieldException eLocalizacion = new TextFieldException(inputLocalizacion, lblResLocalizacion);
			bo.validarLocalizacion(eLocalizacion);
		} catch (TextFieldException e) {
			result = false;
		}
		
		return result;
	}
	
	protected void cancelar() {
		// TODO Realizar la implementación del Cancelar()

	}

	private void cargarComboBox() {
		selectTipo = new JComboBox<EnumEventoTipo>(EnumEventoTipo.values());
		selectModalidad = new JComboBox<EnumEventoModalidad>(EnumEventoModalidad.values());
		try {
			ItrBeanRemote bean = BeanRemoteManager.getBeanItr();
			List<Itr> list = bean.findAll();
			selectItr = new JComboBox<Itr>(list.toArray(new Itr[list.size()]));
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public ViewRegistroEvento() {
		cargarComboBox();

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
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblTitulo = new JLabel("Alta de Evento");
		lblTitulo.setFont(RobotoFont.getTitulo());
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.gridwidth = 2;
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitulo.gridx = 1;
		gbc_lblTitulo.gridy = 1;
		panel.add(lblTitulo, gbc_lblTitulo);

		JLabel lblNombre = new JLabel("Nombre del Evento");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.gridwidth = 2;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 3;
		panel.add(lblNombre, gbc_lblNombre);

		inputNombre = new JTextField();
		GridBagConstraints gbc_inputNombre = new GridBagConstraints();
		gbc_inputNombre.gridwidth = 2;
		gbc_inputNombre.insets = new Insets(0, 0, 5, 5);
		gbc_inputNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputNombre.gridx = 1;
		gbc_inputNombre.gridy = 4;
		panel.add(inputNombre, gbc_inputNombre);
		inputNombre.setColumns(10);
		
		lblResNombre = new JLabel(" ");
		lblResNombre.setForeground(Color.RED);
		GridBagConstraints gbc_lblResNombre = new GridBagConstraints();
		gbc_lblResNombre.gridwidth = 2;
		gbc_lblResNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblResNombre.gridx = 1;
		gbc_lblResNombre.gridy = 5;
		panel.add(lblResNombre, gbc_lblResNombre);

		JLabel lblTipo = new JLabel("Tipo de Evento");
		GridBagConstraints gbc_lblTipo = new GridBagConstraints();
		gbc_lblTipo.gridwidth = 2;
		gbc_lblTipo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipo.gridx = 1;
		gbc_lblTipo.gridy = 6;
		panel.add(lblTipo, gbc_lblTipo);
		
		GridBagConstraints gbc_selectTipo = new GridBagConstraints();
		gbc_selectTipo.gridwidth = 2;
		gbc_selectTipo.insets = new Insets(0, 0, 5, 5);
		gbc_selectTipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectTipo.gridx = 1;
		gbc_selectTipo.gridy = 7;
		panel.add(selectTipo, gbc_selectTipo);

		JLabel lblFechaInicio = new JLabel("Inicio");
		GridBagConstraints gbc_lblFechaInicio = new GridBagConstraints();
		gbc_lblFechaInicio.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaInicio.gridx = 1;
		gbc_lblFechaInicio.gridy = 8;
		panel.add(lblFechaInicio, gbc_lblFechaInicio);

		JLabel lblFechaFinal = new JLabel("Final");
		GridBagConstraints gbc_lblFechaFinal = new GridBagConstraints();
		gbc_lblFechaFinal.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaFinal.gridx = 2;
		gbc_lblFechaFinal.gridy = 8;
		panel.add(lblFechaFinal, gbc_lblFechaFinal);

		// TODO Cambiar esto por el JCalendar
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 9;
		panel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);

		// TODO Cambiar esto por el JCalendar
		textField = new JTextField();
		textField.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 9;
		panel.add(textField, gbc_textField);
		
		lblResFechaInicio = new JLabel(" ");
		lblResFechaInicio.setForeground(Color.RED);
		GridBagConstraints gbc_lblResFechaInicio = new GridBagConstraints();
		gbc_lblResFechaInicio.insets = new Insets(0, 0, 5, 5);
		gbc_lblResFechaInicio.gridx = 1;
		gbc_lblResFechaInicio.gridy = 10;
		panel.add(lblResFechaInicio, gbc_lblResFechaInicio);
		
		lblResFechaFin = new JLabel(" ");
		lblResFechaFin.setForeground(Color.RED);
		GridBagConstraints gbc_lblResFechaFin = new GridBagConstraints();
		gbc_lblResFechaFin.insets = new Insets(0, 0, 5, 5);
		gbc_lblResFechaFin.gridx = 2;
		gbc_lblResFechaFin.gridy = 10;
		panel.add(lblResFechaFin, gbc_lblResFechaFin);

		JLabel lblModalidad = new JLabel("Modalidad del Evento");
		GridBagConstraints gbc_lblModalidad = new GridBagConstraints();
		gbc_lblModalidad.gridwidth = 2;
		gbc_lblModalidad.insets = new Insets(0, 0, 5, 5);
		gbc_lblModalidad.gridx = 1;
		gbc_lblModalidad.gridy = 11;
		panel.add(lblModalidad, gbc_lblModalidad);
		
		GridBagConstraints gbc_selectModalidad = new GridBagConstraints();
		gbc_selectModalidad.gridwidth = 2;
		gbc_selectModalidad.insets = new Insets(0, 0, 5, 5);
		gbc_selectModalidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectModalidad.gridx = 1;
		gbc_selectModalidad.gridy = 12;
		panel.add(selectModalidad, gbc_selectModalidad);

		JLabel lblItr = new JLabel("ITR");
		GridBagConstraints gbc_lblItr = new GridBagConstraints();
		gbc_lblItr.gridwidth = 2;
		gbc_lblItr.insets = new Insets(0, 0, 5, 5);
		gbc_lblItr.gridx = 1;
		gbc_lblItr.gridy = 13;
		panel.add(lblItr, gbc_lblItr);
		
				GridBagConstraints gbc_selectItr = new GridBagConstraints();
				gbc_selectItr.gridwidth = 2;
				gbc_selectItr.insets = new Insets(0, 0, 5, 5);
				gbc_selectItr.fill = GridBagConstraints.HORIZONTAL;
				gbc_selectItr.gridx = 1;
				gbc_selectItr.gridy = 14;
				panel.add(selectItr, gbc_selectItr);

		JLabel lblLocalizacion = new JLabel("Localización");
		GridBagConstraints gbc_lblLocalizacion = new GridBagConstraints();
		gbc_lblLocalizacion.gridwidth = 2;
		gbc_lblLocalizacion.insets = new Insets(0, 0, 5, 5);
		gbc_lblLocalizacion.gridx = 1;
		gbc_lblLocalizacion.gridy = 15;
		panel.add(lblLocalizacion, gbc_lblLocalizacion);

		inputLocalizacion = new JTextField();
		GridBagConstraints gbc_inputLocalizacion = new GridBagConstraints();
		gbc_inputLocalizacion.gridwidth = 2;
		gbc_inputLocalizacion.insets = new Insets(0, 0, 5, 5);
		gbc_inputLocalizacion.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputLocalizacion.gridx = 1;
		gbc_inputLocalizacion.gridy = 16;
		panel.add(inputLocalizacion, gbc_inputLocalizacion);
		inputLocalizacion.setColumns(10);
		
		lblResLocalizacion = new JLabel(" ");
		lblResLocalizacion.setForeground(Color.RED);
		GridBagConstraints gbc_lblResLocalizacion = new GridBagConstraints();
		gbc_lblResLocalizacion.gridwidth = 2;
		gbc_lblResLocalizacion.insets = new Insets(0, 0, 5, 5);
		gbc_lblResLocalizacion.gridx = 1;
		gbc_lblResLocalizacion.gridy = 17;
		panel.add(lblResLocalizacion, gbc_lblResLocalizacion);

		JButton btnIngresar = new JButton("Ingresar Evento");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ingresar();
			}
		});
		GridBagConstraints gbc_btnIngresar = new GridBagConstraints();
		gbc_btnIngresar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnIngresar.insets = new Insets(0, 0, 0, 5);
		gbc_btnIngresar.gridx = 1;
		gbc_btnIngresar.gridy = 18;
		panel.add(btnIngresar, gbc_btnIngresar);

		JButton btnCancelar = new JButton("Cancelar Ingreso");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelar();
			}
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.gridx = 2;
		gbc_btnCancelar.gridy = 18;
		panel.add(btnCancelar, gbc_btnCancelar);
	}

}