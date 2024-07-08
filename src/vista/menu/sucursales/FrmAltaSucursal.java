package vista.menu.sucursales;

import controller.SistemaDeGestion;
import java.awt.Window;
import javax.swing.*;
import utils.TableSucursal;

public class FrmAltaSucursal extends JDialog {

  private JPanel pnlPrincipal;
    private JTextField txtIdSucursal;
  private JTextField txtDireccion;
  private JTextField txtNumero;
  private JButton crearSucursalButton;
  private JLabel lblId;
  private JLabel lblDireccion;
  private JLabel lblNumero;

  public FrmAltaSucursal(Window owner, String titulo, SistemaDeGestion sistemaDeGestion,
      TableSucursal tableModel) {
    super(owner, titulo);
    setContentPane(pnlPrincipal);
    setModal(true);
    setSize(500, 400);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    asociarEventos(sistemaDeGestion, tableModel);
  }

  private void asociarEventos(SistemaDeGestion sistemaDeGestion, TableSucursal tableModel) {
    crearSucursalButton.addActionListener(e -> {
      String idInput = txtIdSucursal.getText();
      String direccion = txtDireccion.getText();
      String numeroInput = txtNumero.getText();

      try {
        int id=Integer.parseInt(idInput);
        int numero=Integer.parseInt(numeroInput);
        if (idInput.isEmpty() || direccion.isEmpty() || numeroInput.isEmpty()) {
          JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (sistemaDeGestion.existeSucursal(id)) {
          JOptionPane.showMessageDialog(this, "Ya existe una Sucursal con ese ID", "Error", JOptionPane.ERROR_MESSAGE);
        }else {
          boolean peticionesResultadosFinalizados = false;
          sistemaDeGestion.altaSucursal(id,direccion,numero, peticionesResultadosFinalizados);
          tableModel.add(id, direccion, numero, peticionesResultadosFinalizados);
          JOptionPane.showMessageDialog(this, "Sucursal creada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
          dispose();
        }
      } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "ID de Sucursal debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
      }
    });
  }
}
