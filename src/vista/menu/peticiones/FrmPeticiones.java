package vista.menu.peticiones;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import controller.AtencionAlPublico;
import controller.Laboratorio;
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
  private FrmPeticiones self;

  public FrmPeticiones(Window owner, String titulo, RolUsuario rol, AtencionAlPublico atencionAlPublico, Laboratorio laboratorio) {
    super(owner, titulo);
    tableModel = new TablePeticion();
    tablePeticiones.setModel(tableModel);
    DefaultComboBoxModel model = new DefaultComboBoxModel();
    ArrayList<Integer> listaPacientes = new ArrayList<Integer>();
    for (int i = 0; i < atencionAlPublico.getPacientes().size(); i++) {
      listaPacientes.add(atencionAlPublico.getPacientes().get(i).getDni());
    }
    model.addAll(listaPacientes);
    cbPaciente.setModel(model);
    setContentPane(pnlPrincipal);
    setModal(true);
    setSize(500, 400);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    asociarEventos(rol, atencionAlPublico, tableModel, laboratorio);
    this.self = this;

  }


  private void asociarEventos(RolUsuario rol, AtencionAlPublico atencionAlPublico, TablePeticion tableModel, Laboratorio laboratorio) {
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
          for (int i = 0; i < peticiones.size(); i++) {
            tableModel.add(peticiones.get(i).getId(),
                    peticiones.get(i).getObraSocial(),
                    peticiones.get(i).getFechaCarga(),
                    peticiones.get(i).getFechaEntrega(),
                    peticiones.get(i).getEstado(),
                    peticiones.get(i).getPractica());
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
          FrmAltaPeticion frame = new FrmAltaPeticion(self, "Alta Petición", atencionAlPublico, (int) cbPaciente.getSelectedItem(), tableModel, laboratorio);
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
          FrmModificarPeticion frame = new FrmModificarPeticion(self, "Modificar Petición", (int) cbPaciente.getSelectedItem(), tableModel, atencionAlPublico, laboratorio);
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
          FrmBajaPeticion frame = new FrmBajaPeticion(self, "Baja Petición", (int) cbPaciente.getSelectedItem(), tableModel, atencionAlPublico);
          frame.setVisible(true);
        }
      }
    });
  }

}
