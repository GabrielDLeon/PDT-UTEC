package com.app.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import com.app.controllers.AsistenciaDAO;
import com.app.controllers.UsuarioDAO;
import com.app.singleton.RobotoFont;
import com.app.views.panels.PanelUsuarioFiltro;
import com.dto.UsuarioBusquedaVO;
import com.entities.Asistencia;
import com.entities.Estudiante;
import com.entities.Evento;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.awt.event.ActionEvent;

public class ViewAsistenciaEstudiantes extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private CustomTable table;
	private PanelUsuarioFiltro panelUsuarioFiltro = new PanelUsuarioFiltro(new Estudiante());
	
	private JPanel panel;
	private JScrollPane scrollPane, scrollPane_1;
	private JLabel lblTitulo;
	private JButton btnClean, btnInsert, btnDelete, btnSearch, btnConvocar;
	
	private Map<Long, Estudiante> map = new HashMap<Long, Estudiante>();
	
	private JList<Estudiante> listaSeleccionados = new JList<Estudiante>();
	private DefaultListModel<Estudiante> lModel = new DefaultListModel<Estudiante>();

	private Evento evento = new Evento();
	
	private AsistenciaDAO dao = new AsistenciaDAO();
	
	private ViewAsistenciaMain main;
	
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
		setGridBagLayout();
		refreshTable(null);
		refreshList();
	}

	protected void refreshTable(UsuarioBusquedaVO vo) {
		table.model.setRowCount(0);
		UsuarioDAO dao = new UsuarioDAO();
		List<Estudiante> estudiantes = new ArrayList<Estudiante>();
		estudiantes = (vo != null) ? dao.searchEstudiantes(vo) : dao.getAllEstudiantes();
		for (Estudiante estudiante : estudiantes) {
			Object[] row = new Object[1];
			row[0] = estudiante;
//			row[1] = estudiante.getDocumento();
//			row[2] = estudiante.getNombre1() + " " + estudiante.getApellido1();
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
		if (row >= 0) {
			Estudiante estudiante = (Estudiante) table.getValueAt(row, 0);
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

	protected void save() {
		List<Estudiante> list = new ArrayList<Estudiante>(map.values());
		String mensaje = "";
		try {
			dao.create(evento, list);
			mensaje = "Estudiantes registrados al Evento correctamente";
		} catch (Exception e) {
			// TODO: handle exception
			mensaje = "Error al intentar registrar los Estudiantes:\n" + e.getMessage();
		}
		main.refreshTable();
		JOptionPane.showMessageDialog(null, mensaje);
	}
	
	protected void clean() {
		panelUsuarioFiltro.cleanForm();
		refreshTable(null);
	}

	public void setMain(ViewAsistenciaMain main) {
		this.main = main;
	}
	
	public Set<Estudiante> getSeleccionados() {
		return new HashSet<Estudiante>(map.values());
	}

	public ViewAsistenciaEstudiantes(List<Asistencia> convocados, Evento evento) {
		if (convocados != null) {
			for (Asistencia asistencia : convocados) {
				Estudiante estudiante = asistencia.getEstudiante();
				map.put(estudiante.getIdUsuario(), estudiante);
			}
		}

		this.evento = evento;

		table = new CustomTable();
		table.setColumns("Estudiante");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 729, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		lblTitulo = new JLabel("Registrar Estudiantes");
		lblTitulo.setFont(RobotoFont.getTitulo());
		
		panel = new JPanel();
		
		scrollPane = new JScrollPane();
		
		scrollPane.setViewportView(table);
		
		scrollPane_1 = new JScrollPane();
		
		listaSeleccionados.setModel(lModel);
		scrollPane_1.setViewportView(listaSeleccionados);
		
		btnInsert = new JButton("Añadir Estudiante");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insert();
			}
		});
		
		btnClean = new JButton("Limpiar");
		btnClean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clean();
			}
		});
		
		btnDelete = new JButton("Eliminar Estudiante");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});

		btnSearch = new JButton("Buscar");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioBusquedaVO vo = panelUsuarioFiltro.search();
				refreshTable(vo);
			}
		});
		
		btnConvocar = new JButton("Convocar Estudiantes Seleccionados");
		btnConvocar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
	
		setup();
	}
	
	private void setGridBagLayout() {
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitulo.gridx = 0;
		gbc_lblTitulo.gridy = 0;
		contentPane.add(lblTitulo, gbc_lblTitulo);
		
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		contentPane.add(panel, gbc_panel);
		
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 5.0, 5.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		GridBagConstraints gbc_panelUsuarioFiltro = new GridBagConstraints();
		gbc_panelUsuarioFiltro.fill = GridBagConstraints.BOTH;
		gbc_panelUsuarioFiltro.insets = new Insets(0, 0, 5, 5);
		gbc_panelUsuarioFiltro.gridx = 0;
		gbc_panelUsuarioFiltro.gridy = 0;
		panel.add(panelUsuarioFiltro, gbc_panelUsuarioFiltro);
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 0;
		panel.add(scrollPane, gbc_scrollPane);

		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.gridx = 2;
		gbc_scrollPane_1.gridy = 0;
		panel.add(scrollPane_1, gbc_scrollPane_1);
		
		GridBagConstraints gbc_btnClean = new GridBagConstraints();
		gbc_btnClean.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnClean.insets = new Insets(0, 0, 5, 5);
		gbc_btnClean.gridx = 0;
		gbc_btnClean.gridy = 1;
		panel.add(btnClean, gbc_btnClean);
		
		GridBagConstraints gbc_btnInsert = new GridBagConstraints();
		gbc_btnInsert.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnInsert.insets = new Insets(0, 0, 5, 5);
		gbc_btnInsert.gridx = 1;
		gbc_btnInsert.gridy = 1;
		panel.add(btnInsert, gbc_btnInsert);
		
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(0, 0, 5, 0);
		gbc_btnDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDelete.gridx = 2;
		gbc_btnDelete.gridy = 1;
		panel.add(btnDelete, gbc_btnDelete);
		
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSearch.insets = new Insets(0, 0, 0, 5);
		gbc_btnSearch.gridx = 0;
		gbc_btnSearch.gridy = 2;
		panel.add(btnSearch, gbc_btnSearch);
		
		GridBagConstraints gbc_btnConvocar = new GridBagConstraints();
		gbc_btnConvocar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnConvocar.gridwidth = 2;
		gbc_btnConvocar.gridx = 1;
		gbc_btnConvocar.gridy = 2;
		panel.add(btnConvocar, gbc_btnConvocar);
	}
}
