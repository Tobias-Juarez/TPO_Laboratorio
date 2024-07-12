package vista.menu.practicas;

import controller.Laboratorio;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

import model.Practica;
import model.ValorNumerico;
import utils.TablePractica;
import utils.TableValorCritico;
import vista.menu.practicas.ValoresCriticos.FrmAltaValoresCriticos;

public class FrmModificarPractica extends JDialog {

  private final TableValorCritico tableModel;
  private JPanel pnlPrincipal;
  private JTextField txtCodigo;
  private JTextField txtNombre;
  private JTextField txtGrupo;
  private JTextField txtDemora;
  private JLabel lblCodigo;
  private JLabel lblNombre;
  private JLabel lblGrupo;
  private JLabel lblDemora;
  private JTable tableValoresCriticos;
  private JButton agregarValoresButton;
  private JButton modificarValoresButton;
  private JButton modificarPracticaButton;
  private ArrayList<ValorNumerico> listaValoresCriticos;
    private FrmModificarPractica self;

  public FrmModificarPractica(Window owner, String titulo, Laboratorio laboratorio,
      TablePractica tableModel) {
    super(owner, titulo);
    this.tableModel = new TableValorCritico();
    tableValoresCriticos.setModel(this.tableModel);

    setContentPane(pnlPrincipal);
    setModal(true);
    setSize(800, 700);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    asociarEventos(laboratorio, tableModel, this.tableModel);
  }

  private void asociarEventos(Laboratorio laboratorio, TablePractica tableModel, TableValorCritico model) {
    listaValoresCriticos = new ArrayList();
    agregarValoresButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        FrmAltaValoresCriticos frame = new FrmAltaValoresCriticos(self, "Alta Valor Crítico", listaValoresCriticos, model);
        frame.setVisible(true);
      }
    });
    modificarPracticaButton.addActionListener(e -> {
      String codigo = txtCodigo.getText();
      String nombre = txtNombre.getText();
      String grupo = txtGrupo.getText();
      String demoraInput = txtDemora.getText();
      try {
        int demora=Integer.parseInt(demoraInput);
        if (codigo.isEmpty() || nombre.isEmpty() || grupo.isEmpty() || demoraInput.isEmpty()) {
          JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!laboratorio.existePractica(codigo)) {
          System.out.println(laboratorio.getPracticas());
          JOptionPane.showMessageDialog(this, "No existe una practica con ese codigo", "Error", JOptionPane.ERROR_MESSAGE);
        }else {
          try {
            int demoraResultado = Integer.parseInt(demoraInput);
            Practica p = laboratorio.buscarPractica(codigo);
            tableModel.remove(codigo);
            p.setCodigo(codigo);
            p.setNombre(nombre);
            p.setGrupo(grupo);
            p.setDemoraResultado(demora);
            p.setValoresCriticos(listaValoresCriticos);
            tableModel.add(codigo, nombre, grupo, demora, listaValoresCriticos);
            JOptionPane.showMessageDialog(this, "Practica modificada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
          } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La demora debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);

          }

        }
      } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "La demora debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
      }
    });
  }

}

