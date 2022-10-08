
public class Docente extends Usuario {

    private String tipo;
    private String area;

    public String getTipo() {
	return tipo;
    }

    public void setTipo(String tipo) {
	this.tipo = tipo;
    }

    public String getArea() {
	return area;
    }

    public void setArea(String area) {
	this.area = area;
    }

    public Docente() {
	super();
    }

    public Docente(String nombre, String apellido, int documento, String departamento, String telefono,
	    String nombreUsuario, String mail, String contrasenia) {
	super(nombre, apellido, documento, departamento, telefono, nombreUsuario, mail, contrasenia);
    }

    @Override
    public String toString() {
	return "Docente [tipo=" + tipo + ", area=" + area + "]";
    }

    public void registrarAsistencia() {
    }

    public void generarJustificacionAsis() {
    }

    public void generarReporteAsis() {
    }

}
