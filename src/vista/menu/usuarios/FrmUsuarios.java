package vista.menu.usuarios;

import controller.SistemaDeGestion;
import utils.TableUsuario;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FrmUsuarios extends JDialog {
  private JPanel pnlPrincipal;
  private JButton crearUsuarioButton;
  private JButton modificarUsuarioButton;
  private JButton eliminarUsuarioButton;
  private JTable tableUsuarios;
  private TableUsuario tableModel;

  private FrmUsuarios self;

  public FrmUsuarios(Window owner, String titulo, SistemaDeGestion sistemaDeGestion) {
    super(owner, titulo);
    tableModel = new TableUsuario();
    tableUsuarios.setModel(tableModel);
    for (int i = 0; i < sistemaDeGestion.getUsuarios().size(); i++) {
      tableModel.add(sistemaDeGestion.getUsuarios().get(i).getUsuario(),
              sistemaDeGestion.getUsuarios().get(i).getNombre(),
              sistemaDeGestion.getUsuarios().get(i).getEmail(),
              sistemaDeGestion.getUsuarios().get(i).getDni(),
              sistemaDeGestion.getUsuarios().get(i).getRol(),
              sistemaDeGestion.getUsuarios().get(i).getFechaNacimiento());
    }
    setContentPane(pnlPrincipal);
    setModal(true);
    setSize(500, 400);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    asociarEventos(sistemaDeGestion, tableModel);
    this.self = this;
  }

  private void asociarEventos(SistemaDeGestion sistemaDeGestion, TableUsuario tableModel) {
    crearUsuarioButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        FrmAltaUsuario frame = new FrmAltaUsuario(self,"Alta Usuario", sistemaDeGestion, tableModel);
        frame.setVisible(true);
      }
    });
    modificarUsuarioButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        FrmModificarUsuario frame = new FrmModificarUsuario(self,"Modificar Usuario", sistemaDeGestion, tableModel);
        frame.setVisible(true);
      }
    });
    eliminarUsuarioButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        FrmBajaUsuario frame = new FrmBajaUsuario(self,"Baja Usuario", sistemaDeGestion, tableModel);
        frame.setVisible(true);
      }
    });
  }

}
