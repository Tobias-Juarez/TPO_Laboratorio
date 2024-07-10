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
  private JButton modificarValoresButton;
  private FrmAltaPractica self;
  private ArrayList<ValorNumerico> listaValoresCriticos;

  public FrmAltaPractica(Window owner, String titulo, Laboratorio laboratorio,
      TablePractica tableModel) {
    super(owner, titulo);
    this.tableModel = new TableValorCritico();
    tableValoresCriticos.setModel(this.tableModel);

    setContentPane(pnlPrincipal);
    setModal(true);
    setSize(500, 400);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    asociarEventos(laboratorio, tableModel, this.tableModel);

  }

  private void asociarEventos(Laboratorio laboratorio, TablePractica tableModel, TableValorCritico tableValorCritico) {
    listaValoresCriticos = new ArrayList();
    agregarValoresButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        FrmAltaValoresCriticos frame = new FrmAltaValoresCriticos(self, "Alta Valor Crítico", listaValoresCriticos, tableValorCritico);
        frame.setVisible(true);
      }
    });
    crearPracticaButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String codigo = txtCodigo.getText();
        String nombre = txtNombre.getText();
        String grupo = txtGrupo.getText();
        String demora = txtDemora.getText();
        if (codigo.isEmpty() || nombre.isEmpty() || grupo.isEmpty() || demora.isEmpty()) {
          JOptionPane.showMessageDialog(self, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
        } else if ( laboratorio.existePractica(codigo)) {
          JOptionPane.showMessageDialog(self, "Ya existe una practica con ese codigo", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
          try {
            int demoraResultado = Integer.parseInt(demora);
            laboratorio.altaPractica(codigo, nombre, grupo, demoraResultado, listaValoresCriticos);
            tableModel.add(codigo, nombre, grupo, demoraResultado);
            JOptionPane.showMessageDialog(self, "Práctica añadida con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
          } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(self, "La demora debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
          }
        }
      }
    });

  }

}
