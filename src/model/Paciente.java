package model;

import java.util.ArrayList;

public class Paciente {
    private int dni;
    private String nombre;
    private String domicilio;
    private String mail;
    private String sexo;
    private int edad;
    private ArrayList<Peticion> peticiones;

    public Paciente(int dni, String nombre, String domicilio, String mail, String sexo, int edad) {
        this.dni = dni;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.mail = mail;
        this.sexo = sexo;
        this.edad = edad;
        this.peticiones = new ArrayList<>();
    }
    public ArrayList<Peticion> getPeticiones() {
        return peticiones;
    }

    public void altaPeticion( int id, String obraSocial, String fechaCarga, String fechaEntrega, String estado, Practica practica) {
        Peticion p = new Peticion(id, obraSocial, fechaCarga, fechaEntrega, estado, practica);
        this.peticiones.add(p);
    }
    public void modificarPeticion(int id, String obraSocial, String fechaCarga, String fechaEntrega, String estado, Practica practica) {
        for (Peticion p : peticiones) {
            if (p.getId() == id) {
                p.setObraSocial(obraSocial);
                p.setFechaCarga(fechaCarga);
                p.setFechaEntrega(fechaEntrega);
                p.setEstado(estado);
                p.setPractica(practica);
                return;
            }
        }
    }
    public void bajaPeticion(int id) {
        this.peticiones.removeIf(peticion -> peticion.getId() == id);
    }


    public int getDni() {
        return this.dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void cargarResultado( int id, String practica, int idPeticion, ArrayList<Valor> valores) {
        for (Peticion p : peticiones) {
            if (p.getId() == idPeticion) {
                p.agregarResultado(id, practica, idPeticion, valores);
            }
        }
    }

    @Override
    public String toString() {
        return "Paciente{" +
            "dni=" + dni +
            ", nombre='" + nombre + '\'' +
            ", domicilio='" + domicilio + '\'' +
            ", mail='" + mail + '\'' +
            ", sexo='" + sexo + '\'' +
            ", edad=" + edad +
            ", peticiones=" + peticiones +
            '}';
    }

    public void eliminarResultado(int id) {
        for (Peticion p : peticiones) {
            p.eliminarResultado(id);
        }
    }
}
