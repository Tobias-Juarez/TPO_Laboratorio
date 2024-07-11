package vista.menu.resultados;

import controller.AtencionAlPublico;
import controller.Laboratorio;
import utils.TableResultado;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FrmBajaResultados extends JDialog{

  private JPanel pnlPrincipal;
  private JLabel lblIdResultado;
  private JTextField txtResultado;
  private JButton darDeBajaButton;
  private FrmBajaResultados self;

  public FrmBajaResultados(Window owner, String titulo, Laboratorio laboratorio, AtencionAlPublico atencionAlPublico, TableResultado tableModel) {
    super(owner, titulo);
    setContentPane(pnlPrincipal);
    setModal(true);
    setSize(500, 400);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    asociarEventos(atencionAlPublico, tableModel);

  }

  private void asociarEventos(AtencionAlPublico atencionAlPublico, TableResultado tableModel) {
    darDeBajaButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String idInput = txtResultado.getText();
        try {
            int id = Integer.parseInt(idInput);
            if (idInput.isEmpty()) {
              JOptionPane.showMessageDialog(self, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (atencionAlPublico.getResultadosDePeticiones().stream().noneMatch(resultado -> resultado.getIdResultado() == id)) {
              JOptionPane.showMessageDialog(self, "El resultado no existe", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
              atencionAlPublico.eliminarResultado(id);
              tableModel.remove(id);
              JOptionPane.showMessageDialog(self, "Resultado dado de baja", "Resultado", JOptionPane.INFORMATION_MESSAGE);
              dispose();
            }
        } catch (NumberFormatException ex) {
          JOptionPane.showMessageDialog(self, "El ID de resultado debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
    });
  }

}

