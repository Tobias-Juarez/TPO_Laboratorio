package vista.menu.peticiones;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

import controller.AtencionAlPublico;
import controller.Laboratorio;
import controller.SistemaDeGestion;
import model.Paciente;
import model.Peticion;
import model.RolUsuario;
import utils.TablePeticion;

public class FrmPeticiones extends JDialog {
  private JPanel pnlPrincipal;
  private JButton crearPeticionButton;
  private JButton modificarPeticionButton;
  private JButton eliminarPeticionButton;
  private JTable tablePeticiones;
  private TablePeticion tableModel;
  private JComboBox cbPaciente;
    private JButton peticionesConValoresCriticosButton;
    private FrmPeticiones self;

  public FrmPeticiones(Window owner, String titulo, RolUsuario rol, AtencionAlPublico atencionAlPublico, Laboratorio laboratorio, SistemaDeGestion sistemaDeGestion) {
    super(owner, titulo);
    tableModel = new TablePeticion();
    tablePeticiones.setModel(tableModel);
    DefaultComboBoxModel model = new DefaultComboBoxModel();
    ArrayList<Integer> listaPacientes = new ArrayList<>();
    for (int i = 0; i < atencionAlPublico.getPacientes().size(); i++) {
      listaPacientes.add(atencionAlPublico.getPacientes().get(i).getDni());
    }
    model.addAll(listaPacientes);
    cbPaciente.setModel(model);
    setContentPane(pnlPrincipal);
    setModal(true);
    setSize(800, 700);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    asociarEventos(rol, atencionAlPublico, tableModel, laboratorio, sistemaDeGestion);
    this.self = this;

  }


  private void asociarEventos(RolUsuario rol, AtencionAlPublico atencionAlPublico, TablePeticion tableModel, Laboratorio laboratorio, SistemaDeGestion sistemaDeGestion) {
    if (rol == RolUsuario.RECEPCIONISTA) {
      eliminarPeticionButton.setEnabled(false);
    }
    cbPaciente.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        tableModel.clear();
        Paciente p = atencionAlPublico.buscarPaciente((int) cbPaciente.getSelectedItem());
        ArrayList<Peticion> peticiones = p.getPeticiones();
        if (peticiones != null) {
            for (Peticion pe : peticiones) {
              atencionAlPublico.actualizarEstadoPeticion(pe.getId());
              tableModel.add(
                        pe.getId(),
                        pe.getObraSocial(),
                        pe.getFechaCarga(),
                        pe.getFechaEntrega(),
                        pe.getEstado(),
                        pe.getPractica(),
                        pe.getSucursal()
                );
            }
        }
      }
    });
    crearPeticionButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (cbPaciente.getSelectedItem() == null) {
          JOptionPane.showMessageDialog(self, "Debe seleccionar un paciente", "Error", JOptionPane.ERROR_MESSAGE);
          return;
        }else {
          FrmAltaPeticion frame = new FrmAltaPeticion(self, "Alta Petición", atencionAlPublico, (int) cbPaciente.getSelectedItem(), tableModel, laboratorio, sistemaDeGestion);
          frame.setVisible(true);
        }
      }
    });
    modificarPeticionButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (cbPaciente.getSelectedItem() == null) {
          JOptionPane.showMessageDialog(self, "Debe seleccionar un paciente", "Error", JOptionPane.ERROR_MESSAGE);
          return;
        }else {
          FrmModificarPeticion frame = new FrmModificarPeticion(self, "Modificar Petición", (int) cbPaciente.getSelectedItem(), tableModel, atencionAlPublico, laboratorio, sistemaDeGestion);
          frame.setVisible(true);
        }
      }
    });
    eliminarPeticionButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (cbPaciente.getSelectedItem() == null) {
          JOptionPane.showMessageDialog(self, "Debe seleccionar un paciente", "Error", JOptionPane.ERROR_MESSAGE);
          return;
        }else {
          FrmBajaPeticion frame = new FrmBajaPeticion(self, "Baja Petición", (int) cbPaciente.getSelectedItem(), tableModel, atencionAlPublico, sistemaDeGestion);
          frame.setVisible(true);
        }
      }
    });
  }

}
