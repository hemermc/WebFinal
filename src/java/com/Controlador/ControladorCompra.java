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
import static java.awt.SystemColor.window;
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
@WebServlet(name = "ControladorCompra", urlPatterns = {"/ControladorCompra"})
public class ControladorCompra extends HttpServlet {

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
        String ida =  request.getParameter("eleccionIda");
        String vuelta = request.getParameter("eleccionVuelta");
        session.setAttribute("respuesta", ida+ ",-" +vuelta);
        CRUDCliente crudCliente = new CRUDCliente(conexion);
        CRUDCompra crudCompra = new CRUDCompra(conexion);
        Cliente cli =(Cliente) session.getAttribute("usuario");
        Compra comp = new Compra(cli.getDni(),1,ida,200);
        crudCompra.insertar(comp);
        ArrayList<Compra> list = new ArrayList();
        list = crudCompra.obtenerComprasUsuario(cli.getDni());
        session.setAttribute("listaCompras", list);
        response.sendRedirect("./VistaUsuarioDetalles.jsp");
    }

//    protected void insertarCompra(HttpServletRequest request, HttpServletResponse response, String id_vuelo) throws ServletException, IOException {
//        String mensaje = "";
//        int identificadorVuelo = Integer.parseInt(id_vuelo);
//        GestionBBDDLocalhost gestionDB = GestionBBDDLocalhost.getInstance();
//        Connection conexion = gestionDB.establecerConexion();
//
//        HttpSession session = request.getSession();
//        Usuario usuario = (Usuario) session.getAttribute("usuario");
//        if (usuario == null) {
//            response.sendRedirect("./VistaInicioSesion.jsp");
//        } else {
//            if (this.comprobarPlazas(request, response, id_vuelo)) {
//                String nombreUsuario = usuario.getNombre_usuario();
//                CRUDCliente crudCliente = new CRUDCliente(conexion);
//                if (crudCliente.inicioSesionValido(usuario)) {
//                    Cliente cliente = crudCliente.obtenerEspecifico(nombreUsuario);
//                    String dni = cliente.getDni();
//                    int asiento = (int) (Math.random() * 75) + 1;
//                    int numeroCompra = (int) (Math.random() * 1000) + 1;
//                    //String numeroCompra = java.util.UUID.randomUUID().toString(); // GENERAR IDENTIFICADOR UNICO DE COMPRA
//                    Compra compraUsuario = new Compra(numeroCompra, dni, identificadorVuelo, asiento);
//                    CRUDCompra compra = new CRUDCompra(conexion);
//                    compra.insertar(compraUsuario);
//                    mensaje = " COMPRA REALIZADA!";
//                    session.setAttribute("mensaje", mensaje);
//                    session.setAttribute("compraUsurio", compraUsuario);
//                }
//            }
//        }
//
//    }
    protected boolean comprobarPlazas(HttpServletRequest request, HttpServletResponse response, String id_vuelo) throws ServletException, IOException {
        String mensaje = "";
        Boolean plazasLibres = false;
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
                plazasLibres = true;
            }
        }
        return plazasLibres;
    }

//    protected ArrayList<Vuelo> vuelosDisponibles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        ArrayList<Vuelo> listaVuelos = new ArrayList<>();
//
//        GestionBBDDLocalhost gestionDB = GestionBBDDLocalhost.getInstance();
//        Connection conexion = gestionDB.establecerConexion();
//
//        HttpSession session = request.getSession();
//        CRUDVuelo vuelos = new CRUDVuelo(conexion);
//
//        listaVuelos = vuelos.obtenerTodos();
//
//        return listaVuelos;
//    }
    
    /*
    Funcion que se debe ejecutar cuando se pulsa el boton de compra
    Comprueba si el user esta logeado, si no redirecciona al inicio de sesion
    si esta logado el user, comprueba si quedan plazas, en caso de que si queden redirige a la vista de pago
    */
    protected void pulsarBotonCompra(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GestionBBDDLocalhost gestionDB = GestionBBDDLocalhost.getInstance();
        Connection conexion = gestionDB.establecerConexion();

        HttpSession session = request.getSession();

        String id_vuelo =(String) session.getAttribute("vueloElegido");
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            response.sendRedirect("./VistaInicioSesion.jsp");
        } else {
            if (this.comprobarPlazas(request, response, id_vuelo)) {
                response.sendRedirect("./VistaPago.jsp");     // Redirecciona a la vista de pago
            } else {
                //NO QUEDAN PLAZAS, REDIRIGIR OTRA VEZ VISTA PARA ELEGIR VUELO?
            }
        }
    }

    /*
    Funcion que debe ejecutarse cuando el usuario pulse en el radio button del vuelo que quiere comprar
    Establece en la sesion el id del vuelo que el usuario  quiere comprar
    */
    protected void pulsarBotonEleccion(HttpServletRequest request, HttpServletResponse response, String id_vuelo) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("vueloElegido", id_vuelo);
    }
}
