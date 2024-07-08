package vista.menu.usuarios;

import controller.SistemaDeGestion;
import model.RolUsuario;
import model.Usuario;
import utils.TableUsuario;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class FrmAltaUsuario extends JDialog {


  private JPanel pnlPrincipal;
    private JTextField txtNombre;
  private JTextField txtEmail;
  private JTextField txtUsuario;
  private JTextField txtPassword;
  private JTextField txtDomicilio;
  private JTextField txtDni;
  private JTextField txtFechaNacimiento;
  private JComboBox cbRol;
  private JButton crearUsuarioButton;
  private JLabel lblNombre;
  private JLabel lblEmail;
  private JLabel lblUsuario;
  private JLabel lblPassword;
  private JLabel lblDomicilio;
  private JLabel lblDni;
  private JLabel lblFechaNacimiento;
  private JLabel lblRol;
  private List<RolUsuario> roles = new ArrayList<RolUsuario>();

  public FrmAltaUsuario(Window owner, String titulo, SistemaDeGestion sistemaDeGestion, TableUsuario tableModel) {
    super(owner, titulo);
    setContentPane(pnlPrincipal);
    setModal(true);
    setSize(500, 400);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    roles.add(RolUsuario.ADMINISTRADOR);
    roles.add(RolUsuario.RECEPCIONISTA);
    roles.add(RolUsuario.LABORATORISTA);
    DefaultComboBoxModel model = new DefaultComboBoxModel();
    model.addAll(roles);
    cbRol.setModel(model);
    asociarEventos(sistemaDeGestion, tableModel);
  }

  private void asociarEventos(SistemaDeGestion sistemaDeGestion, TableUsuario tableModel) {
    crearUsuarioButton.addActionListener(e -> {
      String nombre= txtNombre.getText();
      String email= txtEmail.getText();
      String usuario= txtUsuario.getText();
      String password= txtPassword.getText();
      String domicilio= txtDomicilio.getText();
      String input = txtDni.getText();
      String fechaNacimiento= txtFechaNacimiento.getText();
      RolUsuario rol= (RolUsuario) cbRol.getSelectedItem();
      try {
        int dni = Integer.parseInt(input);
        if (nombre.isEmpty() || email.isEmpty() || usuario.isEmpty() || password.isEmpty() || domicilio.isEmpty() || dni == 0 || fechaNacimiento.isEmpty() || rol == null) {
          JOptionPane.showMessageDialog(this, "Debe completar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        }else if (sistemaDeGestion.existeUsuario(usuario)){
            JOptionPane.showMessageDialog(this, "El usuario ya existe", "Error", JOptionPane.ERROR_MESSAGE);
        }else {
          Usuario u = new Usuario();
          u.setNombre(nombre);
          u.setEmail(email);
          u.setUsuario(usuario);
          u.setPassword(password);
          u.setDomicilio(domicilio);
          u.setDni(dni);
          u.setFechaNacimiento(fechaNacimiento);
          u.setRol(rol);
          sistemaDeGestion.altaUsuario(u);
          tableModel.add(usuario, nombre, email, dni, rol, fechaNacimiento);
          JOptionPane.showMessageDialog(this, "Usuario creado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
          this.dispose();
        }
      } catch (NumberFormatException r) {
        JOptionPane.showMessageDialog(this, "DNI debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
      };

    });


  }

}
