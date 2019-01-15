/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Controlador;

import com.crud.CRUDAdministrador;
import com.crud.CRUDCliente;
import com.modelo.Cliente;
import com.modelo.GestionBBDDLocalhost;
import com.modelo.Usuario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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
 * @author Grupo_12
 */
@WebServlet(name = "ControladorInicio", urlPatterns = {"/ControladorInicio"})
public class ControladorInicio extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            GestionBBDDLocalhost gestionDB = GestionBBDDLocalhost.getInstance();
            Connection conexion = gestionDB.establecerConexion();
            String tipoAcceso = request.getParameter("llamada");
            HttpSession session = request.getSession();
            switch (tipoAcceso) {
                case "registro": {//Solo los clientes se podran registrar desde aqui
                    Cliente cliente = new Cliente(request.getParameter("nombre"),
                            request.getParameter("apellidos"),
                            request.getParameter("dni"),
                            request.getParameter("direccion_entrega"),
                            Integer.parseInt(request.getParameter("telefono")),
                            request.getParameter("email"),
                            request.getParameter("nombre_usuario"),
                            request.getParameter("clave"));

                    CRUDAdministrador crudAdministrador = new CRUDAdministrador(conexion);

                    if (!crudAdministrador.esUsuarioRegistrado(cliente.getNombre_usuario())) {//El usuario no existe en la tabla Administradores
                        CRUDCliente crudCliente = new CRUDCliente(conexion);
                        if (!crudCliente.esUsuarioRegistrado(cliente.getNombre_usuario())) {//El usuario no existe en la tabla Clientes
                            crudCliente.insertar(cliente);//El usuario no existe en la BBDD por lo que se puede registrar
                            session.setAttribute("seguirregistro", cliente);
                            //  response.sendRedirect("./DatosPago.jsp");
                        } else {//El usuario ya exite en la bbdd
                            response.sendRedirect("./VistaRegistroCliente.jsp");//Se vuelven a pedir los datos
                        }
                    }
                    break;
                }
                case "acceso": {
                    Usuario usuario = new Usuario(
                            request.getParameter("nombre_usuario"),
                            request.getParameter("clave"));

                    CRUDAdministrador crudAdministrador = new CRUDAdministrador(conexion);

                    if (crudAdministrador.inicioSesionValido(usuario)) {//Es un administrador
                        session.setAttribute("usuario", crudAdministrador.obtenerEspecifico(usuario.getNombre_usuario()));//Devuelve el objeto Administrador
                        session.setAttribute("administrador", true);
                        response.sendRedirect("./index.jsp");
                    } else {
                        CRUDCliente crudCliente = new CRUDCliente(conexion);
                        if (crudCliente.inicioSesionValido(usuario)) {//Es un cliente
                            session.setAttribute("usuario", crudCliente.obtenerEspecifico(usuario.getNombre_usuario()));//Devuelve el objeto Cliente
                            session.setAttribute("administrador", false);
                            response.sendRedirect("./index.jsp");
                        } else {//El usuario no existe
                            response.sendRedirect("./VistaInicioSesion.jsp");//Se vuelven a pedir los datos
                        }
                    }
                    break;
                }
                default:
                    break;
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void cerrarSesion(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
