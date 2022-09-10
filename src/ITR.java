
public class ITR {

    private String nombre;
    private String departamento;

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getDepartamento() {
	return departamento;
    }

    public void setDepartamento(String departamento) {
	this.departamento = departamento;
    }

    public ITR() {
    }

    public ITR(String nombre, String departamento) {
	super();
	this.nombre = nombre;
	this.departamento = departamento;
    }

    @Override
    public String toString() {
	return "ITR [nombre=" + nombre + ", departamento=" + departamento + "]";
    }

}
