package vista.menu.pacientes;

import controller.AtencionAlPublico;
import java.awt.Window;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import utils.TablePaciente;

public class FrmBajaPaciente extends JDialog {

  private JPanel pnlPrincipal;
  private JTextField txtDocumento;
  private JButton darDeBajaButton;

  public FrmBajaPaciente(Window owner, String titulo, AtencionAlPublico atencionAlPublico,
      TablePaciente tableModel) {
    super(owner, titulo);
    setContentPane(pnlPrincipal);
    setModal(true);
    setSize(500, 400);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    asociarEventos(atencionAlPublico, tableModel);
  }

  private void asociarEventos(AtencionAlPublico atencionAlPublico, TablePaciente tableModel) {
    darDeBajaButton.addActionListener(e -> {
      String dniInput = txtDocumento.getText();
      try {
        int dni=Integer.parseInt(dniInput);
        if (dniInput.isEmpty()) {
          JOptionPane.showMessageDialog(this, "Debe ingresar un DNI", "Error", JOptionPane.ERROR_MESSAGE);
        }else if (!atencionAlPublico.existePaciente(dni)) {
          JOptionPane.showMessageDialog(this, "No existe un paciente con ese DNI", "Error", JOptionPane.ERROR_MESSAGE);
        }else {
          atencionAlPublico.bajaPaciente(dni);
          tableModel.remove(dni);
          JOptionPane.showMessageDialog(this, "Paciente eliminado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
          dispose();
        }
      }
      catch (NumberFormatException ex){
        JOptionPane.showMessageDialog(this, "El DNI debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
      }
    });
  }

}