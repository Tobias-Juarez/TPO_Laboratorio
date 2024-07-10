package model;

import java.util.ArrayList;

public class Peticion {
    private int id;
    private String obraSocial;
    private String fechaCarga;
    private String fechaEntrega;
    private String estado;
    private Practica practica;
    private ArrayList<Resultado> resultados;

    public Peticion(int id, String obraSocial, String fechaCarga, String fechaEntrega, String estado, Practica practica) {
        this.id = id;
        this.obraSocial = obraSocial;
        this.fechaCarga = fechaCarga;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
        this.practica = practica;
        this.resultados = new ArrayList<>();}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(String obraSocial) {
        this.obraSocial = obraSocial;
    }

    public String getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(String fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public Practica getPractica() {
        return practica;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setPractica(Practica practica) {
        this.practica = practica;
    }
}
