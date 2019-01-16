/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Controlador;

import com.common.Constantes;
import com.crud.CRUDAvion;
import com.crud.CRUDVuelo;
import com.modelo.Avion;
import com.modelo.GestionBBDDLocalhost;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        if (!"".equals(idAvion)) {
            switch (action) {
                case "add": {
                    if (cRUDAvion.obtenerEspecifico(idAvion) != null) {
                        //Muestro error
                        notificarMensaje(req, res, "ERROR: El avion introducido ya existe en la base de datos.");
                    } else {
                        //Creo el objeto avion
                        avion = crearAvion(req);
                        cRUDAvion.insertar(avion);
                    }
                    break;
                }

                case "remove": {
                    avion = cRUDAvion.obtenerEspecifico(idAvion);
                    if (avion != null) {
                        // TODO mirar en la base de datos si ha sido comprado algun asiento por algun usuario
                        CRUDVuelo cRUDVuelo = new CRUDVuelo(conexion);
                        LocalDate date = LocalDate.now();
                        if (cRUDVuelo.avionUso(String.valueOf(avion.getId_avion()), date)) {
                            //Borramos la avion
                            cRUDAvion.eliminar(String.valueOf(req.getParameter(Constantes.ID_AVION)));
                        } else {
                            //Muestro error
                            notificarMensaje(req, res, "ERROR: El avion no se puede borrar, ya ha sido comprado algun billete.");
                        }
                    } else {
                        //Muestro error
                        notificarMensaje(req, res, "ERROR: El avion introducido no existe en la base de datos.");
                    }
                    break;
                }case "update": {
                    avion = cRUDAvion.obtenerEspecifico(idAvion);
                    if (avion != null) {
                        cRUDAvion.actualizar(avion);
                    } else {
                        //Muestro error
                        notificarMensaje(req, res, "ERROR: El avion no se ha podido actualizar.");
                    }
                    break;
                }
            }
        } else {
            notificarMensaje(req, res, "No puedes introducir un valor nulo");
        }
        ArrayList<Avion> allAviones = cRUDAvion.obtenerTodos();
        session.setAttribute(Constantes.SESSION_AVIONES, allAviones);
        res.sendRedirect(res.encodeRedirectURL("VistaGestionAvion.jsp"));
        //} 
    }

//Metodo auxiliar para crear Aviones (no repetimos codigo a lo loco)
    public Avion crearAvion(HttpServletRequest req) {
        Avion a;
        a = new Avion(Integer.parseInt(req.getParameter(Constantes.ID_AVION)));
        return a;
    }

    //Metodo auxiliar para enviar mensajes de error al jsp
    public void notificarMensaje(HttpServletRequest req, HttpServletResponse res, String mensaje) throws ServletException, IOException {
        req.setAttribute("mensaje", mensaje);
        req.getRequestDispatcher("/VistaGestionAvion.jsp").forward(req, res);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

}
