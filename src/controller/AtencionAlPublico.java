package controller;

import DAO.PacientesDAO;
import model.Paciente;
import model.Peticion;
import model.Practica;
import model.Usuario;

import java.util.ArrayList;

public class AtencionAlPublico {
    private static AtencionAlPublico instance;
    private ArrayList<Paciente> pacientes;
    private ArrayList<Peticion> peticiones;
    private PacientesDAO pacientesDAO;
    private AtencionAlPublico() throws Exception {
        this.pacientesDAO = new PacientesDAO();
        this.pacientes = new ArrayList<>();
        this.pacientes = this.pacientesDAO.getAll();
        this.peticiones = new ArrayList<>();

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
        try {
            this.pacientesDAO.saveAll(this.pacientes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void modificarPaciente(int dni, String nombre, String domicilio, String mail, String sexo, int edad) {
        for (Paciente p : pacientes) {
            if (p.getDni() == dni) {
                p.setNombre(nombre);
                p.setDomicilio(domicilio);
                p.setMail(mail);
                p.setSexo(sexo);
                p.setEdad(edad);
                try {
                    this.pacientesDAO.saveAll(this.pacientes);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return;
            }
        }
    }

    public void eliminarPaciente(int dni) {
        this.pacientes.removeIf(paciente -> paciente.getDni() == dni);
        try {
            this.pacientesDAO.saveAll(this.pacientes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void altaPeticion(int dni,int id, String obraSocial, String fechaCarga, String fechaEntrega, String estado, Practica practica) {
        for (Paciente p : pacientes) {
            if (p.getDni() == dni) {
                p.altaPeticion( id, obraSocial, fechaCarga, fechaEntrega, estado, practica);
                try {
                    this.pacientesDAO.saveAll(this.pacientes);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return;
            }
        }
    }
    public void modificarPeticion(int dni, int id, String obraSocial, String fechaCarga, String fechaEntrega, String estado, Practica practica) {
        for (Paciente p : pacientes) {
            if (p.getDni() == dni) {
                p.modificarPeticion(id, obraSocial, fechaCarga, fechaEntrega, estado, practica);
                try {
                    this.pacientesDAO.saveAll(this.pacientes);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return;
            }
        }
    }
    public void bajaPeticion(int dni, int id) {
        for (Paciente p : pacientes) {
            if (p.getDni() == dni) {
                p.bajaPeticion(id);
                try {
                    this.pacientesDAO.saveAll(this.pacientes);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
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

    public void verResultados(Paciente paciente) {
        //TODO
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
}
