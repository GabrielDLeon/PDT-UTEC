package com.app.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import com.app.controllers.UsuarioDAO;
import com.app.singleton.RobotoFont;
import com.app.views.panels.PanelUsuarioFiltro;
import com.entities.Estudiante;
import com.entities.Evento;
import com.entities.Usuario;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.awt.event.ActionEvent;

public class ViewAsistenciaEstudiantes extends JInternalFrame {

	private JPanel contentPane;
	private CustomTable table;
	
	private Map<Long, Estudiante> map = new HashMap<Long, Estudiante>();
	
	private JList<Estudiante> listaSeleccionados = new JList<Estudiante>();
	private DefaultListModel<Estudiante> lModel = new DefaultListModel<Estudiante>();

	private Evento evento = new Evento();
	
	// TODO: Eliminar el método Main
	public static void main(String[] args) {
		FlatDarkLaf.setup();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAsistenciaEstudiantes frame = new ViewAsistenciaEstudiantes(null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	protected void setup() {
		table = new CustomTable();
		table.setColumns("Estudiante", "Documento", "Nombre");
		refreshTable();
		refreshList();
	}


	protected void refreshTable() {
		table.model.setRowCount(0);
		UsuarioDAO dao = new UsuarioDAO();
		List<Estudiante> estudiantes = dao.getAllEstudiantes();
		for (Estudiante estudiante : estudiantes) {
			Object[] row = new Object[3];
			row[0] = estudiante;
			row[1] = estudiante.getDocumento();
			row[2] = estudiante.getNombre1() + " " + estudiante.getApellido1();
			table.model.addRow(row);
		}
	}
	
	protected void refreshList() {
		lModel.clear();
		map.forEach((key, estudiante) -> {
			lModel.addElement(estudiante);
		});
	}
	
	protected void insert() {
		int row = table.getSelectedRow();
		System.out.println(row);
		if (row >= 0) {
			Estudiante estudiante = (Estudiante) table.getValueAt(row, 0);
			System.out.println(estudiante);
			map.put(estudiante.getIdUsuario(), estudiante);
			refreshList();
		}
	}

	protected void delete() {
		Estudiante estudiante = listaSeleccionados.getSelectedValue();
		if (estudiante != null)
			map.remove(estudiante.getIdUsuario());
		refreshList();
	}

	
	public Set<Estudiante> getSeleccionados() {
		return new HashSet<Estudiante>(map.values());
	}

	
	public ViewAsistenciaEstudiantes(Set<Estudiante> seleccionados, Evento evento) {
		if (seleccionados != null)
			for (Estudiante estudiante : seleccionados) {
				map.put(estudiante.getIdUsuario(), estudiante);
			}
		this.evento = evento;
		
		setup();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 729, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblTitulo = new JLabel("Registrar Estudiantes");
		lblTitulo.setFont(RobotoFont.getTitulo());
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitulo.gridx = 0;
		gbc_lblTitulo.gridy = 0;
		contentPane.add(lblTitulo, gbc_lblTitulo);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 5.0, 5.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		PanelUsuarioFiltro panelUsuarioFiltro = new PanelUsuarioFiltro(new Estudiante());
		GridBagConstraints gbc_panelUsuarioFiltro = new GridBagConstraints();
		gbc_panelUsuarioFiltro.fill = GridBagConstraints.BOTH;
		gbc_panelUsuarioFiltro.insets = new Insets(0, 0, 5, 5);
		gbc_panelUsuarioFiltro.gridx = 0;
		gbc_panelUsuarioFiltro.gridy = 0;
		panel.add(panelUsuarioFiltro, gbc_panelUsuarioFiltro);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 0;
		panel.add(scrollPane, gbc_scrollPane);
		
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.gridx = 2;
		gbc_scrollPane_1.gridy = 0;
		panel.add(scrollPane_1, gbc_scrollPane_1);
		
		listaSeleccionados.setModel(lModel);
		scrollPane_1.setViewportView(listaSeleccionados);
		
		JButton btnSearch = new JButton("Buscar");
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSearch.insets = new Insets(0, 0, 0, 5);
		gbc_btnSearch.gridx = 0;
		gbc_btnSearch.gridy = 1;
		panel.add(btnSearch, gbc_btnSearch);
		
		JButton btnInsert = new JButton("Añadir Estudiante");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insert();
			}
		});
		GridBagConstraints gbc_btnInsert = new GridBagConstraints();
		gbc_btnInsert.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnInsert.insets = new Insets(0, 0, 0, 5);
		gbc_btnInsert.gridx = 1;
		gbc_btnInsert.gridy = 1;
		panel.add(btnInsert, gbc_btnInsert);
		
		JButton btnDelete = new JButton("Eliminar Estudiante");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDelete.gridx = 2;
		gbc_btnDelete.gridy = 1;
		panel.add(btnDelete, gbc_btnDelete);
	}
	
}
