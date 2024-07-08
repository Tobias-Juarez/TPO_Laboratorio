package vista.menu.resultados;

import javax.swing.*;
import java.awt.*;



public class FrmCargarMuestra extends JDialog{
    private JPanel pnlPrincipal;
    private JComboBox cbTipoMuestra;
    private JTextField txtCantidad;
    private JComboBox cbIdPeticion;
    private JButton cargarMuestraButton;
    private JLabel lblCantidad;
    private JLabel lblTipoMuestra;
    private JLabel lblIdPeticion;

    public FrmCargarMuestra(Window owner, String titulo) {
        super(owner, titulo);
        setContentPane(pnlPrincipal);
        setModal(true);
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);



    }
}
