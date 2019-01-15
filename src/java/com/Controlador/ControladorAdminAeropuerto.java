/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Controlador;

import com.common.Constantes;
import com.crud.CRUDAeropuerto;
import com.crud.CRUDAvion;
import com.crud.CRUDVuelo;
import com.modelo.Aeropuerto;
import com.modelo.Avion;
import com.modelo.FormateaFecha;
import com.modelo.GestionBBDDLocalhost;
import com.modelo.Vuelo;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
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
public class ControladorAdminAeropuerto  extends HttpServlet {

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
        CRUDAeropuerto cRUDAeropuerto = new CRUDAeropuerto(conexion);
        switch (radioSeleccion) {
            case "insertarAeropuerto":
                if (cRUDAeropuerto.obtenerEspecifico(req.getParameter(Constantes.ID_AEROPUERTO)) != null) {
                    //Muestro error
                    notificarMensaje(req, res, "ERROR: Las entradas ya han sido creadas previamente.");
                } else {
                    Aeropuerto aeropuerto = crearAeropuerto(req);
                    //Insertamos el aeropuerto
                    cRUDAeropuerto.insertar(aeropuerto);
                    //Actualizamos el arraylist de entradas
                    ArrayList<Aeropuerto> allAeropuerto = cRUDAeropuerto.obtenerTodos();
                    
                    session.setAttribute("ArrayListAeropuerto", allAeropuerto);
                    res.sendRedirect(res.encodeRedirectURL("/PracticaFinal/gestion_admin.jsp"));
                }
                break;
            case "eliminarAeropuerto":
                if (cRUDAeropuerto.obtenerEspecifico(req.getParameter(Constantes.ID_AEROPUERTO)) != null) {
                    //Muestro error
                    notificarMensaje(req, res, "ERROR: Las entradas no han sido creadas previamente.");
                } else {
                    //Borramos la entrada con los datos recogidos
                    cRUDAeropuerto.eliminar(String.valueOf(req.getParameter(Constantes.ID_VUELO)));
                    //Actualizamos el arraylist de entradas
                    ArrayList<Aeropuerto> allAeropuerto = cRUDAeropuerto.obtenerTodos();
                    
                    session.setAttribute("ArrayListAeropuerto", allAeropuerto);
                    res.sendRedirect(res.encodeRedirectURL("/PracticaFinal/gestion_admin.jsp"));
                }
                break;
            case "consultarAeropuerto":
                Aeropuerto aeropuerto = cRUDAeropuerto.obtenerEspecifico(req.getParameter(Constantes.ID_AEROPUERTO));
                if (aeropuerto != null) {
                    //Guardamos la sala que vamos a consultar/modificar
                    session.setAttribute("Aeropuerto", aeropuerto);
                    //Redireccionamos a la pagina en concreto.
                    res.sendRedirect(res.encodeRedirectURL("/PracticaFinal/cons_modif_entradas.jsp"));
                } else {
                    //Muestro error
                    notificarMensaje(req, res, "ERROR: Las entradas introducidas no existen en la base de datos.");
                }
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

    public Aeropuerto crearAeropuerto(HttpServletRequest req) {
        Aeropuerto areopuerto;
        areopuerto = new Aeropuerto((Integer.parseInt(req.getParameter(Constantes.ID_AEROPUERTO))), 
                req.getParameter("nombre"), req.getParameter("lugar")
                ,Float.parseFloat(req.getParameter("tasa")));
        return areopuerto;
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