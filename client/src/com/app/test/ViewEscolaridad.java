package com.app.test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.app.controllers.GeneratorPDF;
import com.app.controllers.UsuarioDAO;
import com.app.singleton.BeanRemoteManager;
import com.app.views.CustomTable;
import com.entities.Analista;
import com.entities.Estudiante;
import com.entities.Tutor;
import com.entities.Usuario;
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
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ViewEscolaridad extends JInternalFrame {

	private JPanel contentPane;
	private CustomTable tEscolaridad;
	private JButton btnPDF;

	private UsuarioDAO dao = new UsuarioDAO();

	private static Estudiante estudiante;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewEscolaridad frame = new ViewEscolaridad(new Analista());
					frame.setVisible(true);
//					estudiante = BeanRemoteManager.getBeanUsuario().findEstudiante(2L);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ViewEscolaridad(Usuario u) {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		if (u.getClass() == Analista.class || u.getClass() == Tutor.class) {

			JScrollPane scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 1;
			gbc_scrollPane.gridy = 1;
			contentPane.add(scrollPane, gbc_scrollPane);

			tEscolaridad = new CustomTable();
			tEscolaridad.setColumns("ID", "Nombre", "Apellido", "Documento");
			tEscolaridad.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent lse) {
					if (!lse.getValueIsAdjusting()) {
						int row = tEscolaridad.getSelectedRow();
						boolean state = (row >= 0) ? true : false;
						estudiante = dao.findEstudiante(getIdFromTable());
						btnPDF.setEnabled(state);
					}
				}
			});
			scrollPane.setViewportView(tEscolaridad);
		}

		btnPDF = new JButton("Descargar en PDF");
		btnPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (u.getClass() == Analista.class || u.getClass() == Tutor.class) {

					try {
						GeneratorPDF.generatePDF(estudiante);
						JOptionPane.showMessageDialog(null, "Descarga completa de la Escolaridad en formato PDT.");
					} catch (FileNotFoundException | DocumentException | NamingException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}

				} else if (u.getClass() == Estudiante.class) {
					try {
						GeneratorPDF.generatePDF((Estudiante) u);
						JOptionPane.showMessageDialog(null, "Descarga completa de la Escolaridad en formato PDT.");
					} catch (FileNotFoundException | DocumentException | NamingException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}

			}
		});
		GridBagConstraints gbc_btnPDF = new GridBagConstraints();
		gbc_btnPDF.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPDF.insets = new Insets(0, 0, 5, 5);
		gbc_btnPDF.gridx = 1;
		gbc_btnPDF.gridy = 2;
		contentPane.add(btnPDF, gbc_btnPDF);

		if(u.getClass() == Analista.class || u.getClass() == Tutor.class) {
			btnPDF.setEnabled(false);
			fillTable();
		}
		
	}

	protected void fillTable() {
		ArrayList<Estudiante> estudiantes = (ArrayList<Estudiante>) dao.getAllEstudiantes();
		ArrayList<Estudiante> est = new ArrayList<>(estudiantes);

		for (Usuario u : est) {

			Object[] row = new Object[4];
			row[0] = u.getIdUsuario();
			row[1] = u.getNombre1();
			row[2] = u.getApellido1();
			row[3] = u.getDocumento();
			tEscolaridad.model.addRow(row);

		}

	}

	protected Long getIdFromTable() {
		int row = tEscolaridad.getSelectedRow();
		Long id = (Long) tEscolaridad.getValueAt(row, 0);
		return id;
	}

}
