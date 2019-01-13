/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crud;

import com.modelo.Administrador;
import com.modelo.Usuario;
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
public class CRUDAdministrador implements ICRUDGeneral<Administrador>, ICRUDUsuario {

    private final Connection conexion;

    /**
     * Constructor
     *
     * @param conexion
     */
    public CRUDAdministrador(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Inserta un registro en la tabla Usuarios
     *
     * @param administrador
     */
    @Override
    public void insertar(Administrador administrador) {
        String consulta = "INSERT INTO Administradores VALUES (?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {//Implementa el autocierre del PreparedStatement
            ps.setString(1, administrador.getNombre_usuario());
            ps.setString(2, administrador.getClave());
            ps.setInt(3, administrador.getNivel_permisos());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CRUDCliente.class.getName()).log(Level.SEVERE, "Error al insertar un registro de la tabla ADMINISTRADORES", ex);
        }
    }

    /**
     *
     * @param nombre_usuario
     */
    @Override
    public void eliminar(String nombre_usuario) {
        String consulta = "DELETE FROM Administradores WHERE nombre_usuario = ?";//Genera la consulta
        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
            ps.setString(1, nombre_usuario);
            ps.executeUpdate();//Envia la consulta a la bbdd
        } catch (SQLException ex) {
            Logger.getLogger(CRUDCliente.class.getName()).log(Level.SEVERE, "Error al eliminar un registro de la tabla ADMINISTRADORES", ex);
        }
    }

    /**
     * MODIFICABLE--Esta pensado para que antes de actualizar obtengas todos los
     * datos y solo se modifiquen los que elija el administrador así al llamar a
     * este método se tendrán todos los datos asociados a un usuario y no solo
     * el que se ha modificado.
     *
     * @param administrador
     */
    @Override
    public void actualizar(Administrador administrador) {
        String consulta = "UPDATE Administradores "
                + "SET clave = ?, nivel_permisos = ? WHERE nombre_usuario = ?";
        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
            ps.setString(1, administrador.getClave());
            ps.setInt(2, administrador.getNivel_permisos());
            ps.setString(3, administrador.getNombre_usuario());
            ps.executeUpdate();//Envia la consulta a la bbdd
        } catch (SQLException ex) {
            Logger.getLogger(CRUDCliente.class.getName()).log(Level.SEVERE, "Error al actualizar un registro de la tabla ADMINISTRADORES", ex);
        }
    }

    /**
     * Comprueba si existe un registro con el identidicador pasado por parametro
     * y lo devuelve.
     *
     * @param nombre_usuario
     * @return Objeto que contiene los datos del registro buscado.
     */
    @Override
    public Administrador obtenerEspecifico(String nombre_usuario) {
        Administrador administrador = null;
        String consulta = "SELECT * FROM Administradores WHERE nombre_usuario = ?";
        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
            ps.setString(1, nombre_usuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    administrador = formatearResultado(rs);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDCliente.class.getName()).log(Level.SEVERE, "Error al obtener un registro de la tabla ADMINISTRADORES", ex);
        }
        return administrador;
    }

    /**
     * Devuelve todos los registros que contiene la tabla Usuarios.
     *
     * @return Lista que contiene los registros de la tabla.
     */
    @Override
    public List<Administrador> obtenerTodos() {
        ArrayList<Administrador> listaUsuarios = new ArrayList<>();
        String consulta = "SELECT * FROM Administradores";
        try (PreparedStatement ps = conexion.prepareStatement(consulta);
                ResultSet rs = ps.executeQuery(consulta)) {
            while (rs.next()) {
                listaUsuarios.add(formatearResultado(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDCliente.class.getName()).log(Level.SEVERE, "Error al obtener todos los registros de la tabla ADMINISTRADORES", ex);
        }
        return listaUsuarios;
    }

    /**
     * Obtiene los datos del ResultSet y los almacena en un objeto Usuario.
     *
     * @param rs ResultSet que contiene los datos del registro obtenido de la
     * BBDD.
     * @return Objeto con los datos obtenidos.
     */
    @Override
    public Administrador formatearResultado(ResultSet rs) {
        Administrador administrador = null;
        try {
            administrador = new Administrador(
                    rs.getString("nombre_usuario"),
                    rs.getString("clave"),
                    rs.getInt("nivel_permisos"));
        } catch (SQLException ex) {
            Logger.getLogger(CRUDCliente.class.getName()).log(Level.SEVERE, "No se ha podido formatear la información procedente de la tabla ADMINISTRADORES", ex);
        }
        return administrador;
    }

    /**
     * Comprueba si el nombre de usuario ya está registrado en la tabla
     * ADMINISTRADORES
     *
     * @param nombre_usuario
     * @return booleano que indica si la información es correcta.
     */
    @Override
    public boolean esUsuarioRegistrado(String nombre_usuario) {
        boolean valido = false;
        if (obtenerEspecifico(nombre_usuario) != null) {
            valido = true;
        }
        return valido;
    }

    /**
     * Comprueba si los datos de inicio de sesión son correctos
     *
     * @param usuario
     * @return
     */
    @Override
    public boolean inicioSesionValido(Usuario usuario) {
        String consulta = "SELECT * FROM Administradores WHERE nombre_usuario = ? AND clave = ?";
        boolean valido = false;
        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
            ps.setString(1, usuario.getNombre_usuario());
            ps.setString(2, usuario.getClave());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    valido = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDCliente.class.getName()).log(Level.SEVERE, "Error al comprobar la identidad en la tabla ADMINISTRADORES", ex);
        }
        return valido;
    }
}

