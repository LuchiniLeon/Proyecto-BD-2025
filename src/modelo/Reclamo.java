package modelo;

import java.sql.Timestamp; // Usamos la biblioteca java.sql.Timestamp para leer de la DB (data/datatime)

public class Reclamo {

    private int nroReclamo;
    private Timestamp fechaHora; // datetime SQL
    private String fechaRes; // date SQL
    private int idRe; // id del user que realiza el reclamo
    
    // Campo extra para el Listar reclamos con cantidad de rellamados -- a charlar 
    private int cantidadRellamados; 

    //constructor 
    public Reclamo() {
    }

    // Constructor parametrizada
    public Reclamo(int nroReclamo, Timestamp fechaHora, String fechaRes, int cantidadRellamados) {
        this.nroReclamo = nroReclamo;
        this.fechaHora = fechaHora;
        this.fechaRes = fechaRes;
        this.cantidadRellamados = cantidadRellamados;
    }

    //getters y setters 
    //numero de reclamo

    public int getNroReclamo() {
        return nroReclamo;
    }

    public void setNroReclamo(int nroReclamo) {
        this.nroReclamo = nroReclamo;
    }

    //fecha hora del reclamo

    public Timestamp getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Timestamp fechaHora) {
        this.fechaHora = fechaHora;
    }

    //fecha resolucion del reclamo

    public String getFechaRes() {
        return fechaRes;
    }

    public void setFechaRes(String fechaRes) {
        this.fechaRes = fechaRes;
    }
    
    //id del usuario que reclamo
    
    public int getIdRe() {
        return idRe;
    }

    public void setIdRe(int idRe) {
        this.idRe = idRe;
    }

    //cantidad de rellamados 

    public int getCantidadRellamados() {
        return cantidadRellamados;
    }

    public void setCantidadRellamados(int cantidadRellamados) {
        this.cantidadRellamados = cantidadRellamados;
    }
    
    // toString de muestra 
    @Override
    public String toString() {
        return String.format("Nro: %d | Origen: %s | Resuelto: %s | Rellamados: %d", nroReclamo, fechaHora.toString(), fechaRes, cantidadRellamados);
    }
}