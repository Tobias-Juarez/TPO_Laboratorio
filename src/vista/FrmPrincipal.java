package vista;

import controller.AtencionAlPublico;
import controller.Laboratorio;
import controller.SistemaDeGestion;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.RolUsuario;
import model.Usuario;
import vista.menu.FrmMenu;

public class FrmPrincipal  extends JFrame {
    private JPanel pnlPrincipal;
    private JTextField txtUsuario;
    private JTextField txtPassword;
    private JButton iniciarSesionButton;
    private JLabel usuarioLabel;
    private JLabel contraseñaLabel;
    private JButton crearUsuarioButton;

    private final FrmPrincipal self;
    private final SistemaDeGestion sistemaDeGestion = SistemaDeGestion.getInstance();
    private final Laboratorio laboratorio = Laboratorio.getInstance();
    private final AtencionAlPublico atencionAlPublico = AtencionAlPublico.getInstance();


    public FrmPrincipal(String titulo) {
        super(titulo);
        setContentPane(pnlPrincipal);
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        asociarEventos();
        this.self = this;
    }
    private void asociarEventos() {
        iniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText();
                String password = txtPassword.getText();
                if(sistemaDeGestion.getUsuarios().stream().anyMatch(u -> u.getUsuario().equals(usuario) && u.getPassword().equals(password))) {
                    RolUsuario rol = getRol(usuario);
                    FrmMenu frame = new FrmMenu(self, "Menú",rol,sistemaDeGestion, laboratorio,atencionAlPublico);
                    frame.setVisible(true);
                }
                else {
                    JOptionPane.showMessageDialog(null, "El usuario o contraseña no existe");
                }
            }
        });
        crearUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmCrearUsuario frame = new FrmCrearUsuario(self, "Crear Usuario",sistemaDeGestion);
                frame.setVisible(true);
            }
        });
    }
    private RolUsuario getRol(String usuario){

        return sistemaDeGestion.getUsuarios().stream().filter(u -> u.getUsuario().equals(usuario)).findFirst().get().getRol();
    }
    public static void main(String[] args) {
        FrmPrincipal frame = new FrmPrincipal("Sistema de Gestión de Laboratorio");

        frame.setVisible(true);

    }
}
