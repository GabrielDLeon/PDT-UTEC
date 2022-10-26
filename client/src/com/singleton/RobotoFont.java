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
	
	public static Font getTitulo() {
		return getRobotoFont("Roboto-Bold.ttf", 22f);
	}
	
	public static Font getSubTitulo() {
		return getRobotoFont("Roboto-Bold.ttf", 18f);
	}
	
	public static Font getNormal(float size) {
		return getRobotoFont("Roboto-Regular.ttf", size);
	}
	
	public static Font getNormal() {
		return getRobotoFont("Roboto-Regular.ttf", 14f);
	}
	
	public static Font getBold(float size) {
		return getRobotoFont("Roboto-Bold.ttf", size);
	}
	
	public static Font getBold() {
		return getRobotoFont("Roboto-Bold.ttf", 14f);
	}
	
	private static Font getRobotoFont(String type, float size) {
		try {
			roboto = Font.createFont(Font.TRUETYPE_FONT, new File(type)).deriveFont(size);	
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(type)));
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		return roboto;
	}
}
