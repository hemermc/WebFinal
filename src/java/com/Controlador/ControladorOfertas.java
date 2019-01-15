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
import java.util.UUID;

/**
 *
 * @author Robert
 */
@WebServlet(name = "ControladorOfertas", urlPatterns = {"/ControladorOfertas"})
public class ControladorOfertas extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            GestionBBDDLocalhost gestionDB = GestionBBDDLocalhost.getInstance();
            Connection conexion = gestionDB.establecerConexion();

            HttpSession session = request.getSession();

            CRUDVuelo vuelos = new CRUDVuelo(conexion);
            ArrayList<Vuelo> listaVuelosOferta = new ArrayList<>();
            listaVuelosOferta = vuelos.obtenerVuelosOferta(Boolean.TRUE);

            session.setAttribute("listaVuelosOferta", listaVuelosOferta);

        } catch (Exception ex) {
            Logger.getLogger(ControladorInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void comprarBillete(String id_vuelo, HttpServletRequest request, HttpServletResponse response) {
        try {
            String mensaje = "";
            int identificadorVuelo = Integer.parseInt(id_vuelo);
            GestionBBDDLocalhost gestionDB = GestionBBDDLocalhost.getInstance();
            Connection conexion = gestionDB.establecerConexion();

            HttpSession session = request.getSession();
            Usuario usuario = (Usuario) session.getAttribute("usuario");

            if (usuario == null) {
                response.sendRedirect("./VistaInicioSesion.jsp");
            } else {
                CRUDCompra compra = new CRUDCompra(conexion);
                ArrayList<Compra> comprasVuelo = new ArrayList<>();
                comprasVuelo = compra.obtenerComprasVuelo(id_vuelo);
                if (comprasVuelo.size() >= 75) {
                    mensaje = "El vuelo esta completo, no hay asientos disponibles";
                    session.setAttribute("mensaje", mensaje);
                } else {
                    
                    String nombreUsuario = usuario.getNombre_usuario();
                    CRUDCliente crudCliente = new CRUDCliente(conexion);
                    if (crudCliente.inicioSesionValido(usuario)) {
                        Cliente cliente = crudCliente.obtenerEspecifico(nombreUsuario);
                        String dni = cliente.getDni();
                        int asiento =  (int) (Math.random() * 75) + 1;
                        int numeroCompra = (int) (Math.random() * 1000) + 1;
                        //String numeroCompra = java.util.UUID.randomUUID().toString(); // GENERAR IDENTIFICADOR UNICO DE COMPRA
                        Compra compraUsuario  = new Compra(numeroCompra,dni,identificadorVuelo,asiento);
                        mensaje = " COMPRA REALIZADA!";
                        session.setAttribute("mensaje", mensaje);
                        session.setAttribute("compraUsurio", compraUsuario);
                    }

                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ControladorInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
