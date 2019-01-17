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
                    if (!(isNull(req, "id_vuelo") || isNull(req, "origen") || isNull(req, "destino")
                            || isNull(req, "fecha") || isNull(req, "id_avion") || isNull(req, "precio"))) {

                        if (cRUDVuelo.obtenerEspecifico(idVuelo) != null) {
                            //Muestro error
                            notificarMensaje(req, res, "ERROR: El vuelo introducido ya existe en la base de datos.");
                        } else {
                            //Creo el objeto vuelo
                            vuelo = crearVuelo(req);
                            cRUDVuelo.insertar(vuelo);
                        }
                    } else {
//Muestro error
                        notificarMensaje(req, res, "ERROR: Datos erroneos, no se ha podido actualizar.");
                    }
                    break;
                }

                case "update": {
                    if (!(isNull(req, "id_vuelo") || isNull(req, "origen") || isNull(req, "destino")
                            || isNull(req, "fecha") || isNull(req, "id_avion") || isNull(req, "precio"))) {
                        vuelo = cRUDVuelo.obtenerEspecifico(idVuelo);
                        if (vuelo != null) {
                            Vuelo vueloAfter = crearVuelo(req);
                            //Borramos la aeropuerto
                            cRUDVuelo.actualizar(vueloAfter);
                        } else {
                            //Muestro error
                            notificarMensaje(req, res, "ERROR: El avion no se ha podido actualizar.");
                        }
                    } else {
//Muestro error
                        notificarMensaje(req, res, "ERROR: Datos erroneos, no se ha podido actualizar.");
                    }
                }
                break;

                case "filter": {
                    if (!(isNull(req, "id_vuelo"))) {
                        vuelo = cRUDVuelo.obtenerEspecifico(idVuelo);
                        if (vuelo != null) {
                            req.setAttribute("filter", vuelo);
                            req.getRequestDispatcher("/VistaGestionVuelo.jsp").forward(req, res);
                        }
                    } else {
                        //Muestro error
                        notificarMensaje(req, res, "ERROR: Datos erroneos, no se ha podido actualizar.");
                    }
                }
                break;
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
        a = new Vuelo(req.getParameter("id_vuelo"), req.getParameter("origen"),
                req.getParameter("destino"), LocalDate.parse(req.getParameter("fecha")), Integer.parseInt(req.getParameter("id_avion")),
                Float.valueOf(req.getParameter("precio")), true);
        return a;

    }

    //Metodo auxiliar para enviar mensajes de error al jsp
    public void notificarMensaje(HttpServletRequest req, HttpServletResponse res, String mensaje) throws ServletException, IOException {
        req.setAttribute("mensaje", mensaje);
        req.getRequestDispatcher("/VistaGestionVuelo.jsp").forward(req, res);
    }

    public boolean isNull(HttpServletRequest req, String parameter) {
        if (req.getParameter(parameter) == null || req.getParameter(parameter).equals("")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }

}
