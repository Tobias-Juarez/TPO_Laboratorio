package vista.menu.resultados;

import controller.AtencionAlPublico;
import controller.Laboratorio;
import model.Peticion;
import utils.TableResultado;

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

    public FrmNuevoValor(Window owner, String titulo, Laboratorio laboratorio, AtencionAlPublico atencionAlPublico) {
        super(owner, titulo);
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        ArrayList<Peticion> listaPeticiones = atencionAlPublico.getPeticionesDePacientes();
        for (Peticion p : listaPeticiones) {
            model.addElement(p.getId());
        }
        cbNombreValor.setModel(model);


        setContentPane(pnlPrincipal);
        setModal(true);
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        asociarEventos(laboratorio);

    }

    private void asociarEventos(Laboratorio laboratorio) {
        agregarValorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
