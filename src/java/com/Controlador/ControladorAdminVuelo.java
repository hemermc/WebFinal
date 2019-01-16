/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Controlador;

import com.common.Constantes;
import com.crud.CRUDVuelo;
import com.modelo.FormateaFecha;
import com.modelo.GestionBBDDLocalhost;
import com.modelo.Vuelo;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

/**
 *
 * @author javi_
 */
@WebServlet(name = "ControladorAdminVuelo", urlPatterns = {"/ControladorAdminVuelo"})
public class ControladorAdminVuelo extends HttpServlet {

    private GestionBBDDLocalhost bd = GestionBBDDLocalhost.getInstance();

    @Override
    public void init(ServletConfig cfg) throws ServletException {
        super.init();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String action = req.getParameter("action");
        GestionBBDDLocalhost gestionDB = GestionBBDDLocalhost.getInstance();
        Connection conexion = gestionDB.establecerConexion();
        HttpSession session = req.getSession();
        CRUDVuelo cRUDVuelo = new CRUDVuelo(conexion);
        String idVuelo = req.getParameter(Constantes.ID_VUELO);
        Vuelo vuelo;
        if (!"".equals(idVuelo)) {
            switch (action) {
                case "add": {
                    if (cRUDVuelo.obtenerEspecifico(idVuelo) != null) {
                        //Muestro error
                        notificarMensaje(req, res, "ERROR: El vuelo introducido ya existe en la base de datos.");
                    } else {
                        //Creo el objeto vuelo
                        vuelo = crearVuelo(req);
                        cRUDVuelo.insertar(vuelo);
                    }
                    break;
                }

                case "remove": {
                    vuelo = cRUDVuelo.obtenerEspecifico(idVuelo);
                    if (vuelo != null) {

                        //Muestro error
                        notificarMensaje(req, res, "ERROR: El vuelo no se puede borrar, ya ha sido comprado algun billete.");

                    } else {
                        //Muestro error
                        notificarMensaje(req, res, "ERROR: El vuelo introducido no existe en la base de datos.");
                    }
                    break;
                }

                case "update": {
                    vuelo = cRUDVuelo.obtenerEspecifico(idVuelo);
                    if (vuelo != null) {
                        cRUDVuelo.actualizar(vuelo);
                    } else {
                        //Muestro error
                        notificarMensaje(req, res, "ERROR: El vuelo no se ha podido actualizar.");
                    }
                    break;
                }
            }
        }
        ArrayList<Vuelo> allVuelos = cRUDVuelo.obtenerTodos();
        session.setAttribute(Constantes.SESSION_VUELOS, allVuelos);
        res.sendRedirect(res.encodeRedirectURL("VistaGestionVuelo.jsp"));
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList arrayToArrayList(String[] a) {
        ArrayList<String> aL = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            aL.add(a[i]);
        }
        return aL;
    }

    public Vuelo crearVuelo(HttpServletRequest req) {
//mirate el constructor de vuelo
        Vuelo a;
        a = new Vuelo(req.getParameter("origen"),
                req.getParameter("destino"), LocalDate.now(),
                Integer.parseInt(req.getParameter(Constantes.ID_AVION)),
                true);
        return a;

    }

    //Metodo auxiliar para enviar mensajes de error al jsp
    public void notificarMensaje(HttpServletRequest req, HttpServletResponse res, String mensaje) throws ServletException, IOException {
        req.setAttribute("mensaje", mensaje);
        req.getRequestDispatcher("/gestion_admin.jsp").forward(req, res);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

}
