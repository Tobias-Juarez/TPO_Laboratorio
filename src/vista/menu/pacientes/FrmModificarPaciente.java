package vista.menu.pacientes;

import controller.AtencionAlPublico;
import java.awt.Window;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Paciente;
import utils.TablePaciente;

public class FrmModificarPaciente extends JDialog {

  private JPanel pnlPrincipal;
  private JLabel lblDni;
  private JTextField txtDni;
  private JLabel lblNombre;
  private JTextField txtNombre;
  private JLabel lblDomicilio;
  private JTextField txtDomicilio;
  private JTextField txtMail;
  private JLabel lblSexo;
  private JTextField txtSexo;
  private JLabel lblEdad;
  private JTextField txtEdad;
  private JButton modificarInformacionButton;
  private JLabel lblMail;
  private JLabel lblModificar;

  public FrmModificarPaciente(Window owner, String titulo, AtencionAlPublico atencionAlPublico,
      TablePaciente tableModel) {
    super(owner, titulo);
    setContentPane(pnlPrincipal);
    setModal(true);
    setSize(800, 700);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    asociarEventos(atencionAlPublico, tableModel);
  }

  private void asociarEventos(AtencionAlPublico atencionAlPublico, TablePaciente tableModel) {
    modificarInformacionButton.addActionListener(e -> {
      String dniInput = txtDni.getText();
      String nombre = txtNombre.getText();
      String domicilio = txtDomicilio.getText();
      String mail = txtMail.getText();
      String sexo = txtSexo.getText();
      String edadInput = txtEdad.getText();
      try {
        int dni=Integer.parseInt(dniInput);
        int edad=Integer.parseInt(edadInput);
        if (dniInput.isEmpty() || nombre.isEmpty() || domicilio.isEmpty() || mail.isEmpty() || sexo.isEmpty() || edadInput.isEmpty()) {
          JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!atencionAlPublico.existePaciente(dni)) {
          JOptionPane.showMessageDialog(this, "No existe un paciente con ese DNI", "Error", JOptionPane.ERROR_MESSAGE);
        }else {
          Paciente p = atencionAlPublico.buscarPaciente(dni);
          tableModel.remove(dni);
          atencionAlPublico.modificarPaciente(dni, nombre, domicilio, mail, sexo, edad);
          tableModel.add(dni, nombre, domicilio, mail, sexo, edad);
          JOptionPane.showMessageDialog(this, "Paciente modificado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
          dispose();
        }
      } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "DNI y Edad deben ser números", "Error", JOptionPane.ERROR_MESSAGE);
      }
    });
  }
}
