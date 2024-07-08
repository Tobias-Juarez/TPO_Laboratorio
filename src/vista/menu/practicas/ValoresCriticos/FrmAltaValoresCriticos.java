package vista.menu.practicas.ValoresCriticos;

import model.ValorNumerico;
import utils.TableValorCritico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FrmAltaValoresCriticos extends JDialog{
    private JPanel pnlPrincipal;
    private JTextField txtNombre;
    private JTextField txtValorInferior;
    private JTextField txtValorSuperior;
    private JButton anadirValoresButton;

    public FrmAltaValoresCriticos(Window owner, String titulo, ArrayList<ValorNumerico> lista, TableValorCritico tableModel) {
        super(owner, titulo);
        setContentPane(pnlPrincipal);
        setModal(true);
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        asociarEventos(lista, tableModel);

    }

    private void asociarEventos(ArrayList<ValorNumerico> lista, TableValorCritico tableModel) {
        anadirValoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText();
                String valorInferiorInput = txtValorInferior.getText();
                String valorSuperiorInput = txtValorSuperior.getText();
                try {
                    double valorInferior = Double.parseDouble(valorInferiorInput);
                    double valorSuperior = Double.parseDouble(valorSuperiorInput);
                    if (nombre.isEmpty() || valorInferiorInput.isEmpty() || valorSuperiorInput.isEmpty()) {
                        JOptionPane.showMessageDialog(FrmAltaValoresCriticos.this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        lista.add(new ValorNumerico(nombre, valorInferior, valorSuperior));
                        tableModel.add(nombre, valorInferior, valorSuperior);
                        JOptionPane.showMessageDialog(FrmAltaValoresCriticos.this, "Valores críticos añadidos con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(FrmAltaValoresCriticos.this, "Los valores deben ser números", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }
}
