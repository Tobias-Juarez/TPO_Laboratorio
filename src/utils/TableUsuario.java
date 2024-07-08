package utils;


import model.RolUsuario;
import model.Usuario;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableUsuario extends AbstractTableModel {
    private  List<Usuario> listaUsuarios = new ArrayList<Usuario>();
    protected String[] columnNames = new String[]{
            "Usuario", "Nombre", "Email", "DNI", "Rol", "Fecha de Nacimiento"};
    protected Class[] columnClasses = new Class[]{
            String.class, String.class, String.class, Integer.class, RolUsuario.class, String.class};

    @Override
    public String getColumnName(int column) {return columnNames[column];}
    public Class getColumnClass(int column) {return columnClasses[column];}

    @Override
    public int getRowCount() {
        return listaUsuarios.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> listaUsuarios.get(rowIndex).getUsuario();
            case 1 -> listaUsuarios.get(rowIndex).getNombre();
            case 2 -> listaUsuarios.get(rowIndex).getEmail();
            case 3 -> listaUsuarios.get(rowIndex).getDni();
            case 4 -> listaUsuarios.get(rowIndex).getRol();
            case 5 -> listaUsuarios.get(rowIndex).getFechaNacimiento();
            default -> null;
        };
    }
    public void add(String usuario, String nombre, String email, int dni, RolUsuario rol, String fechaNacimiento) {
        Usuario u = new Usuario();
        u.setUsuario(usuario);
        u.setNombre(nombre);
        u.setEmail(email);
        u.setDni(dni);
        u.setRol(rol);
        u.setFechaNacimiento(fechaNacimiento);
        listaUsuarios.add(u);
        fireTableDataChanged();
    }

    public void remove(String usuario) {
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getUsuario().equals(usuario)) {
                listaUsuarios.remove(i);
                fireTableDataChanged();
                return;
            }
        }
    }
}
