/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Controlador;

import com.common.Constantes;
import com.crud.CRUDAvion;
import com.modelo.Avion;
import com.modelo.GestionBBDDLocalhost;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author javi_
 */
@WebServlet(name = "ControladorAdminAvion", urlPatterns = {"/ControladorAdminAvion"})
public class ControladorAdminAvion extends HttpServlet {

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
        CRUDAvion cRUDAvion = new CRUDAvion(conexion);
        Avion avion;
        String idAvion = req.getParameter(Constantes.ID_AVION);
        switch (action) {
            case "add": {
                if (isNull(req, "modelo") || isNull(req, "plazas")) {
                    //Muestro error
                    notificarMensaje(req, res, "ERROR: Datos erroneos, no se ha podido insertar.");
                } else {//Creo el objeto avion
                    avion = crearAvion(req);
                    cRUDAvion.insertar(avion);
                    
                }
            }
            break;

            case "update": {
                if (!(isNull(req, "modelo") || isNull(req, "plazas"))) {
                    Avion avionBefore = cRUDAvion.obtenerEspecifico(idAvion);
                    if (avionBefore != null) {
                        Avion avionUpdate = crearAvion(req);
                        avionUpdate.setId_avion(avionBefore.getId_avion());
                        //Borramos la aeropuerto
                        cRUDAvion.actualizar(avionUpdate);
                    } else {
                        //Muestro error
                        notificarMensaje(req, res, "ERROR: El avion no se ha podido actualizar.");
                    }
                } else {
                    //Muestro error
                    notificarMensaje(req, res, "ERROR: Datos erroneos, no se ha podido actualizar.");
                }
                break;
            }
        }
        ArrayList<Avion> allAviones = cRUDAvion.obtenerTodos();
        session.setAttribute(Constantes.SESSION_AVIONES, allAviones);
        res.sendRedirect(res.encodeRedirectURL("VistaGestionAvion.jsp"));
        //} 
    }

//Metodo auxiliar para crear Aviones (no repetimos codigo a lo loco)
    public Avion crearAvion(HttpServletRequest req) {
        Avion a;
        a = new Avion(req.getParameter("modelo"), Integer.parseInt(req.getParameter("plazas")));
        return a;
    }

    //Metodo auxiliar para enviar mensajes de error al jsp
    public void notificarMensaje(HttpServletRequest req, HttpServletResponse res, String mensaje) throws ServletException, IOException {
        req.setAttribute("mensaje", mensaje);
        req.getRequestDispatcher("/VistaGestionAvion.jsp").forward(req, res);
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
