/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo;

import java.sql.Date;
import java.time.LocalDate;
import lombok.Data;

/**
 *
 * @author javi_
 */
@Data
public class Vuelo {
    private String id_vuelo;
    private String origen;
    private String destino;
    private LocalDate fecha;
    private int id_avion;
    private float precio;
    private Boolean oferta;
    
    public Vuelo(String origen, String destino, LocalDate fecha,int id_avion, Boolean oferta) {
        this.fecha = fecha;
        this.origen = origen;
        this.destino = destino;
        this.id_avion = id_avion;
        this.oferta = oferta;
    }

    public Vuelo(String id_vuelo, String origen, String destino, LocalDate fecha, int id_avion, float precio, Boolean oferta) {
        this.id_vuelo = id_vuelo;
        this.fecha = fecha;
        this.origen = origen;
        this.destino = destino;
        this.id_avion = id_avion;
        this.precio = precio;
        this.oferta = oferta;
    }

    public Vuelo(String origen, String destino, LocalDate fecha, int id_avion, float precio, Boolean oferta) {
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.id_avion = id_avion;
        this.precio = precio;
        this.oferta = oferta;
    }
    
}
