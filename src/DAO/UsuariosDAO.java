package DAO;

import model.Usuario;
import utils.GenericDAO;

public class UsuariosDAO extends GenericDAO {
    public UsuariosDAO() throws Exception {
        super(Usuario.class , "./src/txtDataFiles/usuarios.json");
    }
}
