package app;

import controladores.ControladorPrincipal;
import java.io.IOException;
import modelo.Estudiante;
import vistas.VistaPrincipal;

/**
 *
 * Clase donde se ejecutará el main
 */
public class App {
    public static void main(String[] args) throws IOException {
        VistaPrincipal viewPrincipal = new VistaPrincipal();
        ControladorPrincipal ctrlPrincipal = new ControladorPrincipal(viewPrincipal);
        
        //excel.CeldasDatos(estudiante);
        ctrlPrincipal.iniciar();
        viewPrincipal.setVisible(true);
    }
}
