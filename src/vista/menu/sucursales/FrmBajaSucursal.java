package vista.menu.sucursales;

import controller.SistemaDeGestion;
import java.awt.Window;
import javax.swing.*;
import utils.TableSucursal;

public class FrmBajaSucursal extends JDialog{

  private JPanel pnlPrincipal;
    private JLabel lblSucursal;
    private JTextField txtIdSucursal;
    private JButton darDeBajaButton;

    public FrmBajaSucursal(Window owner, String titulo, SistemaDeGestion sistemaDeGestion,
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
    darDeBajaButton.addActionListener(e -> {
      String idInput = txtIdSucursal.getText();
      try {
        int id=Integer.parseInt(idInput);
        if (idInput.isEmpty()) {
          JOptionPane.showMessageDialog(this, "Debe ingresar un ID de sucursal", "Error", JOptionPane.ERROR_MESSAGE);
        }else if (!sistemaDeGestion.existeSucursal(id)) {
          JOptionPane.showMessageDialog(this, "No existe una sucursal con ese ID", "Error", JOptionPane.ERROR_MESSAGE);
        }else {
          sistemaDeGestion.bajaSucursal(id);
          tableModel.remove(id);
          JOptionPane.showMessageDialog(this, "Sucursal eliminada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
          dispose();
        }
      }
      catch (NumberFormatException ex){
        JOptionPane.showMessageDialog(this, "El ID de la sucursal debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
      }
    });
  }

}

