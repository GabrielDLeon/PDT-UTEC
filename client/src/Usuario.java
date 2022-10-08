
public abstract class Usuario {

    private String nombre;
    private String apellido;
    private int documento;
    private String departamento;
    private String telefono;
    private String nombreUsuario;
    private String mail;
    private String contrasenia;

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getApellido() {
	return apellido;
    }

    public void setApellido(String apellido) {
	this.apellido = apellido;
    }

    public int getDocumento() {
	return documento;
    }

    public void setDocumento(int documento) {
	this.documento = documento;
    }

    public String getDepartamento() {
	return departamento;
    }

    public void setDepartamento(String departamento) {
	this.departamento = departamento;
    }

    public String getTelefono() {
	return telefono;
    }

    public void setTelefono(String telefono) {
	this.telefono = telefono;
    }

    public String getNombreUsuario() {
	return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
	this.nombreUsuario = nombreUsuario;
    }

    public String getMail() {
	return mail;
    }

    public void setMail(String mail) {
	this.mail = mail;
    }

    public String getContrasenia() {
	return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
	this.contrasenia = contrasenia;
    }

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, int documento, String departamento, String telefono,
	    String nombreUsuario, String mail, String contrasenia) {
	super();
	this.nombre = nombre;
	this.apellido = apellido;
	this.documento = documento;
	this.departamento = departamento;
	this.telefono = telefono;
	this.nombreUsuario = nombreUsuario;
	this.mail = mail;
	this.contrasenia = contrasenia;
    }

    @Override
    public String toString() {
	return "Usuario [nombre=" + nombre + ", apellido=" + apellido + ", documento=" + documento + ", departamento="
		+ departamento + ", telefono=" + telefono + ", nombreUsuario=" + nombreUsuario + ", mail=" + mail
		+ ", contrasenia=" + contrasenia + "]";
    }

    public void registro() {
    }

    public void ingreso() {
    }

    public void editarDatos() {
    }

    public void recibirNotificacion() {
    }

    public void verificarIngreso() {
    }

    public void descargarConstancia() {
    }

    public void listarConstancias() {
    }
}
