package utils;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Paciente;
import model.Sucursal;
import model.Usuario;

public class TableSucursal extends AbstractTableModel {
  private List<Sucursal> listaSucursales = new ArrayList<Sucursal>();
  protected String[] columnNames = new String[]{
      "ID", "Dirección", "Numero", "Responsable Técnico"};
  protected Class[] columnClasses = new Class[]{
      Integer.class, String.class, String.class, String.class};

  @Override
  public String getColumnName(int column) {return columnNames[column];}
  public Class getColumnClass(int column) {return columnClasses[column];}

  @Override
  public int getRowCount() {
    return listaSucursales.size();
  }

  @Override
  public int getColumnCount() {
    return this.columnNames.length;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    return switch (columnIndex) {
      case 0 -> listaSucursales.get(rowIndex).getId();
      case 1 -> listaSucursales.get(rowIndex).getDireccion();
      case 2 -> listaSucursales.get(rowIndex).getNumero();
      case 3 -> listaSucursales.get(rowIndex).getResponsableTecnico().getUsuario();
      default -> null;
    };
  }
  public void add(int id, String direccion, int numero, Usuario responsableTecnico) {
    Sucursal s = new Sucursal(id, direccion, numero, responsableTecnico);
    listaSucursales.add(s);
    fireTableDataChanged();
  }

  public void remove(int id) {
    for (int i = 0; i < listaSucursales.size(); i++) {
      if (listaSucursales.get(i).getId() == id) {
        listaSucursales.remove(i);
        fireTableDataChanged();
      }
    }
  }

}
