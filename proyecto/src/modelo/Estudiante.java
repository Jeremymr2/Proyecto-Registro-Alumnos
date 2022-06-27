package modelo;

import javax.swing.JOptionPane;

/**
 *
 * Clase base para la información del estudiante
 */
public class Estudiante extends Persona{
    
    //Atributos
    private String codigo;
    private String enfermedad;
    private int notaMatematica; 
    private int notaCiencias;
    private int notaLenguaje;
    private int notaReligion;
    private int notaEFisica;
    private int notaETrabajo;
    private int notaHistoria;
    private int notaECivica;
    private int notaGeografia;
    private int notaArte;
    private int notaIngles;
    private int promedioFinal;
    private String estado;
    
    //Constructor    
    
    public Estudiante(String nombre, String apellido, int edad, String sexo, String codigo, String enfermedad) {
        super(nombre, apellido, edad, sexo);
        this.codigo = codigo;
        this.enfermedad = enfermedad;
    }
    
    public Estudiante(String codigo,String nombre, String apellido,
            int edad, String sexo, String enfermedad, int notaMatematica,
            int notaCiencias, int notaLenguaje, int notaReligion, int notaEFisica,
            int notaETrabajo, int notaHistoria, int notaECivica, int notaGeografia,
            int notaArte, int notaIngles, int promedioFinal, String estado) {
        
        super(nombre, apellido, edad, sexo);
        this.codigo = codigo;
        this.enfermedad = enfermedad;
        this.notaMatematica = notaMatematica;
        this.notaCiencias = notaCiencias;
        this.notaLenguaje = notaLenguaje;
        this.notaReligion = notaReligion;
        this.notaEFisica = notaEFisica;
        this.notaETrabajo = notaETrabajo;
        this.notaHistoria = notaHistoria;
        this.notaECivica = notaECivica;
        this.notaGeografia = notaGeografia;
        this.notaArte = notaArte;
        this.notaIngles = notaIngles;
        this.promedioFinal = promedioFinal;
        this.estado = estado;
    }
    
    
    
    //Métodos
    
    /**
     * Muestra por ventana todos los datos del Estudiante
     */
    public void mostrar() {
        JOptionPane.showMessageDialog(null, 
                "\t---------------"
                +"\nEstudiante"
                +"\nCódigo : " + codigo
                +"\nNombre: "+getNombre()
                +"\nApellidos: " + getApellido()
                +"\nEdad: " + getEdad()
                +"\nGenero: " + getSexo()
                +"\nEnfermedad: " + enfermedad
                +"\n\t---------------"
        );
    }
    
    public void promedio(){
        promedioFinal = (notaMatematica+notaCiencias+notaLenguaje+notaReligion
                +notaEFisica+notaETrabajo+notaHistoria+notaECivica+notaGeografia
                +notaArte+notaIngles)/11;
       
        if(promedioFinal>=11)
            estado="Promovido";
        else
            estado="Repitente"; 
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public int getPromedioFinal() {
        return promedioFinal;
    }

    public void setPromedioFinal(int promedioFinal) {
        this.promedioFinal = promedioFinal;
    }

    public int getNotaMatematica() {
        return notaMatematica;
    }

    public void setNotaMatematica(int notaMatematica) {
        this.notaMatematica = notaMatematica;
    }

    public int getNotaCiencias() {
        return notaCiencias;
    }

    public void setNotaCiencias(int notaCiencias) {
        this.notaCiencias = notaCiencias;
    }

    public int getNotaLenguaje() {
        return notaLenguaje;
    }

    public void setNotaLenguaje(int notaLenguaje) {
        this.notaLenguaje = notaLenguaje;
    }

    public int getNotaReligion() {
        return notaReligion;
    }

    public void setNotaReligion(int notaReligion) {
        this.notaReligion = notaReligion;
    }

    public int getNotaEFisica() {
        return notaEFisica;
    }

    public void setNotaEFisica(int notaEFisica) {
        this.notaEFisica = notaEFisica;
    }

    public int getNotaETrabajo() {
        return notaETrabajo;
    }

    public void setNotaETrabajo(int notaETrabajo) {
        this.notaETrabajo = notaETrabajo;
    }

    public int getNotaHistoria() {
        return notaHistoria;
    }

    public void setNotaHistoria(int notaHistoria) {
        this.notaHistoria = notaHistoria;
    }

    public int getNotaECivica() {
        return notaECivica;
    }

    public void setNotaECivica(int notaECivica) {
        this.notaECivica = notaECivica;
    }

    public int getNotaGeografia() {
        return notaGeografia;
    }

    public void setNotaGeografia(int notaGeografia) {
        this.notaGeografia = notaGeografia;
    }

    public int getNotaArte() {
        return notaArte;
    }

    public void setNotaArte(int notaArte) {
        this.notaArte = notaArte;
    }

    public int getNotaIngles() {
        return notaIngles;
    }

    public void setNotaIngles(int notaIngles) {
        this.notaIngles = notaIngles;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
        @Override
    public String toString() {
        return "Codigo=" + codigo + "\n Enfermedad=" + enfermedad;
    }
}
