<%@page import="com.modelo.Avion"%>
<%@page import="com.crud.CRUDAvion"%>
<%@page import="com.modelo.Aeropuerto"%>
<%@page import="com.crud.CRUDAeropuerto"%>
<%@page import="com.modelo.Vuelo"%>
<%@page import="com.crud.CRUDVuelo"%>
<%@page import="com.common.Constantes"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.modelo.GestionBBDDLocalhost"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Más que vuelos ADMINISTRADOR</title>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <link rel="stylesheet" href="css/estilos.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body id="body">

        <jsp:include page="ComponenteHeader.jsp"/>
        <%
            GestionBBDDLocalhost gestionDB = GestionBBDDLocalhost.getInstance();
            Connection conexion = gestionDB.establecerConexion();
            CRUDVuelo cRUDVuelo = new CRUDVuelo(conexion);
            CRUDAvion cRUDAvion = new CRUDAvion(conexion);
            ArrayList<Vuelo> listVuelos = cRUDVuelo.obtenerTodos();
            session.setAttribute(Constantes.SESSION_VUELOS, listVuelos);
            
            //Comprobamos si la session es nueva.
            if (session == null | session.isNew()) {
                session.invalidate();
                //Redirecciono al login.html para que se loguee.
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("VistaInicioSesion.jsp");
                dispatcher.forward(request, response);
            } else {
                String mensaje = (String) request.getAttribute("mensaje");
                if (mensaje == null) {
                    mensaje = "";
                }
            }
        %>
        <section class="zonaErrores">${mensaje}</section>

        <form action="ControladorAdminVuelo" method="post">
            <label>Identificador del vuelo</label>
            <input type="text" name="id_vuelo">
            <label>Origen: </label>
            <select name="origen" > 
                <%
                    CRUDAeropuerto aero = new CRUDAeropuerto(conexion);
                    ArrayList<Aeropuerto> listaAeropuertos = (ArrayList) aero.obtenerTodos();
                    out.println("<option value=\"inicio-origen\">Elegir origen</option>");

                    for (Aeropuerto aerop : listaAeropuertos) {
                        out.println("<option value=\"" + aerop.getNombre() + "\">" + aerop.getNombre() + " </option>");
                    }
                %>
            </select>
            <label>Destino: </label>
            <select name="destino" > 
                <%
                    out.println("<option value=\"inicio-origen\">Elegir origen</option>");

                    for (Aeropuerto aerop : listaAeropuertos) {
                        out.println("<option value=\"" + aerop.getNombre() + "\">" + aerop.getNombre() + " </option>");
                    }
                %>
            </select>
            <label>Fecha: </label>
            <input type ="date" name="fecha" >
            <label>Id avion: </label>
            <input type ="number" name="id_avion">
            <label>Precio: </label>
            <input type ="number" name="precio">
            <button type="submit" name="action" value="add">Insertar</button>
            <button type="submit" name="action" value="filter">Filtrar</button><br>
            <%--<button type="submit" name="action" value="remove">Eliminar</button>--%>
        </form>
        <%
            ArrayList<Vuelo> listaVuelos = (ArrayList) session.getAttribute(Constantes.SESSION_VUELOS);
            out.println("<h2>Lista de vuelos</h2>");
            if (listaVuelos != null) {
                for (Vuelo r : listaVuelos) {
                    if (r != null) {
                        out.println("<form action=\"ControladorAdminAvion\" method=\"post\">"
                                + "<p>Id vuelo</p> "
                                + "<input type =\"text\" name=\"id_vuelo\" value=\"" + r.getId_vuelo() + "\">"
                                + "<p>Origen: </p> "
                                + "<input type =\"text\" name=\"origen\" value=\"" + r.getOrigen() + "\">"
                                + "<p>Destino: </p> "
                                + "<input type =\"text\" name=\"destino\" value=\"" + r.getDestino() + "\">"
                                + "<p>Fecha: </p> "
                                + "<input type =\"date\" name=\"fecha\" value=\"" + r.getFecha() + "\">"
                                + "<p>Id avion: </p> "
                                + "<input type =\"text\" name=\"id_avion\" value=\"" + r.getId_avion() + "\">"
                                + "<p>Precio: </p> "
                                + "<input type =\"text\" name=\"precio\" value=\"" + r.getPrecio() + "\">"
                                + "<button type=\"submit\" name=\"action\" value=\"update\">Update</button>"
                                + "</form>");
                    } else {
                        out.println("<h3>No hay ningun vuelo registrado</h3>");
                    }
                }
            }
        %>
        <footer>
            <p>
                <br>© 2018 - 2019 Más que vuelos, S.L. - Todos los derechos reservados.</br>
                <span class ="icon-paypal"></span>
                <span class ="icon-applepay"></span>
            </p>
        </footer>
    </body>
</html>

