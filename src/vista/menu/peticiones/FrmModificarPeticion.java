package vista.menu.peticiones;

import controller.AtencionAlPublico;
import controller.Laboratorio;
import model.Paciente;
import utils.TablePeticion;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class FrmModificarPeticion extends JDialog {

  private JPanel pnlPrincipal;
  private JLabel lblId;
  private JTextField txtId;
  private JLabel lblObraSocial;
  private JTextField txtObraSocial;
  private JLabel lblFechaCarga;
  private JTextField txtFechaCarga;
  private JLabel lblFechaEntrega;
  private JTextField txtFechaEntrega;
  private JLabel lblEstado;
  private JTextField txtEstado;
  private JButton ModificarPeticionButton;
  private JComboBox cbPracticas;

  public FrmModificarPeticion(Window owner, String titulo, int dni, TablePeticion tableModel, AtencionAlPublico atencionAlPublico, Laboratorio laboratorio) {
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
    asociarEventos(dni, tableModel, atencionAlPublico);

  }

  private void asociarEventos(int dni, TablePeticion tableModel, AtencionAlPublico atencionAlPublico) {
    ModificarPeticionButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String idInput = txtId.getText();
        String obraSocial = txtObraSocial.getText();
        String fechaCarga = txtFechaCarga.getText();
        String fechaEntrega = txtFechaEntrega.getText();
        String estado = txtEstado.getText();
        String practica = (String) cbPracticas.getSelectedItem();
        try {
          int id = Integer.parseInt(idInput);
          Paciente p = atencionAlPublico.buscarPaciente(dni);
          if (idInput.isEmpty() || obraSocial.isEmpty() || fechaCarga.isEmpty() || fechaEntrega.isEmpty() || estado.isEmpty() || practica.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
          } else if (p == null) {
            JOptionPane.showMessageDialog(null, "No existe un paciente con ese DNI", "Error", JOptionPane.ERROR_MESSAGE);
            return;
          } else {
            atencionAlPublico.modificarPeticion(dni, id, obraSocial, fechaCarga, fechaEntrega, estado, practica);
            tableModel.remove(id);
            tableModel.add(id, obraSocial, fechaCarga, fechaEntrega, estado, practica);
            dispose();
          }
        } catch (NumberFormatException ex) {
          JOptionPane.showMessageDialog(null, "El ID debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
        }

      }
    });
  }

}
