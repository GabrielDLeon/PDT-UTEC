package com.app.views.panels;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import com.app.singleton.RobotoFont;
import com.dto.UsuarioBusquedaVO;
import com.entities.Estudiante;
import com.entities.Tutor;
import com.entities.Usuario;
import com.enumerators.EnumTutorArea;
import com.enumerators.EnumUsuarioTipo;

import javax.swing.JComboBox;
import javax.swing.JSpinner;

public class PanelUsuarioFiltro extends JPanel {
	
	private JPanel panelEstudiante = new JPanel();
	private JPanel panelTutor = new JPanel();
	
	private JComboBox<EnumUsuarioTipo> selectTipo;
	private JComboBox<EnumTutorArea> selectArea;
	private JSpinner inputGeneracion;
	private JTextField inputNombre;
	private JTextField inputDocumento;

	public PanelUsuarioFiltro(Usuario user) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{15, 0, 15, 0};
		gridBagLayout.rowHeights = new int[]{15, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		if (user.getClass().equals(Estudiante.class)) {
			GridBagConstraints gbc_panelEstudiante = new GridBagConstraints();
			gbc_panelEstudiante.fill = GridBagConstraints.BOTH;
			gbc_panelEstudiante.insets = new Insets(0, 0, 5, 5);
			gbc_panelEstudiante.gridx = 1;
			gbc_panelEstudiante.gridy = 6;
			add(panelEstudiante, gbc_panelEstudiante);

			GridBagLayout gbl_panelEstudiante = new GridBagLayout();
			gbl_panelEstudiante.columnWidths = new int[]{0, 0};
			gbl_panelEstudiante.rowHeights = new int[]{0, 0, 0};
			gbl_panelEstudiante.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_panelEstudiante.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			panelEstudiante.setLayout(gbl_panelEstudiante);
		} else if (user.getClass().equals(Tutor.class)) {
			GridBagConstraints gbc_panelTutor = new GridBagConstraints();
			gbc_panelTutor.insets = new Insets(0, 0, 0, 5);
			gbc_panelTutor.fill = GridBagConstraints.BOTH;
			gbc_panelTutor.gridx = 1;
			gbc_panelTutor.gridy = 7;
			add(panelTutor, gbc_panelTutor);
			
			GridBagLayout gbl_panelTutor = new GridBagLayout();
			gbl_panelTutor.columnWidths = new int[]{0, 0};
			gbl_panelTutor.rowHeights = new int[]{0, 0, 0};
			gbl_panelTutor.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_panelTutor.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			panelTutor.setLayout(gbl_panelTutor);
		}
		
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
		gbc_lblNombre.gridy = 2;
		add(lblNombre, gbc_lblNombre);
		
		inputNombre = new JTextField();
		GridBagConstraints gbc_inputNombre = new GridBagConstraints();
		gbc_inputNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputNombre.insets = new Insets(0, 0, 5, 5);
		gbc_inputNombre.gridx = 1;
		gbc_inputNombre.gridy = 3;
		add(inputNombre, gbc_inputNombre);
		inputNombre.setColumns(10);
		
		JLabel lblDocumento = new JLabel("Documento");
		GridBagConstraints gbc_lblDocumento = new GridBagConstraints();
		gbc_lblDocumento.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblDocumento.insets = new Insets(0, 0, 5, 5);
		gbc_lblDocumento.gridx = 1;
		gbc_lblDocumento.gridy = 4;
		add(lblDocumento, gbc_lblDocumento);
		
		inputDocumento = new JTextField();
		GridBagConstraints gbc_inputDocumento = new GridBagConstraints();
		gbc_inputDocumento.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputDocumento.insets = new Insets(0, 0, 5, 5);
		gbc_inputDocumento.gridx = 1;
		gbc_inputDocumento.gridy = 5;
		add(inputDocumento, gbc_inputDocumento);
		inputDocumento.setColumns(10);
				
		JLabel lblGeneracion = new JLabel("Generación");
		GridBagConstraints gbc_lblGeneracion = new GridBagConstraints();
		gbc_lblGeneracion.anchor = GridBagConstraints.WEST;
		gbc_lblGeneracion.insets = new Insets(0, 0, 5, 0);
		gbc_lblGeneracion.gridx = 0;
		gbc_lblGeneracion.gridy = 0;
		panelEstudiante.add(lblGeneracion, gbc_lblGeneracion);
		
		inputGeneracion = new JSpinner();
		inputGeneracion.setModel(new SpinnerNumberModel(0, 0, 3450, 1));
		GridBagConstraints gbc_inputGeneracion = new GridBagConstraints();
		gbc_inputGeneracion.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputGeneracion.gridx = 0;
		gbc_inputGeneracion.gridy = 1;
		panelEstudiante.add(inputGeneracion, gbc_inputGeneracion);
		
		JLabel lblArea = new JLabel("Area del Tutor");
		GridBagConstraints gbc_lblArea = new GridBagConstraints();
		gbc_lblArea.anchor = GridBagConstraints.WEST;
		gbc_lblArea.insets = new Insets(0, 0, 5, 0);
		gbc_lblArea.gridx = 0;
		gbc_lblArea.gridy = 0;
		panelTutor.add(lblArea, gbc_lblArea);
		
		selectArea = new JComboBox<EnumTutorArea>(EnumTutorArea.values());
		GridBagConstraints gbc_selectArea = new GridBagConstraints();
		gbc_selectArea.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectArea.gridx = 0;
		gbc_selectArea.gridy = 1;
		panelTutor.add(selectArea, gbc_selectArea);
		
	}
	
	public void cleanForm(){
		inputNombre.setText("");
		inputDocumento.setText("");
		inputGeneracion.setValue(0);
		selectArea.setSelectedItem(null);
	}
	
	public UsuarioBusquedaVO search() {
		UsuarioBusquedaVO vo = UsuarioBusquedaVO.builder()
				.nombre(inputNombre.getText())
				.area((EnumTutorArea) selectArea.getSelectedItem())
				.documento(inputDocumento.getText())
				.generacion((int) inputGeneracion.getValue())
				.build();
		return vo;
	}

}
