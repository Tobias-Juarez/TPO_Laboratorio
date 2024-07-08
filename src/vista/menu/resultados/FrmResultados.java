package vista.menu.resultados;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.RolUsuario;

public class FrmResultados extends JDialog {
  private JPanel pnlPrincipal;
  private JButton crearResultadoButton;
  private JButton modificarResultadoButton;
  private JButton eliminarResultadoButton;
  private JButton consultarResultadosButton;
  private JButton cargarMuestraButton;
  private FrmResultados self;

  public FrmResultados(Window owner, String titulo, RolUsuario rol) {
    super(owner, titulo);
    setContentPane(pnlPrincipal);
    setModal(true);
    setSize(500, 400);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    this.self = this;
    asociarEventos(rol);

  }

  private void asociarEventos(RolUsuario rol) {
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
        FrmAltaResultados frame = new FrmAltaResultados(self,"Alta Resultados");
        frame.setVisible(true);

      }
    });
    modificarResultadoButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        FrmModificarResultados frame = new FrmModificarResultados(self,"Modificar Resultados");
        frame.setVisible(true);
      }
    });
    eliminarResultadoButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        FrmBajaResultados frame = new FrmBajaResultados(self,"Baja Resultados");
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
    consultarResultadosButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        FrmConsultarResultados frame = new FrmConsultarResultados(self,"Consultar Resultados");
        frame.setVisible(true);

      }
    });
  }

}
