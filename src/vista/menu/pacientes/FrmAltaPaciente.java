package vista.menu.pacientes;

import controller.AtencionAlPublico;
import utils.TablePaciente;

import java.awt.Window;
import javax.swing.*;

public class FrmAltaPaciente extends JDialog {

  private JPanel pnlPrincipal;
  private JTextField txtDni;
  private JTextField txtNombre;
  private JTextField txtDomicilio;
  private JTextField txtMail;
  private JTextField txtSexo;
  private JLabel lblDni;
  private JLabel lblNombre;
  private JLabel lblDomicilio;
  private JLabel lblSexo;
  private JTextField txtEdad;
  private JLabel lblEdad;
  private JButton darDeAltaButton;

  public FrmAltaPaciente(Window owner, String titulo, AtencionAlPublico atencionAlPublico, TablePaciente tableModel) {
    super(owner, titulo);
    setContentPane(pnlPrincipal);
    setModal(true);
    setSize(500, 400);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    asociarEventos(atencionAlPublico, tableModel);
  }

  private void asociarEventos(AtencionAlPublico atencionAlPublico, TablePaciente tableModel) {
    darDeAltaButton.addActionListener(e -> {
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
        } else if (atencionAlPublico.existePaciente(dni)) {
            JOptionPane.showMessageDialog(this, "Ya existe un paciente con ese DNI", "Error", JOptionPane.ERROR_MESSAGE);
        }else {
          atencionAlPublico.altaPaciente(dni, nombre, domicilio, mail, sexo, edad);
          tableModel.add(dni, nombre, domicilio, mail, sexo, edad);
          JOptionPane.showMessageDialog(this, "Paciente creado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
          dispose();
        }
      } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "DNI y Edad deben ser números", "Error", JOptionPane.ERROR_MESSAGE);
      }
    });
  }
}
