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
  private TableValor tableModel;
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
    setSize(500, 400);
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
  }
}

