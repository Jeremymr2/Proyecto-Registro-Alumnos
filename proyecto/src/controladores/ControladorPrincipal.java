package controladores;

import modelo.GestorEstudiantes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Excel;
import vistas.VistaBuscar;
import vistas.VistaEstadistica;
import vistas.VistaEstudiantes;
import vistas.VistaListado;
import vistas.VistaPrincipal;

public class ControladorPrincipal implements ActionListener {

    private VistaPrincipal vista;
    private GestorEstudiantes temp = new GestorEstudiantes();

    public ControladorPrincipal(VistaPrincipal vista) {
        temp.datos();
        this.vista = vista;
        this.vista.btnDatos.addActionListener(this);
        this.vista.btnRegistro.addActionListener(this);
        this.vista.btnListado.addActionListener(this);
        this.vista.btnEstadistica.addActionListener(this);
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnCargar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnDatos) {
            goDatos();
        }
        if (e.getSource() == vista.btnRegistro) {
            goRegistro();
        }
        if (e.getSource() == vista.btnListado) {
            goListado();
        }
        if (e.getSource() == vista.btnEstadistica) {
            goEstadistica();
        }
        if (e.getSource() == vista.btnGuardar) {
            try {
                crearExcel();
            } catch (IOException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == vista.btnCargar) {
            cargarDatos();
        }
    }

    public void iniciar() {
        vista.setTitle("Menú principal");
        vista.setLocationRelativeTo(null);
    }

    public void goDatos() {
        //Cambia al JFrame VistaEstudiantes
        VistaEstudiantes vistaTabla = new VistaEstudiantes();
        ControladorEstudiantes ctrlTabla = new ControladorEstudiantes(vistaTabla, temp);

        ctrlTabla.iniciar();
        vistaTabla.setVisible(true);
        vista.setVisible(false);
    }

    public void goRegistro() {
        //Cambiando al JFrame VistaEstudiantes
        VistaBuscar vistaBuscar = new VistaBuscar();
        ControladorBuscar ctrlBuscar = new ControladorBuscar(vistaBuscar, 2);

        ctrlBuscar.iniciar();
        vistaBuscar.setVisible(true);
        vista.setVisible(false);
    }

    public void goListado() {
        //Cambiando al JFrame VistaListado
        VistaListado vistaListado = new VistaListado();
        ControladorListado ctrlListado = new ControladorListado(vistaListado);

        ctrlListado.iniciar();
        vistaListado.setVisible(true);
        vista.setVisible(false);
    }

    public void goEstadistica() {
        //Cambiando al JFrame VistaListado
        VistaEstadistica vistaEstadistica = new VistaEstadistica();
        ControladorEstadistica ctrlEstadistica = new ControladorEstadistica(vistaEstadistica);

        ctrlEstadistica.iniciar();
        vistaEstadistica.setVisible(true);
        vista.setVisible(false);
    }

    public void crearExcel() throws IOException {
        Excel excel = new Excel();

        excel.crearExcel();
        temp.excel();
    }

    public void cargarDatos() {
        Excel excel = new Excel();

        try {
            excel.leerArchivo();
        } catch (IOException ex) {
            Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
