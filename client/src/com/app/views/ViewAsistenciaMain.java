package com.app.views;

import java.awt.EventQueue;

import javax.naming.NamingException;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.app.controllers.AsistenciaDAO;
import com.app.singleton.BeanRemoteManager;
import com.app.singleton.RobotoFont;
import com.entities.Analista;
import com.entities.Asistencia;
import com.entities.Estudiante;
import com.entities.Evento;
import com.entities.Usuario;
import com.enumerators.EnumAsistenciaEstado;
import com.enumerators.EnumEventoEstado;
import com.formdev.flatlaf.FlatDarkLaf;
import com.services.eventos.AsistenciaBeanRemote;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class ViewAsistenciaMain extends JInternalFrame {

	private JPanel panel;
	private JPanel contentPane;
	
	private JScrollPane scrollPane;
	
	private JTable tAsistencia;
	private JButton btnDelete;
	private DefaultTableModel model = new DefaultTableModel() {
		@Override
	    public boolean isCellEditable(int row, int column) {
	       //all cells false
	       return false;
	    }
	};
	

	private List<Asistencia> convocados = new ArrayList<Asistencia>();
	private JComboBox<EnumAsistenciaEstado> selectEstado;
	
	private Evento evento;
	private AsistenciaDAO dao = new AsistenciaDAO();
	
	private JSpinner inputNota;
	private JTextField inputNombre;
	private JTextField inputDocumento;
	private JTextField inputGeneracion;
	private JTextField inputItr;

	private JLabel lblTitulo;
	private JLabel lblEvento;
	private JPanel panel_1;
	private JLabel lblEstado;
	private JLabel lblNota;
	private JLabel lblNombre;
	private JLabel lblDocumento;
	private JLabel lblGeneracion;
	private JLabel lblItr;

	private JButton btnInsert;
	private JButton btnUpdate;
	
	// Esto después se tiene que borrar
	// En desarrollo todavía se utiliza
	public static void main(String[] args) {
		FlatDarkLaf.setup();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAsistenciaMain frame = new ViewAsistenciaMain(BeanRemoteManager.getBeanEvento().findById(1L));
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
		clearForm();
		refreshTable();
	}
	
	// Funciones sobre el Tablero
	
	private void refreshTable() {
		model.setRowCount(0);
		convocados = dao.findByEvento(evento.getIdEvento());
		for (Asistencia a : convocados) {
			Object[] row = new Object[4];
			row[0] = a;
			row[1] = a.getEstudiante();
			row[2] = a.getEstado();
			row[3] = a.getCalificacion();
			model.addRow(row);
		}
	}
	
	protected void clearForm() {
		inputNombre.setText("");
		inputDocumento.setText("");
		inputGeneracion.setText("");
		inputItr.setText("");
		selectEstado.setSelectedItem(null);
		inputNota.setValue(0);
	}

	protected void fillForm(int row) {
		Estudiante e = (Estudiante) tAsistencia.getValueAt(row, 1);
		EnumAsistenciaEstado estado = (EnumAsistenciaEstado) tAsistencia.getValueAt(row, 2);
		String nombre = (e.getNombre1() == null)? "" : e.getNombre1();
		String apellido = (e.getApellido1() == null)? "" : e.getApellido1();
		
		double calificacion = (double) tAsistencia.getValueAt(row, 3);
		inputNombre.setText(nombre + " " + apellido);
		inputDocumento.setText(e.getDocumento());
		inputGeneracion.setText(e.getGeneracion());
		if (e.getItr() != null)
			inputItr.setText(e.getItr().toString());
		
		selectEstado.setSelectedItem(estado);
		inputNota.setValue(calificacion);
	}
	
	// Funciones de DDL

	protected void update() {
		int row = tAsistencia.getSelectedRow();
		Asistencia a = (Asistencia) tAsistencia.getValueAt(row, 0);		
		a.setEstado((EnumAsistenciaEstado) selectEstado.getSelectedItem());
		a.setCalificacion((double) inputNota.getValue());
		String mensaje = dao.update(a);
		refreshTable();
		JOptionPane.showMessageDialog(null, mensaje);
	}

	protected void addEstudiante() {
		// TODO Auto-generated method stub
		
	}
	
	private void delete() {
		int row = tAsistencia.getSelectedRow();
		Asistencia a = (Asistencia) tAsistencia.getValueAt(row, 0);
		long idEvento = a.getEvento().getIdEvento();
		System.out.println(idEvento);
		long idEstudiante = a.getEstudiante().getIdUsuario();
		System.out.println(idEstudiante);
		String mensaje = dao.delete(idEvento, idEstudiante);
		refreshTable();
		JOptionPane.showMessageDialog(null, mensaje);
	}
	
	
	// Manejadores de UI
	
	public ViewAsistenciaMain(Evento evento) {
		// Para crear la ventana de Asistencia se le debe pasar un evento
		// En base al evento que le pasemos será el listado que mostrará
		this.evento = evento;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 819, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		scrollPane = new JScrollPane();

		model.addColumn("Asistencia");
		model.addColumn("Estudiante");
		model.addColumn("Estado");
		model.addColumn("Calificación");
		
		tAsistencia = new JTable(model);
		tAsistencia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tAsistencia.setFillsViewportHeight(true);
		tAsistencia.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent lse) {
                if (!lse.getValueIsAdjusting()) {
                    int row = tAsistencia.getSelectedRow();
                    boolean state = (row >= 0) ? true : false;
                    btnUpdate.setEnabled(state);
                    btnDelete.setEnabled(state);
                    inputNota.setEnabled(state);
                    selectEstado.setEnabled(state);
                    if (state) fillForm(row);
                }
            }
        });
		tAsistencia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyChar();
				if (key == 27) {
					tAsistencia.clearSelection();
					clearForm();
				}
			}
		});
		scrollPane.setViewportView(tAsistencia);
		
		selectEstado = new JComboBox<EnumAsistenciaEstado>(EnumAsistenciaEstado.values());
		selectEstado.setEnabled(false);
		
		inputGeneracion = new JTextField();
		inputGeneracion.setEditable(false);
		
		inputItr = new JTextField();
		inputItr.setEditable(false);
		
		inputNota = new JSpinner();
		inputNota.setEnabled(false);
		inputNota.setModel(new SpinnerNumberModel(0.0, 0.0, 5.0, 1.0));
		inputItr.setEditable(false);
		
		btnInsert = new JButton("Convocar Estudiante");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEstudiante();
			}
		});
		
		btnDelete = new JButton("Eliminar Convocado");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		
		btnUpdate = new JButton("Modificar Asistencia");
		btnUpdate.setEnabled(false);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		
		setup();
	}
	
	private void setGridBagLayout() {
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{10, 0, 0, 0, 10, 0};
		gbl_panel.rowHeights = new int[]{12, 0, 0, 12, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		lblTitulo = new JLabel("Asistencias del Evento");
		lblTitulo.setFont(RobotoFont.getTitulo());
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitulo.gridx = 2;
		gbc_lblTitulo.gridy = 1;
		panel.add(lblTitulo, gbc_lblTitulo);
		
		lblEvento = new JLabel("Nombre del Evento");
		GridBagConstraints gbc_lblEvento = new GridBagConstraints();
		gbc_lblEvento.insets = new Insets(0, 0, 5, 5);
		gbc_lblEvento.gridx = 2;
		gbc_lblEvento.gridy = 2;
		panel.add(lblEvento, gbc_lblEvento);
		lblEvento.setFont(RobotoFont.getSubTitulo());
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 4;
		panel.add(scrollPane, gbc_scrollPane);

		panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 3;
		gbc_panel_1.gridy = 4;
		panel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 25, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 5.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		lblNombre = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.WEST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 1;
		panel_1.add(lblNombre, gbc_lblNombre);
		
		inputNombre = new JTextField();
		inputNombre.setEditable(false);
		inputDocumento = new JTextField();
		inputDocumento.setEditable(false);
		
		GridBagConstraints gbc_inputNombre = new GridBagConstraints();
		gbc_inputNombre.insets = new Insets(0, 0, 5, 5);
		gbc_inputNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputNombre.gridx = 1;
		gbc_inputNombre.gridy = 2;
		panel_1.add(inputNombre, gbc_inputNombre);
		inputNombre.setColumns(10);
		
		lblDocumento = new JLabel("Documento");
		GridBagConstraints gbc_lblDocumento = new GridBagConstraints();
		gbc_lblDocumento.anchor = GridBagConstraints.WEST;
		gbc_lblDocumento.insets = new Insets(0, 0, 5, 5);
		gbc_lblDocumento.gridx = 1;
		gbc_lblDocumento.gridy = 3;
		panel_1.add(lblDocumento, gbc_lblDocumento);
		
		GridBagConstraints gbc_inputDocumento = new GridBagConstraints();
		gbc_inputDocumento.insets = new Insets(0, 0, 5, 5);
		gbc_inputDocumento.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputDocumento.gridx = 1;
		gbc_inputDocumento.gridy = 4;
		panel_1.add(inputDocumento, gbc_inputDocumento);
		inputDocumento.setColumns(10);
		
		lblGeneracion = new JLabel("Generación");
		GridBagConstraints gbc_lblGeneracion = new GridBagConstraints();
		gbc_lblGeneracion.anchor = GridBagConstraints.WEST;
		gbc_lblGeneracion.insets = new Insets(0, 0, 5, 5);
		gbc_lblGeneracion.gridx = 1;
		gbc_lblGeneracion.gridy = 5;
		panel_1.add(lblGeneracion, gbc_lblGeneracion);
		
		
		GridBagConstraints gbc_inputGeneracion = new GridBagConstraints();
		gbc_inputGeneracion.insets = new Insets(0, 0, 5, 5);
		gbc_inputGeneracion.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputGeneracion.gridx = 1;
		gbc_inputGeneracion.gridy = 6;
		panel_1.add(inputGeneracion, gbc_inputGeneracion);
		inputGeneracion.setColumns(10);
		
		lblItr = new JLabel("Centro de Estudio");
		GridBagConstraints gbc_lblItr = new GridBagConstraints();
		gbc_lblItr.anchor = GridBagConstraints.WEST;
		gbc_lblItr.insets = new Insets(0, 0, 5, 5);
		gbc_lblItr.gridx = 1;
		gbc_lblItr.gridy = 7;
		panel_1.add(lblItr, gbc_lblItr);
		
		GridBagConstraints gbc_inputItr = new GridBagConstraints();
		gbc_inputItr.insets = new Insets(0, 0, 5, 5);
		gbc_inputItr.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputItr.gridx = 1;
		gbc_inputItr.gridy = 8;
		panel_1.add(inputItr, gbc_inputItr);
		inputItr.setColumns(10);
		
		lblEstado = new JLabel("Estado de la Asistencia");
		GridBagConstraints gbc_lblEstado = new GridBagConstraints();
		gbc_lblEstado.anchor = GridBagConstraints.WEST;
		gbc_lblEstado.insets = new Insets(0, 0, 5, 5);
		gbc_lblEstado.gridx = 1;
		gbc_lblEstado.gridy = 10;
		panel_1.add(lblEstado, gbc_lblEstado);
		
		GridBagConstraints gbc_selectEstado = new GridBagConstraints();
		gbc_selectEstado.insets = new Insets(0, 0, 5, 5);
		gbc_selectEstado.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectEstado.gridx = 1;
		gbc_selectEstado.gridy = 11;
		panel_1.add(selectEstado, gbc_selectEstado);
		
		lblNota = new JLabel("Nota del Estudiante");
		GridBagConstraints gbc_lblNota = new GridBagConstraints();
		gbc_lblNota.anchor = GridBagConstraints.WEST;
		gbc_lblNota.insets = new Insets(0, 0, 5, 5);
		gbc_lblNota.gridx = 1;
		gbc_lblNota.gridy = 12;
		panel_1.add(lblNota, gbc_lblNota);
		
		GridBagConstraints gbc_inputNota = new GridBagConstraints();
		gbc_inputNota.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputNota.insets = new Insets(0, 0, 5, 5);
		gbc_inputNota.gridx = 1;
		gbc_inputNota.gridy = 13;
		panel_1.add(inputNota, gbc_inputNota);
		
		GridBagConstraints gbc_btnInsert = new GridBagConstraints();
		gbc_btnInsert.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnInsert.insets = new Insets(0, 0, 5, 5);
		gbc_btnInsert.gridx = 1;
		gbc_btnInsert.gridy = 5;
		panel.add(btnInsert, gbc_btnInsert);
		
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDelete.insets = new Insets(0, 0, 5, 5);
		gbc_btnDelete.gridx = 2;
		gbc_btnDelete.gridy = 5;
		panel.add(btnDelete, gbc_btnDelete);
		
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnUpdate.insets = new Insets(0, 0, 5, 5);
		gbc_btnUpdate.gridx = 3;
		gbc_btnUpdate.gridy = 5;
		panel.add(btnUpdate, gbc_btnUpdate);
	}

}
