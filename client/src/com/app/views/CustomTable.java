package com.app.views;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class CustomTable extends JTable {

	public DefaultTableModel model = new DefaultTableModel() {
		@Override
	    public boolean isCellEditable(int row, int column) {
	       return false;
	    }
	};
	
	public void setColumns(String ... columns) {
		for (String column : columns) {
			model.addColumn(column);
		}
		setModel(model);
	}
	
	public CustomTable() {
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setFillsViewportHeight(true);
		
		// Deseleccionar al presionar tecla Esc
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyChar();
				if (key == 27) {
					clearSelection();
				}
			}
		});
	}
	
}
