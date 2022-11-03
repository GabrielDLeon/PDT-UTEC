package com.app.test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.app.singleton.BeanRemoteManager;
import com.entities.Evento;
import com.formdev.flatlaf.FlatDarkLaf;
import com.services.eventos.EventoBeanRemote;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;

import javax.swing.JTable;

public class EventoTable extends JFrame {

	private JPanel contentPane;
	private JTable tEvento;
	private static EventoBeanRemote bean;
	private List<Evento> eventos;
	private DefaultTableModel model = new DefaultTableModel();
	
	public static void main(String[] args) {
		FlatDarkLaf.setup();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bean = BeanRemoteManager.getBeanEvento();
					EventoTable frame = new EventoTable();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public EventoTable() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 581);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 484, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		panel.add(scrollPane, gbc_scrollPane);

		model.addColumn("ID");
		model.addColumn("Título");
		model.addColumn("Tipo");
		model.addColumn("Modalidad");
		model.addColumn("Inicio");
		model.addColumn("Fin");
		model.addColumn("ITR");
		
		
		tEvento = new JTable(model);
		tEvento.setFillsViewportHeight(true);
		scrollPane.setViewportView(tEvento);
		
		cargarTabla();
	}
	
	protected void cargarTabla() {
		System.out.println("Buscando eventos");
		eventos = bean.findAll();
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
	}
	
}
