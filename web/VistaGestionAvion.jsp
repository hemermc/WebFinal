<html>
    <%@ include file="/ComponenteHeader.jsp" %>
    <%     GestionBBDDLocalhost gestionDB = GestionBBDDLocalhost.getInstance();
        Connection conexion = gestionDB.establecerConexion();
        CRUDAvion cRUDAvion = new CRUDAvion(conexion);
        ArrayList<Avion> listAvion = cRUDAvion.obtenerTodos();
        session.setAttribute(Constantes.SESSION_AVIONES, listAvion);

        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje == null) {
            mensaje = "";
        }

    %>
    <body>
        <div class ="contenedor">
        <h6 style="color:#FF0000">${mensaje}</h6>
            <div class ="row">
                <div class="col-md-1"></div>
                <div class="col-md-12">
                    <h1>Insertar un nuevo Avion a la base de datos</h1>
                    <form action="ControladorAdminAvion" method="post">
                        <div class="form-group">
                            <label>Modelo: </label>
                            <input type="text" name="modelo" class="form-control" placeholder="Modelo avi&oacute;n">
                        </div>
                        <div class="form-group">
                            <label>Plazas: </label>
                            <input type="number" name="plazas" class="form-control" min ="0" placeholder="N&uacute;mero de asientos"/>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12 text-center">
                                <button type="submit" name="action" value="add" class="btn btn-primary btn-lg"> Insertar</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>id_avion</th>
                            <th>Modelo</th>
                            <th>Plazas</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <%                  
                                ArrayList<Avion> listaAviones = (ArrayList) session.getAttribute(Constantes.SESSION_AVIONES);

                                out.println("<h2>Lista de aviones</h2>");

                                if (listaAviones != null) {
                                    for (Avion a : listaAviones) {
                                            out.println("<form action=\"ControladorAdminAvion\" method=\"post\">");
                                            out.println("<tr><td><label>" + a.getId_avion() + "</label></td>");
                                            out.println("<input type =\"hidden\" name=\"id_avion\" value=\"" + a.getId_avion() + "\">");
                                            out.println("<td><input type =\"text\" name=\"modelo\" value=\"" + a.getModelo()+ "\"></td>");
                                            out.println("<td><input type =\"text\" name=\"plazas\" value=\"" + a.getPlazas()+ "\"></td>");
                                            out.println("<td><button type=\"submit\" name=\"action\" value=\"update\" class=\"btn btn-warning btn-xs\">Update</button><td></tr></form>");
                                        }
                                } else {
                                    out.println("<h3>No hay ningun avion registrado</h3>");
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
