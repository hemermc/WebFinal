/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Controlador;

import com.crud.CRUDAdministrador;
import com.crud.CRUDCliente;
import com.crud.CRUDCompra;
import com.crud.CRUDVuelo;
import com.modelo.Administrador;
import com.modelo.Cliente;
import com.modelo.Compra;
import com.modelo.GestionBBDDLocalhost;
import com.modelo.Usuario;
import com.modelo.Vuelo;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Robert
 */
@WebServlet(name = "ControladorPago", urlPatterns = {"/ControladorPago"})
public class ControladorPago extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GestionBBDDLocalhost gestionDB = GestionBBDDLocalhost.getInstance();
        Connection conexion = gestionDB.establecerConexion();

        HttpSession session = request.getSession();
    }

    /*
    Funcion que se debe ejecutar cuando se pulsa el boton de confirmar
     */
    protected void insertarCompra(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mensaje = "";

        GestionBBDDLocalhost gestionDB = GestionBBDDLocalhost.getInstance();
        Connection conexion = gestionDB.establecerConexion();

        HttpSession session = request.getSession();
        String id_vuelo = (String) session.getAttribute("vueloElegido");
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("./VistaInicioSesion.jsp");
        } else {

            String nombreUsuario = usuario.getNombre_usuario();
            CRUDCliente crudCliente = new CRUDCliente(conexion);
            if (crudCliente.inicioSesionValido(usuario)) {
                Cliente cliente = crudCliente.obtenerEspecifico(nombreUsuario);
                session.setAttribute("datosCliente", cliente);        // se establecen en la sesion los datos del cliente
                String dni = cliente.getDni();
                int asiento = (int) (Math.random() * 75) + 1;
                //String numeroCompra = java.util.UUID.randomUUID().toString(); // GENERAR IDENTIFICADOR UNICO DE COMPRA
                Compra compraUsuario = new Compra( dni, asiento,id_vuelo,0);
                CRUDCompra compra = new CRUDCompra(conexion);
                compra.insertar(compraUsuario);
                session.setAttribute("compraRealizada", compraUsuario);  // se establecen los datos de la compra realizada
                
                response.sendRedirect("./VistaResultadoCompra.jsp");  //Redireccion a la vista donde se muestran los valores de sesion compraRealizada y datosCliente
                //COMPROBAR NOMBRE DE LA VISTA, NO SE COMO SE LA VAS A LLAMAR Y HE PUESTO ESO DE EJEMPLO
                

                mensaje = " COMPRA REALIZADA!";
                session.setAttribute("mensaje", mensaje);
                session.setAttribute("compraUsurio", compraUsuario);
            }

        }

    }
}
