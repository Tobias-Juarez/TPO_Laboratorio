package vista.menu.peticiones;

import controller.AtencionAlPublico;
import controller.Laboratorio;
import controller.SistemaDeGestion;
import model.Peticion;
import utils.TablePeticion;

import javax.swing.*;
import java.awt.*;

public class FrmPeticionesConValoresCriticos extends JDialog{
    private JPanel pnlPrincipal;
    private JTable tablePeticiones;
    private TablePeticion tableModel;
    private FrmPeticionesConValoresCriticos self;

    public FrmPeticionesConValoresCriticos(Window owner, String titulo, AtencionAlPublico atencionAlPublico, Laboratorio laboratorio, SistemaDeGestion sistemaDeGestion) {
        super(owner, titulo);
        tableModel = new TablePeticion();
        tablePeticiones.setModel(tableModel);

        setContentPane(pnlPrincipal);
        setModal(true);
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        asociarEventos(tableModel, atencionAlPublico);
        this.self = this;
    }

    private void asociarEventos(TablePeticion tableModel, AtencionAlPublico atencionAlPublico) {
        tableModel.clear();
         for (Peticion p : atencionAlPublico.getPeticionesConValoresCriticos()){
             tableModel.add(p.getId(), p.getObraSocial(), p.getFechaCarga(), p.getFechaEntrega(), p.getEstado(), p.getPractica(), p.getSucursal());
         }
    }
}
