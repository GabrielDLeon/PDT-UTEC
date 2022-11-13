package com.app.views;

import java.awt.EventQueue;

import javax.naming.NamingException;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.app.controllers.AsistenciaBO;
import com.app.singleton.BeanRemoteManager;
import com.entities.Asistencia;
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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class ViewAsistenciaMain extends JFrame {

	private static AsistenciaBeanRemote bean;

	private JPanel panel;
	private JPanel contentPane;
	
	private JScrollPane scrollPane;
	
	private JTable tEvento;
	private JButton btnEliminar;
	private JButton btnActualizar;
	private JButton btnGuardar;
	private DefaultTableModel model = new DefaultTableModel();

	private JComboBox<EnumAsistenciaEstado> selectEstado;
	private List<Asistencia> convocados;
	
	private Evento evento;
	
	private AsistenciaBO bo = new AsistenciaBO();
	
	// Esto después se tiene que borrar
	// En desarrollo todavía se utiliza
	public static void main(String[] args) {
		FlatDarkLaf.setup();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bean = BeanRemoteManager.getBeanAsistencia();
					ViewAsistenciaMain frame = new ViewAsistenciaMain(BeanRemoteManager.getBeanEvento().findById(1L));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// Manejadores de UI
	
	public ViewAsistenciaMain(Evento evento) {
		// Para crear la ventana de Asistencia se le debe pasar un evento
		// En base al evento que le pasemos será el listado que mostrará
		this.evento = evento;
		
		try {
			bean = BeanRemoteManager.getBeanAsistencia();
		} catch (NamingException e1) {
			e1.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		scrollPane = new JScrollPane();

		model.addColumn("Estudiante");
		model.addColumn("Estado");
		model.addColumn("Calificación");
		
		tEvento = new JTable(model);
		tEvento.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tEvento.setFillsViewportHeight(true);
		scrollPane.setViewportView(tEvento);
		
		btnEliminar = new JButton("Eliminar Convocado");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarDatos();
			}
		});
		
		btnGuardar = new JButton("Guardar Cambios");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveChanges();
			}
		});
		
		TableColumn estadoColumn = tEvento.getColumnModel().getColumn(1);
		selectEstado = new JComboBox<EnumAsistenciaEstado>(EnumAsistenciaEstado.values());
		estadoColumn.setCellEditor(new DefaultCellEditor(selectEstado));
		
		setGridBagLayout();
		actualizarDatos();
	}
	
	private void setGridBagLayout() {
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 484, 484, 484, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		panel.add(scrollPane, gbc_scrollPane);
		
		GridBagConstraints gbc_btnActualizar = new GridBagConstraints();
		gbc_btnActualizar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnActualizar.insets = new Insets(0, 0, 0, 5);
		gbc_btnActualizar.gridx = 1;
		gbc_btnActualizar.gridy = 2;
		panel.add(btnActualizar, gbc_btnActualizar);
		
		GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
		gbc_btnEliminar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEliminar.insets = new Insets(0, 0, 0, 5);
		gbc_btnEliminar.gridx = 2;
		gbc_btnEliminar.gridy = 2;
		panel.add(btnEliminar, gbc_btnEliminar);
		
		GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
		gbc_btnGuardar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnGuardar.insets = new Insets(0, 0, 0, 5);
		gbc_btnGuardar.gridx = 3;
		gbc_btnGuardar.gridy = 2;
		panel.add(btnGuardar, gbc_btnGuardar);
	}
	
	
	// Funciones sobre el Tablero
	
	private void actualizarDatos() {
		if (evento != null) {
			convocados = bean.findByEvento(evento.getIdEvento());
			cargarTabla();
		}
	}
	
	protected void cargarTabla() {
		model.setRowCount(0);
		for (Asistencia a : convocados) {
			Object[] row = new Object[3];
			row[0] = a.getEstudiante();
			row[1] = a.getEstado();
			row[2] = a.getCalificacion();
			model.addRow(row);
		}
	}
	
	
	// Funciones de DDL
	
	protected void saveChanges() {
		String mensaje = bo.update(convocados);
		System.out.println(mensaje);
	}
	
	private void delete() {
		int row = tEvento.getSelectedRow();
		convocados.remove(row);
		cargarTabla();
	}
	
}
