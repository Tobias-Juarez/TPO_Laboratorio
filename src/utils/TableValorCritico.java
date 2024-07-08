package utils;

import model.Paciente;
import model.ValorNumerico;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableValorCritico extends AbstractTableModel {
    private List<ValorNumerico> listaValores = new ArrayList<ValorNumerico>();
    protected String[] columnNames = new String[]{
            "Nombre", "Limite inferior", "Limite superior"};
    protected Class[] columnClasses = new Class[]{
            String.class, Double.class, Double.class};

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
            case 1 -> listaValores.get(rowIndex).getMinimo();
            case 2 -> listaValores.get(rowIndex).getMaximo();
            default -> null;
        };
    }
    public void add(String nombre, double minimo, double maximo) {
        ValorNumerico v = new ValorNumerico(nombre, minimo, maximo);
        listaValores.add(v);
        fireTableDataChanged();
    }

    public void remove(String nombre) {
        for (int i = 0; i < listaValores.size(); i++) {
            if (listaValores.get(i).getNombre().equals(nombre)) {
                listaValores.remove(i);
                fireTableDataChanged();
            }
        }
    }
}
