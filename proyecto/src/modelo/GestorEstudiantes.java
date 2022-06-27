package modelo;

import datos.Repositorio;
import java.io.IOException;
import java.util.Objects;
import javax.swing.JOptionPane;

/**
 *
 * Clase donde se hará el arreglo de objetos de la clase Estudiante
 */
public class GestorEstudiantes {

    //Métodos
    /**
     * Registra un nuevo estudiante.
     * True si se pudo registrar con éxito el nuevo estudiante. False si
     * no fue posible registrarlo.
     */
    public boolean agregarEstudiante(String nombre, String apellido, int edad, String sexo, String codigo, String enfermedad) {
        //Verificando que el código no exista
        for (Estudiante e : Repositorio.estudiantes) {
            if (Objects.equals(e.getCodigo(), codigo)) {
                return false;   //Devuelve FALSE si el código existe
            }
        }
        Estudiante nuevoEstudiante = new Estudiante(nombre, apellido, edad, sexo, codigo, enfermedad);
        return Repositorio.estudiantes.add(nuevoEstudiante);//Devuelve TRUE si se insertó correctamente
    }

    public boolean agregarEstudiante(Estudiante std) {
        //Verificando que el código no exista
        for (Estudiante a : Repositorio.estudiantes) {
            if (std.getCodigo().equals(a.getCodigo())) {
                return false;   //Devuelve FALSE si el código existe
            }
        }
        Estudiante nuevoEstudiante = new Estudiante(std.getNombre(), std.getApellido(), std.getEdad(), std.getSexo(), std.getCodigo(), std.getEnfermedad());
        return Repositorio.estudiantes.add(nuevoEstudiante);//Devuelve TRUE si se insertó correctamente
    }

    /**
     * Busca estudiante por código.
     *
     * Retorna el objeto de tipo Estudiante si lo encuentra en el
     * ArrayList
     */
    public Estudiante buscarEstudiante(String codigo) {
        for (Estudiante e : Repositorio.estudiantes) {
            if (Objects.equals(e.getCodigo(), codigo)) {
                return e;
            }
        }
        JOptionPane.showMessageDialog(null, "ERROR: Estudiante no encontrado.");
        return (null);
    }

    public boolean buscar(String codigo) {
        for (Estudiante e : Repositorio.estudiantes) {
            if (Objects.equals(e.getCodigo(), codigo)) {
                return true;
            }
        }
        JOptionPane.showMessageDialog(null, "ERROR: Estudiante no encontrado.");
        return false;
    }

    /**
     * Elimina los datos de un estudiante registrado por código.
     *
     */
    public void eliminarEstudiante(String codigo) {
        try {
            Repositorio.estudiantes.remove(buscarEstudiante(codigo));
        } catch (Exception a) {
        }
    }

    public void eliminarEstudiante(Estudiante e) {
        try {
            Repositorio.estudiantes.remove(e);
        } catch (Exception a) {
        }
    }
    
    /**
     * Muestra al estudiante buscado mediante código
     *
     */
    public void mostrar(String codigo) {
        try {
            Estudiante e = buscarEstudiante(codigo);
            e.mostrar();
        } catch (Exception a) {
        }
    }

    public void mostrar(Estudiante e) {
        e.mostrar();
    }

    /**
     * Establece la cantidad de estudiantes
     * enfermos,sanos,aprobados,desaprobados
     *
     */
    public void datos() {
        Repositorio.enfermos = 0;
        Repositorio.sanos = 0;
        Repositorio.aprobados = 0;
        Repositorio.desaprobados = 0;

        for (Estudiante e : Repositorio.estudiantes) {
            if ("Si".equals(e.getEnfermedad())) {
                Repositorio.enfermos++;
            }
            if ("No".equals(e.getEnfermedad())) {
                Repositorio.sanos++;
            }
            if ("Promovido".equals(e.getEstado())) {
                Repositorio.aprobados++;
            }
            if ("Repitente".equals(e.getEstado())) {
                Repositorio.desaprobados++;
            }
        }
    }
    
    /**
     * Cambiar información del estudiante en una posición dada
     *
     */
    public boolean cambiarEstudiante(Estudiante e, int posicion) {
        boolean temp = false;
        try {
            Repositorio.estudiantes.get(posicion).setNombre(e.getNombre());
            Repositorio.estudiantes.get(posicion).setApellido(e.getApellido());
            Repositorio.estudiantes.get(posicion).setEdad(e.getEdad());
            Repositorio.estudiantes.get(posicion).setSexo(e.getSexo());
            Repositorio.estudiantes.get(posicion).setEnfermedad(e.getEnfermedad());
            Repositorio.estudiantes.get(posicion).setCodigo(e.getCodigo());
            temp = true;
        } catch (Exception ex) {
            return temp;
        }

        return temp;
    }
    
    
    /**
     * Promedia las notas y le da un valor a notaFinal
     *
     */
    public void promediar() {
        for (Estudiante e : Repositorio.estudiantes) {
            e.promedio();
        }
    }
    
    /**
     * Elimina los estudiantes del arraylist
     *
     */
    public void limpiar() {
        Repositorio.estudiantes.clear();
    }
    
    /**
     * Devuelve la posición según el código dado
     *
     */
    public int posicion(String codigo) {
        return Repositorio.estudiantes.indexOf(buscarEstudiante(codigo));
    }

    public int posicion(Estudiante e) {
        return Repositorio.estudiantes.indexOf(e);
    }
    
    /**
     * Devuelve el tamaño del arraylist
     *
     */
    public int size() {
        return Repositorio.estudiantes.size();
    }
    
    /**
     * Devuelve el estudiante según la posición dada
     *
     */
    public Estudiante get(int i) {
        return Repositorio.estudiantes.get(i);
    }
    
    /**
     * Verifica si el arraylist está vacío
     *
     */
    public boolean isEmpty() {
        return Repositorio.estudiantes.isEmpty();
    }
    
    /**
     * Guarda datos en el excel
     *
     */
    public void guardarExcel(Estudiante e,int posicion) throws IOException{
        Excel excel = new Excel();
        excel.modificar(e,posicion);
    }
    
    public void excel() throws IOException{
        for(Estudiante e:Repositorio.estudiantes){
            guardarExcel(e,posicion(e));
        }
        JOptionPane.showMessageDialog(null, "Datos guardados a excel");
    }
}
