package vista.menu.sucursales;

import controller.SistemaDeGestion;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import utils.TableSucursal;

public class FrmSucursales extends JDialog {
  private JPanel pnlPrincipal;
  private JButton crearSucursalButton;
  private JButton modificarSucursalButton;
  private JButton eliminarSucursalButton;
  private JTable tableSucursales;
  private TableSucursal tableModel;
  private FrmSucursales self;


  public FrmSucursales(Window owner, String titulo, SistemaDeGestion sistemaDeGestion) {
    super(owner, titulo);
    tableModel = new TableSucursal();
    tableSucursales.setModel(tableModel);

    for (int i = 0; i < sistemaDeGestion.getSucursales().size(); i++) {
      tableModel.add(
          sistemaDeGestion.getSucursales().get(i).getId(),
          sistemaDeGestion.getSucursales().get(i).getDireccion(),
          sistemaDeGestion.getSucursales().get(i).getNumero(),
          sistemaDeGestion.getSucursales().get(i).isPeticionResultadosFinalizados()
      );
    }
    setContentPane(pnlPrincipal);
    setModal(true);
    setSize(500, 400);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    asociarEventos(sistemaDeGestion, tableModel);
    this.self = this;
  }

  private void asociarEventos(SistemaDeGestion sistemaDeGestion, TableSucursal tableModel) {
    crearSucursalButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        FrmAltaSucursal frame = new FrmAltaSucursal(self,"Alta Sucursal", sistemaDeGestion, tableModel);
        frame.setVisible(true);
      }
    });
    modificarSucursalButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        FrmModificarSucursal frame = new FrmModificarSucursal(self,"Modificar Sucursal", sistemaDeGestion, tableModel);
        frame.setVisible(true);
      }
    });
    eliminarSucursalButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        FrmBajaSucursal frame = new FrmBajaSucursal(self,"Baja Sucursal", sistemaDeGestion, tableModel);
        frame.setVisible(true);

      }
    });
  }
}
