package modelo;

import java.sql.Timestamp;

public class Rellamado {

    private int nroReclamoRell; // FK de reclamo
    private int nroLlamado; // pk
    private Timestamp fechaHoraLlamado; 

    //constructor vacio (siempre tiene que tener aun si no estan inicializados)
    public Rellamado() {
    }

    //constructor con parametros 
    public Rellamado(int nroReclamoRell, int nroLlamado, Timestamp fechaHoraLlamado) {
        this.nroReclamoRell = nroReclamoRell;
        this.nroLlamado = nroLlamado;
        this.fechaHoraLlamado = fechaHoraLlamado;
    }

    //getters y setters
    //nro reclamo 

    public int getNroReclamoRell() {
        return nroReclamoRell;
    }

    public void setNroReclamoRell(int nroReclamoRell) {
        this.nroReclamoRell = nroReclamoRell;
    }

    //nro llamado

    public int getNroLlamado() {
        return nroLlamado;
    }

    public void setNroLlamado(int nroLlamado) {
        this.nroLlamado = nroLlamado;
    }

    //fecha y hora del llamado

    public Timestamp getFechaHoraLlamado() {
        return fechaHoraLlamado;
    }

    public void setFechaHoraLlamado(Timestamp fechaHoraLlamado) {
        this.fechaHoraLlamado = fechaHoraLlamado;
    }
}