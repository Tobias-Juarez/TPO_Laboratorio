package vista.menu.pacientes;

import controller.AtencionAlPublico;
import model.RolUsuario;
import utils.TablePaciente;
import utils.TableUsuario;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FrmPacientes extends JDialog {

  private JPanel pnlPrincipal;
  private JButton crearPacienteButton;
  private JButton modificarPacienteButton;
  private JButton eliminarPacienteButton;
  private JTable tablePacientes;
  private TablePaciente tableModel;
  private FrmPacientes self;

  public FrmPacientes(Window owner, String titulo, RolUsuario rol, AtencionAlPublico atencionAlPublico) {
    super(owner, titulo);
    tableModel = new TablePaciente();
    tablePacientes.setModel(tableModel);
    for (int i = 0; i < atencionAlPublico.getPacientes().size(); i++) {
      tableModel.add(atencionAlPublico.getPacientes().get(i).getDni(),
              atencionAlPublico.getPacientes().get(i).getNombre(),
              atencionAlPublico.getPacientes().get(i).getDomicilio(),
              atencionAlPublico.getPacientes().get(i).getMail(),
              atencionAlPublico.getPacientes().get(i).getSexo(),
              atencionAlPublico.getPacientes().get(i).getEdad());
    }
    setContentPane(pnlPrincipal);
    setModal(true);
    setSize(500, 400);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    this.self = this;
    asociarEventos(rol, atencionAlPublico, tableModel);

  }

  private void asociarEventos(RolUsuario rol, AtencionAlPublico atencionAlPublico, TablePaciente tableModel) {
    if (rol == RolUsuario.RECEPCIONISTA) {
      eliminarPacienteButton.setEnabled(false);
    }
    crearPacienteButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        FrmAltaPaciente frame = new FrmAltaPaciente(self, "Alta Paciente", atencionAlPublico, tableModel);
        frame.setVisible(true);
      }
    });
    modificarPacienteButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        FrmModificarPaciente frame = new FrmModificarPaciente(self, "Modificar Paciente", atencionAlPublico, tableModel);
        frame.setVisible(true);
      }
    });
    eliminarPacienteButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        FrmBajaPaciente frame = new FrmBajaPaciente(self, "Baja Paciente", atencionAlPublico, tableModel);
        frame.setVisible(true);
      }
    });

  }
}
