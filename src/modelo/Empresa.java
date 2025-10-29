package modelo;

public class Empresa {

    // atributos propios de empresa
    private long cuit; // BIGINT en SQL
    private int capacidadInstalada; // Entre 0 y 50000 (ver DDL)

    //constructor 1
    public Empresa() {
    }

    // Constructor de insercion (id no va porque es autoincremental)
    public Empresa(long cuit, int capacidadInstalada) {
        this.cuit = cuit;
        this.capacidadInstalada = capacidadInstalada;
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