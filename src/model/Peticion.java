package model;

import java.util.ArrayList;

public class Peticion {
    private int id;
    private String obraSocial;
    private String fechaCarga;
    private String fechaEntrega;
    private String estado;
    private Practica practica;
    private Sucursal sucursal;
    private final ArrayList<Resultado> resultados = new ArrayList<>();

    public Peticion(int id, String obraSocial, String fechaCarga, String fechaEntrega, Practica practica, Sucursal sucursal) {
        this.id = id;
        this.obraSocial = obraSocial;
        this.fechaCarga = fechaCarga;
        this.fechaEntrega = fechaEntrega;
        this.practica = practica;
        this.sucursal = sucursal;
        ;}

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

    public void actualizarEstado() {
        if (!resultados.isEmpty()){
            this.estado = "Finalizado";
        } else {
            this.estado = "En proceso";
        }
    }

    public void setPractica(Practica practica) {
        this.practica = practica;
    }

    public ArrayList<Resultado> getResultados() {
        return resultados;
    }



    @Override
    public String toString() {
        return "Peticion{" +
                "id=" + id +
                ", obraSocial='" + obraSocial + '\'' +
                ", fechaCarga='" + fechaCarga + '\'' +
                ", fechaEntrega='" + fechaEntrega + '\'' +
                ", estado='" + estado + '\'' +
                ", practica=" + practica +
                ", resultados=" + resultados +
                '}';
    }

    public void agregarResultado(int id, String practica, int idPeticion, ArrayList<Valor> valores) {
        Resultado r = new Resultado(id, practica, idPeticion, valores);

        this.resultados.add(r);
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void eliminarResultado(int id) {
        this.resultados.removeIf(resultado -> resultado.getIdResultado() == id);
    }

}
