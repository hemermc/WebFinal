<%@page import="com.common.Constantes"%>
<%@page import="com.crud.CRUDAeropuerto"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.modelo.GestionBBDDLocalhost"%>
<%@page import="com.modelo.Aeropuerto"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <%@ include file="/ComponenteHeader.jsp" %>
    <%    GestionBBDDLocalhost gestionDB = GestionBBDDLocalhost.getInstance();
        Connection conexion = gestionDB.establecerConexion();
        CRUDAeropuerto cRUDAeropuerto = new CRUDAeropuerto(conexion);
        ArrayList<Aeropuerto> listAeropuerto = cRUDAeropuerto.obtenerTodos();
        session.setAttribute(Constantes.SESSION_AEROPUERTOS, listAeropuerto);
    %>

    <body id="body">
        <div class ="contenedor">
            <div class ="row">
                <div class="col-md-1"></div>
                <div class="col-md-12">
                    <h1>Insertar un nuevo Aeropuerto a la base de datos</h1>
                    <form action="ControladorAdminAeropuerto" method="post">
                        <div class="form-group">
                            <label>Nombre Aeropuerto</label>
                            <input type="text" name="nombre" class="form-control" placeholder="Nombre Aeropuerto">
                        </div>
                        <div class="form-group">
                            <label>Lugar </label>
                            <input type="text" name="lugar" class="form-control" placeholder="Lugar">
                        </div>
                        <div class="form-group">
                            <label>Tasa</label>
                            <input type="number" name="tasa" class="form-control" min ="0"/>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12 text-center">
                                <button type="submit" name="action" value="add" class="btn btn-primary btn-lg"> Insertar</button>
                                <button type="submit" name="action" value="filter" class="btn btn-primary btn-lg"> Filtrar</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <div>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>id_aeropuerto</th>
                            <th>Nombre</th>
                            <th>Lugar</th>
                            <th>Tasas</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <%                                ArrayList<Aeropuerto> listaAeropuerto = (ArrayList) session.getAttribute(Constantes.SESSION_AEROPUERTOS);
                                Aeropuerto aeroFilter = (Aeropuerto) request.getAttribute("filter");

                                out.println("<h2>Lista de los aeropuertos en la base de datos</h2>");
                                if (aeroFilter != null) {
                                    out.println("<form action=\"ControladorAdminAeropuerto\" method=\"post\">");
                                    out.println("<tr><td><label>" + aeroFilter.getId_aeropuerto() + "</label></td>");
                                    out.println("<input type =\"hidden\" name=\"id_aeropuerto\" value=\"" + aeroFilter.getId_aeropuerto() + "\">");
                                    out.println("<td><input type =\"text\" name=\"nombre\" value=\"" + aeroFilter.getNombre() + "\"></td>");
                                    out.println("<td><input type =\"text\" name=\"lugar\" value=\"" + aeroFilter.getLugar() + "\"></td>");
                                    out.println("<td><input type =\"text\" name=\"tasa\" value=\"" + aeroFilter.getTasa() + "\"></td>");
                                    out.println("<td><button type=\"submit\" name=\"action\" value=\"update\" class=\"btn btn-warning btn-xs\">Update</button><td></tr></form>");
                                } else {
                                    if (listaAeropuerto != null) {
                                        for (Aeropuerto a : listaAeropuerto) {
                                            out.println("<form action=\"ControladorAdminAeropuerto\" method=\"post\">");
                                            out.println("<tr><td><label>" + a.getId_aeropuerto() + "</label></td>");
                                            out.println("<input type =\"hidden\" name=\"id_aeropuerto\" value=\"" + a.getId_aeropuerto() + "\">");
                                            out.println("<td><input type =\"text\" name=\"nombre\" value=\"" + a.getNombre() + "\"></td>");
                                            out.println("<td><input type =\"text\" name=\"lugar\" value=\"" + a.getLugar() + "\"></td>");
                                            out.println("<td><input type =\"text\" name=\"tasa\" value=\"" + a.getTasa() + "\"></td>");
                                            out.println("<td><button type=\"submit\" name=\"action\" value=\"update\" class=\"btn btn-warning btn-xs\">Update</button><td></tr></form>");
                                        }
                                    } else {
                                        out.println("<h3>No hay ningun aeropuerto registrado</h3>");
                                    }
                                }
                            %>
                        </tr>
                    </tbody>
                </table>
            </div>
            <%@ include file="/ComponenteFooter.jsp" %>
        </div>   <%-- cierre contenedor--%>      
    </body>
</html>