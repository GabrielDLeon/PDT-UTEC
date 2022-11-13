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
import com.entities.Analista;
import com.entities.Evento;
import com.entities.Usuario;
import com.formdev.flatlaf.FlatDarkLaf;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

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
	private JScrollPane scrollPane = new JScrollPane();
	
	private JTable tEvento;
	
	private JButton btnConvocatoria;
	private JButton btnCreate;
	private JButton btnUpdate;
	private JButton btnDelete;
	
	private Usuario user = new Analista();
	
	private EventoBO bo = new EventoBO(user);
	
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
		setBounds(100, 100, 700, 606);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(panel, BorderLayout.CENTER);
		
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
		
		try {
			Usuario xd = BeanRemoteManager.getBeanUsuario().findUsuario(3L);
			System.out.println(xd);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		setGridBagLayout();
		setVisible(true);
		refreshTable();
	}
	
	private void setGridBagLayout() {
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 484, 484, 484, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 2.0, 2.0, 2.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		panel.add(scrollPane, gbc_scrollPane);
		
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
