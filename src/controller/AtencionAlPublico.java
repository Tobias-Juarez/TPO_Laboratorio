package controller;

import DAO.PacientesDAO;
import model.*;

import java.util.ArrayList;

public class AtencionAlPublico {
    private static AtencionAlPublico instance;
    private ArrayList<Paciente> pacientes;
    private ArrayList<Peticion> peticiones;
    private ArrayList<Resultado> resultados;
    private PacientesDAO pacientesDAO;
    private AtencionAlPublico() throws Exception {
        this.pacientesDAO = new PacientesDAO();
        this.pacientes = getPacientes();
        this.peticiones = new ArrayList<>();
        this.resultados = new ArrayList<>();
    }
    public static AtencionAlPublico getInstance() {
        if (instance == null) {
            try {
                instance = new AtencionAlPublico();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    public void altaPaciente(int dni, String nombre, String domicilio, String mail, String sexo, int edad) {
        Paciente paciente = new Paciente(dni, nombre, domicilio, mail, sexo, edad);
        this.pacientes.add(paciente);
        this.guardarPacientes();
    }
    public void modificarPaciente(int dni, String nombre, String domicilio, String mail, String sexo, int edad) {
        for (Paciente p : pacientes) {
            if (p.getDni() == dni) {
                p.setNombre(nombre);
                p.setDomicilio(domicilio);
                p.setMail(mail);
                p.setSexo(sexo);
                p.setEdad(edad);
                this.guardarPacientes();
            }
        }
    }


    public void guardarPacientes() {
        try {
            this.pacientesDAO.saveAll(this.pacientes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void altaPeticion(int dni,int id, String obraSocial, String fechaCarga, String fechaEntrega, Practica practica, Sucursal sucursal) {
        for (Paciente p : pacientes) {
            if (p.getDni() == dni) {
                p.altaPeticion( id, obraSocial, fechaCarga, fechaEntrega, practica, sucursal);
                this.guardarPacientes();
                return;
            }
        }
    }
    public void modificarPeticion(int dni, int id, String obraSocial, String fechaCarga, String fechaEntrega, Practica practica, Sucursal sucursal) {
        for (Paciente p : pacientes) {
            if (p.getDni() == dni) {
                p.modificarPeticion(id, obraSocial, fechaCarga, fechaEntrega, practica, sucursal);
                this.guardarPacientes();
                return;
            }
        }
    }
    public void bajaPeticion(int dni, int id) {
        for (Paciente p : pacientes) {
            if (p.getDni() == dni) {
                p.bajaPeticion(id);
                this.guardarPacientes();
                return;
            }
        }
    }
    public Paciente buscarPaciente(int dni) {
        for (Paciente p : pacientes) {
            if (p.getDni()==dni) {
                return p;
            }
        }
        return null;
    }


    public void listarPeticionesConValoresCriticos() {
        //TODO
    }
    public void consultarResultados(Peticion peticionId) {
        //TODO
    }

    public ArrayList<Paciente> getPacientes() {
        try {
            return this.pacientesDAO.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existePaciente(int dni) {
        return this.pacientes.stream().anyMatch(paciente -> paciente.getDni() == dni);
    }

    public void bajaPaciente(int dni) {
        for (int i = 0; i < pacientes.size(); i++) {
            if (pacientes.get(i).getDni() == dni) {
                pacientes.remove(i);
                this.guardarPacientes();
                return;
            }
        }
    }
    public ArrayList<Peticion> getPeticionesDePacientes() {
        this.peticiones.clear();
        for (Paciente p : pacientes) {
            this.peticiones.addAll(p.getPeticiones());
        }
        return this.peticiones;
    }
    public ArrayList<Resultado> getResultadosDePeticiones() {
        this.resultados.clear();
        for (Peticion pe : this.peticiones) {
            this.resultados.addAll(pe.getResultados());
        }
        return this.resultados;
    }
    public Practica getPracticaDePeticion(int id) {
        Practica practica = null;
        for (Peticion pe : this.peticiones) {
            if (pe.getId() == id) {
                practica = pe.getPractica();
            }
        }
        return practica;
    }
    public void agregarResultado(int id, Practica practica, int idPeticion, ArrayList<Valor> listaValores) {
        for (Paciente p : pacientes) {
            for (Peticion pe : p.getPeticiones()) {
                if (pe.getId() == idPeticion) {
                    p.cargarResultado(id, practica.getNombre(), idPeticion, listaValores);
                    this.guardarPacientes();
                }
            }
        }
    }
    public void eliminarResultado(int id) {
        for (Paciente p : pacientes) {
            for (Peticion pe : p.getPeticiones()) {
                if (pe.getResultados().stream().anyMatch(r -> r.getIdResultado() == id)){
                    p.eliminarResultado(id);
                    this.guardarPacientes();
                }

            }
        }
    }

    public void setResultadosDePeticion() {
        this.resultados.clear();
        for (Paciente paciente : pacientes) {
            for (Peticion peticion : paciente.getPeticiones()) {
                if (!peticion.getResultados().isEmpty()) {
                    resultados.addAll(peticion.getResultados());
                }
            }
        }
    }

    public ArrayList<Resultado> getResultados() {
        return resultados;
    }

    public boolean tienePeticionesFinalizadas(int dni) {
        for (Paciente p : pacientes) {
            if (p.getDni() == dni) {
                return p.tienePeticionesConResultados();
            }
        }
        return false;
    }
    public boolean tieneValoresReservados(ArrayList<Valor> listaValores) {
        for (Valor v : listaValores) {
            if (v.isReservado()) {
                return true;
            }
        }
        return false;
    }
    public void actualizarEstadoPeticion(int id) {
        for (Paciente p : pacientes) {
            for (Peticion pe : p.getPeticiones()) {
                if (pe.getId() == id) {
                    pe.actualizarEstado();
                    this.guardarPacientes();
                }
            }
        }
    }
}
