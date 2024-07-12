package utils;

import model.RolUsuario;
import model.Usuario;
import model.Valor;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableValor extends AbstractTableModel {
    private List<Valor> listaValores = new ArrayList<>();
    protected String[] columnNames = new String[]{
            "Nombre", "Valor", "Reservado"};
    protected Class[] columnClasses = new Class[]{
            String.class, Integer.class, Boolean.class};

    @Override
    public String getColumnName(int column) {return columnNames[column];}
    public Class getColumnClass(int column) {return columnClasses[column];}

    @Override
    public int getRowCount() {
        return listaValores.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> listaValores.get(rowIndex).getNombre();
            case 1 -> listaValores.get(rowIndex).getValor();
            case 2 -> listaValores.get(rowIndex).isReservado();
            default -> null;
        };
    }
    public void add(String nombre, int valor, boolean reservado) {
        Valor v = new Valor(nombre, valor, reservado);
        listaValores.add(v);
        fireTableDataChanged();
    }

    public void remove(String usuario) {
        for (int i = 0; i < listaValores.size(); i++) {
            if (listaValores.get(i).getNombre().equals(usuario)) {
                listaValores.remove(i);
                fireTableDataChanged();
                return;
            }
        }
    }
}