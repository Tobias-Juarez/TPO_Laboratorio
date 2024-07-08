package vista.menu.practicas;

import controller.Laboratorio;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

import model.ValorNumerico;
import utils.TablePractica;
import utils.TableValorCritico;
import vista.menu.practicas.ValoresCriticos.FrmAltaValoresCriticos;

public class FrmAltaPractica extends JDialog {

  private JPanel pnlPrincipal;
  private JTextField txtCodigo;
  private JTextField txtNombre;
  private JTextField txtGrupo;
  private JTextField txtDemora;
  private JButton crearPracticaButton;
  private JLabel lblDemora;
  private JLabel lblGrupo;
  private JLabel lblNombre;
  private JLabel lblCodigo;
  private JTable tableValoresCriticos;
  private TableValorCritico tableModel;
  private JButton agregarValoresButton;
  private FrmAltaPractica self;
  private ArrayList<ValorNumerico> listaValoresCriticos;

  public FrmAltaPractica(Window owner, String titulo, Laboratorio laboratorio,
      TablePractica tableModel) {
    super(owner, titulo);
    this.tableModel = new TableValorCritico();
    tableValoresCriticos.setModel(this.tableModel);
    for (int i = 0; i < laboratorio.getPracticas().size(); i++) {
      for (int j = 0; j < laboratorio.getPracticas().get(i).getValoresNumericos().size(); j++) {
        this.tableModel.add(
                laboratorio.getPracticas().get(i).getValoresNumericos().get(j).getNombre(),
                laboratorio.getPracticas().get(i).getValoresNumericos().get(j).getMinimo(),
                laboratorio.getPracticas().get(i).getValoresNumericos().get(j).getMaximo());
      }
    }
    setContentPane(pnlPrincipal);
    setModal(true);
    setSize(500, 400);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    asociarEventos(laboratorio, tableModel, this.tableModel);

  }

  private void asociarEventos(Laboratorio laboratorio, TablePractica tableModel, TableValorCritico tableValorCritico) {
    crearPracticaButton.addActionListener(e -> {
      String codigo = txtCodigo.getText();
      String nombre = txtNombre.getText();
      String grupo = txtGrupo.getText();
      String demoraInput = txtDemora.getText();
      try {
        int demora=Integer.parseInt(demoraInput);
        if (codigo.isEmpty() || nombre.isEmpty() || grupo.isEmpty() || demoraInput.isEmpty()) {
          JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (laboratorio.existePractica(codigo)) {
          JOptionPane.showMessageDialog(this, "Ya existe una practica con ese codigo", "Error", JOptionPane.ERROR_MESSAGE);
        }else {
          laboratorio.altaPractica(codigo, nombre, grupo, demora, listaValoresCriticos);
          tableModel.add(codigo, nombre, grupo, demora);
          JOptionPane.showMessageDialog(this, "Practica creada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
          dispose();
        }
      } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "La demora debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
      }
    });
    agregarValoresButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        listaValoresCriticos = new ArrayList();
        FrmAltaValoresCriticos frame = new FrmAltaValoresCriticos(self, "Alta Valor Crítico", listaValoresCriticos, tableValorCritico);
        frame.setVisible(true);
      }
    });
  }

}
