package vista.menu.resultados;

import controller.AtencionAlPublico;
import controller.Laboratorio;
import model.Peticion;
import utils.TableResultado;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class FrmAltaResultados extends JDialog{

  private JPanel pnlPrincipal;
    private JTextField txtId;
  private JLabel lblId;
  private JLabel lblValores;
  private JButton crearResultadoButton;
  private JTable tableValores;
  private JButton nuevoValorButton;
  private JComboBox cbPeticiones;
  private FrmAltaResultados self;

  public FrmAltaResultados(Window owner, String titulo, Laboratorio laboratorio, AtencionAlPublico atencionAlPublico, TableResultado tableModel) {
    super(owner, titulo);
    DefaultComboBoxModel model = new DefaultComboBoxModel();
    ArrayList<Peticion> listaPeticiones = atencionAlPublico.getPeticionesDePacientes();
    for (Peticion p : listaPeticiones) {
        model.addElement(p.getId());
    }
    cbPeticiones.setModel(model);

    setContentPane(pnlPrincipal);
    setModal(true);
    setSize(500, 400);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    asociarEventos(laboratorio, atencionAlPublico);


  }

  private void asociarEventos(Laboratorio laboratorio, AtencionAlPublico atencionAlPublico) {
    nuevoValorButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        FrmNuevoValor frame = new FrmNuevoValor(self,"Nuevo Valor", laboratorio, atencionAlPublico);
        frame.setVisible(true);
      }
    });
  }
}

