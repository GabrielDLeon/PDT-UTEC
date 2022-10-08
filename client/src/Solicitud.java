import java.time.LocalDate;

public class Solicitud {

    private LocalDate fecha;
    /*
     * En el documento guia es de tipo Date, pero debido a que todas las demas
     * clases utilizan LocalDate decidi optar por este.
     */

    public LocalDate getFecha() {
	return fecha;
    }

    public void setFecha(LocalDate fecha) {
	this.fecha = fecha;
    }

    public Solicitud() {

    }

    public Solicitud(LocalDate fecha) {
	super();
	this.fecha = fecha;
    }

    @Override
    public String toString() {
	return "Solicitud [fecha=" + fecha + "]";
    }

    public void generarReporteSoli() {

    }

    public void verificarSolicitud() {

    }

}
