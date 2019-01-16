<html>
<%@ include file="/ComponenteHeader.jsp" %>
    <body id="body">
       <div class ="contenedor">
           <div class ="row">
               <div class="col-md-1"></div>
               <div class="col-md-12">
                   <h1>Insertar un nuevo Aeropuerto a la base de datos</h1>
                   <form action="ControladorAdminAeropuerto" method="post">
                        <div class="form-group">
                            <label>Nombre Aeropuerto</label>
                            <input type="text" name="nombre" class="form-control" placeholder="Nombre Aeropuerto" required>
                        </div>
                        <div class="form-group">
                            <label>Lugar </label>
                            <input type="text" name="lugar" class="form-control" placeholder="Lugar" required>
                        </div>
                        <div class="form-group">
                            <label>Tasa</label>
                            <input type="number" name="tasa" class="form-control" min ="0" required/>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12 text-center">
                                <button type="submit" name="action" value="add" class="btn btn-primary btn-lg"> Insertar</button>
                                <button type="submit" name="action" value="Filter" class="btn btn-primary btn-lg"> Filtrar</button>
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
                           <%
                            ArrayList<Aeropuerto> listaAeropuerto = (ArrayList) session.getAttribute(Constantes.SESSION_AEROPUERTOS);
                            out.println("<h2>Lista de los aeropuertos en la base de datos</h2>");
                            if (listaAeropuerto != null) {
                                for (Aeropuerto a : listaAeropuerto) {
                                    out.println("<form action=\"ControladorAdminAeropuerto\" method=\"post\">");
                                    out.println("<tr><td><label>" + a.getId_aeropuerto()+"</label></td>");
                                    out.println("<td><input type =\"text\" name=\"id_aeropuerto\" value=\"" + a.getNombre() +"\"></td>");
                                    out.println("<td><input type =\"text\" name=\"id_aeropuerto\" value=\"" + a.getLugar() +"\"></td>");
                                    out.println("<td><input type =\"text\" name=\"id_aeropuerto\" value=\"" + a.getTasa() +"\"></td>");
                                    out.println("<td><button type=\"submit\" name=\"action\" value=\"update\" class=\"btn btn-warning btn-xs\">Update</button><td></tr></form>");
                                }
                            } else {
                                    out.println("<h3>No hay ningun aeropuerto registrado</h3>");
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