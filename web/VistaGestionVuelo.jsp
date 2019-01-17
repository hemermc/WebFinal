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
<html>
    <%@ include file="/ComponenteHeader.jsp" %>

    <%        GestionBBDDLocalhost gestionDB = GestionBBDDLocalhost.getInstance();
        Connection conexion = gestionDB.establecerConexion();
        CRUDVuelo cRUDVuelo = new CRUDVuelo(conexion);
        CRUDAvion cRUDAvion = new CRUDAvion(conexion);
        ArrayList<Vuelo> listVuelos = cRUDVuelo.obtenerTodos();
        session.setAttribute(Constantes.SESSION_VUELOS, listVuelos);

        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje == null) {
            mensaje = "";
        }
    %>

    <body id="body">
        <div class ="contenedor">
            <h6 style="color:#FF0000">${mensaje}</h6>
            <div class ="row">
                <div class="col-md-1"></div>
                <div class="col-md-12">
                    <h1>Vuelos: </h1>
                    <form action="ControladorAdminVuelo" method="post">
                        <div class="form-group">
                            <label>Id Vuelo </label>
                            <input type="text" name="id_vuelo" class="form-control" />
                            <label>Origen: </label>
                            <select name="origen" > 
                                <%
                                    CRUDAeropuerto aero = new CRUDAeropuerto(conexion);
                                    ArrayList<Aeropuerto> listaAeropuertos = (ArrayList) aero.obtenerTodos();
                                    out.println("<option value=\"inicio-origen\">Elegir origen</option>");

                                    for (Aeropuerto aerop : listaAeropuertos) {
                                        out.println("<option value=\"" + aerop.getLugar() + "\">" + aerop.getNombre() + " - " + aerop.getLugar() + "</option>");
                                    }
                                %>                                                

                            </select>
                        </div>
                        <div class="form-group">
                            <label for="destino">Destino: </label>
                            <select name="destino"  > 
                                <%
                                    out.println("<option value=\"inicio-origen\">Elegir destino</option>");
                                    for (Aeropuerto aerop : listaAeropuertos) {
                                        out.println("<option value=\"" + aerop.getLugar() + "\">" + aerop.getNombre() + " - " + aerop.getLugar() + " </option>");
                                    }
                                %>

                            </select>
                        </div>
                            <label>Avion: </label>
                            <input type="number" name="id_avion" class="form-control" min ="0" />
                        <div>
                            <label for="fecha-ida">Fecha: </label>
                            <input type="date" name="fecha" /> 
                        </div>
                        <div class="form-group">
                            <label>Precio: </label>
                            <input type="number" name="precio" class="form-control" min ="0" />
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

            </div>
            <div>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>id_vuelo</th>
                            <th>Origen</th>
                            <th>Destino</th>
                            <th>Fecha</th>
                            <th>id_avion</th>
                            <th>Precio</th>

                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <%
                                ArrayList<Vuelo> listaVuelos = (ArrayList) session.getAttribute(Constantes.SESSION_VUELOS);
                                Vuelo vueloFilter = (Vuelo) request.getAttribute("filter");
                                out.println("<h2>Lista de vuelos</h2>");
                                if (vueloFilter != null) {
                                    out.println("<form action=\"ControladorAdminVuelo\" method=\"post\">");
                                    out.println("<tr><td><label>" + vueloFilter.getId_vuelo() + "</label></td>");
                                    out.println("<td><input type =\"hidden\" name=\"id_vuelo\" value=\"" + vueloFilter.getId_vuelo() + "\"></td>");
                                    out.println("<td><input type =\"text\" name=\"origen\" value=\"" + vueloFilter.getOrigen() + "\"></td>");
                                    out.println("<td><input type =\"text\" name=\"destino\" value=\"" + vueloFilter.getDestino() + "\"></td>");
                                    out.println("<td><input type =\"date\" name=\"fecha\" value=\"" + vueloFilter.getFecha() + "\"></td>");
                                    out.println("<td><input type =\"text\" name=\"id_avion\" value=\"" + vueloFilter.getId_avion() + "\"></td>");
                                    out.println("<td><input type =\"number\" name=\"precio\" value=\"" + vueloFilter.getPrecio() + "\"></td>");

                                    out.println("<td><button type=\"submit\" name=\"action\" value=\"update\" class=\"btn btn-warning btn-xs\">Update</button><td></tr></form>");

                                } else {
                                    if (listaVuelos != null) {
                                        for (Vuelo r : listaVuelos) {
                                            if (r != null) {

                                                out.println("<form action=\"ControladorAdminVuelo\" method=\"post\">");
                                                out.println("<tr><td><label>" + r.getId_vuelo() + "</label></td>");
                                                out.println("<td><input type =\"hidden\" name=\"id_vuelo\" value=\"" + r.getId_vuelo() + "\"></td>");
                                                out.println("<td><input type =\"text\" name=\"origen\" value=\"" + r.getOrigen() + "\"></td>");
                                                out.println("<td><input type =\"text\" name=\"destino\" value=\"" + r.getDestino() + "\"></td>");
                                                out.println("<td><input type =\"date\" name=\"fecha\" value=\"" + r.getFecha() + "\"></td>");
                                                out.println("<td><input type =\"text\" name=\"id_avion\" value=\"" + r.getId_avion() + "\"></td>");
                                                out.println("<td><input type =\"number\" name=\"precio\" value=\"" + r.getPrecio() + "\"></td>");

                                                out.println("<td><button type=\"submit\" name=\"action\" value=\"update\" class=\"btn btn-warning btn-xs\">Update</button><td></tr></form>");
                                            }
                                        }
                                    } else {
                                        out.println("<h3>No hay ningun vuelo registrado</h3>");
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

