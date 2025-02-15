package controller;

import DAO.PracticasDAO;
import model.*;

import java.util.ArrayList;

public class Laboratorio {
    private static Laboratorio instance;
    private final PracticasDAO practicasDAO;
    private final ArrayList<Practica> practicas;
    private final ArrayList<Resultado> resultados;
    private final ArrayList<Muestra> muestras;
    private AtencionAlPublico atencionAlPublico;

    private Laboratorio() throws Exception {
        this.practicasDAO = new PracticasDAO();
        this.practicas = getPracticas();
        this.resultados = new ArrayList<>();
        this.muestras = new ArrayList<>();
        this.atencionAlPublico = AtencionAlPublico.getInstance();
    }
    public static Laboratorio getInstance() {
        if (instance == null) {
            try {
                instance = new Laboratorio();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
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
    public Practica buscarPracticaPorNombre(String nombre) {
        for (Practica p : practicas) {
            if (p.getNombre().equals(nombre)) {
                return p;
            }
        }
        return null;
    }
    public boolean existePeticionEnPractica(String codigo) {
        for (Peticion p : AtencionAlPublico.getInstance().getPeticionesDePacientes()){
            if (p.getPractica().getCodigo().equals(codigo)){
                return true;
            }
        }
        return false;
    }
}
