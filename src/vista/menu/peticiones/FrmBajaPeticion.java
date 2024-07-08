package vista.menu.peticiones;

import controller.AtencionAlPublico;
import utils.TablePeticion;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FrmBajaPeticion extends JDialog {

  private JPanel pnlPrincipal;
  private JTextField txtPeticion;
  private JButton darDeBajaButton;
  private JLabel lblId;

  public FrmBajaPeticion(Window owner, String titulo, int dni, TablePeticion tableModel, AtencionAlPublico atencionAlPublico) {
    super(owner, titulo);
    setContentPane(pnlPrincipal);
    setModal(true);
    setSize(500, 400);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    asociarEventos(dni, tableModel, atencionAlPublico);

  }

  private void asociarEventos(int dni, TablePeticion tableModel, AtencionAlPublico atencionAlPublico) {
    darDeBajaButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String idInput = txtPeticion.getText();
        try {
          int id = Integer.parseInt(idInput);
          if (idInput.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
          }else {
            atencionAlPublico.bajaPeticion(dni, id);
            tableModel.remove(id);
            JOptionPane.showMessageDialog(null, "Peticion eliminada", "Exito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
          }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "El id debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
    });
  }

}