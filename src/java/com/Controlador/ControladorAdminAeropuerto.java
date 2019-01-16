/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Controlador;

import com.common.Constantes;
import com.crud.CRUDAeropuerto;
import com.crud.CRUDVuelo;
import com.modelo.Aeropuerto;
import com.modelo.GestionBBDDLocalhost;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
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
@WebServlet(name = "ControladorAdminAeropuerto", urlPatterns = {"/ControladorAdminAeropuerto"})
public class ControladorAdminAeropuerto extends HttpServlet {

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
        CRUDAeropuerto cRUDAeropuerto = new CRUDAeropuerto(conexion);
        String nombre = req.getParameter(Constantes.NOMBRE);
        int id = -1;
        if (req.getParameter(Constantes.ID_AEROPUERTO) != null) {
            id = Integer.parseInt(req.getParameter(Constantes.ID_AEROPUERTO));
        }
        if (!"".equals(nombre)) {
            Aeropuerto aeropuerto = cRUDAeropuerto.obtenerEspecifico(nombre);
            switch (action) {
                case "add": {
                    if (aeropuerto == null) {
                        //Compruebo que ningun valor sea nulo
                        if (!(isNull(req, "lugar") || isNull(req, "nombre") || isNull(req, "tasa"))) {
                            aeropuerto = crearAeropuerto(req);
                            cRUDAeropuerto.insertar(aeropuerto);
                        } else {
                            //Muestro error
                            notificarMensaje(req, res, "ERROR: Datos erroneos, no se ha podido insertar.");
                        }
                    }
                    break;
                }

                case "remove": {
                    if (aeropuerto != null) {
                        // TODO mirar en la base de datos si ha sido comprado algun asiento por algun usuario
                        CRUDVuelo cRUDVuelo = new CRUDVuelo(conexion);
                        LocalDate date = LocalDate.now();
                        //Borramos la aeropuerto
                        cRUDAeropuerto.eliminar(String.valueOf(req.getParameter(Constantes.ID_AEROPUERTO)));
                    }
                    break;
                }
                case "update": {
                    if (!(isNull(req, "lugar") || isNull(req, "nombre") || isNull(req, "tasa"))) {
                        if (id != -1) {
                            Aeropuerto aeroBeforeUpdate = cRUDAeropuerto.obtenerEspecificoId(id);
                            if (aeroBeforeUpdate != null) {
                                Aeropuerto aeroUpdate = crearAeropuerto(req);
                                aeroUpdate.setId_aeropuerto(aeroBeforeUpdate.getId_aeropuerto());
                                //Borramos la aeropuerto
                                cRUDAeropuerto.actualizar(aeroUpdate);
                            }
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

                case "filter": {
                    if (aeropuerto != null) {
                        req.setAttribute("filter", aeropuerto);
                        req.getRequestDispatcher("/VistaGestionAeropuerto.jsp").forward(req, res);
                    }
                }
                break;
            }
        }
        ArrayList<Aeropuerto> allAeropuertos = cRUDAeropuerto.obtenerTodos();

        session.setAttribute(Constantes.SESSION_AEROPUERTOS, allAeropuertos);

        res.sendRedirect(res.encodeRedirectURL("VistaGestionAeropuerto.jsp"));
        //} 
    }

    public Aeropuerto crearAeropuerto(HttpServletRequest req) {
        Aeropuerto a;
        a = new Aeropuerto(req.getParameter("nombre"), req.getParameter("lugar"), Float.valueOf(req.getParameter("tasa")));
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
