package controladores;

import java.awt.event.ActionEvent;
import vistas.VistaNotas;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.GestorEstudiantes;
import modelo.Tutor;
import vistas.VistaBuscar;
import vistas.VistaPrincipal;

/**
 *
 * @author jmr2_
 */
public class ControladorNotas implements ActionListener {

    private VistaNotas vista;
    private int posicion;
    private GestorEstudiantes estudiantes = new GestorEstudiantes();

    public ControladorNotas(VistaNotas vista, int posicion) {
        this.vista = vista;
        this.posicion = posicion;
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnRegresar.addActionListener(this);
        vista.txtEstudiante.setText(estudiantes.get(posicion).getNombre());
        Tutor tutor = new Tutor("Luis","Guerra",30,"Masculino");
        vista.txtTutor.setText(tutor.getNombre()+" "+tutor.getApellido());
    }

    public void iniciar() {
        vista.setTitle("Notas del estudiante");
        vista.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGuardar) {
            guardar();
        }
        if (e.getSource() == vista.btnRegresar) {
            regresar();
        }
    }

    public void guardar() {
        if (!siVacio()) {
            estudiantes.get(posicion).setNotaMatematica(Integer.parseInt(vista.txtMatemática.getText()));
            estudiantes.get(posicion).setNotaCiencias(Integer.parseInt(vista.txtCiencias.getText()));
            estudiantes.get(posicion).setNotaLenguaje(Integer.parseInt(vista.txtLenguaje.getText()));
            estudiantes.get(posicion).setNotaReligion(Integer.parseInt(vista.txtReligion.getText()));
            estudiantes.get(posicion).setNotaEFisica(Integer.parseInt(vista.txtFisica.getText()));
            estudiantes.get(posicion).setNotaETrabajo(Integer.parseInt(vista.txtTrabajo.getText()));
            estudiantes.get(posicion).setNotaHistoria(Integer.parseInt(vista.txtHistoria.getText()));
            estudiantes.get(posicion).setNotaECivica(Integer.parseInt(vista.txtCivica.getText()));
            estudiantes.get(posicion).setNotaGeografia(Integer.parseInt(vista.txtGeografia.getText()));
            estudiantes.get(posicion).setNotaArte(Integer.parseInt(vista.txtArte.getText()));
            estudiantes.get(posicion).setNotaIngles(Integer.parseInt(vista.txtIngles.getText()));
            
            estudiantes.promediar();
            
            JOptionPane.showMessageDialog(null, "¡Notas ingresadas correctamente!");
            //Cambiando al JFrame VistaEstudiantes
            VistaPrincipal vistaPrincipal = new VistaPrincipal();
            ControladorPrincipal ctrlPrincipal = new ControladorPrincipal(vistaPrincipal);

            ctrlPrincipal.iniciar();
            vistaPrincipal.setVisible(true);
            vista.setVisible(false);
        }else{
            JOptionPane.showMessageDialog(null, "ERROR: Debe ingresar todas las notas");
        }

    }

    public void regresar() {
        //Cambiando al JFrame VistaEstudiantes
        VistaBuscar vistaBuscar = new VistaBuscar();
        ControladorBuscar ctrlBuscar = new ControladorBuscar(vistaBuscar, 2);

        ctrlBuscar.iniciar();
        vistaBuscar.setVisible(true);
        vista.setVisible(false);
    }

    public boolean siVacio() {
        return (vista.txtMatemática.getText().isEmpty()
                || vista.txtCiencias.getText().isEmpty()
                || vista.txtLenguaje.getText().isEmpty()
                || vista.txtReligion.getText().isEmpty()
                || vista.txtFisica.getText().isEmpty()
                || vista.txtTrabajo.getText().isEmpty()
                || vista.txtHistoria.getText().isEmpty()
                || vista.txtCivica.getText().isEmpty()
                || vista.txtGeografia.getText().isEmpty()
                || vista.txtArte.getText().isEmpty()
                || vista.txtIngles.getText().isEmpty());
    }

}
