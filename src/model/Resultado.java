package model;

import java.util.ArrayList;

public class Resultado {
    private int idResultado;
    private String practica;
    private int idPeticion;
    private ArrayList<Valor> valores;

    public Resultado(int idResultado, String practica, int idPeticion, ArrayList<Valor> valores) {
        this.idResultado = idResultado;
        this.practica = practica;
        this.idPeticion = idPeticion;
        this.valores = valores;
    }

    public int getIdResultado() {
        return idResultado;
    }

    public void setIdResultado(int idResultado) {
        this.idResultado = idResultado;
    }

    public String getPractica() {
        return practica;
    }

    public void setPractica(String practica) {
        this.practica = practica;
    }

    public int getIdPeticion() {
        return idPeticion;
    }

    public void setIdPeticion(int idPeticion) {
        this.idPeticion = idPeticion;
    }

    public ArrayList<Valor> getValores() {
        return valores;
    }


    public void setValores(ArrayList<Valor> valores) {
        this.valores = valores;
    }
}
