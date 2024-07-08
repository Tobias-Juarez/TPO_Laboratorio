package utils;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import model.Practica;

public class TablePractica extends AbstractTableModel {
  private List<Practica> listaPracticas = new ArrayList<Practica>();
  protected String[] columnNames = new String[]{
      "Codigo", "Nombre", "Grupo", "Demora Resultado"};
  protected Class[] columnClasses = new Class[]{
      Integer.class, String.class, String.class, String.class, String.class, Integer.class};

  @Override
  public String getColumnName(int column) {return columnNames[column];}
  public Class getColumnClass(int column) {return columnClasses[column];}

  @Override
  public int getRowCount() {
    return listaPracticas.size();
  }

  @Override
  public int getColumnCount() {
    return this.columnNames.length;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    return switch (columnIndex) {
      case 0 -> listaPracticas.get(rowIndex).getCodigo();
      case 1 -> listaPracticas.get(rowIndex).getNombre();
      case 2 -> listaPracticas.get(rowIndex).getGrupo();
      case 3 -> listaPracticas.get(rowIndex).getDemoraResultado();
      default -> null;
    };
  }
  public void add(String codigo, String nombre, String grupo, int demoraResultado) {
    Practica p = new Practica(codigo, nombre, grupo, demoraResultado);
    listaPracticas.add(p);
    fireTableDataChanged();
  }

  public void remove(String codigo) {
    for (int i = 0; i < listaPracticas.size(); i++) {
      if (listaPracticas.get(i).getCodigo().equals(codigo)) {
        listaPracticas.remove(i);
        fireTableDataChanged();
      }
    }
  }
}
