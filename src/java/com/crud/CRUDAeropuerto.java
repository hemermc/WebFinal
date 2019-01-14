/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crud;

import com.common.Constantes;
import com.modelo.Aeropuerto;
import com.modelo.ExceptionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amunguia
 */
public class CRUDAeropuerto implements ICRUDGeneral<Aeropuerto>{

    private final Connection conexion;

    public CRUDAeropuerto (Connection conexion) {
        this.conexion = conexion;
    }
     @Override
    public void insertar(Aeropuerto aeropuerto) throws ExceptionManager {
        String consulta = "INSERT INTO Aeropuerto(id_aeropuerto, nombre, lugar, tasa) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
            ps.setInt(1, aeropuerto.getId_aeropuerto());
            ps.setString(2, aeropuerto.getNombre());
            ps.setString(3, aeropuerto.getLugar());
            ps.setFloat(4, aeropuerto.getTasa());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CRUDAeropuerto.class.getName()).log(Level.SEVERE, "Error al insertar un registro de la tabla AEROPUERTOS", ex);
        }//aeropuerto
    }

    /**
     * Actualiza los datos de un registro de la tabla Aeropuertos
     *
     * @param aeropuerto contiene los datos que se van a actualizar
     * @throws ExceptionManager
     */
    @Override
    public void actualizar(Aeropuerto aeropuerto) throws ExceptionManager {
        String consulta = "UPDATE Aeropuerto SET nombre = ?, lugar = ?, tasa =? "
                + "WHERE " + Constantes.ID_AEROPUERTO + " = ?";
        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
            ps.setString(1, aeropuerto.getNombre());
            ps.setString(2, aeropuerto.getLugar());
            ps.setFloat(3, aeropuerto.getTasa());
            ps.setInt(4, aeropuerto.getId_aeropuerto());

            ps.executeUpdate();//Envia la consulta a la bbdd
        } catch (SQLException ex) {
            Logger.getLogger(CRUDAeropuerto.class.getName()).log(Level.SEVERE, "Error al actualizar un registro de la tabla AEROPUERTOS", ex);
        }
    }

    /**
     * Elimina un registro de la tabla Aeropuertos
     *
     * @param id identificador del registro que se va a eliminar
     * @throws ExceptionManager
     */
    @Override
    public void eliminar(String id) throws ExceptionManager {
        String consulta = "DELETE FROM Aeropuerto WHERE " + Constantes.ID_AEROPUERTO + " = ?";//Genera la consulta

        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();//Envia la consulta a la bbdd
        } catch (SQLException ex) {
            Logger.getLogger(CRUDAeropuerto.class.getName()).log(Level.SEVERE, "Error al eliminar un registro de la tabla AEROPUERTOS", ex);
        }
    }

    /**
     * Obtiene los datos de un registro por su id_aeropuerto
     *
     * @param id identificador del registro que se quiere recuperar
     * @return
     * @throws ExceptionManager
     */
    @Override
    public Aeropuerto obtenerEspecifico(String id) throws ExceptionManager {
        Aeropuerto aeropuerto = null;
        String consulta = "SELECT * FROM Aeropuerto WHERE " + Constantes.ID_AEROPUERTO + " = ?";
        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
            ps.setInt(1, Integer.parseInt(id));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    aeropuerto = formatearResultado(rs);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDAeropuerto.class.getName()).log(Level.SEVERE, "Error al obtener un registro de la tabla AEROPUERTOS", ex);
        }
        return aeropuerto;
    }

    /**
     * Recupera todos los registros de la tabla Aeropuertos
     *
     * @return lista con todos los registros de la tabla
     */
    @Override
    public ArrayList<Aeropuerto> obtenerTodos() {
        ArrayList<Aeropuerto> listaAeropuertos = new ArrayList<>();
        String consulta = "SELECT * FROM Aeropuerto";

        try (PreparedStatement ps = conexion.prepareStatement(consulta);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                listaAeropuertos.add(formatearResultado(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDAeropuerto.class.getName()).log(Level.SEVERE, "Error al obtener todos los registros de la tabla AEROPUERTOS", ex);
        }
        return listaAeropuertos;
    }

    @Override
    public Aeropuerto formatearResultado(ResultSet rs) throws SQLException {
        Aeropuerto aeropuerto = null;

        try {
            aeropuerto = new Aeropuerto(
                    rs.getInt(Constantes.ID_AEROPUERTO), 
                    rs.getString("nombre"),
                    rs.getString("lugar"),
                    rs.getFloat("tasa"));
        } catch (SQLException ex) {
            Logger.getLogger(CRUDAeropuerto.class.getName()).log(Level.SEVERE, "No se ha podido formatear la información procedente de la tabla AEROPUERTOS", ex);
        }
        return aeropuerto;
    }
    
}
