package com.app.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.Analista;
import com.entities.Evento;
import com.entities.Itr;
import com.entities.Usuario;
import com.enumerators.EnumEventoModalidad;
import com.enumerators.EnumEventoTipo;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.services.users.ItrBeanRemote;
import com.toedter.calendar.JDateChooser;
import com.app.controllers.EventoBO;
import com.app.exceptions.TextFieldException;
import com.app.singleton.BeanRemoteManager;
import com.app.singleton.RobotoFont;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.naming.NamingException;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ViewEventoRegistro extends JFrame {

	private JPanel panel;
	private JPanel contentPane;
	private JTextField inputNombre;
	private JTextField inputLocalizacion;
	private JButton btnCreate, btnCancel;
	private JLabel lblResNombre, lblResLocalizacion, lblResFechaFin, lblResFechaInicio, lblTitulo, lblNombre, lblTipo, lblFechaInicio, lblFechaFinal, lblModalidad, lblItr, lblLocalizacion;
	
	private JDateChooser dateInicio = new JDateChooser();
	private JDateChooser dateFin = new JDateChooser();
	
	private ViewEventoMain eventoTable;
	private Evento editEvent;

	private Map<String, JTextField> fieldsMap = new HashMap<>();

	private JComboBox<EnumEventoTipo> selectTipo = new JComboBox<EnumEventoTipo>();
	private JComboBox<EnumEventoModalidad> selectModalidad = new JComboBox<EnumEventoModalidad>();
	private JComboBox<Itr> selectItr = new JComboBox<Itr>();

	private Usuario usuario = new Analista();
	
	private EventoBO bo = new EventoBO(usuario);

	// Este método main después se borra
	// Solo se utiliza en desarrollo
	public static void main(String[] args) {
		FlatLaf.registerCustomDefaultsSource("com.app.themes");
		FlatDarkLaf.setup();
		UIManager.getLookAndFeelDefaults().put("defaultFont", RobotoFont.getNormal());
		UIManager.put(args, args);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewEventoRegistro frame = new ViewEventoRegistro(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	// Manejadores de UI
	
	/**
	 * @wbp.parser.constructor
	 */
	public ViewEventoRegistro(ViewEventoMain eventoTable) {
		this.eventoTable = eventoTable;
		fillComboBox();
		init();
	}
	
	public ViewEventoRegistro(ViewEventoMain eventoTable, Evento evento) {
		this.eventoTable = eventoTable;
		this.editEvent = evento;
		fillComboBox();
		init();
		fillFields();
	}
	
	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		panel = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(panel, BorderLayout.CENTER);
		
		// Labels
		
		lblTitulo = new JLabel((editEvent == null)
				? "Alta de Evento"
				: "Modificación de Evento"
		);
		lblTitulo.setFont(RobotoFont.getTitulo());
		
		lblItr = new JLabel("ITR");
		
		lblLocalizacion = new JLabel("Localización");
		
		lblTipo = new JLabel("Tipo de Evento");
		lblModalidad = new JLabel("Modalidad del Evento");
		
		lblFechaInicio = new JLabel("Inicio");
		lblFechaFinal = new JLabel("Final");
		
		lblNombre = new JLabel("Nombre del Evento");
		
		lblResNombre = new JLabel(" ");
		lblResNombre.setForeground(Color.RED);

		lblResFechaInicio = new JLabel(" ");
		lblResFechaInicio.setForeground(Color.RED);
		
		lblResFechaFin = new JLabel(" ");
		lblResFechaFin.setForeground(Color.RED);
		
		lblResLocalizacion = new JLabel(" ");
		lblResLocalizacion.setForeground(Color.RED);
		
		
		// TextFields & Fields
		
		inputNombre = new JTextField();		
		inputNombre.setColumns(10);		

		inputLocalizacion = new JTextField();
		inputLocalizacion.setColumns(10);

		// Buttons
		
		btnCreate = new JButton((editEvent == null)
				? "Ingresar Evento"
				: "Modificar Evento"
		);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				create();
			}
		});
		
		btnCancel = new JButton("Cancelar");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelar();
			}
		});
		
		setGridBagLayout();
	}
	
	private void setGridBagLayout() {
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);
		
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.gridwidth = 2;
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitulo.gridx = 1;
		gbc_lblTitulo.gridy = 1;
		panel.add(lblTitulo, gbc_lblTitulo);
		
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.gridwidth = 2;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 3;
		panel.add(lblNombre, gbc_lblNombre);
		
		GridBagConstraints gbc_inputNombre = new GridBagConstraints();
		gbc_inputNombre.gridwidth = 2;
		gbc_inputNombre.insets = new Insets(0, 0, 5, 5);
		gbc_inputNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputNombre.gridx = 1;
		gbc_inputNombre.gridy = 4;
		panel.add(inputNombre, gbc_inputNombre);
		
		GridBagConstraints gbc_lblResNombre = new GridBagConstraints();
		gbc_lblResNombre.gridwidth = 2;
		gbc_lblResNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblResNombre.gridx = 1;
		gbc_lblResNombre.gridy = 5;
		panel.add(lblResNombre, gbc_lblResNombre);
		
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
		
		GridBagConstraints gbc_lblFechaInicio = new GridBagConstraints();
		gbc_lblFechaInicio.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaInicio.gridx = 1;
		gbc_lblFechaInicio.gridy = 8;
		panel.add(lblFechaInicio, gbc_lblFechaInicio);
		
		GridBagConstraints gbc_lblFechaFinal = new GridBagConstraints();
		gbc_lblFechaFinal.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaFinal.gridx = 2;
		gbc_lblFechaFinal.gridy = 8;
		panel.add(lblFechaFinal, gbc_lblFechaFinal);
		
		GridBagConstraints gbc_dateInicio = new GridBagConstraints();
		gbc_dateInicio.insets = new Insets(0, 0, 5, 5);
		gbc_dateInicio.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateInicio.gridx = 1;
		gbc_dateInicio.gridy = 9;
		panel.add(dateInicio, gbc_dateInicio);
		
		GridBagConstraints gbc_dateFin = new GridBagConstraints();
		gbc_dateFin.insets = new Insets(0, 0, 5, 5);
		gbc_dateFin.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateFin.gridx = 2;
		gbc_dateFin.gridy = 9;
		panel.add(dateFin, gbc_dateFin);
		
		GridBagConstraints gbc_lblResFechaInicio = new GridBagConstraints();
		gbc_lblResFechaInicio.insets = new Insets(0, 0, 5, 5);
		gbc_lblResFechaInicio.gridx = 1;
		gbc_lblResFechaInicio.gridy = 10;
		panel.add(lblResFechaInicio, gbc_lblResFechaInicio);
		
		GridBagConstraints gbc_lblResFechaFin = new GridBagConstraints();
		gbc_lblResFechaFin.insets = new Insets(0, 0, 5, 5);
		gbc_lblResFechaFin.gridx = 2;
		gbc_lblResFechaFin.gridy = 10;
		panel.add(lblResFechaFin, gbc_lblResFechaFin);
		
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
		
		GridBagConstraints gbc_lblLocalizacion = new GridBagConstraints();
		gbc_lblLocalizacion.gridwidth = 2;
		gbc_lblLocalizacion.insets = new Insets(0, 0, 5, 5);
		gbc_lblLocalizacion.gridx = 1;
		gbc_lblLocalizacion.gridy = 15;
		panel.add(lblLocalizacion, gbc_lblLocalizacion);
		
		GridBagConstraints gbc_inputLocalizacion = new GridBagConstraints();
		gbc_inputLocalizacion.gridwidth = 2;
		gbc_inputLocalizacion.insets = new Insets(0, 0, 5, 5);
		gbc_inputLocalizacion.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputLocalizacion.gridx = 1;
		gbc_inputLocalizacion.gridy = 16;
		panel.add(inputLocalizacion, gbc_inputLocalizacion);
		
		GridBagConstraints gbc_lblResLocalizacion = new GridBagConstraints();
		gbc_lblResLocalizacion.gridwidth = 2;
		gbc_lblResLocalizacion.insets = new Insets(0, 0, 5, 5);
		gbc_lblResLocalizacion.gridx = 1;
		gbc_lblResLocalizacion.gridy = 17;
		panel.add(lblResLocalizacion, gbc_lblResLocalizacion);
		
		GridBagConstraints gbc_btnIngresar = new GridBagConstraints();
		gbc_btnIngresar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnIngresar.insets = new Insets(0, 0, 0, 5);
		gbc_btnIngresar.gridx = 1;
		gbc_btnIngresar.gridy = 18;
		panel.add(btnCreate, gbc_btnIngresar);
		
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.gridx = 2;
		gbc_btnCancelar.gridy = 18;
		panel.add(btnCancel, gbc_btnCancelar);
	}
	
	
	// Funciones sobre el Formulario
	
	private void fillFields() {
		inputNombre.setText(editEvent.getNombre());
		inputLocalizacion.setText(editEvent.getLocalizacion());
		selectItr.setSelectedItem(editEvent.getItr());
		selectModalidad.setSelectedItem(editEvent.getModalidad());
		selectTipo.setSelectedItem(editEvent.getTipo());
	}
	
	private void fillComboBox() {
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
	
	
	// Funciones de DDL
	
	public LocalDateTime convertToLocalDateTime(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDateTime();
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
		
		try {
			LocalDateTime fechaInicio = convertToLocalDateTime(dateInicio.getDate());
			LocalDateTime fechaFin = convertToLocalDateTime(dateFin.getDate());
			bo.validarFechas(fechaInicio, fechaFin);
		} catch (Exception e) {
			result = false;
		}
		
		return result;
	}
	
	private void create() {
		// TODO Realizar la implementación del Ingresar()
		boolean result = true;//validarDatos();
		if (result) {
			try {
				Evento evento = getEventoFromForm();
				if (editEvent == null) {
					bo.create(evento);					
				} else {
					evento.setIdEvento(editEvent.getIdEvento());
					bo.update(evento);
				}
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			
			if (eventoTable != null) eventoTable.refreshTable();					
			
			JOptionPane.showMessageDialog(null, (editEvent == null)
					? "Se insertó el Evento correctamente en el Sistema"
					: "Se modificó el Evento correctamente en el Sistema"
			);
		}
	}
	
	protected void cancelar() {
		// TODO Realizar la implementación del Cancelar()

	}
	
	private Evento getEventoFromForm() throws NullPointerException {
		LocalDateTime fechaInicio = convertToLocalDateTime(dateInicio.getDate());
		LocalDateTime fechaFin = convertToLocalDateTime(dateFin.getDate());
		Evento evento = Evento.builder()
				.nombre(inputNombre.getText().trim())
				.fechaInicio(fechaInicio)
				.fechaFin(fechaFin)
				.itr((Itr) selectItr.getSelectedItem())
				.modalidad((EnumEventoModalidad) selectModalidad.getSelectedItem())
				.tipo((EnumEventoTipo) selectTipo.getSelectedItem())
				.tutores(null)
				.build();
		return evento;
	}
	
}