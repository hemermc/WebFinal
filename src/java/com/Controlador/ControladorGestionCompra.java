/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Controlador;

import com.common.Constantes;
import com.crud.CRUDCompra;
import com.crud.CRUDVuelo;
import com.modelo.Compra;
import com.modelo.GestionBBDDLocalhost;
import com.modelo.Vuelo;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ControladorGestionCompra", urlPatterns = {"/ControladorGestionCompra"})
public class ControladorGestionCompra extends HttpServlet {

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
        CRUDCompra cRUDCompra = new CRUDCompra(conexion);
        String idVuelo = req.getParameter(Constantes.ID_VUELO);
        Vuelo vuelo = cRUDVuelo.obtenerEspecifico(idVuelo);
        if (!isNull(req, "id_vuelo")) {
            switch (action) {
                case "action1": {
                    if (vuelo != null) {
                        ArrayList<Compra> al = cRUDCompra.obtenerComprasVuelo(idVuelo);
                        notificarMensaje(req, res, String.valueOf(al.size()));
                    } else {
                        //Muestro error
                        notificarMensaje(req, res, "ERROR: El vuelo no existe.");
                    }
                    break;
                }

                case "action2": {
                    vuelo = cRUDVuelo.obtenerEspecifico(idVuelo);
                    if (vuelo != null) {
                        ArrayList<Compra> al = cRUDCompra.obtenerComprasVuelo(idVuelo);
                        float precio = 0f;
                        for (Compra co : al) {
                            precio = precio + vuelo.getPrecio();
                        }
                        notificarMensaje(req, res, String.valueOf(precio));
                    } else {
                        //Muestro error
                        notificarMensaje(req, res, "ERROR: El vuelo no existe.");
                    }
                    break;
                }

            }
            res.sendRedirect(res.encodeRedirectURL("VistaGestionCompra.jsp"));

        } else {
            notificarMensaje(req, res, "ERROR: Datos erroneos, no se ha podido visualizar.");
        }
    }

    //Metodo auxiliar para enviar mensajes de error al jsp
    public void notificarMensaje(HttpServletRequest req, HttpServletResponse res, String mensaje) throws ServletException, IOException {

        req.setAttribute("mensaje", mensaje);
        req.getRequestDispatcher("/VistaGestionCompra.jsp").forward(req, res);

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
