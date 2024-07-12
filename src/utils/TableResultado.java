package utils;

import model.Resultado;
import model.Valor;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableResultado extends AbstractTableModel {
    private List<Resultado> listaResultados = new ArrayList<Resultado>();
    protected String[] columnNames = new String[]{
            "ID Resultado", "Practica", "ID Petición", "Valores"};
    protected Class[] columnClasses = new Class[]{
            Integer.class, String.class, Integer.class, ArrayList.class};

    @Override
    public String getColumnName(int column) {return columnNames[column];}
    public Class getColumnClass(int column) {return columnClasses[column];}

    @Override
    public int getRowCount() {
        return listaResultados.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> listaResultados.get(rowIndex).getIdResultado();
            case 1 -> listaResultados.get(rowIndex).getPractica();
            case 2 -> listaResultados.get(rowIndex).getIdPeticion();
            case 3 -> {
                for (Valor v : listaResultados.get(rowIndex).getValores()) {
                    if (v.isReservado()) {
                        yield "Debe retirar por Sucursal";
                    }
                }
                yield listaResultados.get(rowIndex).getValores();
            }
            default -> null;
        };
    }
    public void add(int idResultado, String practica, int idPeticion, ArrayList<Valor> valores) {
        Resultado r = new Resultado(idResultado, practica, idPeticion, valores);
        listaResultados.add(r);
        fireTableDataChanged();
    }

    public void remove(int id) {
        for (int i = 0; i < listaResultados.size(); i++) {
            if (listaResultados.get(i).getIdResultado() == id) {
                listaResultados.remove(i);
                fireTableDataChanged();
            }
        }
    }
}
