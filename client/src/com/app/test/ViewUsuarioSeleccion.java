package com.app.test;

import java.awt.EventQueue;

import javax.naming.NamingException;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.app.controllers.AsistenciaBO;
import com.app.singleton.BeanRemoteManager;
import com.app.singleton.RobotoFont;
import com.entities.Asistencia;
import com.entities.Estudiante;
import com.entities.Evento;
import com.enumerators.EnumAsistenciaEstado;
import com.formdev.flatlaf.FlatDarkLaf;
import com.services.eventos.AsistenciaBeanRemote;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;

import javax.swing.JTable;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ViewUsuarioSeleccion extends JFrame {

	private static AsistenciaBeanRemote bean;

	private JPanel panel;
	private JPanel contentPane;
	
	private JScrollPane scrollPane;
	
	private JTable tEvento;
	private JButton btnEliminar;
	private JButton btnAdd;
	private JButton btnGuardar;
	private DefaultTableModel tModel = new DefaultTableModel();
	private DefaultListModel<Estudiante> lModel;

	private JList listEstudiantes;
	private JLabel lblList;
	private JLabel lblNewLabel;
	private JTextField inputSearch;
	private JScrollPane scrollPane_1;
	
	private Evento evento;

	private List<Estudiante> estudiantes;
	private List<Asistencia> convocados;
	
	
	private AsistenciaBO bo = new AsistenciaBO();
	
	// Esto después se tiene que borrar
	// En desarrollo todavía se utiliza
	public static void main(String[] args) {
		FlatDarkLaf.setup();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bean = BeanRemoteManager.getBeanAsistencia();
					ViewUsuarioSeleccion frame = new ViewUsuarioSeleccion(BeanRemoteManager.getBeanEvento().findById(1L));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// Manejadores de UI
	
	public ViewUsuarioSeleccion(Evento evento) {
		// Para crear la ventana de Asistencia se le debe pasar un evento
		// En base al evento que le pasemos será el listado que mostrará
		this.evento = evento;
		
		try {
			bean = BeanRemoteManager.getBeanAsistencia();
		} catch (NamingException e1) {
			e1.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 601);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		lblNewLabel = new JLabel("Buscador de Estudiantes");
		lblNewLabel.setFont(RobotoFont.getTitulo());
		
		scrollPane = new JScrollPane();

		tModel.addColumn("Estudiante");
		tModel.addColumn("Documento");
		tModel.addColumn("ITR");
		tModel.addColumn("Generación");
		
		tEvento = new JTable(tModel);
		tEvento.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tEvento.setFillsViewportHeight(true);
		scrollPane.setViewportView(tEvento);
		
		lModel = new DefaultListModel<>();
		
		inputSearch = new JTextField();
		
		scrollPane_1 = new JScrollPane();
		
		lblList = new JLabel("Lista de Convocados");
		lblList.setFont(RobotoFont.getTitulo());
		
		listEstudiantes = new JList(lModel);
		scrollPane_1.setViewportView(listEstudiantes);
		
		btnEliminar = new JButton("Eliminar Convocado");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnAdd = new JButton("Agregar a la Lista");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEstudiante();
			}
		});
		
		btnGuardar = new JButton("Guardar Cambios");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarDatos();
			}
		});
		
		setGridBagLayout();
		cargarTabla();
		addEstudiante();
	}
	
	protected void addEstudiante() {
		convocados = bean.findByEvento(evento.getIdEvento());
		lModel.clear();
		for (Asistencia asistencia : convocados) {
			lModel.addElement(asistencia.getEstudiante());
		}
	}

	private void setGridBagLayout() {
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 484, 484, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		GridBagConstraints gbc_inputSearch = new GridBagConstraints();
		gbc_inputSearch.gridwidth = 2;
		gbc_inputSearch.insets = new Insets(0, 0, 5, 5);
		gbc_inputSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputSearch.gridx = 1;
		gbc_inputSearch.gridy = 2;
		panel.add(inputSearch, gbc_inputSearch);
		inputSearch.setColumns(10);
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		panel.add(scrollPane, gbc_scrollPane);
		
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdd.gridx = 1;
		gbc_btnAdd.gridy = 4;
		panel.add(btnAdd, gbc_btnAdd);
		
		GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
		gbc_btnGuardar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnGuardar.insets = new Insets(0, 0, 5, 5);
		gbc_btnGuardar.gridx = 2;
		gbc_btnGuardar.gridy = 4;
		panel.add(btnGuardar, gbc_btnGuardar);
		
		GridBagConstraints gbc_lblList = new GridBagConstraints();
		gbc_lblList.gridwidth = 2;
		gbc_lblList.insets = new Insets(0, 0, 5, 5);
		gbc_lblList.gridx = 1;
		gbc_lblList.gridy = 6;
		panel.add(lblList, gbc_lblList);
		
		
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridheight = 2;
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 7;
		panel.add(scrollPane_1, gbc_scrollPane_1);
		
		GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
		gbc_btnEliminar.gridwidth = 2;
		gbc_btnEliminar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEliminar.insets = new Insets(0, 0, 5, 5);
		gbc_btnEliminar.gridx = 1;
		gbc_btnEliminar.gridy = 9;
		panel.add(btnEliminar, gbc_btnEliminar);
	}
	

	// Funciones sobre el Tablero
	
	protected void cargarTabla() {
		
	}
	
	
	// Funciones de DDL

	
}
