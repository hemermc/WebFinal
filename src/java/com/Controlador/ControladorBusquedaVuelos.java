package com.Controlador;

import com.crud.CRUDVuelo;
import com.modelo.GestionBBDDLocalhost;
import com.modelo.Vuelo;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
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
@WebServlet(name = "ControladorBusquedaVuelos", urlPatterns = {"/ControladorBusquedaVuelos"})
public class ControladorBusquedaVuelos extends HttpServlet {


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
            
                    Vuelo vuelo = new Vuelo(request.getParameter("origen"),
                                            request.getParameter("destino"), 
                                            LocalDate.parse(request.getParameter("fecha")));                                                                                                                                 

                    CRUDVuelo viaje = new CRUDVuelo(conexion); 
                /*    viaje.obtenerVuelo(String origen, String destino, LocalDate(fecha)));*/

                               
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