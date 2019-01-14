/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo;

import lombok.Data;

/**
 *
 * @author amunguia
 */
@Data
public class Aeropuerto {
    private int id_aeropuerto;
    private String nombre;
    private String lugar;
    private float tasa;

    public Aeropuerto(int id_aeropuerto, String nombre, String lugar, float tasa) {
        this.id_aeropuerto = id_aeropuerto;
        this.nombre = nombre;
        this.lugar = lugar;
        this.tasa = tasa;
    }
    
    
}
