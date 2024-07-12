package vista.menu.sucursales;

import controller.SistemaDeGestion;
import java.awt.Window;
import javax.swing.*;
import model.Sucursal;
import utils.TableSucursal;

public class FrmModificarSucursal extends JDialog {


  private JPanel pnlPrincipal;
    private JLabel lblId;
    private JTextField txtIdSucursal;
    private JLabel lblDireccion;
    private JTextField txtDireccion;
    private JLabel lblNumero;
    private JTextField txtNumero;
    private JButton modificarSucursalButton;

    public FrmModificarSucursal(Window owner, String titulo, SistemaDeGestion sistemaDeGestion,
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
    modificarSucursalButton.addActionListener(e -> {
      String idInput = txtIdSucursal.getText();
      String direccion = txtDireccion.getText();
      String numeroInput = txtNumero.getText();
      try {
        int id=Integer.parseInt(idInput);
        int numero=Integer.parseInt(numeroInput);
        if (idInput.isEmpty() || direccion.isEmpty() || numeroInput.isEmpty()) {
          JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!sistemaDeGestion.existeSucursal(id)) {
          JOptionPane.showMessageDialog(this, "No existe una Sucursal con ese ID", "Error", JOptionPane.ERROR_MESSAGE);
        }else {
          Sucursal s = sistemaDeGestion.buscarSucursal(id);
          tableModel.remove(id);
          sistemaDeGestion.bajaSucursal(id);
          sistemaDeGestion.altaSucursal(id, direccion, numero);
          tableModel.add(id, direccion, numero);

          JOptionPane.showMessageDialog(this, "Sucursal modificada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
          dispose();
        }
      } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "ID de Sucursal debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
      }
    });
  }

}
