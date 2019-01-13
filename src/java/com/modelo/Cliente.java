/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Cliente extends Usuario{
    private final String nombre;
    private final String apellidos;
    private final String dni;
    private final String direccion_entrega;
    private final int telefono;
    private final String email;

    
    public Cliente(String nombre, String apellidos, String dni, String direccion_entrega, int telefono, String email, String nombre_usuario, String clave) {
        super(nombre_usuario, clave);
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.direccion_entrega = direccion_entrega;
        this.telefono = telefono;
        this.email = email;
    }
    
}