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
    private int plazas;

    public Avion(int id_avion, int plazas) {
        this.id_avion = id_avion;
        this.plazas = plazas;
    }
  
    public Avion(int id_avion) {
        this.id_avion = id_avion;
        this.plazas = 75;
    }
}

