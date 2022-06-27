package controladores;

import java.awt.event.ActionEvent;
import vistas.VistaListado;
import java.awt.event.ActionListener;
import modelo.GestorEstudiantes;
import vistas.VistaPrincipal;

/**
 *
 * @author jmr2_
 */
public class ControladorListado implements ActionListener {

    //Atributos
    private VistaListado vista;
    private GestorEstudiantes estudiantes = new GestorEstudiantes();

    //Constructor
    public ControladorListado(VistaListado vista) {
        this.vista = vista;
        this.vista.btnRegresar.addActionListener(this);
        actualizarTabla();
    }

    //Métodos
    public void iniciar() {
        vista.setTitle("Lista General de Estudiantes");
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

    public void actualizarTabla() {
        Object[] datos = new Object[16];

        for (int i = 0; i < estudiantes.size(); i++) {
            datos[0] = estudiantes.get(i).getCodigo();
            datos[1] = estudiantes.get(i).getNombre();
            datos[2] = estudiantes.get(i).getApellido();
            datos[3] = estudiantes.get(i).getNotaMatematica();
            datos[4] = estudiantes.get(i).getNotaCiencias();
            datos[5] = estudiantes.get(i).getNotaLenguaje();
            datos[6] = estudiantes.get(i).getNotaReligion();
            datos[7] = estudiantes.get(i).getNotaEFisica();
            datos[8] = estudiantes.get(i).getNotaETrabajo();
            datos[9] = estudiantes.get(i).getNotaHistoria();
            datos[10] = estudiantes.get(i).getNotaECivica();
            datos[11] = estudiantes.get(i).getNotaGeografia();
            datos[12] = estudiantes.get(i).getNotaArte();
            datos[13] = estudiantes.get(i).getNotaIngles();
            datos[14] = estudiantes.get(i).getPromedioFinal();
            datos[15] = estudiantes.get(i).getEstado();

            vista.modelo.addRow(datos);
        }
    }
}
