package model;

public class Valor {
    private String nombre;
    private int valor;
    private boolean reservado;
    public Valor(String nombre,int valor, boolean reservado) {
        this.nombre = nombre;
        this.valor = valor;
        this.reservado = reservado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public boolean isReservado() {
        return reservado;
    }

    public void setReservado(boolean reservado) {
        this.reservado = reservado;
    }

    @Override
    public String toString() {
        return  "nombre='" + nombre + '\'' +
                ", valor=" + valor;
    }
}
