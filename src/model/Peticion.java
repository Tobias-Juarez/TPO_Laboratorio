package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class Peticion {
    private int id;
    private String obraSocial;
    private String fechaCarga;
    private String fechaEntrega;
    private String estado;
    private String practica;
    private ArrayList<Resultado> resultados;

    public Peticion(int id, String obraSocial, String fechaCarga, String fechaEntrega, String estado, String practica) {
        this.id = id;
        this.obraSocial = obraSocial;
        this.fechaCarga = fechaCarga;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
        this.practica = practica;
    }

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

    public String getPractica() {
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

    public void setPractica(String practica) {
        this.practica = practica;
    }
}
