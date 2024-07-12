package controller;

import DAO.SucursalesDAO;
import DAO.UsuariosDAO;
import model.*;

import java.util.ArrayList;

public class SistemaDeGestion {
    private static SistemaDeGestion instance;
    private ArrayList<Usuario> usuarios;
    private UsuariosDAO usuariosDAO;
    private SucursalesDAO sucursalesDAO;
    private ArrayList<Sucursal> sucursales;
    private AtencionAlPublico atencionAlPublico;
    private SistemaDeGestion() throws Exception {
        this.usuariosDAO = new UsuariosDAO();
        this.sucursalesDAO = new SucursalesDAO();
        this.usuarios = getUsuarios();
        this.sucursales = getSucursales();
    }
    public static SistemaDeGestion getInstance() {
        if (instance == null) {
            try {
                instance = new SistemaDeGestion();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }
    public void altaUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
        try {
            this.usuariosDAO.saveAll(this.usuarios);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void guardarSucursales() {
        try {
            this.sucursalesDAO.saveAll(this.sucursales);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void setPeticiones(ArrayList<Peticion> peticiones){
        for (Sucursal s : this.sucursales) {
            for (Peticion p : peticiones) {
                if (p.getSucursal().getId() == s.getId()) {
                    if (s.getPeticiones().stream().anyMatch(peticion -> peticion.getId() == p.getId())) {
                        s.getPeticiones().removeIf(peticion -> peticion.getId() == p.getId());
                        s.getPeticiones().add(p);
                    }else {
                        s.getPeticiones().add(p);
                    }
                }
            }
        }
    }
    public boolean tienePeticionesFinalizadas(int id) {
        for (Sucursal s : sucursales) {
            if (s.getId() == id) {
                return s.tienePeticionesConResultados();
            }
        }
        return false;
    }
    public void altaSucursal(int id, String direccion, int numero, Usuario responsableTecnico) {
        Sucursal sucursal= new Sucursal(id, direccion, numero, responsableTecnico);
        this.sucursales.add(sucursal);
        this.guardarSucursales();
    }
    public void setSucursales(ArrayList<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }

    public ArrayList<Usuario> getUsuarios() {
        try {
            return this.usuariosDAO.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "SistemaDeGestion{" +
            "usuarios=" + usuarios +
            ", sucursales=" + sucursales +
            '}';
    }
    public boolean existeUsuario(String usuario) {
        for (Usuario u : usuarios) {
            if (u.getUsuario().equals(usuario)) {
                return true;
            }
        }
        return false;
    }

    public Usuario buscarUsuario(String usuario) {
        for (Usuario u : usuarios) {
            if (u.getUsuario().equals(usuario)) {
                return u;
            }
        }
        return null;
    }

    public void bajaUsuario(String usuario) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getUsuario().equals(usuario)) {
                usuarios.remove(i);
                try {
                    this.usuariosDAO.saveAll(this.usuarios);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return;
            }
        }
    }

  public boolean existeSucursal(int id) {
      for (Sucursal s : sucursales) {
          if (s.getId()== id) {
              return true;
          }
      }
      return false;
  }

    public Sucursal buscarSucursal(int id) {
        for (Sucursal s : sucursales) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }
    public ArrayList<Sucursal> getSucursales() {
        try {
            return this.sucursalesDAO.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void bajaSucursal(int id) {
        for (int i = 0; i < sucursales.size(); i++) {
            if (sucursales.get(i).getId() == id) {
                sucursales.remove(i);
                this.guardarSucursales();
                return;
            }
        }
    }

}
