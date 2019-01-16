/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo;

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
    
    public Vuelo(String origen, String destino, LocalDate fecha) {
        this.fecha = fecha;
        this.origen = origen;
        this.destino = destino;
    }

    public Vuelo(String id_vuelo, String origen, String destino, LocalDate fecha, int id_avion, float precio) {
        this.id_vuelo = id_vuelo;
        this.fecha = fecha;
        this.origen = origen;
        this.destino = destino;
        this.id_avion = id_avion;
        this.precio = precio;
    }
    
}
