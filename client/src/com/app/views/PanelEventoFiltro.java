package com.app.views;

import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.app.controllers.EventoEstadoBO;
import com.app.controllers.EventoModalidadBO;
import com.app.singleton.BeanRemoteManager;
import com.dto.EventoBusquedaVO;
import com.entities.Analista;
import com.entities.EventoEstado;
import com.entities.EventoModalidad;
import com.entities.Itr;
import com.entities.Tutor;
import com.entities.Usuario;
import com.enumerators.EnumEventoEstado;
import com.enumerators.EnumEventoTipo;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class PanelEventoFiltro extends JPanel {

	private JLabel lblTipo = new JLabel("Tipo de Evento");
	private JLabel lblModalidad = new JLabel("Modalidad");
	private JLabel lblItr = new JLabel("ITR");
	private JLabel lblEstado = new JLabel("Estado del Evento");
	private JLabel lblFiltro = new JLabel("Filtros de búsqueda");
	private JLabel lblBuscador =  new JLabel("Nombre");
	private JLabel lblTutor = new JLabel("Tutor");
	private final JLabel lblFechaInicio = new JLabel("Fecha Inicio");

	private JTextField inputBuscador = new JTextField();
	
	private JDateChooser dateInicio = new JDateChooser();
	
	private JComboBox<Itr> selectItr = new JComboBox<Itr>();
	private JComboBox<EnumEventoTipo> selectTipo = new JComboBox<EnumEventoTipo>();
	private JComboBox<EventoModalidad> selectModalidad = new JComboBox<EventoModalidad>();
	private JComboBox<EventoEstado> selectEstado= new JComboBox<EventoEstado>();
	private JComboBox<Tutor> selectTutor = new JComboBox<Tutor>();
	private JComboBox<EnumEventoEstado> selectFechaInicio = new JComboBox<EnumEventoEstado>();
	
	private Usuario user;
	private EventoEstadoBO estadoBO = new EventoEstadoBO();
	private EventoModalidadBO modalidadBO = new EventoModalidadBO();
	
	public PanelEventoFiltro(Usuario user) {
		this.user = user;
		setupComboBox();
		setGridBagLayout();
		setup();
	}
	
	private void setup() {
		dateInicio.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
	        public void propertyChange(PropertyChangeEvent e) {
	            if ("date".equals(e.getPropertyName())) {
	                boolean state = dateInicio.getDate() != null;
	                selectFechaInicio.setEnabled(state);
	            }
        }});

		JTextFieldDateEditor editor = (JTextFieldDateEditor) dateInicio.getDateEditor();
		editor.setEditable(false);
	}

	public LocalDateTime convertToLocalDateTime(Date dateToConvert) {
	    if (dateToConvert == null) return null;
		return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDateTime();
	}
	
	public EventoBusquedaVO search() {
		String nombre = inputBuscador.getText();
		EnumEventoTipo tipo = (EnumEventoTipo) selectTipo.getSelectedItem();
		EventoModalidad modalidad = (EventoModalidad) selectModalidad.getSelectedItem();
		EventoEstado estado = (EventoEstado) selectEstado.getSelectedItem();
		Itr itr = (Itr) selectItr.getSelectedItem();
		LocalDateTime fechaInicio = convertToLocalDateTime(dateInicio.getDate());
		EnumEventoEstado fechaEstado = (EnumEventoEstado) selectFechaInicio.getSelectedItem();
		
		Tutor tutor = (user.getClass().equals(Tutor.class))
				? (Tutor) user
				: (Tutor) selectTutor.getSelectedItem();
		
		EventoBusquedaVO vo = EventoBusquedaVO.builder()
				.nombre(nombre)
				.tipo(tipo)
				.modalidad(modalidad)
				.estado(estado)
				.itr(itr)
				.tutor(tutor)
				.fechaInicio(fechaInicio)
				.fechaEstado(fechaEstado)
				.build();
		return vo;
	}
	
	private void setupComboBox() {
		selectTipo = new JComboBox<EnumEventoTipo>(EnumEventoTipo.values());
		selectFechaInicio = new JComboBox<EnumEventoEstado>(EnumEventoEstado.values());
		try {
			List<Itr> itrs = BeanRemoteManager.getBeanItr().findAll();
			selectItr = new JComboBox<Itr>(itrs.toArray(new Itr[itrs.size()]));
			
			List<EventoModalidad> modalidades = modalidadBO.findByStatus(true);
			selectModalidad = new JComboBox<EventoModalidad>(modalidades.toArray(new EventoModalidad[modalidades.size()]));
			
			List<EventoEstado> estados = estadoBO.findByStatus(true);
			selectEstado = new JComboBox<EventoEstado>(estados.toArray(new EventoEstado[estados.size()]));
			
			if (user.getClass().equals(Analista.class)) {
				List<Tutor> tutores = BeanRemoteManager.getBeanUsuario().findAllTutores();
				selectTutor = new JComboBox<Tutor>(tutores.toArray(new Tutor[tutores.size()]));
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
		cleanFilters();
	}
	
	public void cleanFilters() {
		inputBuscador.setText("");
		selectTipo.setSelectedItem(null);
		selectModalidad.setSelectedItem(null);
		selectItr.setSelectedItem(null);
		selectEstado.setSelectedItem(null);
		selectTutor.setSelectedItem(null);
		selectFechaInicio.setSelectedItem(null);
		dateInicio.setCalendar(null);
	}

	private void setGridBagLayout() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		GridBagConstraints gbc_lblFiltro = new GridBagConstraints();
		gbc_lblFiltro.gridwidth = 2;
		gbc_lblFiltro.insets = new Insets(0, 0, 5, 5);
		gbc_lblFiltro.gridx = 1;
		gbc_lblFiltro.gridy = 1;
		add(lblFiltro, gbc_lblFiltro);
		
		GridBagConstraints gbc_lblBuscador = new GridBagConstraints();
		gbc_lblBuscador.gridwidth = 2;
		gbc_lblBuscador.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBuscador.insets = new Insets(0, 0, 5, 5);
		gbc_lblBuscador.gridx = 1;
		gbc_lblBuscador.gridy = 3;
		add(lblBuscador, gbc_lblBuscador);
		
		GridBagConstraints gbc_inputBuscador = new GridBagConstraints();
		gbc_inputBuscador.gridwidth = 2;
		gbc_inputBuscador.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputBuscador.insets = new Insets(0, 0, 5, 5);
		gbc_inputBuscador.gridx = 1;
		gbc_inputBuscador.gridy = 4;
		add(inputBuscador, gbc_inputBuscador);
		inputBuscador.setColumns(10);
		
		GridBagConstraints gbc_lblTipo = new GridBagConstraints();
		gbc_lblTipo.gridwidth = 2;
		gbc_lblTipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTipo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipo.gridx = 1;
		gbc_lblTipo.gridy = 5;
		add(lblTipo, gbc_lblTipo);
		
		GridBagConstraints gbc_selectTipo = new GridBagConstraints();
		gbc_selectTipo.gridwidth = 2;
		gbc_selectTipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectTipo.insets = new Insets(0, 0, 5, 5);
		gbc_selectTipo.gridx = 1;
		gbc_selectTipo.gridy = 6;
		add(selectTipo, gbc_selectTipo);
		
		GridBagConstraints gbc_lblModalidad = new GridBagConstraints();
		gbc_lblModalidad.gridwidth = 2;
		gbc_lblModalidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblModalidad.insets = new Insets(0, 0, 5, 5);
		gbc_lblModalidad.gridx = 1;
		gbc_lblModalidad.gridy = 7;
		add(lblModalidad, gbc_lblModalidad);
		
		GridBagConstraints gbc_selectModalidad = new GridBagConstraints();
		gbc_selectModalidad.gridwidth = 2;
		gbc_selectModalidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectModalidad.insets = new Insets(0, 0, 5, 5);
		gbc_selectModalidad.gridx = 1;
		gbc_selectModalidad.gridy = 8;
		add(selectModalidad, gbc_selectModalidad);

		if (selectItr.getItemCount() > 0) {
			GridBagConstraints gbc_lblItr = new GridBagConstraints();
			gbc_lblItr.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblItr.insets = new Insets(0, 0, 5, 5);
			gbc_lblItr.gridx = 1;
			gbc_lblItr.gridy = 9;
			add(lblItr, gbc_lblItr);
			
			GridBagConstraints gbc_selectItr = new GridBagConstraints();
			gbc_selectItr.gridwidth = 2;
			gbc_selectItr.fill = GridBagConstraints.HORIZONTAL;
			gbc_selectItr.insets = new Insets(0, 0, 5, 5);
			gbc_selectItr.gridx = 1;
			gbc_selectItr.gridy = 10;
			add(selectItr, gbc_selectItr);
		}
		
		GridBagConstraints gbc_lblEstado = new GridBagConstraints();
		gbc_lblEstado.gridwidth = 2;
		gbc_lblEstado.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEstado.insets = new Insets(0, 0, 5, 5);
		gbc_lblEstado.gridx = 1;
		gbc_lblEstado.gridy = 11;
		add(lblEstado, gbc_lblEstado);
		
		GridBagConstraints gbc_selectEstado = new GridBagConstraints();
		gbc_selectEstado.gridwidth = 2;
		gbc_selectEstado.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectEstado.insets = new Insets(0, 0, 5, 5);
		gbc_selectEstado.gridx = 1;
		gbc_selectEstado.gridy = 12;
		add(selectEstado, gbc_selectEstado);
		
		if (user.getClass().equals(Analista.class) && selectTutor.getItemCount() > 0) {
			GridBagConstraints gbc_lblTutor = new GridBagConstraints();
			gbc_lblTutor.anchor = GridBagConstraints.WEST;
			gbc_lblTutor.insets = new Insets(0, 0, 5, 5);
			gbc_lblTutor.gridx = 1;
			gbc_lblTutor.gridy = 13;
			add(lblTutor, gbc_lblTutor);
			
			GridBagConstraints gbc_selectTutor = new GridBagConstraints();
			gbc_selectTutor.gridwidth = 2;
			gbc_selectTutor.insets = new Insets(0, 0, 5, 5);
			gbc_selectTutor.fill = GridBagConstraints.HORIZONTAL;
			gbc_selectTutor.gridx = 1;
			gbc_selectTutor.gridy = 14;
			add(selectTutor, gbc_selectTutor);
		}
		
		GridBagConstraints gbc_lblFechaInicio = new GridBagConstraints();
		gbc_lblFechaInicio.gridwidth = 2;
		gbc_lblFechaInicio.anchor = GridBagConstraints.WEST;
		gbc_lblFechaInicio.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaInicio.gridx = 1;
		gbc_lblFechaInicio.gridy = 15;
		add(lblFechaInicio, gbc_lblFechaInicio);
		
		GridBagConstraints gbc_dateInicio = new GridBagConstraints();
		gbc_dateInicio.insets = new Insets(0, 0, 5, 5);
		gbc_dateInicio.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateInicio.gridx = 1;
		gbc_dateInicio.gridy = 16;
		add(dateInicio, gbc_dateInicio);
		selectFechaInicio.setEnabled(false);
		
		GridBagConstraints gbc_selectFechaInicio = new GridBagConstraints();
		gbc_selectFechaInicio.insets = new Insets(0, 0, 5, 0);
		gbc_selectFechaInicio.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectFechaInicio.gridx = 2;
		gbc_selectFechaInicio.gridy = 16;
		add(selectFechaInicio, gbc_selectFechaInicio);
	}

}