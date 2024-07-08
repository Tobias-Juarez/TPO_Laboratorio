package vista.menu.practicas;

import controller.Laboratorio;
import java.awt.Window;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import utils.TablePractica;

public class FrmBajaPractica extends JDialog {

  private JPanel pnlPrincipal;
  private JLabel lblCodigo;
  private JTextField txtCodigo;
  private JButton darDeBajaButton;

  public FrmBajaPractica(Window owner, String titulo, Laboratorio laboratorio,
      TablePractica tableModel) {
    super(owner, titulo);
    setContentPane(pnlPrincipal);
    setModal(true);
    setSize(500, 400);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    asociarEventos(laboratorio, tableModel);
  }

  private void asociarEventos(Laboratorio laboratorio, TablePractica tableModel) {
    darDeBajaButton.addActionListener(e -> {
      String codigo = txtCodigo.getText();
      if (codigo.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Debe ingresar un codigo", "Error", JOptionPane.ERROR_MESSAGE);
      }else if (!laboratorio.existePractica(codigo)) {
        JOptionPane.showMessageDialog(this, "No existe una practica con ese codigo", "Error", JOptionPane.ERROR_MESSAGE);
      }else {
        laboratorio.bajaPractica(codigo);
        tableModel.remove(codigo);
        JOptionPane.showMessageDialog(this, "Practica eliminada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        dispose();
      }
    });
  }

}