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
public class Compra {
    private int id_compra;
    private String dni;
    private int asiento;
    private String id_vuelo;

    public Compra(int id_compra, String dni, int asiento, String id_vuelo) {
        this.id_compra = id_compra;
        this.dni = dni;
        this.id_vuelo = id_vuelo;
        this.asiento = asiento;
    }
    
    
}