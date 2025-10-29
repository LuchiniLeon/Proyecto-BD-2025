package modelo;

// Clase HIJA: DEBE extender a Usuario para heredar direccion y telefono
public class Empresa extends Usuario { 

    // Atributos propios de empresa
    private long cuit; // BIGINT en SQL
    private int capacidadInstalada; // Entre 0 y 50000 (ver DDL)

    // Constructor sin parámetros
    public Empresa() {
        super(); // Llama al constructor base de Usuario
    }

    // Constructor de insercion: DEBE incluir los campos del padre (direccion, telefono)
    public Empresa(String direccion, String telefono, long cuit, int capacidadInstalada) {
        //Inicializa los atributos de la clase padre (Usuario)
        super(direccion, telefono); 
        
        this.cuit = cuit;
        this.capacidadInstalada = capacidadInstalada;
    }


    //getters y setters :)
    
    // cuit
    public long getCuit() {
        return cuit;
    }

    public void setCuit(long cuit) {
        this.cuit = cuit;
    }

    // capacidad instalada
    public int getCapacidadInstalada() {
        return capacidadInstalada;
    }

    public void setCapacidadInstalada(int capacidadInstalada) {
        this.capacidadInstalada = capacidadInstalada;
    }
    
}