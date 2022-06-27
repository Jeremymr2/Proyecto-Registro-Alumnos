package controladores;

import modelo.Estudiante;
import modelo.GestorEstudiantes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import vistas.VistaBuscar;
import vistas.VistaRegistrar;
import vistas.VistaEstudiantes;
import static vistas.VistaEstudiantes.modelo;
import vistas.VistaModificar;
import vistas.VistaPrincipal;

/**
 *
 * Controlador de VistaEstudiantes
 */
public class ControladorEstudiantes implements ActionListener, ItemListener {

    //Atributos
    private VistaEstudiantes vista;
    private GestorEstudiantes estudiantes;

    //Constructor
    public ControladorEstudiantes(VistaEstudiantes vista, GestorEstudiantes estudiantes) {
        this.vista = vista;
        this.estudiantes = estudiantes;
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnRegresar.addActionListener(this);
        this.vista.cbListar.addItemListener(this);
        actualizarTabla();
    }

    //Métodos
    public void iniciar() {
        vista.setTitle("Tabla de Estudiantes");
        vista.setLocationRelativeTo(null);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == vista.cbListar) {
            listar();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnAgregar) {
            goAgregar();
        }
        if (e.getSource() == vista.btnLimpiar) {
            limpiar();
        }
        if (e.getSource() == vista.btnBuscar) {
            buscar();
        }
        if (e.getSource() == vista.btnModificar) {
            modificar();
        }
        if (e.getSource() == vista.btnEliminar) {
            eliminar();
        }
        if (e.getSource() == vista.btnRegresar) {
            goRegresar();
        }
    }
    
    public void listar() {
        String seleccionado = vista.cbListar.getSelectedItem().toString();

        TableRowSorter<TableModel> ordena = new TableRowSorter<TableModel>(modelo);
        vista.tablaEstudiantes.setRowSorter(ordena);

        if (!"Todos".equals(seleccionado)) {
            if (seleccionado.equals("Enfermos")) {
                ordena.setRowFilter(RowFilter.regexFilter("Si"));
            }
            if (seleccionado.equals("Sanos")) {
                ordena.setRowFilter(RowFilter.regexFilter("No"));
            }
        } else {
            vista.tablaEstudiantes.setRowSorter(ordena);
        }
    }

    public void goAgregar() {
        //Cambiando de JFrame a VistaRegistrar
        VistaRegistrar vistaRegistrar = new VistaRegistrar();
        ControladorRegistrar ctrlRegistrar = new ControladorRegistrar(vistaRegistrar);

        ctrlRegistrar.iniciar();
        vistaRegistrar.setVisible(true);
        vista.setVisible(false);
    }

    public void buscar() {
        //Cambiando al JFrame VistaEstudiantes
        VistaBuscar vistaBuscar = new VistaBuscar();
        ControladorBuscar ctrlBuscar = new ControladorBuscar(vistaBuscar,1);

        ctrlBuscar.iniciar();
        vistaBuscar.setVisible(true);
        vista.setVisible(false);
    }

    public void modificar() {
        if (!estudiantes.isEmpty()) {
            //Método que devuelve la fila seleccionada del Jtable
            int fila = vista.tablaEstudiantes.getSelectedRow();
            //Verifica que fila pertenezca dentro del arraylist y mayor o igual a 0 en la tabla (fila y columna)
            if (fila != -1 && fila >= 0) {
                Estudiante cambiarDato = estudiantes.get(fila);
                int pos = estudiantes.posicion(cambiarDato);
                //Cambiando de JFrame a VistaModificar
                VistaModificar vistaModificar = new VistaModificar();
                ControladorModificar ctrlRegistrar = new ControladorModificar(vistaModificar, cambiarDato, pos);

                ctrlRegistrar.iniciar();
                vistaModificar.setVisible(true);
                vista.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "ERROR: Seleccione una fila");
            }
        } else {
            JOptionPane.showMessageDialog(null, "ERROR: No hay filas en la tabla");
        }
    }

    public void eliminar() {
        if (!estudiantes.isEmpty()) {
            //Método que devuelve la fila seleccionada del Jtable
            int fila = vista.tablaEstudiantes.getSelectedRow();
            //Verifica que fila pertenezca dentro del arraylist y mayor o igual a 0 en la tabla
            if (fila != -1 && fila >= 0) {
                //Se elimina el estudiante del arraylist
                estudiantes.eliminarEstudiante(estudiantes.get(fila));
                vista.modelo.removeRow(fila);
            } else {
                JOptionPane.showMessageDialog(null, "ERROR: Seleccione una fila");
            }
        } else {
            JOptionPane.showMessageDialog(null, "ERROR: No hay filas en la tabla");
        }
    }

    public void limpiar() {
        estudiantes.limpiar();
        int fila = vista.tablaEstudiantes.getRowCount();
        for (int i = fila - 1; i >= 0; i--) {
            vista.modelo.removeRow(i);
        }
    }

    public void goRegresar() {
        //Cambiando al JFrame VistaEstudiantes
        VistaPrincipal vistaPrincipal = new VistaPrincipal();
        ControladorPrincipal ctrlPrincipal = new ControladorPrincipal(vistaPrincipal);

        ctrlPrincipal.iniciar();
        vistaPrincipal.setVisible(true);
        vista.setVisible(false);
    }

    public void actualizarTabla() {
        Object[] datos = new Object[6];

        for (int i = 0; i < estudiantes.size(); i++) {
            datos[0] = estudiantes.get(i).getCodigo();
            datos[1] = estudiantes.get(i).getNombre();
            datos[2] = estudiantes.get(i).getApellido();
            datos[3] = String.valueOf(estudiantes.get(i).getEdad());
            datos[4] = estudiantes.get(i).getSexo();
            datos[5] = estudiantes.get(i).getEnfermedad();
            vista.modelo.addRow(datos);
        }
    }

}
