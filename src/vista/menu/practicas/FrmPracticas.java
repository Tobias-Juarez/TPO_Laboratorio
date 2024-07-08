package vista.menu.practicas;

import controller.Laboratorio;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import utils.TablePractica;

public class FrmPracticas extends JDialog {
  private JPanel pnlPrincipal;
  private JButton crearPracticaButton;
  private JButton modificarPracticaButton;
  private JButton eliminarPracticaButton;
  private JTable tablePracticas;
  private TablePractica tableModel;
  private FrmPracticas self;

  public FrmPracticas(Window owner, String titulo, Laboratorio laboratorio) {
    super(owner, titulo);
    tableModel = new TablePractica();
    tablePracticas.setModel(tableModel);
    for (int i = 0; i < laboratorio.getPracticas().size(); i++) {
      tableModel.add(
          laboratorio.getPracticas().get(i).getCodigo(),
          laboratorio.getPracticas().get(i).getNombre(),
          laboratorio.getPracticas().get(i).getGrupo(),
          laboratorio.getPracticas().get(i).getDemoraResultado());
    }
    setContentPane(pnlPrincipal);
    setModal(true);
    setSize(500, 400);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    asociarEventos(laboratorio, tableModel);
    this.self = this;
  }

  private void asociarEventos(Laboratorio laboratorio, TablePractica tableModel) {
    crearPracticaButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        FrmAltaPractica frame = new FrmAltaPractica(self,"Alta Practica", laboratorio, tableModel);
        frame.setVisible(true);
      }
    });
    modificarPracticaButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        FrmModificarPractica frame = new FrmModificarPractica(self,"Modificar Practica", laboratorio, tableModel);
        frame.setVisible(true);
      }
    });
    eliminarPracticaButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        FrmBajaPractica frame = new FrmBajaPractica(self,"Baja Practica", laboratorio, tableModel);
        frame.setVisible(true);
      }
    });
  }

}
