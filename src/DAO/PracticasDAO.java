package DAO;

import model.Paciente;
import model.Practica;
import utils.GenericDAO;

public class PracticasDAO extends GenericDAO {
    public PracticasDAO() throws Exception {
        super(Practica.class , "./src/txtDataFiles/practicas.json");
    }
}