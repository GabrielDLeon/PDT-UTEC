package com.app.test;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

import com.app.singleton.RobotoFont;
import com.enumerators.EnumUsuarioTipo;

import javax.swing.JComboBox;
import javax.swing.JSpinner;

public class PanelUsuarioFiltro extends JPanel {
	
	private JComboBox<EnumUsuarioTipo> selectTipo;
	private JSpinner inputGeneracion;
	private JTextField inputNombre;
	private JTextField inputDocumento;

	public PanelUsuarioFiltro() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblTitulo = new JLabel("Filtros de Búsqueda");
		lblTitulo.setFont(RobotoFont.getSubTitulo());
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitulo.gridx = 1;
		gbc_lblTitulo.gridy = 1;
		add(lblTitulo, gbc_lblTitulo);
		
		JLabel lblNombre = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 3;
		add(lblNombre, gbc_lblNombre);
		
		inputNombre = new JTextField();
		GridBagConstraints gbc_inputNombre = new GridBagConstraints();
		gbc_inputNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputNombre.insets = new Insets(0, 0, 5, 5);
		gbc_inputNombre.gridx = 1;
		gbc_inputNombre.gridy = 4;
		add(inputNombre, gbc_inputNombre);
		inputNombre.setColumns(10);
		
		JLabel lblDocumento = new JLabel("Documento");
		GridBagConstraints gbc_lblDocumento = new GridBagConstraints();
		gbc_lblDocumento.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblDocumento.insets = new Insets(0, 0, 5, 5);
		gbc_lblDocumento.gridx = 1;
		gbc_lblDocumento.gridy = 5;
		add(lblDocumento, gbc_lblDocumento);
		
		inputDocumento = new JTextField();
		GridBagConstraints gbc_inputDocumento = new GridBagConstraints();
		gbc_inputDocumento.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputDocumento.insets = new Insets(0, 0, 5, 5);
		gbc_inputDocumento.gridx = 1;
		gbc_inputDocumento.gridy = 6;
		add(inputDocumento, gbc_inputDocumento);
		inputDocumento.setColumns(10);
		
		JLabel lblTipo = new JLabel("Tipo de Usuario");
		GridBagConstraints gbc_lblTipo = new GridBagConstraints();
		gbc_lblTipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTipo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipo.gridx = 1;
		gbc_lblTipo.gridy = 7;
		add(lblTipo, gbc_lblTipo);
		
		selectTipo = new JComboBox<EnumUsuarioTipo>(EnumUsuarioTipo.values());
		GridBagConstraints gbc_selectTipo = new GridBagConstraints();
		gbc_selectTipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectTipo.insets = new Insets(0, 0, 5, 5);
		gbc_selectTipo.gridx = 1;
		gbc_selectTipo.gridy = 8;
		add(selectTipo, gbc_selectTipo);
		
		JLabel lblGeneracion = new JLabel("Generación");
		GridBagConstraints gbc_lblGeneracion = new GridBagConstraints();
		gbc_lblGeneracion.anchor = GridBagConstraints.WEST;
		gbc_lblGeneracion.insets = new Insets(0, 0, 5, 5);
		gbc_lblGeneracion.gridx = 1;
		gbc_lblGeneracion.gridy = 9;
		add(lblGeneracion, gbc_lblGeneracion);
		
		inputGeneracion = new JSpinner();
		GridBagConstraints gbc_inputGeneracion = new GridBagConstraints();
		gbc_inputGeneracion.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputGeneracion.insets = new Insets(0, 0, 0, 5);
		gbc_inputGeneracion.gridx = 1;
		gbc_inputGeneracion.gridy = 10;
		add(inputGeneracion, gbc_inputGeneracion);

	}

}
