package model;

import java.util.ArrayList;

public class Sucursal {
    private int id;
    private String direccion;
    private int numero;
    private boolean peticionResultadosFinalizados;

    public Sucursal(int id, String direccion, int numero, boolean peticionResultadosFinalizados) {
        this.id = id;
        this.direccion = direccion;
        this.numero = numero;
        this.peticionResultadosFinalizados = peticionResultadosFinalizados;
    }

    public ArrayList<Peticion> getPeticiones() {
        return peticiones;
    }

    public void setPeticiones(ArrayList<Peticion> peticiones) {
        this.peticiones = peticiones;
    }

    private ArrayList<Peticion> peticiones;

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

    public boolean isPeticionResultadosFinalizados() {
        return peticionResultadosFinalizados;
    }

    public void setPeticionResultadosFinalizados(boolean peticionResultadosFinalizados) {
        this.peticionResultadosFinalizados = peticionResultadosFinalizados;
    }

    public boolean isPeticionesResultadosFinalizados() {
        return peticionResultadosFinalizados;
    }
}
