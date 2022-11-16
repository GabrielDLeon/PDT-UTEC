package com.app.test;

import java.awt.EventQueue;

import javax.naming.NamingException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.app.controllers.UsuarioBO;
import com.app.singleton.BeanRemoteManager;
import com.app.singleton.RobotoFont;
import com.app.views.ViewEventoRegistro;
import com.entities.Evento;
import com.entities.Tutor;
import com.formdev.flatlaf.FlatDarkLaf;
import com.services.eventos.AsistenciaBeanRemote;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JTable;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ViewUsuarioSeleccion extends JFrame {

	private JPanel panel;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	
	private JTable tEvento;
	private DefaultTableModel tModel = new DefaultTableModel();
	
	private JList listTutores;
	private DefaultListModel<Tutor> lModel;

	private JTextField inputSearch;

	private JLabel lblList;
	private JLabel lblBuscador;

	private JButton btnEliminar;
	private JButton btnAdd;
	private JButton btnGuardar;
	
	private List<Tutor> tutores;
	private List<Tutor> asignados = new ArrayList<Tutor>();
	private Set<Tutor> temporal = new HashSet<Tutor>();
	
	private UsuarioBO bo = new UsuarioBO();
	
	// Esto después se tiene que borrar
	// En desarrollo todavía se utiliza
	public static void main(String[] args) {
		FlatDarkLaf.setup();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewUsuarioSeleccion frame = new ViewUsuarioSeleccion(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public ViewUsuarioSeleccion(List<Tutor> lista) {
		this.asignados = lista;
		this.temporal = new HashSet<Tutor>(asignados);
		view();
		setup();
	}
	
	private void setup() {
		tutores = bo.getAllTutores();
		refreshTable();
		refreshList();
		setGridBagLayout();
	}

	
	// Funciones sobre el Tablero
	
	private void refreshTable() {
		tModel.setRowCount(0);
		try {
			for (Tutor tutor : tutores) {
				Object[] row = new Object[1];
				row[0] = tutor;
				tModel.addRow(row);
			}
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	private void refreshList() {
		try {
			lModel.clear();
			for (Tutor tutor : temporal) {
				lModel.addElement(tutor);
			}			
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public Tutor getTutorFromTable() {
		int row = tEvento.getSelectedRow();
		Tutor tutor = (Tutor) tEvento.getValueAt(row, 0);
//		tutor = bo.findTutor(tutor.getIdUsuario());
		return tutor;
	}
	
	
	// Funciones

	private void addToList() {
		Tutor tutor = getTutorFromTable();
		temporal.add(tutor);
		refreshList();
	}
	
	private void saveList() {
		asignados.clear();
		asignados.addAll(temporal);
	}
	
	private void deleteFromList() {
		Object result = listTutores.getSelectedValue();
		temporal.remove(result);
		refreshList();
	}

	public List<Tutor> getTutores() {
		return asignados;
	}
	

	// Manejadores de UI
	
	private void view() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 601);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		lblBuscador = new JLabel("Lista de Tutores disponibles");
		lblBuscador.setFont(RobotoFont.getTitulo());
		
		scrollPane = new JScrollPane();
		
		tEvento = new JTable(tModel);
		tEvento.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tEvento.setFillsViewportHeight(true);
		scrollPane.setViewportView(tEvento);
		
		lModel = new DefaultListModel<>();
		
		inputSearch = new JTextField();
		
		scrollPane_1 = new JScrollPane();
		
		lblList = new JLabel("Lista de Responsables");
		lblList.setFont(RobotoFont.getTitulo());
		
		listTutores = new JList(lModel);
		scrollPane_1.setViewportView(listTutores);
		
		btnAdd = new JButton("Agregar a la Lista");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addToList();
			}
		});
		
		btnGuardar = new JButton("Guardar Cambios");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveList();
			}
		});
		btnEliminar = new JButton("Eliminar Convocado");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteFromList();
			}
		});
		
		tModel.addColumn("Docente");
	}
	
	private void setGridBagLayout() {
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 484, 484, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		GridBagConstraints gbc_lblBuscador = new GridBagConstraints();
		gbc_lblBuscador.gridwidth = 2;
		gbc_lblBuscador.insets = new Insets(0, 0, 5, 5);
		gbc_lblBuscador.gridx = 1;
		gbc_lblBuscador.gridy = 1;
		panel.add(lblBuscador, gbc_lblBuscador);
		
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
		gbc_btnAdd.gridwidth = 2;
		gbc_btnAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdd.gridx = 1;
		gbc_btnAdd.gridy = 4;
		panel.add(btnAdd, gbc_btnAdd);
		
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
		
		GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
		gbc_btnGuardar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnGuardar.insets = new Insets(0, 0, 5, 5);
		gbc_btnGuardar.gridx = 1;
		gbc_btnGuardar.gridy = 9;
		panel.add(btnGuardar, gbc_btnGuardar);
		
		GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
		gbc_btnEliminar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEliminar.insets = new Insets(0, 0, 5, 5);
		gbc_btnEliminar.gridx = 2;
		gbc_btnEliminar.gridy = 9;
		panel.add(btnEliminar, gbc_btnEliminar);
	}
	
}
