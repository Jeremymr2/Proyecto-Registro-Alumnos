package modelo;

/**
 *
 * @author jmr2_
 */
public class Tutor extends Persona{
    
    public Tutor(String nombre, String apellido, int edad, String sexo) {
        super(nombre, apellido, edad, sexo);
    }

    @Override
    public String toString() {
        return "Tutor{" + '}';
    }

}
