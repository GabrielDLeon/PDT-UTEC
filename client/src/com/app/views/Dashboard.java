package com.app.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.naming.NamingException;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.app.themes.Header;
import com.entities.Analista;
import com.entities.Usuario;
import com.formdev.flatlaf.FlatDarkLaf;

import javaswingdev.drawer.Drawer;
import javaswingdev.drawer.DrawerController;
import javaswingdev.drawer.DrawerItem;
import javaswingdev.drawer.EventDrawer;
import net.miginfocom.swing.MigLayout;
import java.awt.GridBagLayout;

public class Dashboard extends JFrame {

	private JPanel contentPane;
	private DrawerController drawer;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlatDarkLaf.setup();
					Usuario u = new Analista();
					Dashboard frame = new Dashboard(u);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Dashboard(Usuario u) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 678);
		contentPane = new JPanel();
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[24px][grow]", "[24px,grow]"));
		
		JLabel lblMenu = new JLabel("");
		contentPane.add(lblMenu, "cell 0 0,alignx left,aligny top");
		lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (drawer.isShow()) {
				    drawer.hide();
				} else {
				    drawer.show();
				}
			}
		});
		lblMenu.setIcon(new ImageIcon(Dashboard.class.getResource("/com/app/themes/Menu.png")));
		lblMenu.setFont(new Font("Roboto", Font.PLAIN, 12));
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBorder(null);
		contentPane.add(desktopPane, "cell 1 0,grow");
		GridBagLayout gbl_desktopPane = new GridBagLayout();
		gbl_desktopPane.columnWidths = new int[]{0};
		gbl_desktopPane.rowHeights = new int[]{0};
		gbl_desktopPane.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_desktopPane.rowWeights = new double[]{Double.MIN_VALUE};
		desktopPane.setLayout(gbl_desktopPane);
		
		DrawerItem d1 = new DrawerItem("Modulo Usuario").icon(new ImageIcon(getClass().getResource("/com/app/themes/Usuario.png")));
		
		DrawerItem d2 = new DrawerItem("Modulo Evento").icon(new ImageIcon(getClass().getResource("/com/app/themes/Evento.png")));
		
		DrawerItem d3 = new DrawerItem("Modulo Constancia").icon(new ImageIcon(getClass().getResource("/com/app/themes/Constancia.png")));
		
		DrawerItem d4 = new DrawerItem("Modulo Escolaridad").icon(new ImageIcon(getClass().getResource("/com/app/themes/Escolaridad.png")));
		
		DrawerItem d5 = new DrawerItem("Salir").icon(new ImageIcon(getClass().getResource("/com/app/themes/Logout.png")));
		
		Header h = new Header();
		
		drawer = Drawer.newDrawer(this)
				.drawerBackground(new Color(65, 65, 65))
		        .enableScroll(true)
		        .header(h)
		        .separator(2, new Color(75, 75, 75))
		        .space(20)
		        .addChild(d1.build())
		        .addChild(d2.build())
		        .addChild(d3.build())
		        .addChild(d4.build())
		        .addFooter(d5.build())
		        
		        .event(new EventDrawer(){
				@Override
				public void selected(int index, DrawerItem item) {
					if (item == d1) {
						desktopPane.removeAll();
						viewCrud vc;
						try {
							vc = new viewCrud();
							desktopPane.add(vc);
							Dimension desktopSize = desktopPane.getSize();
							Dimension FrameSize = vc.getSize();
							vc.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height - FrameSize.height)/2);
							vc.show();
						} catch (NamingException e) {
							e.printStackTrace();
						}
				        drawer.hide();
					} else if (item == d2) {
						desktopPane.removeAll();
					} else if (item == d5) {
						dispose();
					} 
				}
		        })

		        .build();
		
	} 
}

/*
 * 		model.addColumn("ID");
		model.addColumn("Nombre 1");
		model.addColumn("Nombre 2");
		model.addColumn("Apellido 1");
		model.addColumn("Apellido 2");
		model.addColumn("Genero");
		model.addColumn("Fecha Nac.");
		model.addColumn("Documento");
		model.addColumn("Telefono");
		model.addColumn("Correo");
		model.addColumn("Departamento");
		model.addColumn("Localidad");
		model.addColumn("ITR");
		model.addColumn("Usuario");
		model.addColumn("Contraseña");
		model.addColumn("Estado");
 * 
 * */
