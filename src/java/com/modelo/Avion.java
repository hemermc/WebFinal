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
public class Avion {
    private int id_avion;
    private String modelo;
    private int plazas;

    public Avion(int id_avion, String modelo, int plazas) {
        this.id_avion = id_avion;
        this.modelo = modelo;
        this.plazas = plazas;
    }
  
    public Avion(String modelo, int plazas) {
        this.modelo = modelo;
        this.plazas = plazas;
    }
}

