package vista.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

import controller.AtencionAlPublico;
import controller.Laboratorio;
import controller.SistemaDeGestion;
import model.RolUsuario;
import vista.menu.pacientes.FrmPacientes;
import vista.menu.peticiones.FrmPeticiones;
import vista.menu.practicas.FrmPracticas;
import vista.menu.resultados.FrmResultados;
import vista.menu.sucursales.FrmSucursales;
import vista.menu.usuarios.FrmUsuarios;

public class FrmMenu extends JDialog {
    private JPanel pnlPrincipal;
    private JButton pacientesButton;
    private JButton practicasButton;
    private JButton sucursalesButton;
    private JButton peticionesButton;
    private JButton resultadosButton;
    private JButton usuariosButton;
    private FrmMenu self;


    public FrmMenu(Window owner, String titulo, RolUsuario rol, SistemaDeGestion sistemaDeGestion, Laboratorio laboratorio, AtencionAlPublico atencionAlPublico) {
        super(owner, titulo);
        setContentPane(pnlPrincipal);
        setModal(true);
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        asociarEventos(rol, sistemaDeGestion, laboratorio, atencionAlPublico);
        this.self = this;

    }

    private void asociarEventos(RolUsuario rol, SistemaDeGestion sistemaDeGestion, Laboratorio laboratorio, AtencionAlPublico atencionAlPublico) {
        if (rol == RolUsuario.RECEPCIONISTA) {
            practicasButton.setEnabled(false);
            usuariosButton.setEnabled(false);
            sucursalesButton.setEnabled(false);
        }
        else if (rol == RolUsuario.LABORATORISTA) {
            pacientesButton.setEnabled(false);
            peticionesButton.setEnabled(false);
            sucursalesButton.setEnabled(false);
            usuariosButton.setEnabled(false);
            practicasButton.setEnabled(false);
        }
        pacientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmPacientes frame = new FrmPacientes(self, "Pacientes", rol, atencionAlPublico);
                frame.setVisible(true);
            }
        });
        sucursalesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmSucursales frame = new FrmSucursales (self, "Sucursales", sistemaDeGestion);
                frame.setVisible(true);

            }
        });
        practicasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmPracticas frame = new FrmPracticas(self, "Practicas", laboratorio);
                frame.setVisible(true);

            }
        });
        peticionesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmPeticiones frame = new FrmPeticiones(self, "Peticiones", rol, atencionAlPublico, laboratorio);
                frame.setVisible(true);

            }
        });
        resultadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmResultados frame = new FrmResultados(self, "Resultados", rol, laboratorio, atencionAlPublico);
                frame.setVisible(true);

            }
        });
        usuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmUsuarios frame = new FrmUsuarios(self, "Usuarios", sistemaDeGestion);
                frame.setVisible(true);

            }
        });
    }
}
