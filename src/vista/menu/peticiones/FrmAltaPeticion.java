package vista.menu.peticiones;

import controller.AtencionAlPublico;
import controller.Laboratorio;
import model.Paciente;
import utils.TablePeticion;

import java.awt.Window;
import java.util.ArrayList;
import javax.swing.*;

public class FrmAltaPeticion extends JDialog {

  private JPanel pnlPrincipal;
  private JTextField txtId;
  private JTextField txtObraSocial;
  private JTextField txtFechaCarga;
  private JTextField txtFechaEntrega;
  private JTextField txtEstado;
  private JButton altaDePeticionButton;
  private JLabel lblEstado;
  private JLabel lblFechaEntrega;
  private JLabel lblFechaCarga;
  private JLabel lblObraSocial;
  private JLabel lblId;
  private JComboBox cbPracticas;


  public FrmAltaPeticion(Window owner, String titulo, AtencionAlPublico atencionAlPublico, int selectedItem, TablePeticion tableModel, Laboratorio laboratorio) {
    super(owner, titulo);
    DefaultComboBoxModel model = new DefaultComboBoxModel();
    ArrayList<String> listaPracticas = new ArrayList<String>();
    for (int i = 0; i < laboratorio.getPracticas().size(); i++) {
      listaPracticas.add(laboratorio.getPracticas().get(i).getNombre());
    }
    model.addAll(listaPracticas);
    cbPracticas.setModel(model);
    setContentPane(pnlPrincipal);
    setModal(true);
    setSize(500, 400);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    asociarEventos(atencionAlPublico, selectedItem, tableModel);
  }

  private void asociarEventos(AtencionAlPublico atencionAlPublico, int selectedItem, TablePeticion tableModel) {
    altaDePeticionButton.addActionListener(e -> {
      String idInput = txtId.getText();
      String obraSocial = txtObraSocial.getText();
      String fechaCarga = txtFechaCarga.getText();
      String fechaEntrega = txtFechaEntrega.getText();
      String estado = txtEstado.getText();
      String practica = (String) cbPracticas.getSelectedItem();
      try {
        int id = Integer.parseInt(idInput);
        Paciente p = atencionAlPublico.buscarPaciente(selectedItem);
        if (idInput.isEmpty() || obraSocial.isEmpty() || fechaCarga.isEmpty() || fechaEntrega.isEmpty() || estado.isEmpty() || practica.isEmpty()) {
          JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
          return;
        } else if (p == null) {
          JOptionPane.showMessageDialog(this, "No existe un paciente con ese DNI", "Error", JOptionPane.ERROR_MESSAGE);
          return;
        } else {
        atencionAlPublico.altaPeticion(selectedItem, id, obraSocial, fechaCarga, fechaEntrega, estado, practica);
        tableModel.add(id, obraSocial, fechaCarga, fechaEntrega, estado, practica);
        dispose();
        }
      } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "El ID debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
      }

    });
  }

}
