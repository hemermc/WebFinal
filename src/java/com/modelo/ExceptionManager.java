/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo;

/**
 *
 * @author amunguia
 */
public class ExceptionManager extends RuntimeException{

    public ExceptionManager() {
    }

    public ExceptionManager(String message) {
        super(message);
    }

    public ExceptionManager(String message, Throwable cause) {
        super(message, cause);
    }
}

