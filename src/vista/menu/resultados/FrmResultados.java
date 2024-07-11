package vista.menu.resultados;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import controller.AtencionAlPublico;
import controller.Laboratorio;
import model.RolUsuario;
import utils.TableResultado;

public class FrmResultados extends JDialog {
  private JPanel pnlPrincipal;
  private JButton crearResultadoButton;
  private JButton modificarResultadoButton;
  private JButton eliminarResultadoButton;
  private JButton consultarResultadosButton;
  private JButton cargarMuestraButton;
  private JTable tableResultados;
  private TableResultado tableModel;
  private FrmResultados self;

  public FrmResultados(Window owner, String titulo, RolUsuario rol, Laboratorio laboratorio, AtencionAlPublico atencionAlPublico) {
    super(owner, titulo);
    tableModel = new TableResultado();
    tableResultados.setModel(tableModel);
    atencionAlPublico.setResultadosDePeticion();
    for (int i = 0; i < atencionAlPublico.getResultados().size(); i++) {
      tableModel.add(
              atencionAlPublico.getResultados().get(i).getIdResultado(),
              atencionAlPublico.getResultados().get(i).getPractica(),
              atencionAlPublico.getResultados().get(i).getIdPeticion(),
              atencionAlPublico.getResultados().get(i).getValores());
    }

    setContentPane(pnlPrincipal);
    setModal(true);
    setSize(500, 400);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    this.self = this;
    asociarEventos(rol, laboratorio,atencionAlPublico, tableModel);

  }

  private void asociarEventos(RolUsuario rol, Laboratorio laboratorio, AtencionAlPublico atencionAlPublico, TableResultado tableModel) {
    if (rol == RolUsuario.LABORATORISTA) {
      eliminarResultadoButton.setEnabled(false);
      consultarResultadosButton.setEnabled(false);
    }
    if (rol == RolUsuario.RECEPCIONISTA) {
      crearResultadoButton.setEnabled(false);
      modificarResultadoButton.setEnabled(false);
      eliminarResultadoButton.setEnabled(false);
      cargarMuestraButton.setEnabled(false);
    }
    crearResultadoButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        FrmAltaResultados frame = new FrmAltaResultados(self,"Alta Resultados", laboratorio, atencionAlPublico, tableModel);
        frame.setVisible(true);

      }
    });
    modificarResultadoButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        FrmModificarResultados frame = new FrmModificarResultados(self,"Modificar Resultados", laboratorio, atencionAlPublico, tableModel);
        frame.setVisible(true);
      }
    });
    eliminarResultadoButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        FrmBajaResultados frame = new FrmBajaResultados(self,"Baja Resultados", laboratorio, atencionAlPublico, tableModel);
        frame.setVisible(true);
      }
    });
    cargarMuestraButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        FrmCargarMuestra frame = new FrmCargarMuestra(self,"Cargar Muestra");
        frame.setVisible(true);

      }
    });

  }

}
