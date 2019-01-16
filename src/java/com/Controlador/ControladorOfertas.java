/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Controlador;

import com.crud.CRUDAdministrador;
import com.crud.CRUDAeropuerto;
import com.crud.CRUDCliente;
import com.crud.CRUDCompra;
import com.crud.CRUDVuelo;
import com.modelo.Administrador;
import com.modelo.Aeropuerto;
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

//            GestionBBDDLocalhost gestionDB = GestionBBDDLocalhost.getInstance();
//            Connection conexion = gestionDB.establecerConexion();
//
//            HttpSession session = request.getSession();
//
//            CRUDVuelo vuelos = new CRUDVuelo(conexion);
//            ArrayList<Vuelo> listaVuelosOferta = new ArrayList<>();
//            listaVuelosOferta = vuelos.obtenerVuelosOferta(Boolean.TRUE);
//
//            session.setAttribute("listaVuelosOferta", listaVuelosOferta);


            GestionBBDDLocalhost gestionDB = GestionBBDDLocalhost.getInstance();
            Connection conexion = gestionDB.establecerConexion();
            HttpSession session = request.getSession();
            
            String id_vuelo = request.getParameter("eleccion");
            
            
        CRUDCliente crudCliente = new CRUDCliente(conexion);
  
        Cliente cli =(Cliente) session.getAttribute("usuario");
        
        //CALCULAR PRECIO
        CRUDVuelo vuelo = new CRUDVuelo(conexion);
        CRUDAeropuerto aeropuerto = new CRUDAeropuerto(conexion);

        Vuelo vueloEleccion = vuelo.obtenerEspecifico(id_vuelo);
        
        Aeropuerto AeropuertoEleccionOrigen = aeropuerto.obtenerEspecifico(vueloEleccion.getOrigen());
        Aeropuerto AeropuertoEleccionDestino = aeropuerto.obtenerEspecifico(vueloEleccion.getDestino());
        
        float tasaAeropuertoEleccionOrigen = AeropuertoEleccionOrigen.getTasa();
        float tasaAeropuertoEleccionDestino = AeropuertoEleccionDestino.getTasa();
        float precioEleccion = vueloEleccion.getPrecio() + tasaAeropuertoEleccionOrigen + tasaAeropuertoEleccionDestino;
        
        float ivaEleccion = (21 *100)/precioEleccion;
        
        precioEleccion = precioEleccion +ivaEleccion;
        
          //->COMPROBAR SI SE APLICA REBAJA 
        CRUDCompra compra = new CRUDCompra(conexion);
        ArrayList<Compra> comprasUsuario = new ArrayList<>();
        comprasUsuario = compra.obtenerComprasUsuario(cli.getDni());
         if (comprasUsuario.size() % 3 == 0) {
            precioEleccion = precioEleccion / 2;
        }
         
         session.setAttribute("precioEleccion", precioEleccion);
         
         String dni = cli.getDni();

        int asientoEleccion = (int) (Math.random() * 75) + 1;
        int numeroCompraEleccion = (int) (Math.random() * 1000) + 1;
        
        Compra compraUsuarioEleccion = new Compra(dni, asientoEleccion,id_vuelo,precioEleccion);

        compra.insertar(compraUsuarioEleccion);
        
         ArrayList<Compra> list = new ArrayList();
        list = compra.obtenerComprasUsuario(cli.getDni());
        session.setAttribute("listaCompras", list);
        
        response.sendRedirect("./VistaUsuarioDetalles.jsp");

        } catch (Exception ex) {
            Logger.getLogger(ControladorInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void comprarBillete(String id_vuelo, HttpServletRequest request, HttpServletResponse response) {
        try {
            String mensaje = "";
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
                        int asiento = (int) (Math.random() * 75) + 1;
                        int numeroCompra = (int) (Math.random() * 1000) + 1;
                        //String numeroCompra = java.util.UUID.randomUUID().toString(); // GENERAR IDENTIFICADOR UNICO DE COMPRA
                        Compra compraUsuario = new Compra(dni, asiento, id_vuelo, 0);
                        compra.insertar(compraUsuario);
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
