/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo;

import java.sql.Date;
import java.util.ArrayList;
import lombok.Data;


@Data
public class Usuario {

    protected final String nombre_usuario;
    protected final String clave;

    public Usuario(String nombre_usuario, String clave) {
        this.nombre_usuario = nombre_usuario;
        this.clave = clave;
    }
}