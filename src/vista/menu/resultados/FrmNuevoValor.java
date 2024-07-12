package vista.menu.resultados;

import controller.AtencionAlPublico;
import controller.Laboratorio;
import model.Practica;
import model.Valor;
import model.ValorNumerico;
import utils.TableValor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FrmNuevoValor extends JDialog{
    private JPanel pnlPrincipal;
    private JTextField txtValor;
    private JComboBox cbNombreValor;
    private JButton agregarValorButton;
    private JCheckBox reservadoCheckBox;

    public FrmNuevoValor(Window owner, String titulo, Laboratorio laboratorio, AtencionAlPublico atencionAlPublico, Practica practica, ArrayList<Valor> listaValores, TableValor tableValor) {
        super(owner, titulo);
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        ArrayList<ValorNumerico> listaValoresNumericos = practica.getValoresNumericos();
        for (ValorNumerico vn : listaValoresNumericos) {
            model.addElement(vn.getNombre());
        }
        cbNombreValor.setModel(model);


        setContentPane(pnlPrincipal);
        setModal(true);
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        asociarEventos(laboratorio, listaValores, tableValor);

    }

    private void asociarEventos(Laboratorio laboratorio, ArrayList<Valor> listaValores, TableValor tableValor) {
        agregarValorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = (String) cbNombreValor.getSelectedItem();
                String valorInput = txtValor.getText();
                boolean reservado = reservadoCheckBox.isSelected();
                try {
                    int valor = Integer.parseInt(valorInput);
                    if ((nombre != null && nombre.isEmpty()) || valorInput.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    else {
                        for (Valor v : listaValores) {
                            if (v.getNombre().equals(nombre)) {
                                tableValor.remove(nombre);
                                v.setValor(valor);
                                tableValor.add(nombre, valor, reservado);
                                JOptionPane.showMessageDialog(null, "Valor Modificado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                                dispose();
                                return;
                            }
                        }
                        listaValores.add(new Valor(nombre, valor, reservado));
                        JOptionPane.showMessageDialog(null, "Valor Agregado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        tableValor.add(nombre, valor, reservado);
                        dispose();


                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El valor ingresado no es un número", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

            }
        });
    }
}
