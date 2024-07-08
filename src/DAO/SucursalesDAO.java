package DAO;

import model.Sucursal;
import model.Usuario;
import utils.GenericDAO;

public class SucursalesDAO extends GenericDAO {
    public SucursalesDAO() throws Exception {
        super(Sucursal.class , "./src/txtDataFiles/sucursales.json");
    }
}