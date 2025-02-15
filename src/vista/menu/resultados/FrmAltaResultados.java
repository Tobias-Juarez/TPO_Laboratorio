package vista.menu.resultados;

import controller.AtencionAlPublico;
import controller.Laboratorio;
import model.Peticion;
import model.Practica;
import model.Valor;
import utils.TableResultado;
import utils.TableValor;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class FrmAltaResultados extends JDialog{

  private JPanel pnlPrincipal;
    private JTextField txtId;
  private JLabel lblId;
  private JLabel lblValores;
  private JButton crearResultadoButton;
  private JTable tableValores;
  private final TableValor tableModel;
  private JButton nuevoValorButton;
  private JComboBox cbPeticiones;
  private FrmAltaResultados self;

  public FrmAltaResultados(Window owner, String titulo, Laboratorio laboratorio, AtencionAlPublico atencionAlPublico, TableResultado tableModel) {
    super(owner, titulo);
    this.tableModel = new TableValor();
    tableValores.setModel(this.tableModel);

    DefaultComboBoxModel model = new DefaultComboBoxModel();
    ArrayList<Peticion> listaPeticiones = atencionAlPublico.getPeticionesDePacientes();
    for (Peticion p : listaPeticiones) {
        model.addElement(p.getId());
    }
    cbPeticiones.setModel(model);

    setContentPane(pnlPrincipal);
    setModal(true);
    setSize(800, 700);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    asociarEventos(laboratorio, atencionAlPublico, listaPeticiones, tableModel, this.tableModel);



  }

  private void asociarEventos(Laboratorio laboratorio, AtencionAlPublico atencionAlPublico, ArrayList<Peticion> listaPeticiones, TableResultado tableModel, TableValor tableValor) {
    ArrayList<Valor> listaValores = new ArrayList<>();
    nuevoValorButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int id = (int) cbPeticiones.getSelectedItem();
        Practica practica = atencionAlPublico.getPracticaDePeticion(id);
        FrmNuevoValor frame = new FrmNuevoValor(self,"Nuevo Valor", laboratorio, atencionAlPublico, practica, listaValores, tableValor);
        frame.setVisible(true);
      }
    });
    crearResultadoButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String idInput = txtId.getText();
        int idPeticion = (int) cbPeticiones.getSelectedItem();
        try {
          int id = Integer.parseInt(idInput);
          if (idInput.isEmpty()) {
            JOptionPane.showMessageDialog(FrmAltaResultados.this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
          } else if (atencionAlPublico.getResultadosDePeticiones().stream().anyMatch(resultado -> resultado.getIdResultado() == id)) {
            JOptionPane.showMessageDialog(FrmAltaResultados.this, "El ID de resultado ya existe", "Error", JOptionPane.ERROR_MESSAGE);
          } else {
            Practica practica = atencionAlPublico.getPracticaDePeticion(idPeticion);
            atencionAlPublico.agregarResultado(id, practica, idPeticion, listaValores);
            tableModel.add(id, practica.getNombre(), idPeticion, listaValores);
            JOptionPane.showMessageDialog(FrmAltaResultados.this, "Resultado creado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
          }

        } catch (NumberFormatException ex) {
          JOptionPane.showMessageDialog(FrmAltaResultados.this, "El ID debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);

        }
      }
    });
  }
}

