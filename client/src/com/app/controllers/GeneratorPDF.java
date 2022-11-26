package com.app.controllers;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import javax.naming.NamingException;

import com.app.singleton.BeanRemoteManager;
import com.entities.Asistencia;
import com.entities.Estudiante;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class GeneratorPDF {

	private static Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
    private static Font subFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
    private static Font smallFont = new Font(Font.FontFamily.HELVETICA, 12);
    private static Font smallBoldFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
    
	public static void main(String[] args) {
		try {
			Estudiante estudiante = BeanRemoteManager.getBeanUsuario().findEstudiante(2L);
			generatePDF(estudiante);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void generatePDF(Estudiante estudiante) throws FileNotFoundException, DocumentException, NamingException {
		FileOutputStream file = new FileOutputStream("reports/reporte.pdf");
		Document document = new Document();
		PdfWriter.getInstance(document, file);
		document.open();
		
		addMetaData(document);
		addTitle(document);
		addContent(document, estudiante);
		document.close();
	}
	
	private static void addMetaData(Document document) {
        document.addTitle("Escolaridad");
        document.addSubject("App");
        document.addKeywords("Reporte, Escolaridad");
        document.addAuthor("NULL");
        document.addCreator("NULL");
    }
	
	private static void addTitle(Document document) throws DocumentException {
		Paragraph preface = new Paragraph();
        preface.add(new Paragraph("Reporte de Escolaridad", titleFont));
        preface.add(new Paragraph("Reporte generado por: " + System.getProperty("user.name") + ", " + new Date(), smallFont));
        document.add(preface);
	}
	
	private static void addContent(Document document, Estudiante estudiante) throws DocumentException, NamingException {
		Paragraph preface = new Paragraph();
		
		String nombre1 = convertToString(estudiante.getNombre1());
		String nombre2 = convertToString(estudiante.getNombre2());
		String apellido1 = convertToString(estudiante.getApellido1());
		String apellido2 = convertToString(estudiante.getApellido2());
		String documento = convertToString(estudiante.getDocumento());
		if (documento.isEmpty()) documento = "Documento no registrado";
		
		preface.add(new Paragraph("Datos del Estudiante",titleFont));
		preface.add(new Paragraph("Nombre completo: "+nombre1+ " " +nombre2+ " " + apellido1 + " "+ apellido2));
		preface.add(new Paragraph("Documento: "+ documento));
		
		PdfPTable table = new PdfPTable(3);
		addTableHeader(table);
		addRows(table, estudiante.getIdUsuario());
		
		addEmptyLine(preface, 2);
		document.add(preface);
		document.add(table);
	}
	
	private static String convertToString(String s) {
		s = Objects.toString(s, "").toUpperCase();
		return s;
	}
	
	private static void addTableHeader(PdfPTable table) {
	    Stream.of("Nombre del Evento", "Estado de Asistencia", "Calificación")
	      .forEach(columnTitle -> {
	        PdfPCell header = new PdfPCell();
	        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        header.setPhrase(new Phrase(columnTitle));
	        table.addCell(header);
	    });
	}
	
	private static void addRows(PdfPTable table, Long idEstudiante) throws NamingException {
		List<Asistencia> asistencias = BeanRemoteManager.getBeanAsistencia().findByEstudiante(idEstudiante);
		asistencias.forEach(asistencia -> {
			table.addCell(asistencia.getEvento().getNombre());
			table.addCell(asistencia.getEstado().toString());
			table.addCell(Objects.toString(asistencia.getCalificacion(), ""));
		});
	}
	
	private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
