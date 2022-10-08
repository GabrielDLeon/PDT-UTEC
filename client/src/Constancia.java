import java.time.LocalDate;

public class Constancia {

    private Tipo tipo;
    private LocalDate Fecha;
    /*
     * En el documento guia es de tipo Date, pero debido a que todas las demas
     * clases utilizan LocalDate decidi optar por este.
     */

    public Tipo getTipo() {
	return tipo;
    }

    public void setTipo(Tipo tipo) {
	this.tipo = tipo;
    }

    public LocalDate getFecha() {
	return Fecha;
    }

    public void setFecha(LocalDate fecha) {
	Fecha = fecha;
    }

    public Constancia() {

    }

    public Constancia(Tipo tipo, LocalDate fecha) {
	super();
	this.tipo = tipo;
	Fecha = fecha;
    }

    @Override
    public String toString() {
	return "Constancia [tipo=" + tipo + ", Fecha=" + Fecha + "]";
    }

    public void generarReporteConst() {

    }

    public void generarPDF() {

    }

}
