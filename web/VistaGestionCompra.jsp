<%@page import="com.modelo.ExceptionManager"%>
<%@page import="com.modelo.Vuelo"%>
<%@page import="com.crud.CRUDVuelo"%>
<%@page import="com.modelo.Compra"%>
<%@page import="com.crud.CRUDCompra"%>
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
            CRUDCompra cRUDCompra = new CRUDCompra(conexion);
            CRUDVuelo cRUDVuelo = new CRUDVuelo(conexion);
            ArrayList<Compra> listCompra = cRUDCompra.obtenerTodos();
            session.setAttribute(Constantes.SESSION_COMPRAS, listCompra);
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

        <%
            try {
                ArrayList<Compra> listCompra2 = (ArrayList) session.getAttribute(Constantes.SESSION_COMPRAS);

                out.println("<h2>Lista de ventas</h2>");
                if (listCompra2 != null) {
                    for (Compra c : listCompra2) {
                        if (c != null) {
                            Vuelo v = cRUDVuelo.obtenerEspecifico(c.getId_vuelo());
                            if (v != null) {
                                out.println("<p>Id compra: </p> "
                                        + "<input type =\"text\" name=\"id_compra\" value=\"" + c.getId_compra()
                                        + "\"><p> Dni: </p>"
                                        + "<input type =\"text\" name=\"dni\" value=\"" + c.getDni() + "\">"
                                        + "<p> Asiento: </p>"
                                        + "<input type =\"text\" name=\"asiento\" value=\"" + c.getAsiento() + "\">"
                                        + "<p> Id vuelo: </p>"
                                        + "<input type =\"text\" name=\"id_vuelo\" value=\"" + c.getId_vuelo() + "\">"
                                        + "<p>Origen: </p> "
                                        + "<input type =\"text\" name=\"origen\" value=\"" + 1 + "\">"
                                        + "<p>Destino: </p> "
                                        + "<input type =\"text\" name=\"destino\" value=\"" + 2 + "\">"
                                        + "<p>Fecha: </p> "
                                        + "<input type =\"date\" name=\"fecha\" value=\"" + 3 + "\">"
                                        + "<p>Id avion: </p> "
                                        + "<input type =\"text\" name=\"id_avion\" value=\"" + 4 + "\">"
                                        + "<p>Precio: </p> "
                                        + "<input type =\"text\" name=\"precio\" value=\"" + 5 + "\">");
                            }
                        }
                    }
                } else {
                    out.println("<h3>No hay ninguna venta registrada</h3>");
                }
            } catch (ExceptionManager e) {
                out.println("<h3>" + e + " </h3>");
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

