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
@WebServlet(name = "ControladorUsuario", urlPatterns = {"/ControladorUsuario"})
public class ControladorUsuario extends HttpServlet {

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
            Usuario usuario = (Usuario) session.getAttribute("usuario");

            String nombreUsuario = usuario.getNombre_usuario();

            CRUDAdministrador crudAdministrador = new CRUDAdministrador(conexion);

            if (crudAdministrador.inicioSesionValido(usuario)) {
                Administrador administrador = crudAdministrador.obtenerEspecifico(nombreUsuario);
                session.setAttribute("usuario", administrador);

                //response.sendRedirect("./index.jsp");

            } else {
                CRUDCliente crudCliente = new CRUDCliente(conexion);
                if (crudCliente.inicioSesionValido(usuario)) {//Es un cliente
                    Cliente cliente = crudCliente.obtenerEspecifico(nombreUsuario);
                    session.setAttribute("usuario", cliente);//Devuelve el objeto Cliente
                    session.setAttribute("administrador", false);

                    CRUDCompra compras = new CRUDCompra(conexion);

                    ArrayList<Compra> comprasUsuario = new ArrayList<>();
                    comprasUsuario = compras.obtenerComprasUsuario(cliente.getDni());
                    session.setAttribute("listaCompras", comprasUsuario);
                    
                    CRUDVuelo vuelos = new CRUDVuelo(conexion);
                    ArrayList<Vuelo> listaVuelos = new ArrayList<>();
                    int size = comprasUsuario.size();
                    float totalGastado = 0;
                    for (int x = 0; x < size; x++) {
                        Vuelo vuelo = vuelos.obtenerEspecifico(comprasUsuario.get(x).getId_vuelo());
                        
                        listaVuelos.add(vuelo);
                        totalGastado = totalGastado + vuelo.getPrecio();
                        
                        
                    }
                    session.setAttribute("totalGastado", totalGastado);
                    session.setAttribute("listaVuelos", listaVuelos);

                    response.sendRedirect("./VistaUsuarioDetalles.jsp");

                } else {//El usuario no existe
                    response.sendRedirect("./VistaInicioSesion.jsp");//Se vuelven a pedir los datos
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(ControladorInicio.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
