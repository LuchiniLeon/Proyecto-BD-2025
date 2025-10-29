package modelo;

// Clase PADRE: Contiene los atributos comunes de la tabla 'usuario'
public abstract class Usuario {

    // Atributos de la tabla 'usuario'
    private int id; 
    private String direccion;
    private String telefono;

    // Constructor base
    public Usuario() {}

    // Constructor para la inserción (sin ID)
    public Usuario(String direccion, String telefono) {
        this.direccion = direccion;
        this.telefono = telefono;
    }

    //setters y getters 
    
    public int getId() { 
        return id; 
    }

    public void setId(int id) { 
        this.id = id; 
    }
    
    public String getDireccion() {
        return direccion; 
    }

    public void setDireccion(String direccion) { 
        this.direccion = direccion; 
    }
    
    public String getTelefono() {
        return telefono; 
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
     }
}