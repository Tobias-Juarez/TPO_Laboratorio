package vista.menu.usuarios;

import controller.SistemaDeGestion;
import utils.TableUsuario;

import java.awt.Window;
import javax.swing.*;

public class FrmBajaUsuario extends JDialog {


  private JPanel pnlPrincipal;
  private JLabel lblUsuario;
  private JTextField txtUsuario;
  private JButton darDeBajaButton;

  public FrmBajaUsuario(Window owner, String titulo, SistemaDeGestion sistemaDeGestion, TableUsuario tableModel) {
    super(owner, titulo);
    setContentPane(pnlPrincipal);
    setModal(true);
    setSize(500, 400);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    asociarEventos(sistemaDeGestion, tableModel);
  }

  private void asociarEventos(SistemaDeGestion sistemaDeGestion, TableUsuario tableModel) {
    darDeBajaButton.addActionListener(e -> {
      String usuario = txtUsuario.getText();
      if (usuario.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Debe ingresar un usuario", "Error", JOptionPane.ERROR_MESSAGE);
      }else if (!sistemaDeGestion.existeUsuario(usuario)) {
        JOptionPane.showMessageDialog(this, "El usuario no existe", "Error", JOptionPane.ERROR_MESSAGE);
      }else {
        sistemaDeGestion.bajaUsuario(usuario);
        tableModel.remove(usuario);
        JOptionPane.showMessageDialog(this, "Usuario eliminado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        dispose();
      }
    });
  }

}
