package vista.menu.peticiones;

import controller.AtencionAlPublico;
import controller.Laboratorio;
import controller.SistemaDeGestion;
import model.Paciente;
import model.Practica;
import model.Sucursal;
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
    private JComboBox cbSucursal;

    public FrmModificarPeticion(Window owner, String titulo, int dni, TablePeticion tableModel, AtencionAlPublico atencionAlPublico, Laboratorio laboratorio, SistemaDeGestion sistemaDeGestion) {
    super(owner, titulo);
    DefaultComboBoxModel model = new DefaultComboBoxModel();
    ArrayList<String> listaPracticas = new ArrayList<String>();
    for (int i = 0; i < laboratorio.getPracticas().size(); i++) {
      listaPracticas.add(laboratorio.getPracticas().get(i).getNombre());
    }
    model.addAll(listaPracticas);
    cbPracticas.setModel(model);
    DefaultComboBoxModel modelSucursal = new DefaultComboBoxModel();
    ArrayList<Sucursal> listaSucursales = sistemaDeGestion.getSucursales();
    for ( Sucursal s : listaSucursales) {
      modelSucursal.addElement(s.getId());
    }
    cbSucursal.setModel(modelSucursal);
    setContentPane(pnlPrincipal);
    setModal(true);
      setSize(800, 700);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    asociarEventos(dni, tableModel, atencionAlPublico, laboratorio, sistemaDeGestion);

  }

  private void asociarEventos(int dni, TablePeticion tableModel, AtencionAlPublico atencionAlPublico, Laboratorio laboratorio, SistemaDeGestion sistemaDeGestion) {
    ModificarPeticionButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String idInput = txtId.getText();
        String obraSocial = txtObraSocial.getText();
        String fechaCarga = txtFechaCarga.getText();
        String fechaEntrega = txtFechaEntrega.getText();
        String practicaInput = (String) cbPracticas.getSelectedItem();
        Practica practica = laboratorio.buscarPracticaPorNombre(practicaInput);
        Sucursal sucursal = sistemaDeGestion.buscarSucursal((int) cbSucursal.getSelectedItem());

        try {
          int id = Integer.parseInt(idInput);
          Paciente p = atencionAlPublico.buscarPaciente(dni);
          if (idInput.isEmpty() || obraSocial.isEmpty() || fechaCarga.isEmpty() || fechaEntrega.isEmpty() || (practicaInput != null && practicaInput.isEmpty()) || sucursal == null) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
          } else if (p == null) {
            JOptionPane.showMessageDialog(null, "No existe un paciente con ese DNI", "Error", JOptionPane.ERROR_MESSAGE);
            return;
          } else {
            atencionAlPublico.modificarPeticion(dni, id, obraSocial, fechaCarga, fechaEntrega, practica, sucursal);
            tableModel.remove(id);
            tableModel.add(id, obraSocial, fechaCarga, fechaEntrega,"", practica, sucursal);
            dispose();
          }
        } catch (NumberFormatException ex) {
          JOptionPane.showMessageDialog(null, "El ID debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
        }

      }
    });
  }

}
