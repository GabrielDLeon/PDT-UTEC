import java.time.LocalDate;

public class Estudiante extends Usuario {

    private String generacion;
    private int semestre;
    private LocalDate fechaNac;
    private String genero;
    private String localidad;

    public String getGeneracion() {
	return generacion;
    }

    public void setGeneracion(String generacion) {
	this.generacion = generacion;
    }

    public int getSemestre() {
	return semestre;
    }

    public void setSemestre(int semestre) {
	this.semestre = semestre;
    }

    public LocalDate getFechaNac() {
	return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
	this.fechaNac = fechaNac;
    }

    public String getGenero() {
	return genero;
    }

    public void setGenero(String genero) {
	this.genero = genero;
    }

    public String getLocalidad() {
	return localidad;
    }

    public void setLocalidad(String localidad) {
	this.localidad = localidad;
    }

    public Estudiante() {
	super();
    }

    public Estudiante(String nombre, String apellido, int documento, String departamento, String telefono,
	    String nombreUsuario, String mail, String contrasenia) {
	super(nombre, apellido, documento, departamento, telefono, nombreUsuario, mail, contrasenia);
    }

    @Override
    public String toString() {
	return "Estudiante [generacion=" + generacion + ", semestre=" + semestre + ", fechaNac=" + fechaNac
		+ ", genero=" + genero + ", localidad=" + localidad + "]";
    }

    public void listarConvocatorias() {
    }

    public void listarJustificaciones() {
    }

    public void listarConstancias() {
    }

    public void listarReclamos() {
    }

}
