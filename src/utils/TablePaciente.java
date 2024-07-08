package utils;

import model.Paciente;
import model.RolUsuario;
import model.Usuario;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TablePaciente extends AbstractTableModel {
    private List<Paciente> listaPacientes = new ArrayList<Paciente>();
    protected String[] columnNames = new String[]{
            "DNI", "Nombre", "Domicilio", "Mail", "Sexo", "Edad"};
    protected Class[] columnClasses = new Class[]{
            Integer.class, String.class, String.class, String.class, String.class, Integer.class};

    @Override
    public String getColumnName(int column) {return columnNames[column];}
    public Class getColumnClass(int column) {return columnClasses[column];}

    @Override
    public int getRowCount() {
        return listaPacientes.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> listaPacientes.get(rowIndex).getDni();
            case 1 -> listaPacientes.get(rowIndex).getNombre();
            case 2 -> listaPacientes.get(rowIndex).getDomicilio();
            case 3 -> listaPacientes.get(rowIndex).getMail();
            case 4 -> listaPacientes.get(rowIndex).getSexo();
            case 5 -> listaPacientes.get(rowIndex).getEdad();
            default -> null;
        };
    }
    public void add(int dni, String nombre, String domicilio, String mail, String sexo, int edad) {
        Paciente p = new Paciente();
        p.setDni(dni);
        p.setNombre(nombre);
        p.setDomicilio(domicilio);
        p.setMail(mail);
        p.setSexo(sexo);
        p.setEdad(edad);
        listaPacientes.add(p);
        fireTableDataChanged();
    }

    public void remove(int dni) {
        for (int i = 0; i < listaPacientes.size(); i++) {
            if (listaPacientes.get(i).getDni() == dni) {
                listaPacientes.remove(i);
                fireTableDataChanged();
            }
        }
    }
}
