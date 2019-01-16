<%@page import="com.common.Constantes"%>
<%@page import="com.crud.CRUDAeropuerto"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.modelo.GestionBBDDLocalhost"%>
<%@page import="com.modelo.Aeropuerto"%>
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
            CRUDAeropuerto cRUDAeropuerto = new CRUDAeropuerto(conexion);
            ArrayList<Aeropuerto> listAeropuerto = cRUDAeropuerto.obtenerTodos();
            session.setAttribute(Constantes.SESSION_AEROPUERTOS, listAeropuerto);
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
                out.print("<section " + mensaje + "</section>");
            }
        %>
        <section class="zonaErrores">${mensaje}</section>

        <form action="ControladorAdminAeropuerto" method="post">
            Insertar un nuevo Aeropuerto a la base de datos<br>
            <label>Identificador</label>
            <input type="number" name="id_aeropuerto">
            <label>Nombre</label>
            <input type="text" name="nombre">
            <label>Lugar</label>
            <input type="text" name="lugar">
            <label>Tasa</label>
            <input type="number" name="tasa"><br>
            <button type="submit" name="action" value="add">Insertar</button><br>
            <button type="submit" name="action" value="filter">Filtrar</button><br>
            <%--<button type="submit" name="action" value="remove">Eliminar</button>--%>
        </form>
        <%
            ArrayList<Aeropuerto> listaAeropuerto = (ArrayList) session.getAttribute(Constantes.SESSION_AEROPUERTOS);
            out.println("<h2>Lista de los aeropuertos en la base de datos</h2>");
            if (listaAeropuerto != null) {
                for (Aeropuerto a : listaAeropuerto) {
                    out.println("<form action=\"ControladorAdminAeropuerto\" method=\"post\">"
                            + "<p>Id aeropuerto: </p> "
                            + "<input type =\"text\" name=\"id_aeropuerto\" value=\"" + a.getId_aeropuerto()
                            + "\"><p> Nombre: </p>"
                            + "<input type =\"text\" name=\"nombre\" value=\"" + a.getNombre()
                            + "\"><p> Lugar: </p>"
                            + "<input type =\"text\" name=\"lugar\" value=\"" + a.getLugar()
                            + "\"><p> Tasa: </p> "
                            + "<input type =\"text\" name=\"tasa\" value=\"" + a.getTasa() + "\">"
                            + "<button type=\"submit\" name=\"action\" value=\"update\">Update</button>"
                            + "</form>");
                }

            } else {
                out.println("<h3>No hay ningun aeropuerto registrado</h3>");
            }
        %>
        <footer>
            <p>
                <br>© 2018 - 2019 Más que vuelos, S.L. - Todos los derechos reservados.</br>
                <span class ="icon-paypal"></span>
                <span class ="icon-applepay"></span>
            </p>
        </footer>
    </div>
</body>
</html>