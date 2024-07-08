package vista.menu.resultados;

import javax.swing.*;
import java.awt.*;

public class FrmConsultarResultados extends JDialog{
    private JPanel pnlPrincipal;
    private JLabel lblIdPeticion;
    private JComboBox cbIdPeticion;
    private JButton confirmarButton;

    public FrmConsultarResultados(Window owner, String titulo) {
        super(owner, titulo);
        setContentPane(pnlPrincipal);
        setModal(true);
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);



    }
}
