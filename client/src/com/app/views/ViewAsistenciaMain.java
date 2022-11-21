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
import com.app.singleton.RobotoFont;
import com.entities.Analista;
import com.entities.Asistencia;
import com.entities.Evento;
import com.entities.Usuario;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ViewAsistenciaMain extends JFrame {

	private JPanel panel;
	private JPanel contentPane;
	
	private JScrollPane scrollPane;
	
	private JTable tEvento;
	private JButton btnEliminar;
	private JButton btnGuardar;
	private DefaultTableModel model = new DefaultTableModel();

	private JComboBox<EnumAsistenciaEstado> selectEstado;
	private List<Asistencia> convocados;
	
	private Evento evento;
	
	private AsistenciaBO bo = new AsistenciaBO();
	private JLabel lblTitulo;
	private JLabel lblEvento;
	private JButton btnReiniciar;
	
	// Esto después se tiene que borrar
	// En desarrollo todavía se utiliza
	public static void main(String[] args) {
		FlatDarkLaf.setup();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAsistenciaMain frame = new ViewAsistenciaMain(BeanRemoteManager.getBeanEvento().findById(1958L));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void setup() {
		System.out.println("Evento: " + evento.getNombre());
		setGridBagLayout();
		restore();
	}
	
	// Funciones sobre el Tablero
	
	private void refreshTable() {
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
	
	private void saveChanges() {
		evento.setAsistencias(convocados);
//		String mensaje = bo.update(evento);
//		JOptionPane.showMessageDialog(null, mensaje);
	}
	
	private void restore() {
		convocados = bo.findByEvento(evento.getIdEvento());
		refreshTable();
	}
	
	private void delete() {
		int row = tEvento.getSelectedRow();
		convocados.remove(row);
		refreshTable();
	}
	
	
	// Manejadores de UI
	
	public ViewAsistenciaMain(Evento evento) {
		// Para crear la ventana de Asistencia se le debe pasar un evento
		// En base al evento que le pasemos será el listado que mostrará
		this.evento = evento;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 606, 605);
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
		
		/*
		TableColumn estadoColumn = tEvento.getColumnModel().getColumn(1);
		selectEstado = new JComboBox<EnumAsistenciaEstado>(EnumAsistenciaEstado.values());
		estadoColumn.setCellEditor(new DefaultCellEditor(selectEstado));
		*/
		setup();
	}
	
	private void setGridBagLayout() {
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{10, 100, 100, 0, 10, 0};
		gbl_panel.rowHeights = new int[]{12, 0, 0, 12, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		lblTitulo = new JLabel("Asistencias del Evento");
		lblTitulo.setFont(RobotoFont.getTitulo());
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.gridwidth = 3;
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitulo.gridx = 1;
		gbc_lblTitulo.gridy = 1;
		panel.add(lblTitulo, gbc_lblTitulo);
		
		lblEvento = new JLabel("Nombre del Evento");
		lblEvento.setFont(RobotoFont.getSubTitulo());
		GridBagConstraints gbc_lblEvento = new GridBagConstraints();
		gbc_lblEvento.gridwidth = 3;
		gbc_lblEvento.insets = new Insets(0, 0, 5, 5);
		gbc_lblEvento.gridx = 1;
		gbc_lblEvento.gridy = 2;
		panel.add(lblEvento, gbc_lblEvento);
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 4;
		panel.add(scrollPane, gbc_scrollPane);
		
		btnGuardar = new JButton("Guardar Cambios");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveChanges();
			}
		});
		
		GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
		gbc_btnGuardar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnGuardar.insets = new Insets(0, 0, 0, 5);
		gbc_btnGuardar.gridx = 1;
		gbc_btnGuardar.gridy = 5;
		panel.add(btnGuardar, gbc_btnGuardar);
		
		btnEliminar = new JButton("Eliminar Convocado");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		
		btnReiniciar = new JButton("Reiniciar Convocados");
		btnReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restore();
			}
		});
		GridBagConstraints gbc_btnReiniciar = new GridBagConstraints();
		gbc_btnReiniciar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnReiniciar.insets = new Insets(0, 0, 0, 5);
		gbc_btnReiniciar.gridx = 2;
		gbc_btnReiniciar.gridy = 5;
		panel.add(btnReiniciar, gbc_btnReiniciar);
		
		GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
		gbc_btnEliminar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEliminar.insets = new Insets(0, 0, 0, 5);
		gbc_btnEliminar.gridx = 3;
		gbc_btnEliminar.gridy = 5;
		panel.add(btnEliminar, gbc_btnEliminar);
	}
	
}
