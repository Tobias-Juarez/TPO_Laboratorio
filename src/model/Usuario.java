package model;

public class Usuario {
    private String nombre;
    private String email;
    private String usuario;
    private String password;
    private String domicilio;
    private int dni;
    private String fechaNacimiento;
    private RolUsuario rol;

    public Usuario() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    @Override
    public String toString() {
        return "Usuario{" +
            "nombre='" + nombre + '\'' +
            ", email='" + email + '\'' +
            ", usuario='" + usuario + '\'' +
            ", password='" + password + '\'' +
            ", domicilio='" + domicilio + '\'' +
            ", dni=" + dni +
            ", fechaNacimiento=" + fechaNacimiento +
            ", rol=" + rol +
            '}';
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public RolUsuario getRol() {
        return rol;
    }

    public void setRol(RolUsuario rol) {
        this.rol = rol;
    }
}
