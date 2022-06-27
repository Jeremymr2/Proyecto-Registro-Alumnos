package controladores;

import modelo.Estudiante;
import modelo.GestorEstudiantes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Excel;
import vistas.VistaRegistrar;
import vistas.VistaEstudiantes;

/**
 *
 * Controlador de VistaRegistrar
 */
public class ControladorRegistrar implements ActionListener{
    
    //Atributos
    private VistaRegistrar vista;
    private GestorEstudiantes estudiantes = new GestorEstudiantes();
    
    //Constructor
    public ControladorRegistrar(VistaRegistrar vista) {
        this.vista = vista;
        this.vista.btnRegistrar.addActionListener(this);
        this.vista.btnRegresar.addActionListener(this);
    }
    
    //Métodos
    public void iniciar(){
        vista.setTitle("Registrar Estudiantes");
        vista.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btnRegistrar){
            agregar();
        }
        if(e.getSource() == vista.btnRegresar){
            goRegresar();
        }
    }
    
    public void agregar(){
        String nombre = vista.txtNombre.getText();
        String apellido = vista.txtApellido.getText();
        Excel excel = new Excel();
        try {
        int edad = Integer.parseInt(vista.txtEdad.getText());
        String sexo =vista.cbSexo.getSelectedItem().toString();
        String codigo = vista.txtCodigo.getText();
        String enfermedad = vista.cbEnfermedad.getSelectedItem().toString();
        
        Estudiante temp = new Estudiante(nombre,apellido,edad,sexo,codigo,enfermedad);
        
            if(siVacio())
                JOptionPane.showMessageDialog(null, "ERROR: Complete todos los campos");
            else
                if(estudiantes.agregarEstudiante(temp)){
                    JOptionPane.showMessageDialog(null, "¡Estudiante registrado con éxito!");
                    vaciarCampos();
                }
                else{
                    JOptionPane.showMessageDialog(null, "ERROR: Código ya registrado");
                    vista.txtCodigo.setText("");
                }
                    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: Su edad debe ser un número");
        }    
    }
    
    public void goRegresar(){
        //Cambiando al JFrame VistaEstudiantes
        VistaEstudiantes vistaTabla = new VistaEstudiantes();
        ControladorEstudiantes ctrlTabla = new ControladorEstudiantes(vistaTabla,estudiantes);
        
        ctrlTabla.iniciar();
        vistaTabla.setVisible(true);
        vista.setVisible(false);
    }
    
    public void vaciarCampos(){
        vista.txtNombre.setText("");
        vista.txtEdad.setText("");
        vista.txtApellido.setText("");
        vista.cbSexo.setSelectedItem(" ");
        vista.txtCodigo.setText("");
        vista.cbEnfermedad.setSelectedItem(" ");
    }
    
    public boolean siVacio(){
        return (vista.txtNombre.getText().isEmpty() 
                || vista.txtApellido.getText().isEmpty() 
                || " ".equals( vista.cbSexo.getSelectedItem().toString())
                || vista.txtEdad.getText().isEmpty() 
                || vista.txtCodigo.getText().isEmpty() 
                || " ".equals(vista.cbEnfermedad.getSelectedItem().toString())
                );
    }

}
