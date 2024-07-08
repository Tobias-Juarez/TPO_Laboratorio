package DAO;

import model.Paciente;
import model.Usuario;
import utils.GenericDAO;

public class PacientesDAO extends GenericDAO {
    public PacientesDAO() throws Exception {
        super(Paciente.class , "./src/txtDataFiles/pacientes.json");
    }
}

