package controladores;

import java.awt.event.ActionEvent;
import vistas.VistaEstadistica;
import java.awt.event.ActionListener;
import vistas.VistaPrincipal;

/**
 *
 * @author jmr2_
 */
public class ControladorEstadistica implements ActionListener {

    //Atributos
    VistaEstadistica vista;

    //Constructor
    public ControladorEstadistica(VistaEstadistica vista) {
        this.vista = vista;
        this.vista.btnRegresar.addActionListener(this);        
    }

    //Métodos
    public void iniciar() {
        vista.setTitle("Estadísticas de Estudiantes");
        vista.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnRegresar) {
            regresar();
        }
    }

    public void regresar() {
        //Cambiando al JFrame VistaEstudiantes
        VistaPrincipal vistaPrincipal = new VistaPrincipal();
        ControladorPrincipal ctrlPrincipal = new ControladorPrincipal(vistaPrincipal);

        ctrlPrincipal.iniciar();
        vistaPrincipal.setVisible(true);
        vista.setVisible(false);
    }

}
