package com.app.views;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ViewConstancia extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewConstancia frame = new ViewConstancia();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewConstancia() {
		setBounds(100, 100, 280, 479);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JButton btnTransporte = new JButton("Transporte");
		btnTransporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
				    try {
						Desktop.getDesktop().browse(new URI("https://drive.google.com/file/d/1-I-jc9ya9pCKWwXtlAMzdf1pxAt8Cnle/view"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		JLabel lblConstancias = new JLabel("Constancias");
		lblConstancias.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblConstancias = new GridBagConstraints();
		gbc_lblConstancias.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblConstancias.insets = new Insets(0, 0, 5, 5);
		gbc_lblConstancias.gridx = 1;
		gbc_lblConstancias.gridy = 1;
		getContentPane().add(lblConstancias, gbc_lblConstancias);
		GridBagConstraints gbc_btnTransporte = new GridBagConstraints();
		gbc_btnTransporte.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnTransporte.insets = new Insets(0, 0, 5, 5);
		gbc_btnTransporte.gridx = 1;
		gbc_btnTransporte.gridy = 3;
		getContentPane().add(btnTransporte, gbc_btnTransporte);
		
		JButton btnExamen = new JButton("Examen");
		btnExamen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
				    try {
						Desktop.getDesktop().browse(new URI("https://drive.google.com/file/d/1iXUWpp01jFRIY0W4SZtpJtzMyvhgL6As/view"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		GridBagConstraints gbc_btnExamen = new GridBagConstraints();
		gbc_btnExamen.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnExamen.insets = new Insets(0, 0, 5, 5);
		gbc_btnExamen.gridx = 1;
		gbc_btnExamen.gridy = 4;
		getContentPane().add(btnExamen, gbc_btnExamen);
		
		JButton btnActivo = new JButton("Estudiante Activo");
		btnActivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
				    try {
						Desktop.getDesktop().browse(new URI("https://drive.google.com/file/d/1nX52_HUdUa07s5I6UOJfFZbrw1rFog6b/view"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		GridBagConstraints gbc_btnActivo = new GridBagConstraints();
		gbc_btnActivo.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnActivo.insets = new Insets(0, 0, 5, 5);
		gbc_btnActivo.gridx = 1;
		gbc_btnActivo.gridy = 5;
		getContentPane().add(btnActivo, gbc_btnActivo);
		
		JButton btnActividadExt = new JButton("Actividad Externa");
		btnActividadExt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
				    try {
						Desktop.getDesktop().browse(new URI("https://drive.google.com/file/d/1MgMlGDX7aVHNEgWSKDdLZiAZ2SoVHyKF/view"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		GridBagConstraints gbc_btnActividadExt = new GridBagConstraints();
		gbc_btnActividadExt.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnActividadExt.insets = new Insets(0, 0, 5, 5);
		gbc_btnActividadExt.gridx = 1;
		gbc_btnActividadExt.gridy = 6;
		getContentPane().add(btnActividadExt, gbc_btnActividadExt);
		
		JButton btnPruebaParcial = new JButton("Prueba Parcial");
		btnPruebaParcial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
				    try {
						Desktop.getDesktop().browse(new URI("https://drive.google.com/file/d/1tCjB8oy5yLQPrlKmFC5KQU_h5oJZ4W2J/view"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		GridBagConstraints gbc_btnPruebaParcial = new GridBagConstraints();
		gbc_btnPruebaParcial.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPruebaParcial.insets = new Insets(0, 0, 5, 5);
		gbc_btnPruebaParcial.gridx = 1;
		gbc_btnPruebaParcial.gridy = 7;
		getContentPane().add(btnPruebaParcial, gbc_btnPruebaParcial);
		
		JButton btnRevalidas = new JButton("Revalidas");
		btnRevalidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
				    try {
						Desktop.getDesktop().browse(new URI("https://drive.google.com/file/d/19x8cCR7Ak4LhYrDFy2MBdbh0phg4vTH1/view"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		GridBagConstraints gbc_btnRevalidas = new GridBagConstraints();
		gbc_btnRevalidas.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRevalidas.insets = new Insets(0, 0, 0, 5);
		gbc_btnRevalidas.gridx = 1;
		gbc_btnRevalidas.gridy = 8;
		getContentPane().add(btnRevalidas, gbc_btnRevalidas);

	}

}
