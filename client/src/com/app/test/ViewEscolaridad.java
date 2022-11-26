package com.app.test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.app.controllers.GeneratorPDF;
import com.app.singleton.BeanRemoteManager;
import com.entities.Estudiante;
import com.itextpdf.text.DocumentException;

import java.awt.GridBagLayout;

import javax.naming.NamingException;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class ViewEscolaridad extends JFrame {

	private JPanel contentPane;
	private JTable tEscolaridad;
	
	private static Estudiante estudiante;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewEscolaridad frame = new ViewEscolaridad();
					frame.setVisible(true);
					estudiante = BeanRemoteManager.getBeanUsuario().findEstudiante(2L);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ViewEscolaridad() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		tEscolaridad = new JTable();
		scrollPane.setViewportView(tEscolaridad);
		
		JButton btnPDF = new JButton("Descargar en PDF");
		btnPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					GeneratorPDF.generatePDF(estudiante);
					JOptionPane.showMessageDialog(null, "Descarga completa de la Escolaridad en formato PDT.");
				} catch (FileNotFoundException | DocumentException | NamingException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		GridBagConstraints gbc_btnPDF = new GridBagConstraints();
		gbc_btnPDF.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPDF.insets = new Insets(0, 0, 5, 5);
		gbc_btnPDF.gridx = 1;
		gbc_btnPDF.gridy = 2;
		contentPane.add(btnPDF, gbc_btnPDF);
	}

}
