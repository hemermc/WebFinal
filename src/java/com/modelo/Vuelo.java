/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo;

import java.sql.Date;

/**
 *
 * @author javi_
 */
public class Vuelo {
    private int numeroAvion;
    private Date fecha;
    private int numeroPasajeros;
    private int precioVuelo;
    private Lugares origen;
    private Lugares destino;

    public Vuelo() {
    }

    public Vuelo(int numeroAvion, Date fecha, int numeroPasajeros, int precioVuelo, Lugares origen, Lugares destino) {
        this.numeroAvion = numeroAvion;
        this.fecha = fecha;
        this.numeroPasajeros = numeroPasajeros;
        this.precioVuelo = precioVuelo;
        this.origen = origen;
        this.destino = destino;
    }

    public int getNumeroAvion() {
        return numeroAvion;
    }

    public void setNumeroAvion(int numeroAvion) {
        this.numeroAvion = numeroAvion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getNumeroPasajeros() {
        return numeroPasajeros;
    }

    public void setNumeroPasajeros(int numeroPasajeros) {
        this.numeroPasajeros = numeroPasajeros;
    }

    public int getPrecioVuelo() {
        return precioVuelo;
    }

    public void setPrecioVuelo(int precioVuelo) {
        this.precioVuelo = precioVuelo;
    }

    public Lugares getOrigen() {
        return origen;
    }

    public void setOrigen(Lugares origen) {
        this.origen = origen;
    }

    public Lugares getDestino() {
        return destino;
    }

    public void setDestino(Lugares destino) {
        this.destino = destino;
    }
}
