package controladores;

import modelo.Estudiante;
import modelo.GestorEstudiantes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vistas.VistaEstudiantes;
import vistas.VistaModificar;

/**
 *
 * @author jmr2_
 */
public class ControladorModificar implements ActionListener {

    private VistaModificar vista;
    private GestorEstudiantes estudiantes = new GestorEstudiantes();
    private Estudiante estudiante;
    private int posicion;

    public ControladorModificar(VistaModificar vista, Estudiante estudiante, int posicion) {
        this.vista = vista;
        this.estudiante = estudiante;
        this.posicion = posicion;
        this.vista.btnRegistrar.addActionListener(this);
        this.vista.btnCancelar.addActionListener(this);
        vista.txtNombre.setText(estudiante.getNombre());
        vista.txtApellido.setText(estudiante.getApellido());
        vista.txtCodigo.setText(estudiante.getCodigo());
        vista.txtEdad.setText(Integer.toString(estudiante.getEdad()));
        vista.cbEnfermedad.setSelectedItem(estudiante.getEnfermedad());
        vista.cbSexo.setSelectedItem(estudiante.getSexo());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnRegistrar) {
            modificar();
        }
        if (e.getSource() == vista.btnCancelar) {
            goCancelar();
        }
    }

    public void iniciar() {
        vista.setTitle("Modificar datos de estudiantes");
        vista.setLocationRelativeTo(null);
    }

    public void modificar() {
        String nombre = vista.txtNombre.getText();
        String apellido = vista.txtApellido.getText();
        try {
            int edad = Integer.parseInt(vista.txtEdad.getText());
            String sexo = vista.cbSexo.getSelectedItem().toString();
            String codigo = vista.txtCodigo.getText();
            String enfermedad = vista.cbEnfermedad.getSelectedItem().toString();
            Estudiante temp = new Estudiante(nombre, apellido, edad, sexo, codigo, enfermedad);

            if (siVacio()) {
                JOptionPane.showMessageDialog(null, "ERROR: Complete todos los campos");
            } else if (estudiantes.cambiarEstudiante(temp, posicion)) {
                JOptionPane.showMessageDialog(null, "¡Estudiante modificado con éxito!");
                goCancelar();
            } else {
                JOptionPane.showMessageDialog(null, "ERROR: Código ya registrado");
                vista.txtCodigo.setText("");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: Su edad debe ser un número");
        }
    }

    public void goCancelar() {
        //Cambiando al JFrame VistaEstudiantes
        VistaEstudiantes vistaTabla = new VistaEstudiantes();
        ControladorEstudiantes ctrlTabla = new ControladorEstudiantes(vistaTabla, estudiantes);

        ctrlTabla.iniciar();
        vistaTabla.setVisible(true);
        vista.setVisible(false);
    }

    public void vaciarCampos() {
        vista.txtNombre.setText("");
        vista.txtEdad.setText("");
        vista.txtApellido.setText("");
        vista.cbSexo.setSelectedItem(" ");
        vista.txtCodigo.setText("");
        vista.cbEnfermedad.setSelectedItem(" ");
    }

    public boolean siVacio() {
        return (vista.txtNombre.getText().isEmpty()
                || vista.txtApellido.getText().isEmpty()
                || " ".equals(vista.cbSexo.getSelectedItem().toString())
                || vista.txtEdad.getText().isEmpty()
                || vista.txtCodigo.getText().isEmpty()
                || " ".equals(vista.cbEnfermedad.getSelectedItem().toString()));
    }

}
