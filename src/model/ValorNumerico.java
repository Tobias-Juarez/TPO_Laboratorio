package model;

public class ValorNumerico {
    private String nombre;
    private double minimo;
    private double maximo;

    public ValorNumerico(String nombre, double minimo, double maximo) {
        this.nombre = nombre;
        this.minimo = minimo;
        this.maximo = maximo;
    }
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getMinimo() {
        return minimo;
    }

    public void setMinimo(double minimo) {
        this.minimo = minimo;
    }

    public double getMaximo() {
        return maximo;
    }

    public void setMaximo(double maximo) {
        this.maximo = maximo;
    }
}
