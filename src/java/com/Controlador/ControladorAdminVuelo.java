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
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

/**
 *
 * @author javi_
 */
@WebServlet(name = "ControladorAdminVuelo", urlPatterns = {"/ControladorAdminVuelo"})
public class ControladorAdminVuelo  extends HttpServlet {

    private GestionBBDDLocalhost bd = GestionBBDDLocalhost.getInstance();

    @Override
    public void init(ServletConfig cfg) throws ServletException {
        super.init();
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String radioSeleccion = (String) req.getParameter("eleccionUsuario");
        GestionBBDDLocalhost gestionDB = GestionBBDDLocalhost.getInstance();
        Connection conexion = gestionDB.establecerConexion();
        HttpSession session = req.getSession();
        CRUDVuelo cRUDVuelo = new CRUDVuelo(conexion);
        switch (radioSeleccion) {
            case "insertarVuelo":  //Opcion Insertar vuelo
                if (cRUDVuelo.obtenerEspecifico(req.getParameter(Constantes.ID_VUELO)) != null) {
                    //Muestro error
                    notificarMensaje(req, res, "ERROR: El vuelo introducido ya existe en la base de datos.");
                } else {
                    Vuelo vuelo = crearVuelo(req);
                    //Insertamos la sala
                    cRUDVuelo.insertar(vuelo);
                    res.sendRedirect(res.encodeRedirectURL("/PracticaFinal/gestion_admin.jsp"));
                }
                break;
            case "eliminarVuelo":  //Opcion Borrar vuelo
                if (cRUDVuelo.obtenerEspecifico(req.getParameter(Constantes.ID_VUELO)) != null) {
                    //Borramos la sala
                    cRUDVuelo.eliminar(String.valueOf(req.getParameter(Constantes.ID_VUELO)));
                    res.sendRedirect(res.encodeRedirectURL("/PracticaFinal/gestion_admin.jsp"));
                } else {
                    //Muestro error
                    notificarMensaje(req, res, "ERROR: El vuelo introducido no existe en la base de datos.");
                }
                break;
            case "consultVuelo":  //Opcion Consultar vuelo
                Vuelo vuelo = cRUDVuelo.obtenerEspecifico(req.getParameter(Constantes.ID_VUELO));
                if (vuelo != null) {
                    //Guardamos la sala que vamos a consultar/modificar
                    session.setAttribute("Vuelo", vuelo);
                    //Redireccionamos a la pagina en concreto.
                    res.sendRedirect(res.encodeRedirectURL("/PracticaFinal/cons_modif_sala.jsp"));
                } else {
                    //Muestro error
                    notificarMensaje(req, res, "ERROR: El vuelo introducida no existe en la base de datos.");
                }
                break;
            case "Modificar Vuelo": //Opcion Modificar vuelo
                //Creo el objeto vuelo
                vuelo = crearVuelo(req);
                //Modifico la sala
                cRUDVuelo.actualizar(vuelo);
                //Vuelvo a mandar al cliente a la misma pagina para que compruebe los cambios realizados en la sala
                //Guardamos la sala que vamos a consultar/modificar
                session.setAttribute("Vuelo", vuelo);
                //Redireccionamos a la pagina en concreto.
                res.sendRedirect(res.encodeRedirectURL("/PracticaFinal/cons_modif_sala.jsp"));
                break;
        }
    }

    public ArrayList arrayToArrayList(String[] a) {
        ArrayList<String> aL = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            aL.add(a[i]);
        }
        return aL;
    }

    //Metodo auxiliar para crear vuelo (no repetimos codigo a lo loco)
    public Vuelo crearVuelo(HttpServletRequest req) {
        Vuelo v;
        v = new Vuelo(req.getParameter(Constantes.ID_VUELO), 
                req.getParameter("origen"), req.getParameter("destino")
                ,FormateaFecha.comoLocalDate(Date.valueOf(req.getParameter("fecha"))), 
                Integer.parseInt(req.getParameter("id_avion")), Float.parseFloat(req.getParameter("precio")), Boolean.parseBoolean(req.getParameter("oferta")));
        return v;
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