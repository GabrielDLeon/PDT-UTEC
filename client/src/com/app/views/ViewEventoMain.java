package com.app.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.app.controllers.EventoBO;
import com.app.singleton.BeanRemoteManager;
import com.app.singleton.RobotoFont;
import com.dto.EventoBusquedaVO;
import com.entities.Analista;
import com.entities.Evento;
import com.entities.Itr;
import com.entities.Usuario;
import com.enumerators.EnumEventoEstado;
import com.enumerators.EnumEventoModalidad;
import com.enumerators.EnumEventoTipo;
import com.formdev.flatlaf.FlatDarkLaf;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;

import javax.swing.JTable;
import javax.naming.NamingException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ViewEventoMain extends JFrame {
	
	private DefaultTableModel model = new DefaultTableModel() {
		@Override
	    public boolean isCellEditable(int row, int column) {
	       return false;
	    }
	};

	private JPanel panel = new JPanel();
	private JPanel contentPane;
	private JPanel panel_1;
	
	private JTable tEvento;
	private JScrollPane scrollPane = new JScrollPane();
	
	private JButton btnConvocatoria;
	private JButton btnCreate;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnBuscar;
	
	private JLabel lblTipo = new JLabel("Tipo de Evento");
	private JLabel lblModalidad = new JLabel("Modalidad");
	private JLabel lblItr = new JLabel("ITR");
	private JLabel lblEstado = new JLabel("Estado del Evento");
	private JLabel lblFiltro = new JLabel("Filtros de búsqueda");
	private JLabel lblBuscador =  new JLabel("Nombre");

	private JTextField inputBuscador = new JTextField();

	private JComboBox<Itr> selectItr = new JComboBox<Itr>();
	private JComboBox<EnumEventoTipo> selectTipo = new JComboBox<EnumEventoTipo>();
	private JComboBox<EnumEventoModalidad> selectModalidad = new JComboBox<EnumEventoModalidad>();
	private JComboBox<EnumEventoEstado> selectEstado= new JComboBox<EnumEventoEstado>();
	
	private Usuario user = new Analista();
	
	private EventoBO bo = new EventoBO(user);
	private JButton btnClean;
	
	// Este método main después se borra
	// Solo se utiliza en desarrollo
	public static void main(String[] args) {
		FlatDarkLaf.setup();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewEventoMain frame = new ViewEventoMain();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	// Manejadores de UI
	
	public ViewEventoMain() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 606);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(panel, BorderLayout.CENTER);
		
		lblFiltro.setFont(RobotoFont.getSubTitulo());
		panel_1 = new JPanel();
		
		model.addColumn("ID");
		model.addColumn("Título");
		model.addColumn("Tipo");
		model.addColumn("Modalidad");
		model.addColumn("Inicio");
		model.addColumn("Fin");
		model.addColumn("ITR");
		
		tEvento = new JTable(model);
		tEvento.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tEvento.setFillsViewportHeight(true);
		scrollPane.setViewportView(tEvento);
		tEvento.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent lse) {
                if (!lse.getValueIsAdjusting()) {
                    int row = tEvento.getSelectedRow();
                    boolean state = (row >= 0) ? true : false;
                    btnConvocatoria.setEnabled(state);
                    btnUpdate.setEnabled(state);
                    btnDelete.setEnabled(state);
                }
            }
        });
		tEvento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyChar();
				if (key == 27) {
					tEvento.clearSelection();
				}
			}
		});
		
		btnConvocatoria = new JButton("Convocatoria");
		btnConvocatoria.setEnabled(false);
		btnConvocatoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showConvocatoria();
			}
		});
		
		btnCreate = new JButton("Crear Evento");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				create();
			}
		});
		
		btnUpdate = new JButton("Modificar Evento");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		btnUpdate.setEnabled(false);
		
		btnDelete = new JButton("Eliminar Evento");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		btnDelete.setEnabled(false);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		
		btnClean = new JButton("Limpiar Filtros");
		btnClean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cleanFilters();
			}
		});
				
		setupComboBox();
		setGridBagLayout();
		setVisible(true);
		refreshTable();
	}

	private void setGridBagLayout() {
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 50, 50, 50, 0, 200, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, 2.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		panel.add(scrollPane, gbc_scrollPane);
		
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 5;
		gbc_panel_1.gridy = 1;
		panel.add(panel_1, gbc_panel_1);
		
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{20, 0, 20, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 5.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		GridBagConstraints gbc_lblFiltro = new GridBagConstraints();
		gbc_lblFiltro.insets = new Insets(0, 0, 5, 5);
		gbc_lblFiltro.gridx = 1;
		gbc_lblFiltro.gridy = 1;
		panel_1.add(lblFiltro, gbc_lblFiltro);
		
		GridBagConstraints gbc_lblBuscador = new GridBagConstraints();
		gbc_lblBuscador.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBuscador.insets = new Insets(0, 0, 5, 5);
		gbc_lblBuscador.gridx = 1;
		gbc_lblBuscador.gridy = 3;
		panel_1.add(lblBuscador, gbc_lblBuscador);
		
		GridBagConstraints gbc_inputBuscador = new GridBagConstraints();
		gbc_inputBuscador.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputBuscador.insets = new Insets(0, 0, 5, 5);
		gbc_inputBuscador.gridx = 1;
		gbc_inputBuscador.gridy = 4;
		panel_1.add(inputBuscador, gbc_inputBuscador);
		inputBuscador.setColumns(10);
		
		GridBagConstraints gbc_lblTipo = new GridBagConstraints();
		gbc_lblTipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTipo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipo.gridx = 1;
		gbc_lblTipo.gridy = 5;
		panel_1.add(lblTipo, gbc_lblTipo);
		
		GridBagConstraints gbc_selectTipo = new GridBagConstraints();
		gbc_selectTipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectTipo.insets = new Insets(0, 0, 5, 5);
		gbc_selectTipo.gridx = 1;
		gbc_selectTipo.gridy = 6;
		panel_1.add(selectTipo, gbc_selectTipo);
		
		GridBagConstraints gbc_lblModalidad = new GridBagConstraints();
		gbc_lblModalidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblModalidad.insets = new Insets(0, 0, 5, 5);
		gbc_lblModalidad.gridx = 1;
		gbc_lblModalidad.gridy = 7;
		panel_1.add(lblModalidad, gbc_lblModalidad);
		
		GridBagConstraints gbc_selectModalidad = new GridBagConstraints();
		gbc_selectModalidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectModalidad.insets = new Insets(0, 0, 5, 5);
		gbc_selectModalidad.gridx = 1;
		gbc_selectModalidad.gridy = 8;
		panel_1.add(selectModalidad, gbc_selectModalidad);
		
		GridBagConstraints gbc_lblItr = new GridBagConstraints();
		gbc_lblItr.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblItr.insets = new Insets(0, 0, 5, 5);
		gbc_lblItr.gridx = 1;
		gbc_lblItr.gridy = 9;
		panel_1.add(lblItr, gbc_lblItr);

		GridBagConstraints gbc_selectItr = new GridBagConstraints();
		gbc_selectItr.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectItr.insets = new Insets(0, 0, 5, 5);
		gbc_selectItr.gridx = 1;
		gbc_selectItr.gridy = 10;
		panel_1.add(selectItr, gbc_selectItr);
		
		GridBagConstraints gbc_lblEstado = new GridBagConstraints();
		gbc_lblEstado.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEstado.insets = new Insets(0, 0, 5, 5);
		gbc_lblEstado.gridx = 1;
		gbc_lblEstado.gridy = 11;
		panel_1.add(lblEstado, gbc_lblEstado);
		
		GridBagConstraints gbc_selectEstado = new GridBagConstraints();
		gbc_selectEstado.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectEstado.insets = new Insets(0, 0, 5, 5);
		gbc_selectEstado.gridx = 1;
		gbc_selectEstado.gridy = 12;
		panel_1.add(selectEstado, gbc_selectEstado);
		
		GridBagConstraints gbc_btnClean = new GridBagConstraints();
		gbc_btnClean.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnClean.insets = new Insets(0, 0, 5, 5);
		gbc_btnClean.gridx = 1;
		gbc_btnClean.gridy = 14;
		panel_1.add(btnClean, gbc_btnClean);
		
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBuscar.insets = new Insets(0, 0, 0, 5);
		gbc_btnBuscar.gridx = 1;
		gbc_btnBuscar.gridy = 15;
		panel_1.add(btnBuscar, gbc_btnBuscar);
		
		GridBagConstraints gbc_btnCreate = new GridBagConstraints();
		gbc_btnCreate.gridwidth = 3;
		gbc_btnCreate.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCreate.insets = new Insets(0, 0, 5, 5);
		gbc_btnCreate.gridx = 1;
		gbc_btnCreate.gridy = 2;
		panel.add(btnCreate, gbc_btnCreate);

		GridBagConstraints gbc_btnConvocatoria = new GridBagConstraints();
		gbc_btnConvocatoria.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnConvocatoria.insets = new Insets(0, 0, 5, 5);
		gbc_btnConvocatoria.gridx = 1;
		gbc_btnConvocatoria.gridy = 3;
		panel.add(btnConvocatoria, gbc_btnConvocatoria);
		
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnUpdate.insets = new Insets(0, 0, 5, 5);
		gbc_btnUpdate.gridx = 2;
		gbc_btnUpdate.gridy = 3;
		panel.add(btnUpdate, gbc_btnUpdate);
		
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDelete.insets = new Insets(0, 0, 5, 5);
		gbc_btnDelete.gridx = 3;
		gbc_btnDelete.gridy = 3;
		panel.add(btnDelete, gbc_btnDelete);
	}

	private void setupComboBox() {
		selectTipo = new JComboBox<EnumEventoTipo>(EnumEventoTipo.values());
		selectModalidad = new JComboBox<EnumEventoModalidad>(EnumEventoModalidad.values());
		selectEstado = new JComboBox<EnumEventoEstado>(EnumEventoEstado.values());
		try {
			List<Itr> itrs = BeanRemoteManager.getBeanItr().findAll();
			System.out.println(itrs);
			selectItr = new JComboBox<Itr>(itrs.toArray(new Itr[itrs.size()]));
		} catch (NamingException e) {
			e.printStackTrace();
		}
		cleanFilters();
	}
	
	// Funciones sobre el Tablero
	
	public void refreshTable() {
		model.setRowCount(0);
		List<Evento> eventos = bo.getList();
		try {
			for (Evento evento : eventos) {
				Object[] row = new Object[7];
				row[0] = evento.getIdEvento();
				row[1] = evento.getNombre();
				row[2] = evento.getTipo();
				row[3] = evento.getModalidad();
				row[4] = evento.getFechaInicio();
				row[5] = evento.getFechaFin();
				row[6] = evento.getItr();
				model.addRow(row);
			}
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public Evento getEventoFromTable() {
		int row = tEvento.getSelectedRow();
		Long id = (Long) tEvento.getValueAt(row, 0);
		Evento evento = bo.find(id);
		return evento;
	}
	
	protected void search() {
		String nombre = inputBuscador.getText();
		System.out.println(nombre);
		
		EnumEventoTipo tipo = (EnumEventoTipo) selectTipo.getSelectedItem();
		System.out.println(tipo);
		
		Object modalidad = selectModalidad.getSelectedItem();
		System.out.println(modalidad);
		
		Itr itr = (Itr) selectItr.getSelectedItem();
		System.out.println(itr);
		
		Object estado = selectEstado.getSelectedItem();
		System.out.println(estado);
		try {
			EventoBusquedaVO vo = EventoBusquedaVO.builder()
					.nombre("")
					.estado(null)
					.modalidad(null)
					.tipo(tipo)
					.itr(null)
					.build();
			BeanRemoteManager.getBeanEvento().search(vo);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	protected void cleanFilters() {
		inputBuscador.setText("");
		selectTipo.setSelectedItem(null);
		selectModalidad.setSelectedItem(null);
		selectItr.setSelectedItem(null);
		selectEstado.setSelectedItem(null);
		
	}
	
	// Funciones de DDL

	protected void create() {
		ViewEventoRegistro viewRegistro = new ViewEventoRegistro(this);
		viewRegistro.setVisible(true);
		viewRegistro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	protected void update() {
		Evento evento = getEventoFromTable();
		ViewEventoRegistro viewRegistro = new ViewEventoRegistro(this, evento);
		viewRegistro.setVisible(true);
		viewRegistro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	protected void delete() {
		int row = tEvento.getSelectedRow();
		Long id = (Long) tEvento.getValueAt(row, 0);
		int dialogResult = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el Evento seleccionado?","Confirmación", JOptionPane.YES_NO_OPTION);
		if(dialogResult == JOptionPane.YES_OPTION){
			String mensaje = bo.delete(id);
			refreshTable();
			JOptionPane.showMessageDialog(null, mensaje);
		}
	}
	
	protected void showConvocatoria() {
		Evento evento = getEventoFromTable();
		if (evento != null) {
			ViewAsistenciaMain viewAsistenciaMain = new ViewAsistenciaMain(evento);
			viewAsistenciaMain.setVisible(true);
			viewAsistenciaMain.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		} else {
			JOptionPane.showInternalMessageDialog(null, "No se encontró el Evento.");
		}
	}

}
