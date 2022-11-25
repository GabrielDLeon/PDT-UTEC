package com.app.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.app.controllers.EventoEstadoBO;
import com.app.controllers.EventoModalidadBO;
import com.entities.EventoEstado;
import com.entities.EventoModalidad;
import com.formdev.flatlaf.FlatDarkLaf;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class ViewEventoModalidad extends JFrame {

	private JPanel contentPane;
	private JTextField inputNombre;
	private JButton btnCreate = new JButton("Crear Modalidad");
	private JButton btnUpdate = new JButton("Modificar Modalidad");
	private JButton btnDelete = new JButton("Eliminar Modalidad");
	
	DefaultListModel<EventoModalidad> model = new DefaultListModel();
	private JList listModalidad = new JList(model);
	
	private List<EventoModalidad> modalidades = new ArrayList<EventoModalidad>();
	
	private EventoModalidadBO bo = new EventoModalidadBO();
	
	public static void main(String[] args) {
		FlatDarkLaf.setup();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewEventoModalidad frame = new ViewEventoModalidad();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void setup() {
		refreshList();
	}
	
	private void create() {
		String nombre = inputNombre.getText();
		EventoModalidad modalidad = EventoModalidad.builder()
				.nombre(nombre)
				.activo(true)
				.build();
		String mensaje = bo.create(modalidad);
		refreshList();
		inputNombre.setText("");
		JOptionPane.showMessageDialog(null, mensaje);
	}
	

	private void update() {
		EventoModalidad modalidad = (EventoModalidad) listModalidad.getSelectedValue();
		modalidad.setNombre(inputNombre.getText());
		int option = JOptionPane.showConfirmDialog(null, "¿Desea modificar la Modalidad seleccionada?", "Confirmación", JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION) {
			String mensaje = bo.update(modalidad);
			refreshList();
			inputNombre.setText("");
			JOptionPane.showMessageDialog(null, mensaje);
		}
	}
	

	private void delete() {
		int option = JOptionPane.showConfirmDialog(null, "¿Desea eliminar la Modalidad seleccionado?", "Confirmación", JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION) {
			EventoModalidad modalidad = (EventoModalidad) listModalidad.getSelectedValue();
			String mensaje = bo.delete(modalidad.getIdModalidad());
			refreshList();
			inputNombre.setText("");
			JOptionPane.showMessageDialog(null, mensaje);
		}
	}
	
	
	private void refreshList() {		
		model.removeAllElements();
		modalidades = bo.findByStatus(true);
		model.addAll(modalidades);
	}
	
	public ViewEventoModalidad() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 740, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		listModalidad.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent lse) {
				if(!lse.getValueIsAdjusting()) {
					int row = listModalidad.getSelectedIndex();
					boolean state = (row >= 0) ? true : false;
                    btnUpdate.setEnabled(state);
                    btnDelete.setEnabled(state);
                    
                    if (state) {
                    	EventoModalidad modalidad = (EventoModalidad) listModalidad.getSelectedValue();
                    	inputNombre.setText(modalidad.getNombre());
                    }
				}
			}
		});
		listModalidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyChar();
				if (key == 27) {
					listModalidad.clearSelection();
					inputNombre.setText("");
				}
			}
		});
		
		scrollPane.setViewportView(listModalidad);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.gridx = 3;
		gbc_panel_1.gridy = 1;
		contentPane.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 5.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNombre = new JLabel("Nombre del Estado");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.WEST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 1;
		panel_1.add(lblNombre, gbc_lblNombre);
		
		inputNombre = new JTextField();
		GridBagConstraints gbc_inputNombre = new GridBagConstraints();
		gbc_inputNombre.insets = new Insets(0, 0, 5, 5);
		gbc_inputNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputNombre.gridx = 1;
		gbc_inputNombre.gridy = 2;
		panel_1.add(inputNombre, gbc_inputNombre);
		inputNombre.setColumns(10);
		
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				create();
			}
		});
		GridBagConstraints gbc_btnCreate = new GridBagConstraints();
		gbc_btnCreate.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCreate.insets = new Insets(0, 0, 5, 5);
		gbc_btnCreate.gridx = 1;
		gbc_btnCreate.gridy = 3;
		panel_1.add(btnCreate, gbc_btnCreate);
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnUpdate.insets = new Insets(0, 0, 5, 5);
		gbc_btnUpdate.gridx = 1;
		gbc_btnUpdate.gridy = 4;
		panel_1.add(btnUpdate, gbc_btnUpdate);
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDelete.insets = new Insets(0, 0, 0, 5);
		gbc_btnDelete.gridx = 1;
		gbc_btnDelete.gridy = 5;
		panel_1.add(btnDelete, gbc_btnDelete);
		
		setup();
	}

}
