<html>
    <%@ include file="/ComponenteHeader.jsp" %>
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
        <body>
        <div class ="contenedor">
        <h6 style="color:#FF0000">${mensaje}</h6>
        <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>id_compra</th>
                            <th>DNI</th>
                            <th>Asiento</th>
                            <th>Vuelo</th>
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
            try {
                ArrayList<Compra> listCompra2 = (ArrayList) session.getAttribute(Constantes.SESSION_COMPRAS);

                out.println("<h2>Lista de ventas</h2>");
                if (listCompra2 != null) {
                    for (Compra c : listCompra2) {
                        if (c != null) {
                            Vuelo v = cRUDVuelo.obtenerEspecifico(c.getId_vuelo());
                            if (v != null) {
                                out.println("<form action=\"ControladorGestionCompra\" method=\"post\">");
                                out.println("<button type=\"submit\" name=\"action\" value=\"consulta1\" class=\"btn btn-primary\">Contula 1</button>");
                                out.println("<button type=\"submit\" name=\"action\" value=\"consulta2\" class=\"btn btn-primary\">Contula 2</button>");
                                out.println("<tr><td><label>" + c.getId_compra() + "</label></td>");
                                out.println("<td><label>" + c.getDni() + "</label></td>");
                                out.println("<td><label>" + c.getAsiento()+ "</label></td>");
                                out.println("<td><label>" + c.getId_vuelo() + "</label></td>");
                                out.println("<td><label>" + v.getOrigen() + "</label></td>");
                                out.println("<td><label>" + v.getDestino()+ "</label></td>");
                                out.println("<td><label>" + v.getFecha() + "</label></td>");
                                out.println("<td><label>" + v.getId_avion()+ "</label></td>");
                                out.println("<td><label>" + v.getPrecio() + "</label></td>");
                                out.println("</tr></form>");

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
                    </tr>
                </tbody>
            </table>
         <%@ include file="/ComponenteFooter.jsp" %>
        </div>   <%-- cierre contenedor--%>      
    </body>
</html>

