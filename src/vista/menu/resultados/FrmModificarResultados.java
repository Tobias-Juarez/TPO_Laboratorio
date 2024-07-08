package vista.menu.resultados;

import java.awt.Window;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FrmModificarResultados extends JDialog {


    private JPanel pnlPrincipal;
  private JTextArea txtValores;
  private JLabel lblValores;
  private JLabel lblId;
  private JTextField txtId;
  private JButton modificarResultadoButton;

  public FrmModificarResultados(Window owner, String titulo) {
    super(owner, titulo);
    setContentPane(pnlPrincipal);
    setModal(true);
    setSize(500, 400);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    asociarEventos();
  }

  private void asociarEventos() {
  }

}

