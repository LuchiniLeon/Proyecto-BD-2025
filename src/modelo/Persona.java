package modelo;

public class Persona extends Usuario {

    private int dni;
    private String nombre;
    private String fechaNac; 
    private int edad; 

    public Persona() {
        super(); // Llama al constructor base de Usuario
    }

    // Constructor con los campos del padre (direccion, telefono)
    public Persona(String direccion, String telefono, int dni, String nombre, String fechaNac, int edad) {
        super(direccion, telefono); 
        
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