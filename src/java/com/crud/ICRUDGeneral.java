/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crud;

import com.modelo.ExceptionManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author amunguia
 */
public interface ICRUDGeneral<T> {

    void insertar(T obj) throws ExceptionManager;

    void eliminar(String id) throws ExceptionManager;

    void actualizar(T obj) throws ExceptionManager;
    
    T obtenerEspecifico(String id) throws ExceptionManager;
    
    List<T> obtenerTodos() throws ExceptionManager;

    T formatearResultado(ResultSet rs) throws SQLException;
}

