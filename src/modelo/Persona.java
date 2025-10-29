package modelo;

public class Persona extends Usuario{

    // Atributos específicos de la tabla 'persona'
    private int dni;
    private String nombre;
    private String fechaNac; // Se puede usar String o java.sql.Date
    private int edad; 

    // constructor sin parametros
    public Persona() {
    }

    // constructor con parametros 
    public Persona(int dni, String nombre, String fechaNac, int edad) {
        this.dni = dni;
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.edad = edad;
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
    }

    // edad

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}

