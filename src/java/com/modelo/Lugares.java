/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo;

/**
 *
 * @author javi_
 */
public class Lugares {
    
    private String nombre;
    private int tasaAeropuerto;
    private int tipo;

    public Lugares() {

    }

    public Lugares(String nombre, int tasaAeropuerto, int tipo) {
        this.nombre = nombre;
        this.tasaAeropuerto = tasaAeropuerto;
        this.tipo =  tipo;            
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTasaAeropuerto() {
        return tasaAeropuerto;
    }

    public void setTasaAeropuerto(int tasaAeropuerto) {
        this.tasaAeropuerto = tasaAeropuerto;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
