package utils;


import model.Peticion;
import model.Practica;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TablePeticion extends AbstractTableModel {
    private List<Peticion> listaPeticiones = new ArrayList<Peticion>();
    protected String[] columnNames = new String[]{
            "ID de Petición", "Obra Social", "Fecha de carga", "Fecha de entrega", "Estado", "Practica"};
    protected Class[] columnClasses = new Class[]{
            Integer.class, String.class, String.class, String.class, String.class, String.class};

    @Override
    public String getColumnName(int column) {return columnNames[column];}
    public Class getColumnClass(int column) {return columnClasses[column];}

    @Override
    public int getRowCount() {
        return listaPeticiones.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> listaPeticiones.get(rowIndex).getId();
            case 1 -> listaPeticiones.get(rowIndex).getObraSocial();
            case 2 -> listaPeticiones.get(rowIndex).getFechaCarga();
            case 3 -> listaPeticiones.get(rowIndex).getFechaEntrega();
            case 4 -> listaPeticiones.get(rowIndex).getEstado();
            case 5 -> listaPeticiones.get(rowIndex).getPractica().getNombre();
            default -> null;
        };
    }
    public void add(int id, String obraSocial, String fechaCarga, String fechaEntrega, String estado, Practica practica) {
        Peticion p = new Peticion(id, obraSocial, fechaCarga, fechaEntrega, estado, practica);
        listaPeticiones.add(p);
        fireTableDataChanged();
    }

    public void remove(int id) {
        for (int i = 0; i < listaPeticiones.size(); i++) {
            if (listaPeticiones.get(i).getId() == id) {
                listaPeticiones.remove(i);
                fireTableDataChanged();
            }
        }
    }

    public void clear() {
        listaPeticiones.clear();
        fireTableDataChanged();
    }
}
