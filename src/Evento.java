import java.time.LocalDate;

public class Evento {

    private String titulo;
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;
    private String informacion;

    public String getTitulo() {
	return titulo;
    }

    public void setTitulo(String titulo) {
	this.titulo = titulo;
    }

    public LocalDate getFechaInicio() {
	return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
	this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFinal() {
	return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
	this.fechaFinal = fechaFinal;
    }

    public String getInformacion() {
	return informacion;
    }

    public void setInformacion(String informacion) {
	this.informacion = informacion;
    }

    public Evento() {

    }

    public Evento(String titulo, LocalDate fechaInicio, LocalDate fechaFinal, String informacion) {
	super();
	this.titulo = titulo;
	this.fechaInicio = fechaInicio;
	this.fechaFinal = fechaFinal;
	this.informacion = informacion;
    }

    @Override
    public String toString() {
	return "Evento [titulo=" + titulo + ", fechaInicio=" + fechaInicio + ", fechaFinal=" + fechaFinal
		+ ", informacion=" + informacion + "]";
    }

    public void listarEstudianteConvocado() {

    }

    public void agregarEstudiante() {

    }

    public void deshabilitarEstudiante() {

    }

    public void eliminarEstudiante() {

    }

    public void registrarAsistencia() {

    }

}
