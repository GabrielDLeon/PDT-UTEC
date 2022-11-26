package com.app.views;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import com.app.controllers.ItrDAO;
import com.entities.Departamento;
import com.entities.Itr;
import com.formdev.flatlaf.FlatDarkLaf;
import com.services.users.DepartamentoBeanRemote;

import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ViewItrAux extends JInternalFrame {
	
	private JTextField txtNombre;
	private JButton btnCreate = new JButton("Crear ITR");
	private JButton btnUpdate = new JButton("Modificar ITR");
	private JButton btnDelete = new JButton("Eliminar ITR");
	private JComboBox<Departamento> selectDepartamento;
	
	private DefaultListModel<Itr> model = new DefaultListModel();
	private JList list = new JList(model);
	private List<Itr> itrs = new ArrayList<Itr>();
	
	private List<Departamento> departamentoList;
	
	private ItrDAO dao = new ItrDAO();
	
	public List<Departamento> getDepartamento() throws NamingException {
		DepartamentoBeanRemote beanDepartamento = (DepartamentoBeanRemote) InitialContext
				.doLookup("ejb:/PDT-Server/DepartamentoBean!com.services.users.DepartamentoBeanRemote");
		return departamentoList = beanDepartamento.findAll();
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		FlatDarkLaf.setup();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewItrAux frame = new ViewItrAux();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws NamingException 
	 */
	public ViewItrAux() throws NamingException {
		
		setBounds(100, 100, 880, 670);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblTitle = new JLabel("Seleccione un ITR");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 7;
		gbc_lblTitle.gridy = 1;
		getContentPane().add(lblTitle, gbc_lblTitle);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 7;
		gbc_scrollPane.gridy = 2;
		getContentPane().add(scrollPane, gbc_scrollPane);
		list.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyChar();
				if (key == 27) {
					list.clearSelection();
					txtNombre.setText("");
				}
			}
		});
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) {
					int row = list.getSelectedIndex();
					boolean state = (row >=0) ? true : false;
					btnUpdate.setEnabled(state);
                    btnDelete.setEnabled(state);
                    if (state) {
                    	Itr i = (Itr) list.getSelectedValue();
                    	txtNombre.setText(i.getNombre());
                    	try {
                    		selectDepartamento.setSelectedItem(i.getDepartamento());
                    	} catch (Exception e1) {
                    		e1.printStackTrace();
                    	}
                    }
				}
			}
		});
		
		
		
		scrollPane.setViewportView(list);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 3;
		getContentPane().add(lblNombre, gbc_lblNombre);
		
		txtNombre = new JTextField();
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.gridwidth = 3;
		gbc_txtNombre.insets = new Insets(0, 0, 5, 5);
		gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombre.gridx = 2;
		gbc_txtNombre.gridy = 3;
		getContentPane().add(txtNombre, gbc_txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblDepartamento = new JLabel("Departamento");
		lblDepartamento.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblDepartamento = new GridBagConstraints();
		gbc_lblDepartamento.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartamento.gridx = 1;
		gbc_lblDepartamento.gridy = 4;
		getContentPane().add(lblDepartamento, gbc_lblDepartamento);
		
		ArrayList<Departamento> departamentos = (ArrayList<Departamento>) getDepartamento();
		
		selectDepartamento = new JComboBox<Departamento>(departamentos.toArray(new Departamento[departamentos.size()]));
		selectDepartamento.setFont(new Font("Roboto", Font.PLAIN, 12));
		GridBagConstraints gbc_selectDepartamento = new GridBagConstraints();
		gbc_selectDepartamento.gridwidth = 3;
		gbc_selectDepartamento.insets = new Insets(0, 0, 5, 5);
		gbc_selectDepartamento.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectDepartamento.gridx = 2;
		gbc_selectDepartamento.gridy = 4;
		getContentPane().add(selectDepartamento, gbc_selectDepartamento);
		
		GridBagConstraints gbc_btnCreate = new GridBagConstraints();
		gbc_btnCreate.insets = new Insets(0, 0, 0, 5);
		gbc_btnCreate.gridx = 1;
		gbc_btnCreate.gridy = 6;
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				create();
			}
		});
		getContentPane().add(btnCreate, gbc_btnCreate);
		
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(0, 0, 0, 5);
		gbc_btnDelete.gridx = 2;
		gbc_btnDelete.gridy = 6;
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		getContentPane().add(btnDelete, gbc_btnDelete);
		
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.insets = new Insets(0, 0, 0, 5);
		gbc_btnUpdate.gridx = 3;
		gbc_btnUpdate.gridy = 6;
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		getContentPane().add(btnUpdate, gbc_btnUpdate);
		
		setup();

	}
	
	private void setup() {
		refreshList();
	}

	private void refreshList() {		
		model.removeAllElements();
		itrs = dao.getAll();
		model.addAll(itrs);
	}
	
	private void create() {
		String nombre = txtNombre.getText();
		Departamento d = (Departamento) selectDepartamento.getSelectedItem();
		Itr i = Itr.builder()
				.nombre(nombre)
				.departamento(d)
				.build();
		dao.create(i);
		refreshList();
		txtNombre.setText("");
		JOptionPane.showMessageDialog(null, "Se creo el ITR exitosamente");
	}
	
	private void update() {
		Itr i = (Itr) list.getSelectedValue();
		i.setNombre(txtNombre.getText());
		i.setDepartamento((Departamento) selectDepartamento.getSelectedItem());
		int option = JOptionPane.showConfirmDialog(null, "¿Desea modificar el ITR seleccionado?", "Confirmación", JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION) {
			dao.update(i);
			refreshList();
			txtNombre.setText("");
			JOptionPane.showMessageDialog(null, "Se modifico el ITR Exitosamente");
		}
	}
	
	private void delete() {
		int option = JOptionPane.showConfirmDialog(null, "¿Desea eliminar El ITR seleccionado?", "Confirmación", JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION) {
			Itr i = (Itr) list.getSelectedValue();
			dao.delete(i.getIdItr());
			refreshList();
			txtNombre.setText("");
			JOptionPane.showMessageDialog(null, "Se elimino el ITR exitosamente");
		}
	}
	
}
