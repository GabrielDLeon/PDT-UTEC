package com.singleton;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class RobotoFont {
	
	private static Font roboto;

	private RobotoFont() {
		// TODO Auto-generated constructor stub
	}
	
	public static Font getRobotoFont() {
		try {
			roboto = Font.createFont(Font.TRUETYPE_FONT, new File("Roboto-Regular.ttf")).deriveFont(14f);	
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Roboto-Regular.ttf")));
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		return roboto;
	}
}
