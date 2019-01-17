<html>
    <%@ include file="/ComponenteHeader.jsp" %>

    <%            GestionBBDDLocalhost gestionDB = GestionBBDDLocalhost.getInstance();
        Connection conexion = gestionDB.establecerConexion();
        CRUDCompra cRUDCompra = new CRUDCompra(conexion);
        CRUDVuelo cRUDVuelo = new CRUDVuelo(conexion);
        ArrayList<Compra> listCompra = cRUDCompra.obtenerTodos();
        session.setAttribute(Constantes.SESSION_COMPRAS, listCompra);

        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje == null) {
            mensaje = "";
        }
        float precioTotal = 0;
    %> 
    <body>

        <div class ="contenedor">
            <form action="ControladorGestionCompra" method="post">
                <label>Id Vuelo </label>
                <input type="text" name="id_vuelo" class="form-control" />
                <button type="submit" name="action" value="action1" class="btn btn-primary">Consulta la venta de los billetes</button>
                <button type="submit" name="action" value="action2" class="btn btn-primary">Consulta la ganancia por vuelo</button>

                <h6 style="color:#FF0000">${mensaje}</h6>
            </form>
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


                        <%                            try {
                                ArrayList<Compra> listCompra2 = (ArrayList) session.getAttribute(Constantes.SESSION_COMPRAS);
                                out.println("<h2>Lista de ventas</h2>");
                                if (listCompra2 != null) {
                                    for (Compra c : listCompra2) {
                                        if (c != null) {
                                            Vuelo v = cRUDVuelo.obtenerEspecifico(c.getId_vuelo());
                                            if (v != null) {
                                                precioTotal = precioTotal + v.getPrecio();
                                                out.println("<tr><td><label>" + c.getId_compra() + "</label></td>");
                                                out.println("<td><label>" + c.getDni() + "</label></td>");
                                                out.println("<td><label>" + c.getAsiento() + "</label></td>");
                                                out.println("<td><label>" + c.getId_vuelo() + "</label></td>");
                                                out.println("<td><label>" + v.getOrigen() + "</label></td>");
                                                out.println("<td><label>" + v.getDestino() + "</label></td>");
                                                out.println("<td><label>" + v.getFecha() + "</label></td>");
                                                out.println("<td><label>" + v.getId_avion() + "</label></td>");
                                                out.println("<td><label>" + v.getPrecio() + "</label></td>");
                                                out.println("</tr>");

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
            <%out.println("<h3>La ganancia total es: " + precioTotal + " Euros</h3>");%>
            <%@ include file="/ComponenteFooter.jsp" %>
        </div>   <%-- cierre contenedor--%>      
    </body>
</html>

