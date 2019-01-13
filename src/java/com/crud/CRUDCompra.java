/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crud;

import com.common.Constantes;
import com.modelo.Compra;
import com.modelo.Compra;
import com.modelo.ExceptionManager;
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
public class CRUDCompra implements ICRUDGeneral<Compra> {

    private final Connection conexion;

    public CRUDCompra(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Inserta un registro en la tabla Compras
     *
     * @param compra
     * @throws ExceptionManager
     */
    @Override
    public void insertar(Compra compra) throws ExceptionManager {
        String consulta = "INSERT INTO Compras(" + Constantes.ID_COMPRA+ 
                ", "+ Constantes.DNI +"," +Constantes.ID_VUELO + ","+ Constantes.ASIENTO+") VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
            ps.setInt(1, compra.getId_compra());
            ps.setString(2, compra.getDni());
            ps.setInt(3, compra.getId_vuelo());
            ps.setInt(4, compra.getAsiento());
        ;
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CRUDCompra.class.getName()).log(Level.SEVERE, "Error al insertar un registro de la tabla COMPRAS", ex);
        }
    }

    /**
     * Actualiza los datos de un registro de la tabla Compras
     *
     * @param compra contiene los datos que se van a actualizar
     * @throws ExceptionManager
     */
    @Override
    public void actualizar(Compra compra) throws ExceptionManager {
        String consulta = "UPDATE Vuelos SET " + Constantes.DNI
                + " = ?, " + Constantes.ID_VUELO + "=?, " + Constantes.ASIENTO + "=? "
                + "WHERE " + Constantes.ID_COMPRA + " = ?";
        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
            ps.setString(1, compra.getDni());
             ps.setInt(2, compra.getId_vuelo());
              ps.setInt(3, compra.getAsiento());
            ps.setInt(4, compra.getId_compra());
            

            ps.executeUpdate();//Envia la consulta a la bbdd
        } catch (SQLException ex) {
            Logger.getLogger(CRUDCompra.class.getName()).log(Level.SEVERE, "Error al actualizar un registro de la tabla COMPRAS", ex);
        }
    }

    /**
     * Elimina un registro de la tabla Compras
     *
     * @param id identificador del registro que se va a eliminar
     * @throws ExceptionManager
     */
    @Override
    public void eliminar(String id) throws ExceptionManager {
        String consulta = "DELETE FROM Compras WHERE " + Constantes.ID_COMPRA + " = ?";//Genera la consulta

        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();//Envia la consulta a la bbdd
        } catch (SQLException ex) {
            Logger.getLogger(CRUDCompra.class.getName()).log(Level.SEVERE, "Error al eliminar un registro de la tabla COMPRAS", ex);
        }
    }

    /**
     * Obtiene los datos de un registro por su id_compra
     *
     * @param id identificador del registro que se quiere recuperar
     * @return
     * @throws ExceptionManager
     */
    @Override
    public Compra obtenerEspecifico(String id) throws ExceptionManager {
        Compra compra = null;
        String consulta = "SELECT * FROM Compras WHERE " + Constantes.ID_COMPRA + " = ?";
        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
            ps.setInt(1, Integer.parseInt(id));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    compra = formatearResultado(rs);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDCompra.class.getName()).log(Level.SEVERE, "Error al obtener un registro de la tabla COMPRAS", ex);
        }
        return compra;
    }

    /**
     * Recupera todos los registros de la tabla Compras
     *
     * @return lista con todos los registros de la tabla
     */
    @Override
    public ArrayList<Compra> obtenerTodos() {
        ArrayList<Compra> listaCompras = new ArrayList<>();
        String consulta = "SELECT * FROM Compras";

        try (PreparedStatement ps = conexion.prepareStatement(consulta);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                listaCompras.add(formatearResultado(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDCompra.class.getName()).log(Level.SEVERE, "Error al obtener todos los registros de la tabla COMPRAS", ex);
        }
        return listaCompras;
    }

    @Override
    public Compra formatearResultado(ResultSet rs) throws SQLException {
        Compra compra = null;

        try {
            compra = new Compra(
                    rs.getInt(Constantes.ID_COMPRA),
                    rs.getString(Constantes.DNI),
                    rs.getInt(Constantes.ID_VUELO),
                    rs.getInt(Constantes.ASIENTO));
        } catch (SQLException ex) {
            Logger.getLogger(CRUDCompra.class.getName()).log(Level.SEVERE, "No se ha podido formatear la información procedente de la tabla COMPRAS", ex);
        }
        return compra;
    }
}
