package controller;

import DAO.PracticasDAO;
import model.*;

import java.util.ArrayList;

public class Laboratorio {
    private static Laboratorio instance;
    private PracticasDAO practicasDAO;
    private ArrayList<Practica> practicas;
    private ArrayList<Resultado> resultados;
    private ArrayList<Muestra> muestras;
    private Laboratorio() {
        this.practicas = new ArrayList<>();
        this.resultados = new ArrayList<>();
        this.muestras = new ArrayList<>();
        try {
            this.practicasDAO = new PracticasDAO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static Laboratorio getInstance() {
        if (instance == null) {
            instance = new Laboratorio();
        }
        return instance;
    }

    public ArrayList<Practica> getPracticas() {
        try {
            return practicasDAO.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Resultado> getResultados() {
        return resultados;
    }

    public ArrayList<Muestra> getMuestras() {
        return muestras;
    }

    public void altaPractica(String codigo, String nombre, String grupo, int demoraResultado, ArrayList<ValorNumerico> listaValoresCriticos) {
        Practica p = new Practica(codigo, nombre, grupo, demoraResultado);
        p.addValoresNumericos(listaValoresCriticos);
        this.practicas.add(p);
        try {
            this.practicasDAO.saveAll(this.practicas);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void bajaPractica(String codigo){
        for (int i = 0; i < practicas.size(); i++) {
            if (practicas.get(i).getCodigo().equals(codigo)) {
                practicas.remove(i);
                try {
                    practicasDAO.saveAll(practicas);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return;
            }
        }
    }
    public void cargarResultado(Resultado resultado) {
        //TODO
    }
    public void procesarMuestra(Muestra muestra) {
        //TODO
    }

    @Override
    public String toString() {
        return "Laboratorio{" +
            "practicas=" + practicas +
            ", resultados=" + resultados +
            ", muestras=" + muestras +
            '}';
    }

    public boolean existePractica(String codigo) {
        for (Practica p : practicas) {
            if (p.getCodigo().equals(codigo)) {
                return true;
            }
        }
        return false;
    }
    public Practica buscarPractica(String codigo) {
        for (Practica p : practicas) {
            if (p.getCodigo().equals(codigo)) {
                return p;
            }
        }
        return null;
    }
}
