/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crud;

import com.common.Constantes;
import com.modelo.Avion;
import com.modelo.Vuelo;
import com.modelo.ExceptionManager;
import com.modelo.FormateaFecha;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amunguia
 */
public class CRUDVuelo implements ICRUDGeneral<Vuelo> {

    private final Connection conexion;

    public CRUDVuelo(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Inserta un registro en la tabla Vuelos
     *
     * @param vuelo
     * @throws ExceptionManager
     */
    @Override
    public void insertar(Vuelo vuelo) throws ExceptionManager {
        String consulta = "INSERT INTO Vuelos(" + Constantes.ID_VUELO + ", "
                + Constantes.ORIGEN + ", " + Constantes.DESTINO + ", "
                + Constantes.FECHA + ", " + Constantes.ID_AVION + ", precio) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
            ps.setString(1, vuelo.getId_vuelo());
            ps.setString(2, vuelo.getOrigen());
            ps.setString(3, vuelo.getDestino());
            ps.setDate(4, FormateaFecha.comoDate(vuelo.getFecha()));
            ps.setInt(5, vuelo.getId_avion());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CRUDVuelo.class.getName()).log(Level.SEVERE, "Error al insertar un registro de la tabla VUELOS", ex);
        }
    }

    /**
     * Actualiza los datos de un registro de la tabla Vuelos
     *
     * @param vuelo contiene los datos que se van a actualizar
     * @throws ExceptionManager
     */
    @Override
    public void actualizar(Vuelo vuelo) throws ExceptionManager {
        String consulta = "UPDATE Vuelos SET " + Constantes.ORIGEN + " = ?, " + Constantes.DESTINO
                + " = ?, " + Constantes.FECHA + ", " + Constantes.ID_AVION + " "
                + "WHERE " + Constantes.ID_VUELO + " = ?";
        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
            ps.setString(1, vuelo.getOrigen());
            ps.setString(2, vuelo.getDestino());
            ps.setDate(3, FormateaFecha.comoDate(vuelo.getFecha()));
            ps.setInt(4, vuelo.getId_avion());
            ps.setFloat(5, vuelo.getPrecio());
            ps.setString(6, vuelo.getId_vuelo());

            ps.executeUpdate();//Envia la consulta a la bbdd
        } catch (SQLException ex) {
            Logger.getLogger(CRUDVuelo.class.getName()).log(Level.SEVERE, "Error al actualizar un registro de la tabla VUELOS", ex);
        }
    }

    /**
     * Elimina un registro de la tabla Vuelos
     *
     * @param id identificador del registro que se va a eliminar
     * @throws ExceptionManager
     */
    @Override
    public void eliminar(String id) throws ExceptionManager {
        String consulta = "DELETE FROM Vuelos WHERE " + Constantes.ID_VUELO + " = ?";//Genera la consulta

        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();//Envia la consulta a la bbdd
        } catch (SQLException ex) {
            Logger.getLogger(CRUDVuelo.class.getName()).log(Level.SEVERE, "Error al eliminar un registro de la tabla VUELOS", ex);
        }
    }

    /**
     * Obtiene los datos de un registro por su id_vuelo
     *
     * @param id identificador del registro que se quiere recuperar
     * @return
     * @throws ExceptionManager
     */
    @Override
    public Vuelo obtenerEspecifico(String id) throws ExceptionManager {
        Vuelo vuelo = null;
        String consulta = "SELECT * FROM Vuelos WHERE " + Constantes.ID_VUELO + " = ?";
        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
            //ps.setInt(1, Integer.parseInt(id));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    vuelo = formatearResultado(rs);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDVuelo.class.getName()).log(Level.SEVERE, "Error al obtener un registro de la tabla VUELOS", ex);
        }
        return vuelo;
    }

    /**
     * Recupera todos los registros de la tabla Vuelos
     *
     * 
     * @return lista con todos los registros de la tabla
     */
    @Override
    public ArrayList<Vuelo> obtenerTodos() {
        ArrayList<Vuelo> listaVuelos = new ArrayList<>();
        String consulta = "SELECT * FROM Vuelos";

        try (PreparedStatement ps = conexion.prepareStatement(consulta);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                listaVuelos.add(formatearResultado(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDVuelo.class.getName()).log(Level.SEVERE, "Error al obtener todos los registros de la tabla VUELOS", ex);
        }
        return listaVuelos;
    }

    /**
     * Recupera los registros de la tabla Vuelos filtrando por la categoria de
     * la vuelo y el estado "ACTIVA"
     *
     * @param categoria
     * @return
     */
    public ArrayList<Vuelo> obtenerVuelos(String origen, String destino) {
        ArrayList<Vuelo> listaVuelos = new ArrayList<>();
        String consulta = "SELECT * FROM Vuelos WHERE origen = ? AND destino = ?";
        try (PreparedStatement ps = conexion.prepareStatement(consulta)){;
                ps.setString(1, origen);
                ps.setString(2, destino);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    listaVuelos.add(formatearResultado(rs));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDCliente.class.getName()).log(Level.SEVERE, "Error al obtener un registro de la tabla VUELOS", ex);
        }
        return listaVuelos;
    }
    
    public ArrayList<Vuelo> obtenerVuelosOferta(Boolean oferta) {
        ArrayList<Vuelo> listaVuelos = new ArrayList<>();
        String consulta = "SELECT * FROM Vuelos WHERE oferta = ?";
        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
                ps.setBoolean(1, oferta);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    listaVuelos.add(formatearResultado(rs));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDVuelo.class.getName()).log(Level.SEVERE, "Error al obtener un registro de la tabla VUELOS", ex);
        }
        return listaVuelos;
    }


    @Override
    public Vuelo formatearResultado(ResultSet rs) throws SQLException {
        Vuelo vuelo = null;

        try {
            vuelo = new Vuelo(
                    rs.getString(Constantes.ID_VUELO), 
                    rs.getString(Constantes.ORIGEN),
                    rs.getString(Constantes.DESTINO),
                    FormateaFecha.comoLocalDate(rs.getDate(Constantes.FECHA)),
                    rs.getInt(Constantes.ID_AVION),
                    rs.getFloat("precio"));
        } catch (SQLException ex) {
            Logger.getLogger(CRUDVuelo.class.getName()).log(Level.SEVERE, "No se ha podido formatear la información procedente de la tabla VUELOS", ex);
        }
        return vuelo;
    }

    public boolean avionUso(String id, LocalDate date) {
        boolean uso = false;
        Vuelo vuelo = obtenerEspecifico(id);
        if(vuelo != null){
            if(vuelo.getFecha().isAfter(date)){
                uso = true;
            }
        }
        return uso;
    }
}

