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
@WebServlet(name = "ControladorAdminAvion", urlPatterns = {"/ControladorAdminAvion"})
public class ControladorAdminAvion extends HttpServlet {

    private GestionBBDDLocalhost bd = GestionBBDDLocalhost.getInstance();

    @Override
    public void init(ServletConfig cfg) throws ServletException {
        super.init();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String radioSeleccion = (String) req.getParameter("eleccionUsuario");
        GestionBBDDLocalhost gestionDB = GestionBBDDLocalhost.getInstance();
        Connection conexion = gestionDB.establecerConexion();
        HttpSession session = req.getSession();
        CRUDAvion cRUDAvion = new CRUDAvion(conexion);
        Avion avion;
        switch (radioSeleccion) {
            
            case "insertarA": //Opcion Insertar avion
                if (cRUDAvion.obtenerEspecifico(req.getParameter(Constantes.ID_AVION)) != null) {
                    //Muestro error 
                    //notificarMensaje(req, res, "ERROR: La pelicula introducida ya existe en la base de datos.");
                } else {
                    //Creo el objeto avion
                    avion = crearAvion(req);
                    cRUDAvion.insertar(avion);
                    ArrayList<Avion> allAviones = cRUDAvion.obtenerTodos();
                    session.setAttribute("ArrayListAviones", allAviones);
                    res.sendRedirect(res.encodeRedirectURL("/VistaConcato.jsp"));
                }
                break;
            case "eliminarP": //Opcion Borrar avion
                avion = cRUDAvion.obtenerEspecifico(req.getParameter(Constantes.ID_AVION));
                if (avion != null) {
                    // TODO mirar en la base de datos si ha sido comprado algun asiento por algun usuario
                    CRUDVuelo cRUDVuelo = new CRUDVuelo(conexion);
                    LocalDate date = LocalDate.now();
                    if (cRUDVuelo.avionUso(String.valueOf(avion.getId_avion()), date)) {
                        //Borramos la avion
                        cRUDAvion.eliminar(String.valueOf(req.getParameter(Constantes.ID_AVION)));
                        //Actualizamos el arraylist de avion
                        ArrayList<Avion> allAviones = cRUDAvion.obtenerTodos();
                        session.setAttribute("ArrayListAviones", allAviones);
                        res.sendRedirect(res.encodeRedirectURL("/VistaConcato.jsp"));
                    } else {
                        //Muestro error
                        //notificarMensaje(req, res, "ERROR: El avion no se puede borrar, ya ha sido comprado algun billete.");
                    }
                } else {
                    //Muestro error
                    //notificarMensaje(req, res, "ERROR: El avion introducido no existe en la base de datos.");
                }
                break;
            case "consultAvion": //Opcion Consultar avion
                avion = cRUDAvion.obtenerEspecifico(req.getParameter(Constantes.ID_AVION));
                if (avion != null) {
                    //Guardamos la avion que vamos a consultar/modificar
                    session.setAttribute("Avion", avion);
                    //Redireccionamos a la pagina en concreto.
                    res.sendRedirect(res.encodeRedirectURL("VistaConcato.jsp"));
                } else {
                    //Muestro error
                    //notificarMensaje(req, res, "ERROR: El avion introducido no existe en la base de datos.");
                }
                break;

        }
    }

    //Metodo auxiliar para crear Aviones (no repetimos codigo a lo loco)
    public Avion crearAvion(HttpServletRequest req) {
        Avion a;
        a = new Avion(Integer.parseInt(req.getParameter(Constantes.ID_AVION)),
                Integer.parseInt(req.getParameter(" ")));
        return a;
    }
/*
    //Metodo auxiliar para enviar mensajes de error al jsp
    public void notificarMensaje(HttpServletRequest req, HttpServletResponse res, String mensaje) throws ServletException, IOException {
        req.setAttribute("mensaje", mensaje);
        req.getRequestDispatcher("/gestion_admin.jsp").forward(req, res);
    }*/

    @Override
    public void destroy() {
        super.destroy();
    }

}
