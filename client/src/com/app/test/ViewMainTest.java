package com.app.test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.app.singleton.RobotoFont;
import com.app.views.ViewEventoEstado;
import com.app.views.ViewEventoMain;
import com.app.views.ViewEventoModalidad;
import com.entities.Analista;
import com.entities.Estudiante;
import com.entities.Tutor;
import com.entities.Usuario;
import com.formdev.flatlaf.FlatDarkLaf;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewMainTest extends JFrame {

	private JPanel contentPane;
	private JButton btnUsuario;

	private Usuario usuario = new Analista();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlatDarkLaf.setup();
					UIManager.getLookAndFeelDefaults().put("defaultFont", RobotoFont.getNormal());
					UIManager.put(args, args);
					ViewMainTest frame = new ViewMainTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ViewMainTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 282);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{20, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblUsuario = new JLabel("Ingresar con el Tipo de Usuario");
		GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
		gbc_lblUsuario.anchor = GridBagConstraints.WEST;
		gbc_lblUsuario.fill = GridBagConstraints.VERTICAL;
		gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuario.gridx = 1;
		gbc_lblUsuario.gridy = 1;
		panel.add(lblUsuario, gbc_lblUsuario);
		
		JComboBox selectUsuario = new JComboBox();
		selectUsuario.setModel(new DefaultComboBoxModel(new String[] {"Analista", "Tutor", "Estudiante"}));
		GridBagConstraints gbc_selectUsuario = new GridBagConstraints();
		gbc_selectUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_selectUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectUsuario.gridx = 1;
		gbc_selectUsuario.gridy = 2;
		panel.add(selectUsuario, gbc_selectUsuario);
		
		btnUsuario = new JButton("Seleccionar");
		btnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tipo = (String) selectUsuario.getSelectedItem();
				lblUsuario.setText("Usuario seleccionado: "+ tipo);
				switch (tipo) {
					case "Analista": usuario = new Analista(); break;
					case "Tutor": usuario = new Tutor(); break;
					case "Estudiante": usuario = new Estudiante(); break;
				}
			}
		});
		GridBagConstraints gbc_btnUsuario = new GridBagConstraints();
		gbc_btnUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_btnUsuario.gridx = 2;
		gbc_btnUsuario.gridy = 2;
		panel.add(btnUsuario, gbc_btnUsuario);
		
		JButton btnEvento = new JButton("Administrar Eventos");
		btnEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewEventoMain view = new ViewEventoMain(usuario);
				view.setVisible(true);
				view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		GridBagConstraints gbc_btnEvento = new GridBagConstraints();
		gbc_btnEvento.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEvento.insets = new Insets(0, 0, 5, 5);
		gbc_btnEvento.gridx = 1;
		gbc_btnEvento.gridy = 1;
		contentPane.add(btnEvento, gbc_btnEvento);
		
		JButton btnEstado = new JButton("Admistrar Estados");
		btnEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewEventoEstado view = new ViewEventoEstado();
				view.setVisible(true);
				view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		GridBagConstraints gbc_btnEstado = new GridBagConstraints();
		gbc_btnEstado.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEstado.insets = new Insets(0, 0, 5, 5);
		gbc_btnEstado.gridx = 1;
		gbc_btnEstado.gridy = 2;
		contentPane.add(btnEstado, gbc_btnEstado);
		
		JButton btnModalidad = new JButton("Administrar Modalidades");
		btnModalidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewEventoModalidad view = new ViewEventoModalidad();
				view.setVisible(true);
				view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		GridBagConstraints gbc_btnModalidad = new GridBagConstraints();
		gbc_btnModalidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnModalidad.insets = new Insets(0, 0, 0, 5);
		gbc_btnModalidad.gridx = 1;
		gbc_btnModalidad.gridy = 3;
		contentPane.add(btnModalidad, gbc_btnModalidad);
	}

}
