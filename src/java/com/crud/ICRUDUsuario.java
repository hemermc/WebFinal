/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crud;

import com.modelo.Usuario;

/**
 *
 * @author amunguia
 */
public abstract interface ICRUDUsuario {

    boolean esUsuarioRegistrado(String nombre_usuario);

    boolean inicioSesionValido(Usuario usuario);
}