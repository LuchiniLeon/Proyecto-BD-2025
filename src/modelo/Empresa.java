package modelo;

public class Empresa {

    // atributo heredado de Usuario (fk)
    private int id; 
    private String direccion;
    private String telefono;

    // atributos propios de empresa
    private long cuit; // BIGINT en SQL
    private int capacidadInstalada; // Entre 0 y 50000 (ver DDL)

    //constructor 1
    public Empresa() {
    }

    // Constructor de insercion (id no va porque es autoincremental)
    public Empresa(String direccion, String telefono, long cuit, int capacidadInstalada) {
        this.direccion = direccion;
        this.telefono = telefono;
        this.cuit = cuit;
        this.capacidadInstalada = capacidadInstalada;
    }

    //getters y setters

    // id (autoincremental ;) )

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    //direccion

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    //telefono

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    //cuit

    public long getCuit() {
        return cuit;
    }

    public void setCuit(long cuit) {
        this.cuit = cuit;
    }

    //capacidad instalada

    public int getCapacidadInstalada() {
        return capacidadInstalada;
    }

    public void setCapacidadInstalada(int capacidadInstalada) {
        this.capacidadInstalada = capacidadInstalada;
    }
}