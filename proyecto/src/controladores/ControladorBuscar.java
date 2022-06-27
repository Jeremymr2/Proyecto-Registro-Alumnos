package controladores;

import java.awt.event.ActionEvent;
import vistas.VistaBuscar;
import java.awt.event.ActionListener;
import modelo.GestorEstudiantes;
import vistas.VistaEstudiantes;
import vistas.VistaNotas;
import vistas.VistaPrincipal;

/**
 *
 * @author jmr2_
 */
public class ControladorBuscar implements ActionListener {

    private VistaBuscar vista;
    private GestorEstudiantes estudiantes = new GestorEstudiantes();
    private int opc;

    public ControladorBuscar(VistaBuscar vista, int opc) {
        this.vista = vista;
        this.opc = opc;
        this.vista.btnBuscar.addActionListener(this);
        this.vista.btnCancelar.addActionListener(this);
    }

    public void iniciar() {
        vista.setTitle("Buscar código");
        vista.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnBuscar) {
            buscar();
        }
        if (e.getSource() == vista.btnCancelar) {
            cancelar();
        }
    }

    public void buscar() {
        String codigo;
        codigo = vista.txtCodigo.getText();
        if (opc == 2) {
            if (estudiantes.buscar(codigo)) {
                //Cambiando al JFrame VistaEstudiantes
                VistaNotas vistaNotas = new VistaNotas();
                int posicion = estudiantes.posicion(codigo);
                ControladorNotas ctrlPrincipal = new ControladorNotas(vistaNotas,posicion);

                ctrlPrincipal.iniciar();
                vistaNotas.setVisible(true);
                vista.setVisible(false);
            }
        } else {
            estudiantes.mostrar(codigo); 
        }
        vista.txtCodigo.setText("");

    }

    public void cancelar() {
        if (opc == 1) {
            //Cambiando al JFrame VistaEstudiantes
            VistaEstudiantes vistaTabla = new VistaEstudiantes();
            ControladorEstudiantes ctrlTabla = new ControladorEstudiantes(vistaTabla, estudiantes);

            ctrlTabla.iniciar();
            vistaTabla.setVisible(true);
            vista.setVisible(false);
        } else {
            //Cambiando al JFrame VistaEstudiantes
            VistaPrincipal vistaPrincipal = new VistaPrincipal();
            ControladorPrincipal ctrlPrincipal = new ControladorPrincipal(vistaPrincipal);

            ctrlPrincipal.iniciar();
            vistaPrincipal.setVisible(true);
            vista.setVisible(false);
        }
    }

}
