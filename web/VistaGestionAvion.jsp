<%@page import="com.common.Constantes"%>
<%@page import="com.crud.CRUDAvion"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.modelo.GestionBBDDLocalhost"%>
<%@page import="com.modelo.Avion"%>
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
            CRUDAvion cRUDAvion = new CRUDAvion(conexion);
            ArrayList<Avion> listAvion = cRUDAvion.obtenerTodos();
            session.setAttribute(Constantes.SESSION_AVIONES, listAvion);
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

        <form action="ControladorAdminAvion" method="post">
            <label>Identificador del avion</label>
            <input type="number" name="id_avion">
            <button type="submit" name="action" value="add">Insertar</button>
            <button type="submit" name="action" value="filter">Filtrar</button><br>
            <%--<button type="submit" name="action" value="remove">Eliminar</button>--%>
        </form>
        <%
            ArrayList<Avion> listaAviones = (ArrayList) session.getAttribute(Constantes.SESSION_AVIONES);
            out.println("<h2>Lista de aviones</h2>");
            if (listaAviones != null) {
                for(Avion a: listaAviones)
                    out.println("<form action=\"ControladorAdminAvion\" method=\"post\">"
                            + "<p>Id avion: </p> "
                            + "<input type =\"text\" name=\"id_avion\" value=\"" + a.getId_avion()
                            + "\"><p> Cantidad de asientos: </p>"
                            + "<input type =\"text\" name=\"nombre\" value=\"" + a.getPlazas() + "\">"
                            + "<button type=\"submit\" name=\"action\" value=\"update\">Update</button>"
                            + "</form>");
                
            } else {
                out.println("<h3>No hay ningun avion registrado</h3>");
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

