/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author amunguia
 */
@Getter
@Setter
public class Administrador extends Usuario {

    public Administrador(String nombre_usuario, String clave) {
        super(nombre_usuario, clave);
    }
}
