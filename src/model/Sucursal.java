package model;

import java.util.ArrayList;

public class Sucursal {
    private int id;
    private String direccion;
    private int numero;
    ArrayList<Peticion> peticiones;

    public Sucursal(int id, String direccion, int numero) {
        this.id = id;
        this.direccion = direccion;
        this.numero = numero;
        this.peticiones = new ArrayList<>();
    }

    public ArrayList<Peticion> getPeticiones() {
        return peticiones;
    }

    public void setPeticiones(ArrayList<Peticion> peticiones) {
        this.peticiones = peticiones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }


    public boolean tienePeticionesConResultados() {
        for (Peticion p : peticiones) {
            if (!p.getResultados().isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
