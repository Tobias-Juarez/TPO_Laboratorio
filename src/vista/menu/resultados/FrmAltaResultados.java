package vista.menu.resultados;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

  public FrmAltaResultados(Window owner, String titulo) {
    super(owner, titulo);
    setContentPane(pnlPrincipal);
    setModal(true);
    setSize(500, 400);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    asociarEventos();

  }

  private void asociarEventos() {
    nuevoValorButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

      }
    });
  }
}

