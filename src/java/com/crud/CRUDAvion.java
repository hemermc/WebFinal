/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crud;

import com.common.Constantes;
import com.modelo.Avion;
import com.modelo.ExceptionManager;
import com.modelo.FormateaFecha;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amunguia
 */
public class CRUDAvion implements ICRUDGeneral<Avion> {

    private final Connection conexion;

    public CRUDAvion(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Inserta un registro en la tabla Aviones
     *
     * @param avion
     * @throws ExceptionManager
     */
    @Override
    public void insertar(Avion avion) throws ExceptionManager {
        String consulta = "INSERT INTO Aviones(" + Constantes.ID_AVION+ 
                ", "+ Constantes.PLAZAS +") VALUES (?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
            ps.setInt(1, avion.getId_avion());
            ps.setInt(2, avion.getPlazas());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CRUDAvion.class.getName()).log(Level.SEVERE, "Error al insertar un registro de la tabla AVIONES", ex);
        }
    }

    /**
     * Actualiza los datos de un registro de la tabla Aviones
     *
     * @param avion contiene los datos que se van a actualizar
     * @throws ExceptionManager
     */
    @Override
    public void actualizar(Avion avion) throws ExceptionManager {
        String consulta = "UPDATE Aviones SET " + Constantes.PLAZAS + " = ? " + " "
                + "WHERE " + Constantes.ID_AVION + " = ?";
        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
            ps.setInt(1, avion.getPlazas());
            ps.setInt(2, avion.getId_avion());
            

            ps.executeUpdate();//Envia la consulta a la bbdd
        } catch (SQLException ex) {
            Logger.getLogger(CRUDAvion.class.getName()).log(Level.SEVERE, "Error al actualizar un registro de la tabla AVIONES", ex);
        }
    }

    /**
     * Elimina un registro de la tabla Aviones
     *
     * @param id identificador del registro que se va a eliminar
     * @throws ExceptionManager
     */
    @Override
    public void eliminar(String id) throws ExceptionManager {
        String consulta = "DELETE FROM Aviones WHERE " + Constantes.ID_VUELO + " = ?";//Genera la consulta

        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();//Envia la consulta a la bbdd
        } catch (SQLException ex) {
            Logger.getLogger(CRUDAvion.class.getName()).log(Level.SEVERE, "Error al eliminar un registro de la tabla AVIONES", ex);
        }
    }

    /**
     * Obtiene los datos de un registro por su id_avion
     *
     * @param id identificador del registro que se quiere recuperar
     * @return
     * @throws ExceptionManager
     */
    @Override
    public Avion obtenerEspecifico(String id) throws ExceptionManager {
        Avion avion = null;
        String consulta = "SELECT * FROM Aviones WHERE " + Constantes.ID_AVION + " = ?";
        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
            ps.setInt(1, Integer.parseInt(id));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    avion = formatearResultado(rs);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDAvion.class.getName()).log(Level.SEVERE, "Error al obtener un registro de la tabla AVIONES", ex);
        }
        return avion;
    }

    /**
     * Recupera todos los registros de la tabla Aviones
     *
     * @return lista con todos los registros de la tabla
     */
    @Override
    public ArrayList<Avion> obtenerTodos() {
        ArrayList<Avion> listaAviones = new ArrayList<>();
        String consulta = "SELECT * FROM Aviones";

        try (PreparedStatement ps = conexion.prepareStatement(consulta);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                listaAviones.add(formatearResultado(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDAvion.class.getName()).log(Level.SEVERE, "Error al obtener todos los registros de la tabla AVIONES", ex);
        }
        return listaAviones;
    }

    @Override
    public Avion formatearResultado(ResultSet rs) throws SQLException {
        Avion avion = null;

        try {
            avion = new Avion(
                    rs.getInt(Constantes.ID_AVION), 
                    rs.getInt(Constantes.PLAZAS));
        } catch (SQLException ex) {
            Logger.getLogger(CRUDAvion.class.getName()).log(Level.SEVERE, "No se ha podido formatear la información procedente de la tabla AVIONES", ex);
        }
        return avion;
    }
}