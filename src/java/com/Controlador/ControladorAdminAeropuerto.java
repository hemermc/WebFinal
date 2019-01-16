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
        Aeropuerto aeropuerto;
        String idAeropuerto = req.getParameter(Constantes.ID_AEROPUERTO);
        if (!"".equals(idAeropuerto)) {
            switch (action) {
                case "add": {
                    if (cRUDAeropuerto.obtenerEspecifico(idAeropuerto) != null) {
                        //Muestro error
                        notificarMensaje(req, res, "ERROR: El aeropuerto introducido ya existe en la base de datos.");
                    } else {
                        //Creo el objeto aeropuerto

                        aeropuerto = crearAeropuerto(req);
                        cRUDAeropuerto.insertar(aeropuerto);
                    }
                    break;
                }

                case "remove": {
                    aeropuerto = cRUDAeropuerto.obtenerEspecifico(idAeropuerto);
                    if (aeropuerto != null) {
                        // TODO mirar en la base de datos si ha sido comprado algun asiento por algun usuario
                        CRUDVuelo cRUDVuelo = new CRUDVuelo(conexion);
                        LocalDate date = LocalDate.now();
                        //Borramos la aeropuerto
                        cRUDAeropuerto.eliminar(String.valueOf(req.getParameter(Constantes.ID_AEROPUERTO)));
                    } else {
                        //Muestro error
                        notificarMensaje(req, res, "ERROR: El aeropuerto introducido no existe en la base de datos.");
                    }
                    break;
                }
                case "update": {
                    aeropuerto = cRUDAeropuerto.obtenerEspecifico(idAeropuerto);
                    if (aeropuerto != null) {
                        //Borramos la aeropuerto
                        cRUDAeropuerto.actualizar(aeropuerto);
                    } else {
                        //Muestro error
                        notificarMensaje(req, res, "ERROR: El aeropuerto no ha podido ser actualizado.");
                    }
                    break;
                }
            }
        } else {
            notificarMensaje(req, res, "No puedes introducir un valor nulo");
        }
        ArrayList<Aeropuerto> allAeropuertos = cRUDAeropuerto.obtenerTodos();
        session.setAttribute(Constantes.SESSION_AEROPUERTOS, allAeropuertos);
        res.sendRedirect(res.encodeRedirectURL("VistaGestionAeropuerto.jsp"));
        //} 
    }

//Metodo auxiliar para crear Aeropuertos (no repetimos codigo a lo loco)
    public Aeropuerto crearAeropuerto(HttpServletRequest req) {
        Aeropuerto a;
        a = new Aeropuerto(Integer.parseInt(req.getParameter(Constantes.ID_AEROPUERTO)),
                req.getParameter("nombre"), req.getParameter("lugar"), Float.valueOf(req.getParameter("tasa")));
        return a;
    }

    //Metodo auxiliar para enviar mensajes de error al jsp
    public void notificarMensaje(HttpServletRequest req, HttpServletResponse res, String mensaje) throws ServletException, IOException {
        req.setAttribute("mensaje", mensaje);
        req.getRequestDispatcher("/VistaGestionAeropuerto.jsp").forward(req, res);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

}
