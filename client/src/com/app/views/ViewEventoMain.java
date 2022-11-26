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
import com.app.controllers.EventoEstadoBO;
import com.app.controllers.EventoModalidadBO;
import com.app.singleton.BeanRemoteManager;
import com.app.singleton.RobotoFont;
import com.dto.EventoBusquedaVO;
import com.entities.Analista;
import com.entities.Evento;
import com.entities.EventoEstado;
import com.entities.EventoModalidad;
import com.entities.Itr;
import com.entities.Tutor;
import com.entities.Usuario;
import com.enumerators.EnumEventoEstado;
import com.enumerators.EnumEventoTipo;
import com.formdev.flatlaf.FlatDarkLaf;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;

import javax.swing.JTable;
import javax.naming.NamingException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ViewEventoMain extends JFrame {
	
	private JPanel panel = new JPanel();
	private JPanel contentPane;
	
	private CustomTable tEvento;
	private JScrollPane scrollPane = new JScrollPane();
	
	private JButton btnConvocatoria;
	private JButton btnCreate;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnBuscar;
	private JButton btnClean;
	
	// Para cambiar la perspectiva de usuario se debe modificar el new User() por el tipo de usuario deseado
	// Por ejemplo, si queremos usar la vista Analista: Usuario user = new Analista();
	private Usuario user;
	private EventoBO bo;

	private List<Evento> eventos;
	private final JTextField textField = new JTextField();
	private PanelEventoFiltro panelFiltro;
	
	// Este método main después se borra
	// Solo se utiliza en desarrollo
	public static void main(String[] args) {
		FlatDarkLaf.setup();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewEventoMain frame = new ViewEventoMain(new Analista());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	// Manejadores de UI
	
	private void setup() {
		// Si necesitas utilizar WindowBuilder tienes que comentar la llamada al método setupComboBox()
//		setupComboBox();
		setGridBagLayout();
		eventos = bo.getList();
		refreshTable(null);
		setVisible(true);
	}
	
	public ViewEventoMain(Usuario user) {
		textField.setColumns(10);
		this.user = user;
		this.bo = new EventoBO(user);
		
		panelFiltro = new PanelEventoFiltro(user);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 606);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(panel, BorderLayout.CENTER);
		
		tEvento = new CustomTable();
		tEvento.setColumns("ID", "Título","Tipo", "Modalidad", "Estado", "Inicio", "Fin", "ITR");
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

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		
		btnClean = new JButton("Limpiar Filtros");
		btnClean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clean();
			}
		});
		
		setup();
	}
	
	private void setGridBagLayout() {
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 50, 50, 50, 0, 200, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		panel.add(scrollPane, gbc_scrollPane);
		
		GridBagConstraints gbc_panelFiltro = new GridBagConstraints();
		gbc_panelFiltro.insets = new Insets(0, 0, 5, 5);
		gbc_panelFiltro.fill = GridBagConstraints.BOTH;
		gbc_panelFiltro.gridx = 5;
		gbc_panelFiltro.gridy = 1;
		panel.add(panelFiltro, gbc_panelFiltro);
		
		GridBagConstraints gbc_btnConvocatoria = new GridBagConstraints();
		gbc_btnConvocatoria.gridwidth = 3;
		gbc_btnConvocatoria.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnConvocatoria.insets = new Insets(0, 0, 5, 5);
		gbc_btnConvocatoria.gridx = 1;
		gbc_btnConvocatoria.gridy = 2;
		panel.add(btnConvocatoria, gbc_btnConvocatoria);
		
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBuscar.insets = new Insets(0, 0, 5, 5);
		gbc_btnBuscar.gridx = 5;
		gbc_btnBuscar.gridy = 2;
		panel.add(btnBuscar, gbc_btnBuscar);
		
		GridBagConstraints gbc_btnLimpiar = new GridBagConstraints();
		gbc_btnLimpiar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLimpiar.insets = new Insets(0, 0, 5, 5);
		gbc_btnLimpiar.gridx = 5;
		gbc_btnLimpiar.gridy = 3;
		panel.add(btnClean, gbc_btnLimpiar);
		
		if (user.getClass().equals(Analista.class)) {
			GridBagConstraints gbc_btnCreate = new GridBagConstraints();
			gbc_btnCreate.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnCreate.insets = new Insets(0, 0, 5, 5);
			gbc_btnCreate.gridx = 1;
			gbc_btnCreate.gridy = 3;
			panel.add(btnCreate, gbc_btnCreate);
			
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
	}
	
	// Funciones sobre el Tablero
	
	public void refreshTable(EventoBusquedaVO vo) {
		tEvento.model.setRowCount(0);
		if (vo != null) {
			eventos = bo.search(vo);
		} else {
			eventos = bo.getList();
		}
		
		try {
			for (Evento evento : eventos) {
				Object[] row = new Object[8];
				row[0] = evento.getIdEvento();
				row[1] = evento.getNombre();
				row[2] = evento.getTipo();
				row[3] = evento.getModalidad();
				row[4] = evento.getEstado();
				row[5] = evento.getFechaInicio();
				row[6] = evento.getFechaFin();
				row[7] = evento.getItr();
				tEvento.model.addRow(row);
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
	
	private void search() {
		EventoBusquedaVO vo = panelFiltro.search();
		refreshTable(vo);
	}
	
	
	private void clean() {
		panelFiltro.cleanFilters();
		refreshTable(null);
	}
	
	// Funciones de DDL

	private void create() {
		ViewEventoRegistro viewRegistro = new ViewEventoRegistro(this);
		viewRegistro.setVisible(true);
		viewRegistro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	

	private void update() {
		Evento evento = getEventoFromTable();
		ViewEventoRegistro viewRegistro = new ViewEventoRegistro(this, evento);
		viewRegistro.setVisible(true);
		viewRegistro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	

	private void delete() {
		int row = tEvento.getSelectedRow();
		Long id = (Long) tEvento.getValueAt(row, 0);
		int dialogResult = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el Evento seleccionado?","Confirmación", JOptionPane.YES_NO_OPTION);
		if(dialogResult == JOptionPane.YES_OPTION){
			String mensaje = bo.delete(id);
			eventos = bo.getList();
			refreshTable(null);
			JOptionPane.showMessageDialog(null, mensaje);
		}
	}
	
	
	private void showConvocatoria() {
		Evento evento = getEventoFromTable();
		if (evento != null) {
			ViewAsistenciaMain viewAsistenciaMain = new ViewAsistenciaMain(evento);
			viewAsistenciaMain.setVisible(true);
			viewAsistenciaMain.setDefaultCloseOperation(HIDE_ON_CLOSE);
		} else {
			JOptionPane.showInternalMessageDialog(null, "No se encontró el Evento.");
		}
	}

}
