import java.time.LocalDate;

public class Reclamo {

    private LocalDate fecha;
    private String asunto;
    private String mensaje;

    public LocalDate getFecha() {
	return fecha;
    }

    public void setFecha(LocalDate fecha) {
	this.fecha = fecha;
    }

    public String getAsunto() {
	return asunto;
    }

    public void setAsunto(String asunto) {
	this.asunto = asunto;
    }

    public String getMensaje() {
	return mensaje;
    }

    public void setMensaje(String mensaje) {
	this.mensaje = mensaje;
    }

    public Reclamo() {

    }

    public Reclamo(LocalDate fecha, String asunto, String mensaje) {
	super();
	this.fecha = fecha;
	this.asunto = asunto;
	this.mensaje = mensaje;
    }

    @Override
    public String toString() {
	return "Reclamo [fecha=" + fecha + ", asunto=" + asunto + ", mensaje=" + mensaje + "]";
    }

}
