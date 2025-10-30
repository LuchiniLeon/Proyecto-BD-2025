package modelo;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Persona extends Usuario {

    private int dni;
    private String nombre;
    private String fechaNac; 
    private int edad; 

    public Persona() {
        super(); // Llama al constructor base de Usuario
    }

    // Constructor con los campos del padre (direccion, telefono)
    public Persona(String direccion, String telefono, int dni, String nombre, String fechaNac) {
        super(direccion, telefono); 
        
        this.dni = dni;
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.edad = calcularEdad();
    }

    private int calcularEdad(){
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //Formatea el string con el patrón entre comillas
        LocalDate fNac = LocalDate.parse(fechaNac,f); //Convierte la fecha de nacimiento (string) en un día
        LocalDate fActual = LocalDate.now(); //Obtiene la fecha actual del sistema
        return Period.between(fNac, fActual).getYears(); // retorna la edad
    }
    //get y sets
    // dni

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    // nombre

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    // fecha nacimiento

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
        this.edad = calcularEdad();
    }

    // edad

    public int getEdad() {
        return edad;
    }

}